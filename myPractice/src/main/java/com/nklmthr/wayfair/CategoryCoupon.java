package com.nklmthr.wayfair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CategoryCoupon {

	public static void main(String[] args) {
		String Coupons = "[{\"CategoryName\":\"Comforter Sets\", \"CouponName\":\"Comforters Sale\"},"+
					           "\"CategoryName\":\"Bedding\", \"CouponName\":\"Savings on Bedding\"},"+
					          " {\"CategoryName\":\"Bed & Bath\", \"CouponName\":\"Low price for Bed & Bath\"}"+
					       "]";
		        
		    String   Categories = "[    {\"CategoryName\":\"Comforter Sets\", \"CategoryParentName\":\"Bedding\"},"+
		    		"		           {\"CategoryName\":\"Bedding\", \"CategoryParentName\":\"Bed & Bath\"},"+
		    		"		           {\"CategoryName\":\"Bed & Bath\", \"CategoryParentName\":null},"+
		    		"		           {\"CategoryName\":\"Soap Dispensers\", \"CategoryParentName\":\"Bathroom Accessories\"},"+
		    		"		           {\"CategoryName\":\"Bathroom Accessories\", \"CategoryParentName\":\"Bed & Bath\"},"+
		    		"		           {\"CategoryName\":\"Toy Organizers\", \"CategoryParentName\":\"Baby And Kids\"},"+
		    		"		           {\"CategoryName\":\"Baby And Kids\", \"CategoryParentName\":null}"+
		    		"		       ]";
//		Category cat1 = new Category("Bed & Bath",null);
//		Category cat2 = new Category("Baby And Kids",null);
//		Category cat3 = new Category("Bedding",cat1);
//		Category cat4 = new Category("Comforter Sets",cat3);
//		Category cat5 = new Category("Bathroom Accessories",cat1);
//		Category cat5 = new Category("Bathroom Accessories",cat1);
//		Category cat5 = new Category("Bathroom Accessories",cat1);
//		
//		Coupon coup1 = new Coupon("Comforter Sets","Low price for Bed & Bath");
//		Coupon coup2 = new Coupon("Bedding","Savings on Bedding");
//		Coupon coup3 = new Coupon("Bed & Bath","Low price for Bed & Bath");
		
		
		Object CouponsJson= JSONValue.parse(Coupons); 
		JSONArray couponArray=(JSONArray)CouponsJson;
		for(int i=0; i< couponArray.length();i++) {
			Object obj  = couponArray.get(i);
			
		}
		
		Coupon[] coupons = null;
		Category[] categories = null;
		
		String categoryStr=""; String parentStr="";
		Map<String, Category> cats = new HashMap<>();
		for(int i=0;i< 10;i++) {
			String category;
//			Category cat = new Category(category, null);
//			cats.put(categoryStr, cat);
		}
		
		for(int i=0;i<10;i++) {
			Category category = cats.get(i);
			category.setParent(cats.get(parentStr));
			
		}
		
		Category searchCat =null;
		
		Coupon coupon = findCoupon(coupons, categories, searchCat);

	}

	private static Coupon findCoupon(Coupon[] coupons, Category[] categories, Category searchCat) {
		
		for (Coupon coupon : coupons) {
			Category tmpCat = searchCat;
			while (!(null != tmpCat && tmpCat.equals(coupon.getCategory()))) {
				tmpCat = searchCat.getParent();
			}
			return coupon;
		}

		return null;
	}

}

class Category {
	private String name;
	private Category parent;
	List<Coupon> coupons;
	public Category() {
		super();
	}

	public Category(String name, Category parent) {
		super();
		this.name = name;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

}

class Coupon {
	private String couponName;
	private Category category;

	public Coupon() {
		super();
	}

	public Coupon(String couponName, Category category) {
		super();
		this.couponName = couponName;
		this.category = category;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}