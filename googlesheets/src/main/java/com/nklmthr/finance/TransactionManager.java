package com.nklmthr.finance;

import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
	public void updateTransactionsToBeUpdate(List<Transaction> fiTransactions,
			List<Transaction> googleSheetTransactions) {
		List<Transaction> tempList = new ArrayList<Transaction>();
		tempList.addAll(fiTransactions);
		for (Transaction gTrans : googleSheetTransactions) {
			for (Transaction fTrans : fiTransactions) {
				if (null != gTrans.getReference() && gTrans.getReference().equals(fTrans.getReference())) {
					tempList.remove(fTrans);
				} else if (null != gTrans.getExternalReference()
						&& gTrans.getExternalReference().equals(fTrans.getExternalReference())) {
					tempList.remove(fTrans);
				} else if (gTrans.getDate().equals(fTrans.getDate()) && gTrans.getAccount().equals(fTrans.getAccount())
						&& gTrans.getDescription().equals(fTrans.getDescription())
						&& gTrans.getAmount().equals(fTrans.getAmount())) {
					tempList.remove(fTrans);
				}
			}
		}
		googleSheetTransactions.addAll(tempList);
	}
}
