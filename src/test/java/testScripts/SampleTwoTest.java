package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTwoTest {
	
  //@Parameters("browser2")
  @Test//(retryAnalyzer = RetrySampleTest.class)
    public void cypressSearchTest()  {  
	//if(strBrowser.equalsIgnoreCase("edge")) {
//	  System.setProperty("webdriver.chrome.driver", "D:\\webdrivers\\chromedriver.exe");
//	  WebDriverManager.edgedriver().setup();
//	  }
//	  WebDriver driver = new EdgeDriver();
	  WebDriverManager.edgedriver().setup();
	  WebDriver driver = new EdgeDriver();
	  
//	  WebDriverManager.firefoxdriver().setup();
//	  WebDriver driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
	    WebElement searchBox = driver.findElement(By.name("q"));	    
	    searchBox.sendKeys("Cypress Tutorial");
	    searchBox.submit();
	    Assert.assertEquals(driver.getTitle(), "Cypress Tutorial - Google Search");
	    driver.close();
  }
}
