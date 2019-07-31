package com.company.retail.store.discounts.model;

import com.company.retail.store.discounts.enums.ProductTypeEnum;
import com.company.retail.store.discounts.service.Item;

/*
 * Class for actual product items
 */
public class Product implements Item {

    private final String name;
    private final double unitPrice;
    private final ProductTypeEnum type;

    public Product(String name, double priceInDollars, ProductTypeEnum type) {
        this.name = name;
        this.unitPrice = priceInDollars;
        this.type = type;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getName() {
        return name;
    }

    public double priceForQuantity(int quantity) {
        return unitPrice * quantity;
    }
    
    public ProductTypeEnum getType() {
    	return type;
    }
}
