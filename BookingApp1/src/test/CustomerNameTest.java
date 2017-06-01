package test;

import static org.junit.Assert.*;

import org.junit.Test;

import jebs.object.Customer;

public class CustomerNameTest {
	
	Customer customer = new Customer();
	@Test
	public void test1() {
		customer.setName("tim0t4y 7uRn3r");
		assertFalse("tim0t4y 7uRn3r".equals(customer.getName()));
	}
	@Test
	public void test2() {
		customer.setName(" ");
		assertFalse(" ".equals(customer.getName()));
	}
	@Test
	public void test3() {
		customer.setName("10239843");
		assertFalse("10239843".equals(customer.getName()));
	}
	@Test
	public void test4() {
		customer.setName("[]-=;'./,`");
		assertFalse("[]-=;'./,`".equals(customer.getName()));
	}
	@Test
	public void test5() {
		customer.setName("12123]][;..`1");
		assertFalse("12123]][;..`1".equals(customer.getName()));
	}
	@Test
	public void test6() {
		customer.setName("Taspf][';`kfja");
		assertFalse("Taspf][';`kfja".equals(customer.getName()));
	}

}
