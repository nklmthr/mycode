package com.nklmthr.finance.personal.service;

import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.CategoryRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger logger = Logger.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getHomeCategory() {
        Category home = categoryRepository.findHomeCategory();
        getHiddenCategories().forEach(home.getChildCategorys()::remove);
        return home;
    }

    public List<Category> getAllCategory() {
        List<Category> categoryList = categoryRepository
                .findAll(Sort.by(Sort.Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "name")));
        logger.info("getAllCategory:" + categoryList.size());
        return categoryList;
    }

    public List<Category> getAllCategoryExcludingHidden() {
        List<Category> categoryList = getAllCategory().stream()
                .filter(s -> !s.isHidden()).collect(Collectors.toList());
        logger.info("getAllCategoryExcludingHidden:" + categoryList.size());
        return categoryList;
    }

    public void saveCategory(Category category) {
        Category temp = category;
        int level = 0;
        while (temp.getParentCategory() != null) {
            temp = temp.getParentCategory();
            level++;
        }
        category.setLevel(level);
        category.setHidden(false);
        logger.info("saveCategory:" + category);
        categoryRepository.save(category);
    }

    public Category findCategoryById(String id) {
        Optional<Category> cat = categoryRepository.findById(id);
        if (cat.isPresent()) {
            logger.info("findCategoryById:" + id);
            return cat.get();
        }
        logger.info("findCategoryById:" + id + "=null");
        return null;
    }

    public void deleteCategoryById(String id) {
        logger.info("deleteCategoryById:" + id);
        categoryRepository.deleteById(id);

    }

    public Category getRootCategory() {
        logger.info("getRootCategory");
        return categoryRepository.findAllCategorysAtLevel(0).get(0);
    }

    public List<Category> getHiddenCategories() {
        logger.info("getHiddenCategories");
        return categoryRepository.findHiddenCategory();
    }

    public Category findCategoryByName(String name) {
        logger.info("findCategoryByName"+name);
        return categoryRepository.findByName(name);
    }

}
