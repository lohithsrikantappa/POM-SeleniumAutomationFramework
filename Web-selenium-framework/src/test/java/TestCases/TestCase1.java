package TestCases;

import org.testng.annotations.Test;
import PageObjects.Loginpage;

public class TestCase1 extends Loginpage{
	
	Loginpage login = new Loginpage();
	
  @Test(groups = "smoke")
  public void verifyingtheloginPageSusccessfull() throws InterruptedException{
	  login.enterCredentails();
	 
  }
  
  @Test(groups = "Regression")
  public void verifying_the_Invalid_credential() throws InterruptedException{
	  login.enterCredentails();
	
  }
}
