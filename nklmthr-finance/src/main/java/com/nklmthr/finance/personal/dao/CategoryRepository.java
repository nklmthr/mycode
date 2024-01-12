package com.nklmthr.finance.personal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

	@Query("select c from Category c where level = ?1 order by c.name")
	List<Category> findAllCategorysAtLevel(int i);
	

}
