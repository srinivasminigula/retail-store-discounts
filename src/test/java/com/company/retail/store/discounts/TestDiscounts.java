package com.company.retail.store.discounts;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.company.retail.store.discounts.enums.ProductTypeEnum;
import com.company.retail.store.discounts.enums.UserTypeEnum;
import com.company.retail.store.discounts.model.Cart;
import com.company.retail.store.discounts.model.Product;
import com.company.retail.store.discounts.model.User;
import com.company.retail.store.discounts.service.DiscountPolicy;
import com.company.retail.store.discounts.service.Item;
import com.company.retail.store.discounts.service.ThresholdDiscount;

/*
 * Test Cases for no discount policy
 */
public class TestDiscounts {

	private Item groceryItem;
	private Item otherItem;
	private User employee;
	private User affiliate;
	private User simpleUser;
	private User simpleUserWith2Years;
	private DiscountPolicy discountPolicy;

	@Before
	public void setUp() {
		employee = new User(UserTypeEnum.EMPLOYEE, "John");
		affiliate = new User(UserTypeEnum.AFFILIATE, "Michael");
		simpleUser = new User(UserTypeEnum.SIMPLE, "Bob");
		simpleUserWith2Years = new User(UserTypeEnum.SIMPLE, "Alex", LocalDateTime.of(2014, 7, 19, 6, 40, 45));
		groceryItem = new Product("Rice", 20, ProductTypeEnum.GROCERY);
		otherItem = new Product("TV", 222, ProductTypeEnum.OTHER);
		discountPolicy = new ThresholdDiscount();
	}

	@Test
	public void testEmployeeWithGrocery() {
		Cart cart = new Cart(employee, discountPolicy);
		cart.add(groceryItem, 4);
		// No discount because of grocery item
		assertEquals(80, cart.total(), 0.01);

	}

	@Test
	public void testEmployeeWithOtherItem() {
		Cart cart = new Cart(employee, discountPolicy);
		cart.add(otherItem, 4);
		/*
		 * 30% discount then 5 dollars off of each 100 dollars of total price because of
		 * other item Total 222 * 4 = 888 After 30% discount = 621.6 After 30 dollars
		 * off due to price over $600 = 591.6
		 */
		assertEquals(591.6, cart.total(), 0.01);

	}

	@Test
	public void testAffiliateWithGrocery() {
		Cart cart = new Cart(affiliate, discountPolicy);
		cart.add(groceryItem, 4);
		// No discount because of grocery item
		assertEquals(80, cart.total(), 0.01);

	}

	@Test
	public void testAffiliateWithOtherItem() {
		Cart cart = new Cart(affiliate, discountPolicy);
		cart.add(otherItem, 4);
		/*
		 * 10% discount then 5 dollars off of each 100 dollars of total price because of
		 * other item Total 222 * 4 = 888 After 10% discount = 799.2 After 35 dollars
		 * off due to price over $700 = 591.6
		 */
		assertEquals(764.2, cart.total(), 0.01);

	}

	@Test
	public void testSimpleUserWithGrocery() {
		Cart cart = new Cart(simpleUser, discountPolicy);
		cart.add(groceryItem, 4);
		// No discount because of grocery item
		assertEquals(80, cart.total(), 0.01);

	}

	@Test
	public void testSimpleUserWithOtherItem() {
		Cart cart = new Cart(simpleUser, discountPolicy);
		cart.add(otherItem, 4);
		/*
		 * Total 222 * 4 = 888 No percentage discount because user is a customer for
		 * less than 2 years After 40 dollars off due to price over $800 = 848
		 */
		assertEquals(848, cart.total(), 0.01);
	}

	@Test
	public void testSimpleUserWith2YearsWithGrocery() {
		Cart cart = new Cart(simpleUserWith2Years, discountPolicy);
		cart.add(groceryItem, 4);
		// No discount because of grocery item
		assertEquals(80, cart.total(), 0.01);

	}

	@Test
	public void testSimpleUserWith2YearsWithOtherItem() {
		Cart cart = new Cart(simpleUserWith2Years, discountPolicy);
		cart.add(otherItem, 4);
		/*
		 * 5% discount then 5 dollars off of each 100 dollars of total price because of
		 * other item Total 222 * 4 = 888 After 5% discount = 843.6 After 40 dollars off
		 * due to price over $800 =803.6
		 */
		assertEquals(803.6, cart.total(), 0.01);

	}

	@Test
	public void testEmployeeWithGroceryAndOtherItem() {
		Cart cart = new Cart(employee, discountPolicy);
		cart.add(groceryItem, 4);
		cart.add(otherItem, 4);
		/*
		 * Total (20 * 4) + (222 * 4) = 968 No discount on grocery items = 968 still
		 * After 30% discount on 4 other items totalling 888 = 701.6 After 35 dollars
		 * off due to price over $700 =666.6
		 */
		assertEquals(666.6, cart.total(), 0.01);

	}
}
