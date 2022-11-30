package com.srishti.geektrust.metro.journey;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.annotations.GenericGenerator;

import com.srishti.geektrust.metro.controllers.TravelCategory;
import com.srishti.geektrust.metro.station.Station;

@Entity(name = "JOURNEY")
@NamedNativeQuery(name = "findCollectionSummary", query = "SELECT SUM(DISCOUNT) AS totalDiscount, SUM(TOTAL_FAIR_CHARGED) AS totalAmount FROM JOURNEY", resultSetMapping = "collectionSummary")
@SqlResultSetMapping(name = "collectionSummary", classes = @ConstructorResult(targetClass = com.srishti.geektrust.metro.reports.CollectionSummary.class, columns = {
		@ColumnResult(name = "totalDiscount", type = Double.class),
		@ColumnResult(name = "totalAmount", type = Double.class) }))

@NamedNativeQuery(name = "findPassengerCountSummary", query = "SELECT CATEGORY AS CATEGORY, COUNT(1) AS PASSENGERCOUNT FROM JOURNEY GROUP BY CATEGORY ORDER BY CATEGORY ", resultSetMapping = "passengerSummary")
@SqlResultSetMapping(name = "passengerSummary", classes = @ConstructorResult(targetClass = com.srishti.geektrust.metro.reports.PassengerSummary.class, columns = {
		@ColumnResult(name = "category", type = String.class),
		@ColumnResult(name = "passengerCount", type = Integer.class) }))

public class Journey {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column
	private String id;

	@Column
	private String cardId;

	@Column
	private String name;

	@Column
	private Double startingBalance;

	@Column
	@Enumerated(EnumType.STRING)
	private TravelCategory category;

	@Column
	private Double rate;

	@Column
	private Double discount;

	@Column
	private Double endAmount;

	@ManyToOne
	@JoinColumn(name = "start_station", referencedColumnName = "id")
	private Station startStation;

	@ManyToOne
	@JoinColumn(name = "end_station", referencedColumnName = "id")
	private Station endStation;

	@Column
	private Date startTime;

	@Column
	private Date endTime;

	@Column
	private boolean isReturn;

	@Column
	private Double totalFairCharged;

	@Column
	private boolean inProgress;

	@Column
	private Double rechargeServiceFee;

	@Column
	private Double rechargeAmount;

	public Journey() {
		super();
	}

	public Journey(Date startTime, Date endTime, String cardId, String name, TravelCategory category,
			Station startStation, Station endStation, Double rate, Double discount, Double startingBalance,
			Double rechargeAmount, Double rechargeServiceFee, Double endAmount, Double totalFairCharged,
			boolean isReturn, boolean inProgress) {
		super();
		this.cardId = cardId;
		this.name = name;
		this.startingBalance = startingBalance;
		this.category = category;
		this.rate = rate;
		this.discount = discount;
		this.rechargeServiceFee = rechargeServiceFee;
		this.rechargeAmount = rechargeAmount;
		this.endAmount = endAmount;
		this.startStation = startStation;
		this.endStation = endStation;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isReturn = isReturn;
		this.totalFairCharged = totalFairCharged;
		this.inProgress = inProgress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getStartingBalance() {
		return startingBalance;
	}

	public void setStartingBalance(Double startingBalance) {
		this.startingBalance = startingBalance;
	}

	public TravelCategory getCategory() {
		return category;
	}

	public void setCategory(TravelCategory category) {
		this.category = category;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getEndAmount() {
		return endAmount;
	}

	public void setEndAmount(Double endAmount) {
		this.endAmount = endAmount;
	}

	public Station getStartStation() {
		return startStation;
	}

	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}

	public Station getEndStation() {
		return endStation;
	}

	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isReturn() {
		return isReturn;
	}

	public void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}

	public Double getTotalFairCharged() {
		return totalFairCharged;
	}

	public void setTotalFairCharged(Double totalFairCharged) {
		this.totalFairCharged = totalFairCharged;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

	public Double getRechargeServiceFee() {
		return rechargeServiceFee;
	}

	public void setRechargeServiceFee(Double rechargeServiceFee) {
		this.rechargeServiceFee = rechargeServiceFee;
	}

	public Double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

}
