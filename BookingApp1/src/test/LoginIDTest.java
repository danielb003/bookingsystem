package test;

import static org.junit.Assert.*;

import org.junit.Test;
import jebs.mainpackage.LoginValidation;


public class LoginIDTest {
  
	LoginValidation login = new LoginValidation();
	@Test
	public void test() {
		assertTrue(login.loginIDValidation("c001"));
	}
	
	@Test 
	public void test1() {
		assertTrue(!login.loginIDValidation("c01"));
	}
	
	@Test 
	public void test2() {
		assertTrue(!login.loginIDValidation("p001"));
	}
	
	@Test 
	public void test3() {
		assertTrue(!login.loginIDValidation("abcd"));
	}
	
	@Test 
	public void test4() {
		assertTrue(!login.loginIDValidation("c1"));
	}
	
	@Test 
	public void test5() {
		assertTrue(login.loginIDValidation("o001"));
	}

}

