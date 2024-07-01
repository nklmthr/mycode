package com.nklmthr.finance.personal.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.CategorySpends;
import com.nklmthr.finance.personal.dao.Transaction;

@Service
public class CategorySpendsService {
	Logger logger = Logger.getLogger(CategorySpendsService.class);

	@Autowired
	CategoryService categoryService;

	@Autowired
	private TransactionService transactionService;

	public CategorySpends getCategorySpendsTree(Integer year, Integer month) {
		CategorySpends rootCatSpend = new CategorySpends();
		Map<String, CategorySpends> map = new HashMap<>();
		Category rootCategory = categoryService.getRootCategory();
		Queue<Category> queue = new LinkedList<Category>();
		queue.add(rootCategory);
		while (!queue.isEmpty()) {
			Category cat = queue.poll();
			CategorySpends catSpend = null;
			if (cat.getLevel() == 0) {
				catSpend = rootCatSpend;
			} else {
				catSpend = new CategorySpends();
				catSpend.setParentCategorySpends(map.get(cat.getParentCategory().getId()));
			}
			catSpend.setId(cat.getId());
			catSpend.setName(cat.getName());
			catSpend.setLevel(cat.getLevel());
			logger.debug("Category:" + cat.getName() + ", year=" + year + ", month=" + month);
			List<Transaction> catTransactions = transactionService.getTransactionsByCategoryInMonth(year, month,
					cat.getId());
			for (Transaction t : catTransactions) {
				if(t.getCategory().isHidden()) {
					continue;
				}
				if (t.getTransactionType().equals(TransactionType.DEBIT)) {
					catSpend.setAmount(catSpend.getAmount().add(t.getAmount()));
				} else {
					catSpend.setAmount(catSpend.getAmount().subtract(t.getAmount()));
				}
			}
			map.put(catSpend.getId(), catSpend);
			logger.debug("Current CatSpend:" + catSpend + " transactions size:" + catTransactions + " amount ="
					+ catSpend.getAmount() + " map.size:" + map.size());
			queue.addAll(cat.getChildCategorys().stream().filter((s -> !s.isHidden())).collect(Collectors.toSet()));
		}

		queue.add(rootCategory);
		while (!queue.isEmpty()) {
			Category cat = queue.poll();
			CategorySpends catSpend = map.get(cat.getId());
			for (Category childCats : cat.getChildCategorys().stream().filter(s -> !s.isHidden())
					.collect(Collectors.toSet())) {
				CategorySpends childCategorySpend = map.get(childCats.getId());
				logger.debug("Adding child category Spend:" + childCategorySpend);
				catSpend.getChildCategorySpends().add(childCategorySpend);
			}
			queue.addAll(cat.getChildCategorys().stream().filter((s -> !s.isHidden())).collect(Collectors.toSet()));
		}
		List<Category> categorys = categoryService.getAllCategorys();
		int highestLevel = findHighestCategoryLevel(categorys);
		logger.info("highestLevel:" + highestLevel);
		while (highestLevel >= 0) {
			for (Category c : categorys) {
				if (c.getLevel() == highestLevel) {
					CategorySpends currentCS = map.get(c.getId());
					CategorySpends parentCS = null;
					if (c.getParentCategory() != null) {
						parentCS = map.get(c.getParentCategory().getId());
					}
					if (parentCS != null) {
						parentCS.setAmount(parentCS.getAmount().add(currentCS.getAmount()));
					}
				}
			}
			highestLevel--;
		}
		return rootCatSpend;
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
			if (temp != null && cs.getName().equals(temp.getName())) {
				return cs;
			}
		}
		return null;
	}

}
