package com.nklmthr.finance.personal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
	@Query("select c from Category c where level = ?1 and hidden=false order by c.name")
	List<Category> findAllCategorysAtLevel(int i);

	@Query("select c from Category c where hidden = true")
	List<Category> findHiddenCategory();
	
	@Query("select c from Category c where name ='SPLIT'")
	Category findSplitCategory();
	
	@Query("select c from Category c where level = 0")
	Category findHomeCategory();

	@Query("select c from Category c where name =?1")
	Category findByName(String name);
	

}
