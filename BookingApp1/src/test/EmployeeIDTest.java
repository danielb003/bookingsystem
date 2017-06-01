package test;

import static org.junit.Assert.*;

import org.junit.Test;
import jebs.object.Employee;

public class EmployeeIDTest {

	Employee employee = new Employee();
	@Test
	public void test1() {
		employee.setID("abc");
		assertFalse("abc".equals(employee.getID()));	
	}
	@Test
	public void test2() {
		employee.setID("1");
		assertFalse("1".equals(employee.getID()));	
	}
	@Test
	public void test3() {
		employee.setID("%!@~");
		assertFalse("%!@~".equals(employee.getID()));	
	}
	@Test
	public void test4() {
		employee.setID("0000");
		assertFalse("0000".equals(employee.getID()));	
	}
	@Test
	public void test5() {
		employee.setID("1010101010101010101");
		assertFalse("1010101010101010101".equals(employee.getID()));	
	}
	@Test
	public void test6() {
		employee.setID(" ");
		assertFalse(" ".equals(employee.getID()));	
	}

}
