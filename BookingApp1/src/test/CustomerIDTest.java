package test;

import static org.junit.Assert.*;

import org.junit.Test;
import jebs.object.Customer;


public class CustomerIDTest {
	
	Customer customer = new Customer();
	@Test
	public void test1() {
		customer.setID("abc");
		assertFalse("abc".equals(customer.getID()));	
	}
	@Test
	public void test2() {
		customer.setID("1");
		assertFalse("1".equals(customer.getID()));
	}
	@Test
	public void test3() {
		customer.setID("%!@~");
		assertFalse("%!@~".equals(customer.getID()));
	}
	@Test
	public void test4() {
		customer.setID("0000");
		assertFalse("0000".equals(customer.getID()));
	}
	@Test
	public void test5() {
		customer.setID("1010101010101010101");
		assertFalse("1010101010101010101".equals(customer.getID()));
	}
	@Test
	public void test6() {
		customer.setID(" ");
		assertFalse(" ".equals(customer.getID()));	
	}

}

