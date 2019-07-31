package com.company.retail.store.discounts.service;

/*
 * Interface for Overall Discount Policy for all items of cart
 */
public interface DiscountPolicy {
	double applyDiscount(double totalAmount);
}
