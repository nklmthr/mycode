package com.srishti.geektrust.metro.journey;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srishti.geektrust.metro.reports.CollectionSummary;
import com.srishti.geektrust.metro.reports.PassengerSummary;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, String> {

	Journey findByCardIdAndInProgress(String id, boolean inProgress);

	List<Journey> findByCardIdAndInProgressAndEndTimeBetween(String cardId, boolean inProgess, Date todayDate,
			Date tommDate);

	@Query(name = "findCollectionSummary", nativeQuery = true)
	CollectionSummary findTotalCollection();

	@Query(name = "findPassengerCountSummary", nativeQuery = true)
	List<PassengerSummary> findPassengerCountSummary();

}
