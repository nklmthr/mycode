package com.srishti.geektrust.metro.controllers;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.srishti.geektrust.metro.card.MetroCard;
import com.srishti.geektrust.metro.card.MetroCardRepository;
import com.srishti.geektrust.metro.journey.Journey;
import com.srishti.geektrust.metro.journey.JourneyRepository;
import com.srishti.geektrust.metro.ratecard.RateCard;
import com.srishti.geektrust.metro.ratecard.RateCardRepository;
import com.srishti.geektrust.metro.reports.CollectionSummary;
import com.srishti.geektrust.metro.reports.PassengerSummary;
import com.srishti.geektrust.metro.station.Station;
import com.srishti.geektrust.metro.station.StationRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class MetroController {
	Logger logger = Logger.getLogger(MetroController.class);

	@Autowired
	RateCardRepository rateCardRepository;

	@Autowired
	MetroCardRepository metroCardRepository;

	@Autowired
	StationRepository stationRepository;

	@Autowired
	JourneyRepository journeyRepository;

	@RequestMapping(value = "/card", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<MetroCard> getAllCards() {
		List<MetroCard> results = (List<MetroCard>) metroCardRepository.findAll();
		return results;
	}

	@RequestMapping(value = "/issueCard", method = RequestMethod.POST)
	public ResponseEntity<String> issueCard(@RequestParam String nameOfHolder, @RequestParam TravelCategory category) {
		MetroCard card = new MetroCard();
		card.setId(UUID.randomUUID().toString());
		card.setName(nameOfHolder);
		card.setCategory(category);
		card.setBalance(0.0);
		metroCardRepository.save(card);
		return new ResponseEntity<String>("Card issue success. your card id is" + card.getId(), HttpStatus.OK);
	}

	@RequestMapping(value = "/swipeIn", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<String> swipeIn(@RequestParam String cardId, @RequestParam String start) {
		logger.info("swipe in request recieved for cardId=" + cardId + ", station=" + start);
		Journey inProgressJourneys = journeyRepository.findByCardIdAndInProgress(cardId, true);
		if (inProgressJourneys != null) {
			logger.error("Swipe in failure. In Progress Journey exists in the system");
			return new ResponseEntity<String>("Swipe in failure. In Progress Journey exists in the system",
					HttpStatus.BAD_REQUEST);
		}
		Station station = (Station) stationRepository.findByName(start.toUpperCase());
		logger.info("Station=" + station.getId() + ",name=" + station.getName());
		if (station != null) {
			Optional<MetroCard> cardOptional = metroCardRepository.findById(cardId);
			if (cardOptional.isPresent()) {
				MetroCard card = cardOptional.get();
				logger.info("Metrocard-cardId=" + cardId + ",name=" + card.getName());
				List<RateCard> rateCardList = (List<RateCard>) rateCardRepository.findAll();
				for (RateCard rate : rateCardList) {
					if (rate.getCategory().equals(card.getCategory())) {
						Date today = DateUtils.truncate(new Date(), Calendar.DATE);
						Date tommorow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
						logger.debug("Today=" + today + ",tommorow=" + tommorow);
						List<Journey> existingJourneys = journeyRepository
								.findByCardIdAndInProgressAndEndTimeBetween(cardId, false, today, tommorow);
						logger.info("existingJourneys found size="
								+ (existingJourneys != null ? existingJourneys.size() : "0"));
						double rechargeAmountRequired = 0.0;
						double rechargeServiceFees = 0.0;
						double totalRechargeAmount = 0.0;
						if (existingJourneys != null && existingJourneys.size() == 1) {
							boolean rechargeRequired = rate.getAmount() / 2 > card.getBalance();
							if (rechargeRequired) {
								rechargeAmountRequired = rate.getAmount() / 2 - card.getBalance();
								rechargeServiceFees = rechargeAmountRequired * 0.02;
								totalRechargeAmount = rechargeAmountRequired + rechargeServiceFees;
							}
							saveAsReturnJourney(card, rate, station, existingJourneys.get(0), totalRechargeAmount,
									rechargeServiceFees);
						} else {
							boolean rechargeRequired = rate.getAmount() > card.getBalance();
							if (rechargeRequired) {
								rechargeAmountRequired = rate.getAmount() - card.getBalance();
								rechargeServiceFees = rechargeAmountRequired * 0.02;
								totalRechargeAmount = rechargeAmountRequired + rechargeServiceFees;
							}
							saveAsNewJourney(card, rate, station, totalRechargeAmount, rechargeServiceFees);
						}
						return new ResponseEntity<String>("Swipe in Success.", HttpStatus.OK);
					}
				}
			}
		}
		return new ResponseEntity<String>("Swipe in failure.", HttpStatus.BAD_REQUEST);

	}

	private void saveAsNewJourney(MetroCard card, RateCard rate, Station station, double rechargeAmount,
			double rechargeServiceFees) {
		Journey journey = new Journey(new Date(), null, card.getId(), card.getName(), card.getCategory(), station, null,
				rate.getAmount(), 0.0, card.getBalance(), rechargeAmount, rechargeServiceFees,
				card.getBalance() + rechargeAmount - (rate.getAmount() + rechargeServiceFees),
				(rate.getAmount() + rechargeServiceFees), false, true);
		journeyRepository.save(journey);
	}

	private void saveAsReturnJourney(MetroCard card, RateCard rate, Station station, Journey existingJourney,
			double rechargeAmount, double rechargeServiceFees) {
		Journey journey = new Journey(new Date(), null, card.getId(), card.getName(), card.getCategory(), station, null,
				rate.getAmount(), rate.getAmount() / 2, card.getBalance(), rechargeAmount, rechargeServiceFees,
				card.getBalance() + rechargeAmount - (rate.getAmount() / 2 + rechargeServiceFees),
				(rate.getAmount() / 2 + rechargeServiceFees), true, true);
		journeyRepository.save(journey);
	}

	@RequestMapping(value = "/swipeOut", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<String> swipeOut(String cardId, String end) {
		Journey inProgressJourneys = journeyRepository.findByCardIdAndInProgress(cardId, true);
		if (inProgressJourneys == null) {
			return new ResponseEntity<String>(
					"Swipe Out failure. In Progress Journey does not exists in the system for swipe out",
					HttpStatus.BAD_REQUEST);
		}
		Station station = (Station) stationRepository.findByName(end.toUpperCase());
		if (station != null) {
			Optional<MetroCard> cardOptional = metroCardRepository.findById(cardId);
			if (cardOptional.isPresent()) {
				MetroCard card = cardOptional.get();
				Journey existingJourney = journeyRepository.findByCardIdAndInProgress(card.getId(), true);
				if (existingJourney != null) {
					existingJourney.setEndStation(station);
					existingJourney.setEndTime(new Date());
					existingJourney.setInProgress(false);
					card.setBalance(existingJourney.getEndAmount());
					metroCardRepository.save(card);
					journeyRepository.save(existingJourney);

					return new ResponseEntity<String>("Swipe Out success.", HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<String>("Swipe Out failure.", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/collectionSummary", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<CollectionSummary> collectionSummary() {
		CollectionSummary summary = journeyRepository.findTotalCollection();
		return new ResponseEntity<CollectionSummary>(summary, HttpStatus.OK);
	}

	@RequestMapping(value = "/passengerSummary", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<PassengerSummary>> passengerSummary() {
		List<PassengerSummary> summary = journeyRepository.findPassengerCountSummary();
		return new ResponseEntity<List<PassengerSummary>>(summary, HttpStatus.OK);
	}

}
