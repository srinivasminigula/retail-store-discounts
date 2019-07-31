package com.company.retail.store.discounts.service;

import com.company.retail.store.discounts.enums.ProductTypeEnum;

/*
 * Class to implement pricing at item level
 */
public class PricingPolicy implements Item {

    private final Item baseItem;

    public PricingPolicy(Item baseItem) {
        this.baseItem = baseItem;
    }

    public double getUnitPrice() { 
    	return baseItem.getUnitPrice();
    }

    public String getName() { 
    	return baseItem.getName(); 
    }
    
    public ProductTypeEnum getType() {
    	return baseItem.getType(); 
    }

    public double priceForQuantity(int quantity) {
        return baseItem.priceForQuantity(quantity);
    }
}
