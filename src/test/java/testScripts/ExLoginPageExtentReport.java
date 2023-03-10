package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

    public class ExLoginPageExtentReport {
	WebDriver driver;
	
	ExtentReports reports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	
	@BeforeTest
	public void setupExtent() {
		reports = new ExtentReports();
		spark = new ExtentSparkReporter("target\\LoginPageReport.html");
		reports.attachReporter(spark);		
	 }
	
	  @BeforeMethod
	  public void setup() {	
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
	 }
	  
	  @Test(priority=1)
	  public void LoginPageTest() {	
		  extentTest = reports.createTest("Login Page Test");
		  driver.get("http://the-internet.herokuapp.com/login");
		  driver.findElement(By.xpath("//input[@name='username']")).sendKeys("tomsmith");
		  driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		  driver.findElement(By.cssSelector(".radius")).click();		  
      }
	  
	  @Test(priority=2)	  
	  public void DanubeLoginTest() {
		  extentTest = reports.createTest("Danube Login Test");
		  driver.get("https://danube-webshop.herokuapp.com/");
		  driver.findElement(By.id("login")).click();
		  driver.findElement(By.id("n-email")).sendKeys("shri.01@gmail.com");
		  driver.findElement(By.id("n-password2")).sendKeys("Shri.01");
		  driver.findElement(By.id("goto-signin-btn")).click();
		  Assert.assertEquals(driver.getTitle(), "danube-store Page");
	  }
	  
	  @Test(priority=3)	  
	  public void ParaBankLoginTest() {
		  extentTest = reports.createTest("ParaBank Login Test");
		  driver.get("https://parabank.parasoft.com/");
		  driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Shri Athi");
		  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Shri.01");
		  driver.findElement(By.xpath("//input[@class='button']")).click();
	  }
	  
	  @AfterMethod
	  public void teardown(ITestResult result) {
	  if(ITestResult.FAILURE == result.getStatus()) {
	    		extentTest.log(Status.FAIL, result.getThrowable().getMessage());
	    	}
		    driver.close();
	  }
	    
	  @AfterTest
	  public void finishExtent() {
		   reports.flush();
	  }
}
