package test;

import static org.junit.Assert.*;

import org.junit.Test;
import jebs.mainpackage.LoginValidation;


public class LoginPasswordTest {
	LoginValidation login = new LoginValidation();
	@Test
	public void test1() {
		assertTrue(1 == login.loginPasswordValidation("c001", "abcd1234"));
	}
	@Test
	public void test2() {
		assertTrue(0 == login.loginPasswordValidation("c001", "abcd12345"));
	}
	@Test
	public void test3() {
		assertTrue(2 == login.loginPasswordValidation("o001", "ownerpass"));
	}
	@Test
	public void test4() {
		assertTrue(0 == login.loginPasswordValidation("o001", "abcd1234"));
	}
	@Test
	public void test5() {
		assertTrue(0 == login.loginPasswordValidation("c002", "abcd1234"));
	}
	@Test
	public void test6() {
		assertTrue(0 == login.loginPasswordValidation("c01", "abcd1234"));
	}	

}

