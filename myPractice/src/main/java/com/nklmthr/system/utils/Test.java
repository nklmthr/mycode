package com.nklmthr.system.utils;

public class Test {

	public static void main(String[] args) {
		boolean is_buyer = false;
		// TODO Auto-generated method stub
		StringBuilder rawQuery = new StringBuilder("select count(id) ");
        rawQuery.append(" from %s d where");
        rawQuery.append(" document_type = 'InvoiceDetailRequest'");
        rawQuery.append(" and created >= :startDate");
        rawQuery.append(" and trunc(created) <= :endDate ");
        if (is_buyer) {
            rawQuery.append(" and to_org = :org");
        } else {
            rawQuery.append(" and from_org = :org");
        }
        rawQuery.append(" and d.id in (select cxml_doc from %s)");
        System.out.println(rawQuery);
	}

}
