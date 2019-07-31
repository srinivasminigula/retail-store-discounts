/**
 * Main class to run the application
 */

package com.company.retail.store.discounts;

import com.company.retail.store.discounts.enums.ProductTypeEnum;
import com.company.retail.store.discounts.enums.UserTypeEnum;
import com.company.retail.store.discounts.model.Cart;
import com.company.retail.store.discounts.model.Product;
import com.company.retail.store.discounts.model.User;
import com.company.retail.store.discounts.service.DiscountPolicy;
import com.company.retail.store.discounts.service.Item;
import com.company.retail.store.discounts.service.ThresholdDiscount;

/**
 * @author srinivasminigula
 *
 */
public class Application {

	public static void main(String[] args){
		User employee = new User(UserTypeEnum.EMPLOYEE, "Ahmed");
		Item groceryItem = new Product("Milk", 20, ProductTypeEnum.GROCERY);
		Item otherItem = new Product("Phone", 222, ProductTypeEnum.OTHER);
		DiscountPolicy discountPolicy = new ThresholdDiscount();
		Cart cart = new Cart(employee, discountPolicy);
		cart.add(groceryItem, 4);
		cart.add(otherItem, 4);
		/*
		 * Total (20 * 4) + (222 * 4) = 968 No discount on grocery items = 968 still
		 * After 30% discount on 4 other items totalling 888 = 701.6 After 35 dollars
		 * off due to price over $700 = 666.59999 or 666.6
		 */
		System.out.println(cart.total());
	}

}
