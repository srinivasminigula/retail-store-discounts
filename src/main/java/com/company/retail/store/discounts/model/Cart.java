package com.company.retail.store.discounts.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

import com.company.retail.store.discounts.enums.UserTypeEnum;
import com.company.retail.store.discounts.service.DiscountPolicy;
import com.company.retail.store.discounts.service.Item;
import com.company.retail.store.discounts.service.PromotionPricing;

/*
 * Cart Class to calculate complete to add items and contain the final net amount
 */
public class Cart {
	private Map<Item, Integer> quantities;
	private DiscountPolicy discountPolicy;
	private User user;

	public Cart(User user) {
		quantities = new LinkedHashMap<>();
		this.user = user;
	}

	public Cart(User user, DiscountPolicy discountPolicy) {
		quantities = new LinkedHashMap<>();
		this.user = user;
		this.discountPolicy = discountPolicy;
	}

	public double total() {
		double result = 0;
		for (Item each : quantities.keySet()) {
			result += each.priceForQuantity(quantities.get(each));
		}

		if (discountPolicy != null) {
			result = discountPolicy.applyDiscount(result);
		}

		return result;
	}

	public void add(Item itemToBuy) {
		add(itemToBuy, 1);
	}

	// To add multiple quantities of item
	public void add(Item itemToBuy, int howMany) {
		Item item;

		// Apply 30% discount in case of employee of store
		if (user.getType() == UserTypeEnum.EMPLOYEE) {
			item = new PromotionPricing(itemToBuy, 30);
		}
		// Apply 10% discount in case of affiliate
		else if (user.getType() == UserTypeEnum.AFFILIATE) {
			item = new PromotionPricing(itemToBuy, 10);
		}

		// If a user has been a customer for 2 or more years apply 5% discount
		else if (user.getType() == UserTypeEnum.SIMPLE
				&& ChronoUnit.YEARS.between(user.getJoiningDate(), LocalDateTime.now()) >= 2) {
			item = new PromotionPricing(itemToBuy, 5);
		}

		else {
			item = itemToBuy;
		}

		int previousQuantity = quantities.containsKey(item) ? quantities.get(item) : 0;
		quantities.put(item, previousQuantity + howMany);
	}
}
