package com.nklmthr.finance.personal.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyBalanceRepository extends JpaRepository<MonthlyBalance, String> {

	@Query("select mb from MonthlyBalance mb where year(mb.date) = ?1 and month(mb.date) = ?2")
	List<MonthlyBalance> findAllMonthBalanceForLastMonth(Integer year, Integer month);

	@Query("select mb from MonthlyBalance mb where year(mb.date) = ?1 and month(mb.date) = ?2 and day(mb.date) =?3")
	List<MonthlyBalance> findByDate(Integer year, Integer month, Integer day);

	
	

}
