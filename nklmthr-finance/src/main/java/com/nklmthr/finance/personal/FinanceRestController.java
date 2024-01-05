package com.nklmthr.finance.personal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/categorySpends")
	public Collection<CategorySpends> getCategorySpends() {
		List<Category> categorys = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "level"));
		System.out.println("categories " + categorys.size());
		List<CategorySpends> results = new ArrayList<CategorySpends>();
		for (Category cat : categorys) {
			CategorySpends catSpend = new CategorySpends();
			catSpend.setId(cat.getId());
			catSpend.setName(cat.getName());
			catSpend.setLevel(cat.getLevel());
			catSpend.setParentCategory(cat.getParentCategory());
			List<Transaction> catTransactions = transactionRepository.findAllByCategory(cat);
			for (Transaction t : catTransactions) {
				catSpend.setAmount(catSpend.getAmount() + t.getAmount());
			}
			results.add(catSpend);
		}

		int highestLevel = findHighestCategoryLevel(categorys);
		System.out.println(highestLevel);
		while (highestLevel >= 0) {
			for (Category c : categorys) {
				if (c.getLevel() == highestLevel) {
					CategorySpends currentCS = getCategorySpendsForCategory(results, c);
					CategorySpends parentCS = getCategorySpendsForCategory(results, c.getParentCategory());
					parentCS.setAmount(parentCS.getAmount() + currentCS.getAmount());
					System.out.println(c.getLevel() + ", " + c.getName() + ", " + highestLevel);
					System.out.println(currentCS);
					System.out.println(parentCS);
				}
			}
			highestLevel--;
		}
		Collections.sort(results);
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
			if(temp==null) {
				if(cs.getName().equals("Home")) {
					return cs;
				}
			}						
			else if (cs.getName().equals(temp.getName())) {
				return cs;
			}
		}return null;
}}
