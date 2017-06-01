package test;

import static org.junit.Assert.*;

import org.junit.Test;

import jebs.object.Customer;

public class CustomerNumberTest {
	
	Customer customer = new Customer();
	@Test
	public void test1() {
		customer.setNumber("");
		assertFalse("".equals(customer.getNumber()));
	}
	@Test
	public void test2() {
		customer.setNumber(" ");
		assertFalse(" ".equals(customer.getNumber()));
	}
	@Test
	public void test3() {
		customer.setNumber("040d3r45t6");
		assertFalse("040d3r45t6".equals(customer.getNumber()));
	}
	@Test
	public void test4() {
		customer.setNumber("012345678");
		assertFalse("012345678".equals(customer.getNumber()));
	}
	@Test
	public void test5() {
		customer.setNumber("ABCDEFGHI");
		assertFalse("ABCDEFGHI".equals(customer.getNumber()));
	}
	@Test
	public void test6() {
		customer.setNumber("|}[{}:?><~");
		assertFalse("|}[{}:?><~".equals(customer.getNumber()));
	}

}
