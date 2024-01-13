package com.nklmthr.finance.personal.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.CategoryRepository;

@Service
public class CategoryService {

	private static Logger logger = Logger.getLogger(CategoryService.class);

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getAllCategorys() {
		List<Category> categoryList = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "name")));
		return categoryList;
	}

	public Category saveCategory(Category category) {
		Category temp = (Category) category.clone();
		int level = 0;
		while (temp.getParentCategory() != null) {
			temp = temp.getParentCategory();
			level++;
		}
		category.setLevel(level);
		return categoryRepository.save(category);
	}

	public Category findCategoryById(String id) {
		Optional<Category> cat = categoryRepository.findById(id);
		if (cat.isPresent()) {
			return cat.get();
		}
		return null;
	}

	public void deleteCategoryById(String id) {
		categoryRepository.deleteById(id);

	}

	public Category getRootCategory() {
		return categoryRepository.findAllCategorysAtLevel(0).get(0);
	}

}
