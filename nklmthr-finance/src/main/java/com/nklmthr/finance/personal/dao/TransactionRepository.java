package com.nklmthr.finance.personal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
	@Query("select t, t.category from Transaction t order by t.category.level")
	List<Transaction> findBySortedCategoryLevel();

	List<Transaction> findAllByCategory(Category cat);

	@Query("select t from Transaction t where year(t.date) = ?1 and month(t.date) = ?2 order by t.date desc")
	List<Transaction> findAllTransactionsByMonth(Integer year, Integer month);

//	@Query("select t from Transaction t where t.category.id = ?1 and year(t.date) = year(current_date) and month(t.date) = month(current_date) order by t.date desc")
//	List<Transaction> findAllByCategoryCurrentMonth(String cat);

	@Query("select t from Transaction t where year(t.date) = ?1 and month(t.date) = ?2 and t.category.id = ?3 order by t.date desc")
	List<Transaction> findAllTransactionsInCategoryByMonth(Integer year, Integer month, String categoryId);

}
