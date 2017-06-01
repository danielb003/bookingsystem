package test;

import static org.junit.Assert.*;

import org.junit.Test;
import jebs.object.Employee;

public class EmployeeAddressTest {

	Employee employee = new Employee();
	@Test
	public void test1() {
		employee.setAddress("");
		assertFalse("".equals(employee.getAddress()));
	}
	@Test
	public void test2() {
		employee.setAddress(" ");
		assertFalse(" ".equals(employee.getAddress()));
	}
	@Test
	public void test3() {
		employee.setAddress("65* Test st, Testville 3012");
		assertFalse("65* Test st, Testville 3012".equals(employee.getAddress()));
	}
	@Test
	public void test4() {
		employee.setAddress("1012302495901");
		assertTrue("1012302495901".equals(employee.getAddress()));
	}
	@Test
	public void test5() {
		employee.setAddress("90009-88897709");
		assertFalse("90009-88897709".equals(employee.getAddress()));
	}
	@Test
	public void test6() {
		employee.setAddress("?_=[][{}};./,.`~");
		assertFalse("?_=[][{}};./,.`~".equals(employee.getAddress()));
	}

}
