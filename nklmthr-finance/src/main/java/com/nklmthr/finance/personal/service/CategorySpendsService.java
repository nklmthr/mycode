package com.nklmthr.finance.personal.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
		Map<String, CategorySpends> map = new LinkedHashMap<String, CategorySpends>();
		Category rootCategory = categoryService.getParentCategoryByType(CategoryType.HOME);
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
			
			List<Transaction> catTransactions = transactionService.getTransactionsByCategoryInMonth(year, month,
					cat.getId());
			logger.debug("Category:" + cat.getName() +",transactions="+catTransactions.size()+ ", year=" + year + ", month=" + month);
			catTransactions.stream().forEach(s -> logger.debug(s.getDescription()+s.getAmount()+s.getCategory().getName()));
			for (Transaction t : catTransactions) {
				/*
				 * if (!t.getCategory().getCategoryType().equals(CategoryType.EXPENSE)) {
				 * continue; }
				 */
				if (t.getTransactionType().equals(TransactionType.DEBIT)) {
					catSpend.setAmount(catSpend.getAmount().subtract(t.getAmount()));
				} else {
					catSpend.setAmount(catSpend.getAmount().add(t.getAmount()));
				}
			}
			map.put(catSpend.getId(), catSpend);
			logger.debug("Current CatSpend:" + catSpend + " transactions size:" + catTransactions + " amount ="
					+ catSpend.getAmount() + " map.size:" + map.size());
			queue.addAll(cat.getChildCategorys());
		}

		queue.add(rootCategory);
		while (!queue.isEmpty()) {
			Category cat = queue.poll();
			CategorySpends catSpend = map.get(cat.getId());
			for (Category childCats : cat.getChildCategorys()) {
				CategorySpends childCategorySpend = map.get(childCats.getId());
				logger.debug("Adding child category Spend:" + childCategorySpend);
				catSpend.getChildCategorySpends().add(childCategorySpend);
			}
			queue.addAll(cat.getChildCategorys());
		}
		List<Category> categorys = categoryService.getAllCategory();
		int highestLevel = findHighestCategoryLevel(categorys);
		logger.info("highestLevel:" + highestLevel);
		while (highestLevel >= 0) {
			for (Category c : categorys) {				
				if (c.getLevel() == highestLevel) {
					logger.debug(c.getName()+c.getLevel());
					CategorySpends currentCS = map.get(c.getId());
					CategorySpends parentCS = null;
					if (!c.getCategoryType().equals(CategoryType.HOME)) {
						parentCS = map.get(c.getParentCategory().getId());
						logger.debug(c.getName()+"currentCS="+currentCS.getName()+",parentCS="+parentCS);
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
}
