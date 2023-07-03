package TestCases;

import org.testng.annotations.Test;

import PageObjects.Loginpage;
import driverbase.BaseTest;

public class testcase2 extends BaseTest{
	
	Loginpage login = new Loginpage();
	  @Test
	  public void verifyingtheloginPageSusccessfull() throws InterruptedException{
		  login.enterCredentails();
		 
	  }
}
