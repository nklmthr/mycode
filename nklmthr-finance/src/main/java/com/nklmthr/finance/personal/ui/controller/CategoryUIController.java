package com.nklmthr.finance.personal.ui.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.service.CategoryService;

@Controller
@RequestMapping("/")
public class CategoryUIController {
	Logger logger = Logger.getLogger(CategoryUIController.class);

	@Autowired
	CategoryService categoryService;

	@GetMapping("/category/categoryFragment")
	public String getCategoryFragment(Model m) {
		return "category/categoryFragment";
	}

	@GetMapping("/Categorys")
	public String getCategorys(Model m) {
		List<Category> categoryList = categoryService.getAllCategorys();				
		m.addAttribute("categoryList", categoryList.get(0));
		logger.info("getCategorys size:" + categoryList.size());
		return "category/Categorys";
	}

	@GetMapping("/addnewCategory")
	public String addnewCategory(Model m) {
		List<Category> categorysList = categoryService.getAllCategorys();
		m.addAttribute("CategoryList", categorysList);
		logger.info("addnewCategory");
		Category category = new Category();
		m.addAttribute("category", category);
		return "category/addnewCategory";
	}

	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category) {		
		categoryService.saveCategory(category);
		logger.info("saveCategory " + category.getName());
		return "redirect:/Categorys";
	}

	@GetMapping("/showFormForCategoryUpdate/{id}")
	public String showFormForCategoryUpdate(@PathVariable(value = "id") String id, Model m) {
		List<Category> Categorys = categoryService.getAllCategorys();
		m.addAttribute("CategoryList", Categorys);
		Category c = categoryService.findCategoryById(id);
		m.addAttribute("category", c);
		logger.info("showFormForCategoryUpdate ");
		return "category/UpdateCategory";
	}

	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable(value = "id") String id, Model model) {
		categoryService.deleteCategoryById(id);
		logger.info("deleteCategory " + id);
		return "redirect:/Categorys";
	}
	
}
