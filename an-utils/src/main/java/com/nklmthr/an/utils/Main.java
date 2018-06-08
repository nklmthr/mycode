package com.nklmthr.an.utils;

import java.util.List;

import com.nklmthr.an.utils.domain.POManager;
import com.nklmthr.an.utils.dto.Order;

public class Main {

	public static void main(String[] args) {
		try {
			List<Order> orders = POManager.createOrders(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
