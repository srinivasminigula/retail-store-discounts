package com.company.retail.store.discounts.service;

import com.company.retail.store.discounts.enums.ProductTypeEnum;

/*
 * Interface for all items
 */
public interface Item {
	double getUnitPrice();
	double priceForQuantity(int quantity);
	String getName();
	ProductTypeEnum getType();
}
