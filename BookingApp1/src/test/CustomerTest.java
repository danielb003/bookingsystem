package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CustomerIDTest.class,CustomerNameTest.class,CustomerPasswordTest.class,
	CustomerAddressTest.class,CustomerNumberTest.class})
public class CustomerTest {

}