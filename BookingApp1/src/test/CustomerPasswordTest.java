package test;

import static org.junit.Assert.*;

import org.junit.Test;

import jebs.object.Customer;

public class CustomerPasswordTest {
	
	Customer customer = new Customer();
	@Test
	public void test1() {
		customer.setPassword(" ");
		assertFalse(" ".equals(customer.getPassword()));
	}
	@Test
	public void test2() {
		customer.setPassword("1234");
		assertFalse("1234".equals(customer.getPassword()));
	}
	@Test
	public void test3() {
		customer.setPassword("abcd");
		assertFalse("abcd".equals(customer.getPassword()));
	}
	@Test
	public void test4() {
		customer.setPassword("ABCDJAKS0101092");
		assertTrue("ABCDJAKS0101092".equals(customer.getPassword()));
	}
	@Test
	public void test5() {
		customer.setPassword("AbcjsLsfp]]['.1/]");
		assertTrue("AbcjsLsfp]]['.1/]".equals(customer.getPassword()));
	}
	@Test
	public void test6() {
		customer.setPassword("lospfA]['/.20193");
		assertTrue("lospfA]['/.20193".equals(customer.getPassword()));
	}

}
