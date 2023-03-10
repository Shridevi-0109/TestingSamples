package testScripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExerciseDhanubeStore {
	WebDriver driver;
	Properties prop;
	
	
	@BeforeTest
	public void setup() throws IOException
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\ConfigFiles\\config.properties";
		prop=new Properties();
		FileInputStream obtained = new FileInputStream(path);
		prop.load(obtained);
		driver.get(prop.getProperty("url"));
		driver.get("https://danube-webshop.herokuapp.com");
	}
	
	
	@Test(dataProvider="Register")
	public void register(String name, String surname, String email, String password, String company) throws InterruptedException, InvalidFormatException, IOException, SAXException, ParserConfigurationException
	{
	
		driver.findElement(By.id(readXMLData("Signup"))).click();
		driver.findElement(By.id(readXMLData("Name"))).sendKeys(name);
		driver.findElement(By.id(readXMLData("Surname"))).sendKeys(surname);
		driver.findElement(By.id(readXMLData("Email"))).sendKeys(email);
		driver.findElement(By.id(readXMLData("Password"))).sendKeys(password);
		driver.findElement(By.id(readXMLData("Company"))).sendKeys(company);
		driver.findElement(By.id(readXMLData("Myself"))).click();
		driver.findElement(By.id(readXMLData("Agreement"))).click();
		driver.findElement(By.id(readXMLData("Policy"))).click();
		driver.findElement(By.xpath(readXMLData("Register"))).click();		
	}
	
	
	@Test
	public void search() throws InterruptedException, IOException, InvalidFormatException {
		driver.findElement(By.name(readXLData("search"))).sendKeys(prop.getProperty("search"));
		driver.findElement(By.className(readXLData("searchbtn"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(readXLData("addtocart"))).click();
		driver.findElement(By.xpath(readXLData("checkout"))).click();
		Thread.sleep(3000);
		driver.findElement(By.id(readXLData("name"))).sendKeys(prop.getProperty("name"));
		driver.findElement(By.id(readXLData("surname"))).sendKeys(prop.getProperty("surname"));
		driver.findElement(By.id(readXLData("address"))).sendKeys(prop.getProperty("address"));
		driver.findElement(By.id(readXLData("zipcode"))).sendKeys(prop.getProperty("zipcode"));
		driver.findElement(By.id(readXLData("city"))).sendKeys(prop.getProperty("city"));
		driver.findElement(By.id(readXLData("company"))).sendKeys(prop.getProperty("company"));
		driver.findElement(By.id(readXLData("single"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(readXLData("buy"))).click();
		Thread.sleep(2000);
	}
	
	//Read from XML
	public String readXMLData(String tagname) throws SAXException, IOException, ParserConfigurationException {
		  String path=System.getProperty("user.dir")+"//src//test//resources//testData//danube.xml";
		  File file= new File(path);
		  DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
		  DocumentBuilder build=factory.newDocumentBuilder();
		  Document document= build.parse(file);
		  NodeList List= document.getElementsByTagName("Danube");
		  Node node1=List.item(0);
		  Element elem=(Element)node1;
		  return elem.getElementsByTagName(tagname).item(0).getTextContent();
	  
	  }
	
	//Read from XL
		public String readXLData(String objName) throws InvalidFormatException, IOException {
			  String objPath="";
			  String path=System.getProperty("user.dir")+"//src//test//resources//testData//danubeData.xlsx";
			  XSSFWorkbook workbook= new XSSFWorkbook(new File(path));
			  XSSFSheet sheet=workbook.getSheet("sheet1");
			  int numRows=sheet.getLastRowNum();
			  for(int i=0; i<=numRows; i++)
			  {
				  XSSFRow row=sheet.getRow(i);
				  if(row.getCell(0).getStringCellValue().equalsIgnoreCase(objName))
					  objPath=row.getCell(1).getStringCellValue();
			  }
			  return objPath;
		}
	 
	 @DataProvider(name="Register")
	  public Object[][] getData() throws CsvValidationException, IOException{
		  String path=System.getProperty("user.dir")+"//src//test//resources//testData//danube.csv";
		  String[] cols;
		  CSVReader reader = new CSVReader(new FileReader(path));
		  ArrayList<Object> dataList=new ArrayList<Object>();
		  while((cols=reader.readNext())!=null)
		  {
			  Object[] record= {cols[0], cols[1], cols[2], cols[3], cols[4]};
			  dataList.add(record);
		  }
		  return dataList.toArray(new Object[dataList.size()][]);
		  
	  }
	 
}
