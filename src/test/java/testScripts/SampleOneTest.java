package testScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

//import org.testng.annotations.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleOneTest {
	
	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	public void readFromProp() throws IOException 
	{
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		driver.manage().timeouts ().implicitlyWait (Duration.ofSeconds (30));
		driver.manage().window().maximize();
		String path = System.getProperty("user.dir")
				+"\\src\\test\\resources\\congigFiles\\configuration.properties";
		prop = new Properties();
		FileInputStream fin = new FileInputStream(path);
		prop.load(fin);
		
	}
	
  @Test //(alwaysRun=true,dependsOnMethods = "seleniumSearchTest")
  public void javaSearchTest() {
	    //Properties prop = new Properties();
		driver.get(prop.getProperty("url"));
	    WebElement searchBox = driver.findElement(By.name("q"));
	    //SoftAssert softAssert = new SoftAssert();
	    //softAssert.assertEquals(driver.getTitle(),"Google Page");
	    searchBox.sendKeys("Java Tutorial");
	    searchBox.submit();
	    Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	    //softAssert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	    
  }
  
  @Test //(priority=1)
  public void seleniumSearchTest() {
	    //Properties prop = new Properties();
		driver.get(prop.getProperty("url"));
	    WebElement searchBox = driver.findElement(By.name("q"));
	    //SoftAssert softAssert = new SoftAssert();
	    //softAssert.assertEquals(driver.getTitle(),"Google Page");
	    searchBox.sendKeys("Selenium Tutorial");
	    searchBox.submit();
	    Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
	    //softAssert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
	    
  }  
//  
// @Test (enabled=false)
//  public void cucumberSearchTest() {
//	  
//		driver.get(prop.getProperty("url"));
//	    WebElement searchBox = driver.findElement(By.name("q"));
//	    //SoftAssert softAssert = new SoftAssert();
//	    //softAssert.assertEquals(driver.getTitle(),"Google Page");
//	    searchBox.sendKeys("Cucumber Tutorial");
//	    searchBox.submit();
//	    Assert.assertEquals(driver.getTitle(), "Cucumber Tutorial - Google Search");
//	    //softAssert.assertEquals(driver.getTitle(), "Cucumber Tutorial - Google Search");
//  
//  }
// 
   
 @Parameters("browser")	
 @BeforeMethod
	public void setup(String strBrowser) {
	 if(strBrowser.equalsIgnoreCase("chrome")) {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();	 
		  
	}else if(strBrowser.equalsIgnoreCase("edge")) {
		  WebDriverManager.edgedriver().setup();
		  driver = new EdgeDriver();
		  
	}
		  driver.manage().window().maximize();
	
 }
    @AfterTest
    public void teardown() {
	    driver.close();
   }
   
}

