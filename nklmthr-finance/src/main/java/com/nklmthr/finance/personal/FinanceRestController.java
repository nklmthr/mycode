package com.nklmthr.finance.personal;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.CategoryRepository;
import com.nklmthr.finance.personal.dao.CategorySpends;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.dao.TransactionRepository;

@RestController
@RequestMapping("/api")
public class FinanceRestController {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/categorys")
	public List<Category> getCategories() {
		List<Category> categorys = new ArrayList<Category>();
		categorys = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "level"));
		logger.info("getCategories size:" + categorys.size());
		return categorys;

	}

	@GetMapping("/months")
	public static Map<Integer, String> getMonths() {
		Map<Integer, String> months = new LinkedHashMap<Integer, String>();
		YearMonth current = YearMonth.now();
		for (int i = -5; i < 1; i++) {
			months.put(i, current.plusMonths(i).getMonth().name());
		}
		return months;
	}

	@GetMapping("/categorySpends")
	public List<CategorySpends> getCategorySpends() {
		List<Category> categorys = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "level"));
		logger.info("categories " + categorys.size());
		List<CategorySpends> results = new ArrayList<CategorySpends>();
		for (Category cat : categorys) {
			CategorySpends catSpend = new CategorySpends();
			catSpend.setId(cat.getId());
			catSpend.setName(cat.getName());
			catSpend.setLevel(cat.getLevel());
			catSpend.setParentCategory(cat.getParentCategory());
			List<Transaction> catTransactions = transactionRepository.findAllByCategory(cat);
			for (Transaction t : catTransactions) {
				catSpend.setAmount(catSpend.getAmount().add(t.getAmount()));
			}
			results.add(catSpend);
		}
		logger.debug("results " + results);
		logger.info("CategorySpends size:" + results.size());
		int highestLevel = findHighestCategoryLevel(categorys);
		logger.info("highestLevel:" + highestLevel);
		while (highestLevel >= 0) {
			for (Category c : categorys) {
				if (c.getLevel() == highestLevel) {
					CategorySpends currentCS = getCategorySpendsForCategory(results, c);
					CategorySpends parentCS = getCategorySpendsForCategory(results, c.getParentCategory());
					if (parentCS != null) {
						parentCS.setAmount(parentCS.getAmount().add(currentCS.getAmount()));
					}
				}
			}
			highestLevel--;
		}
		Collections.sort(results);
		logger.info("result getCategorySpends size:" + results.size());
		return results;
	}

	private int findHighestCategoryLevel(List<Category> categorys) {
		int level = 0;
		for (Category c : categorys) {
			if (c.getLevel() > level) {
				level = c.getLevel();
			}
		}
		return level;
	}

	private CategorySpends getCategorySpendsForCategory(List<CategorySpends> results, Category temp) {
		for (CategorySpends cs : results) {
			if (temp!=null && cs.getName().equals(temp.getName())) {
				return cs;
			}
		}
		return null;
	}
}
