package test;

import static org.junit.Assert.*;

import org.junit.Test;

import jebs.object.Employee;

public class EmployeeNumberTest {
	
	Employee employee = new Employee();
	@Test
	public void test1() {
		employee.setNumber("");
		assertFalse("".equals(employee.getNumber()));
	}
	@Test
	public void test2() {
		employee.setNumber(" ");
		assertFalse(" ".equals(employee.getNumber()));
	}
	@Test
	public void test3() {
		employee.setNumber("040d3r45t6");
		assertFalse("040d3r45t6".equals(employee.getNumber()));
	}
	@Test
	public void test4() {
		employee.setNumber("041928345");
		assertFalse("041928345".equals(employee.getNumber()));
	}
	@Test
	public void test5() {
		employee.setNumber("ABCDEFGHI");
		assertFalse("ABCDEFGHI".equals(employee.getNumber()));
	}
	@Test
	public void test6() {
		employee.setNumber("|}[{}:?><~");
		assertFalse("|}[{}:?><~".equals(employee.getNumber()));
	}

}
