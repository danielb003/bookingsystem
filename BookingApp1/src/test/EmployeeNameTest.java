package test;

import static org.junit.Assert.*;

import org.junit.Test;
import jebs.object.Employee;

public class EmployeeNameTest {
	
	Employee employee = new Employee();
	@Test
	public void test1() {
		employee.setName("tim0t4y 7uRn3r");
		assertFalse("tim0t4y 7uRn3r".equals(employee.getName()));
	}
	@Test
	public void test2() {
		employee.setName(" ");
		assertFalse(" ".equals(employee.getName()));
	}
	@Test
	public void test3() {
		employee.setName("10239843");
		assertFalse("10239843".equals(employee.getName()));
	}
	@Test
	public void test4() {
		employee.setName("[]-=;'./,`");
		assertFalse("[]-=;'./,`".equals(employee.getName()));
	}
	@Test
	public void test5() {
		employee.setName("12123]][;..`1");
		assertFalse("12123]][;..`1".equals(employee.getName()));
	}
	@Test
	public void test6() {
		employee.setName("Taspf][';`kfja");
		assertFalse("Taspf][';`kfja".equals(employee.getName()));
	}

}
