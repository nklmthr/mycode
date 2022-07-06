package com.myfiannce.home.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfiannce.home.model.Category;
import com.myfiannce.home.repository.CategoryRepository;

@Service
public class CategoryService {
	private static final Logger logger = Logger.getLogger(CategoryService.class);
	@Autowired
	private CategoryRepository repository;

	public Category createEmployee(Category e) {
		Category retun = repository.save(e);
		return retun;
	}

	public List<Category> getAllCategories() {
		Iterable<Category> categories = repository.findAll();
		Set<Category> tempSet = new HashSet<Category>();
		tempSet.addAll((Collection<? extends Category>) categories);
		List<Category> result = new ArrayList<Category>();
		for (Category cat : categories) {
			if (!checkIfExist(cat, tempSet)) {
				result.add(cat);
			}
		}
		System.out.println(result);
		result.addAll(result);
		return result;
	}

	private boolean checkIfExist(Category cat, Set<Category> result) {
		for (Category category : result) {
			if (cat.getId() == 1) {
				return false;
			}
			if (cat.equals(category)) {
				return true;
			} else if (cat.getChildren().size() > 0) {
				return checkIfExist(cat, category.getChildren());
			} else {
				return false;
			}
		}
		return false;
	}
}
