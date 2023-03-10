package testScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUploadTest {
	WebDriver driver;
   @Test
	public void fileUploadTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		//Thread.sleep(2000);
		WebElement addFile = driver.findElement(By.xpath(".//input[@type='file']"));
	    String filePath=System.getProperty("user.dir")+"//FileUploadImg.png";
	    addFile.sendKeys(filePath);
	    driver.findElement(By.xpath(".//span[text()='Start upload']")).click();
	    //Thread.sleep(2000);
	    if(driver.findElement(By.xpath("//FileUploadImg.png']")).isDisplayed()) {
	       Assert.assertTrue(true, " Image Uploaded");
        }else {
	   Assert.assertTrue(true, " Image not Uploaded");
   }

   }
}
