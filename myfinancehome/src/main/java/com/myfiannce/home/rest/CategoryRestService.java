package com.myfiannce.home.rest;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myfiannce.home.model.Category;
import com.myfiannce.home.service.CategoryService;

@Controller
public class CategoryRestService {
	private static final Logger logger = Logger.getLogger(CategoryRestService.class);

	@Autowired
	private CategoryService service;

	@GetMapping("/category")
	@ResponseBody
	public List<Category> getAllCategories() {
		logger.info("Start getAllCategories");
		List<Category> result = service.getAllCategories();
		logger.info("End getAllCategories, size=" + result.size());
		return result;
	}
}
