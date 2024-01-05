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

}
