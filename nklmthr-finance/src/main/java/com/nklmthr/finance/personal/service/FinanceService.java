package com.nklmthr.finance.personal.service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nklmthr.finance.personal.dao.AccountRepository;
import com.nklmthr.finance.personal.dao.AccountTypeRepository;
import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.CategoryRepository;
import com.nklmthr.finance.personal.dao.InstitutionRepository;
import com.nklmthr.finance.personal.dao.TransactionRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FinanceService {

	private static Logger logger = Logger.getLogger(FinanceService.class);

	@Autowired
	private AccountTypeRepository accountTypeRepository;

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	public Map<String, Map<String, String>> getReportData() {
		Map<String, Map<String, String>> results = new LinkedHashMap<String, Map<String, String>>();
		List<Object[]> objectArrList = transactionRepository.findCummulativeSumOfTransactionsByMonthAndCategoryLevel(1);
		List<Category> categorys = categoryRepository.findAllCategorysAtLevel(1);
		for(Object[] objArr: objectArrList) {
			List<String> tempCategorys = new ArrayList<String>(categorys.stream().map(s -> s.getName()).toList());
			int year = Integer.parseInt(objArr[0].toString());
			int month = Integer.parseInt(objArr[1].toString());
			String cat = objArr[2].toString();
			String amount = objArr[3].toString();
			tempCategorys.remove(cat);
			
			
		}		
		return results;
	}

}
