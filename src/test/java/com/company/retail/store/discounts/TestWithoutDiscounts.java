package com.company.retail.store.discounts;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.company.retail.store.discounts.enums.ProductTypeEnum;
import com.company.retail.store.discounts.enums.UserTypeEnum;
import com.company.retail.store.discounts.model.Cart;
import com.company.retail.store.discounts.model.Product;
import com.company.retail.store.discounts.model.User;
import com.company.retail.store.discounts.service.Item;

/*
 * Test Cases for no discount policy for any type and just to see if cart is working
 */
public class TestWithoutDiscounts {

	private Cart cart;
	private Item item;

	@Before
	public void setUp() {
		User user = new User(UserTypeEnum.SIMPLE, "John");
		cart = new Cart(user);
		item = new Product("something", 1000, ProductTypeEnum.OTHER);
	}

	@Test
	public void testEmptyCartCostsZero() {
		assertEquals(0, cart.total(), 0.01);
	}

	@Test
	public void testSingleBasicItemCostsItsUnitPrice() {
		cart.add(item);
		assertEquals(item.getUnitPrice(), cart.total(), 0.01);
	}

	@Test
	public void testMultipleBasicItemsCostProportionally() {
		int howMany = 3;
		cart.add(item, howMany);
		assertEquals(howMany * item.getUnitPrice(), cart.total(), 0.01);
	}

	@Test
	public void testSeparatelyAdding() {
		int howMany = 3;
		for (int i = howMany; i > 0; i--) {
			cart.add(item);
		}
		assertEquals(howMany * item.getUnitPrice(), cart.total(), 0.01);
	}
}
