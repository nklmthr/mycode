package com.nklmthr.finance.personal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyBalanceRepository extends JpaRepository<MonthlyBalance, String> {

	@Query("select new com.nklmthr.finance.personal.dao.MonthlyBalanceSummaryDTO(mb.date, mb.account.accountType.classification, sum(mb.amount)) from MonthlyBalance mb where year(mb.date) = ?1 and month(mb.date) = ?2 group by mb.date, mb.account.accountType.classification")
	List<MonthlyBalanceSummaryDTO> findAllMonthBalanceForLastMonth(Integer year, Integer month);

	@Query("select mb from MonthlyBalance mb where year(mb.date) = ?1 and month(mb.date) = ?2 and day(mb.date) =?3")
	List<MonthlyBalance> findByDate(Integer year, Integer month, Integer day);

}
