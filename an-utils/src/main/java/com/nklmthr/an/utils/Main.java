package com.nklmthr.an.utils;

import java.util.List;

import com.nklmthr.an.utils.domain.InvoiceManager;
import com.nklmthr.an.utils.domain.POManager;
import com.nklmthr.an.utils.domain.ProfileManager;
import com.nklmthr.an.utils.dto.Order;

public class Main {

	public static void main(String[] args) {
		try {
			ProfileManager.clearAllAdditionalAddress();
			//Address address = ProfileManager.createCountryAddress("MY");
			
			
			List<Order> orders = POManager.createOrders(1);
			Thread.sleep(5000);
			InvoiceManager.createInvoiceWithOrders(orders);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
