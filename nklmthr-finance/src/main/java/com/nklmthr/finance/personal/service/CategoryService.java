package com.nklmthr.finance.personal.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.CategoryRepository;

@Service
public class CategoryService {

	private static final Logger logger = Logger.getLogger(CategoryService.class);

	@Autowired
	private CategoryRepository categoryRepository;

	public Category getParentCategoryByType(CategoryType type) {
		Pageable page = PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "level"));
		Page<Category> stemByType = categoryRepository.getParentCategoryByType(type, page);
		return stemByType.getContent().get(0);
	}

	//No Change
	public List<Category> getAllCategory() {
		List<Category> categoryList = categoryRepository
				.findAll(Sort.by(Sort.Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "name")));
		logger.info("getAllCategory:" + categoryList.size());
		return categoryList;
	}

	/*
	public List<Category> getAllCategoryExcludingHidden() {
		List<Category> categoryList = getAllCategory();
		logger.info("getAllCategoryExcludingHidden:" + categoryList.size());
		return categoryList;
	}*/

	//No Change
	public void saveCategory(Category category) {
		Category temp = category;
		int level = 0;
		while (temp.getParentCategory() != null) {
			temp = temp.getParentCategory();
			level++;
		}
		category.setLevel(level);
		logger.info("saveCategory:" + category);
		categoryRepository.save(category);
	}

	//No Change
	public Category findCategoryById(String id) {
		Optional<Category> cat = categoryRepository.findById(id);
		if (cat.isPresent()) {
			logger.info("findCategoryById:" + id);
			return cat.get();
		}
		logger.info("findCategoryById:" + id + "=null");
		return null;
	}

	//NoChange
	public void deleteCategoryById(String id) {
		logger.info("deleteCategoryById:" + id);
		categoryRepository.deleteById(id);

	}

	public List<Category> getAllCategorysByCategoryType(CategoryType categoryType) {
		Pageable page = Pageable.unpaged(Sort.by(Sort.Direction.ASC, "level"));
		Page<Category> categoryPage = categoryRepository.getParentCategoryByType(categoryType, page);
		logger.info("getAllCategorysByCategoryType:" + categoryPage.getSize());
		return categoryPage.getContent();
	}

	/*
	public Category getRootCategory() {
		logger.info("getRootCategory");
		return categoryRepository.findStemByCategoryType(CategoryType.EXPENSE);
	}
	*/

	/*
	public Category findCategoryByName(String name) {
		logger.info("findCategoryByName" + name);
		return categoryRepository.findByName(name);
	}
	*/

}
