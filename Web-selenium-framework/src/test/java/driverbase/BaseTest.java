package driverbase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
 
public class BaseTest {
    public static ExtentSparkReporter Reporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static WebDriver driver;
    
    private static ConfigFile config = new ConfigFile();
	public ExtentTest logger;
 
	
    @BeforeTest(alwaysRun = true)
public void initializeReport(){

    Reporter =  new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/extentReport.html");
    System.out.println(System.getProperty("user.dir"));
    Reporter.config().setDocumentTitle("Automation Report");
    Reporter.config().setReportName("report");
    Reporter.config().setTheme(Theme.STANDARD);
    extent =new ExtentReports();
 
    extent.attachReporter(Reporter);   
}
    public static String CaptureScreenshot(WebDriver driver) throws IOException {
String FileSeparator = System.getProperty("file.separator");
String Extent_report_path = "."+FileSeparator+"Reports";
String ScreenshotPath = Extent_report_path+FileSeparator+"screenshots";
 
File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
String screenshotName = "screenshot"+Math.random()+".png";
String screenshotpath = ScreenshotPath+FileSeparator+screenshotName;
 
FileUtils.copyFile(src,new File(screenshotpath));
return "."+FileSeparator+"screenshots"+FileSeparator+screenshotName;
 
 
    }
    
    @BeforeMethod(alwaysRun = true)
    public void BrowserSetup() {
    	

    	if (config.getBrowserType().equalsIgnoreCase("chrome")) {
    		WebDriverManager.chromedriver().setup();
    		driver = new ChromeDriver();
    		driver.get(config.getURL());
    		driver.manage().window().maximize();
    	}
    	else if (config.getBrowserType().equalsIgnoreCase("Firefox")) {
    		WebDriverManager.firefoxdriver().setup();
    		driver = new FirefoxDriver();
    		driver.get(config.getURL());
    		driver.manage().window().maximize();
    	}
    	else if (config.getBrowserType().equalsIgnoreCase("edge")) {
    		WebDriverManager.edgedriver().setup();
    		driver = new EdgeDriver();
    		driver.get(config.getURL());
    		driver.manage().window().maximize();
    	}
    	}
    	


    
    @AfterMethod
    public void AfterMethodcheck(ITestResult result, Method method) throws IOException
    {
    	logger= extent.createTest(result.getName());
    	if (result.getStatus()== ITestResult.FAILURE)
    	{
    	 logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable().toString() + " - Test case is failed", ExtentColor.RED));
    	 logger.log(Status.FAIL, MarkupHelper.createLabel(method.getName() + " - Test method is failed", ExtentColor.RED));
    	 
    	 logger.log(Status.FAIL, logger.addScreenCaptureFromPath(CaptureScreenshot(driver)) + "Attached Screenshot");
    	}
    	
    	else if (result.getStatus()== ITestResult.SUCCESS) {
    		logger.log(Status.PASS, MarkupHelper.createLabel(result.getMethod() + " - Test case is passed", ExtentColor.GREEN));
    	
    	}
    	
    	else if (result.getStatus()== ITestResult.SKIP) {
    		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod() + " - Test case is skipped", ExtentColor.ORANGE));
    	
    	}
    	driver.quit();
    	
    }
    
    
	@AfterTest
	public void after()
	{
		extent.flush();
		driver.close();
		driver.quit();
	}
    
    
    
    }
    



