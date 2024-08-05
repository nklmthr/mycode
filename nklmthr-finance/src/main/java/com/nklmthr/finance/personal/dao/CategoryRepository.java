package com.nklmthr.finance.personal.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nklmthr.finance.personal.service.CategoryType;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

	@Query("select c from Category c where c.categoryType= ?1 ")
	Page<Category> getParentCategoryByType(CategoryType type, Pageable page);

}
