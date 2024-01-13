package com.nklmthr.finance.personal.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

	@Query("select t from Transaction t where year(t.date) = ?1 and month(t.date) = ?2 and t.category.id = ?3 and t.transactionType = 0 order by t.date desc")
	List<Transaction> findAllTransactionsInCategoryByMonth(Integer year, Integer month, String categoryId);

	@Query("select t from Transaction t where year(t.date) =?1  and month(t.date) = ?2 and t.category.id IN ?3 order by t.date desc")
	List<Transaction> findAllTransactionsInCategoriesByMonth(Integer year, Integer month, List<String> categorys);
	
	@Query("select year(t.date) as year, month(t.date) as month, t.category.name as category, sum(t.amount) as amount from Transaction t "
			+ " where t.category.level =?1 "
			+ "group by year, month, category order by  year, month, category ")
	List<Object[]> findCummulativeSumOfTransactionsByMonthAndCategoryLevel(Integer level);	

}
