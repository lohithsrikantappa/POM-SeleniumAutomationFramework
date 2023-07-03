package PageObjects;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import driverbase.ConfigFile;

import driverbase.BaseTest;

public class Loginpage extends BaseTest {
	
	String username = "//div/[]";
	String password = "password";
	

	ConfigFile config = new ConfigFile();
	public void enterCredentails() throws InterruptedException
	
	{
		Thread.sleep(200);
		driver.findElement(By.xpath(username)).sendKeys(config.getUserName());
		driver.findElement(By.xpath(password)).sendKeys(config.getPassword());
		logger.log(Status.FAIL, "EnterCredential");
		org.testng.Reporter.log("testing credentails");
		
	}
	

}
