package quickstart.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.util.NumberToTextConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FakerApi {
	WebDriver driver;
	String browser = null;
	String url = null ;
	
	@BeforeMethod
	public void setup() throws Exception {
		
		File file = new File(".\\properties\\config.properties");
		FileInputStream input = new FileInputStream(file);
		Properties prop  = new Properties();
		
		prop.load(input);
		browser = prop.getProperty("browser");
	    url = prop.getProperty("url");
	    

			
	}
	
	@Test
	public void login() throws InterruptedException, FileNotFoundException {
		
		if(browser.contentEquals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		
		driver.get("https://jobsplanet.com/signup");
	    Faker faker = new Faker(new Locale("en-IND"));

		
		 WebElement LastName = driver.findElement(By.xpath("//input[@id='lastName']"));
		 LastName.clear();
		 String lname = faker.name().lastName();

		// LastName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
		 LastName.sendKeys(lname);
		  
		  WebElement MiddleName = driver.findElement(By.id("middleName"));
		  MiddleName.clear();
			 String mname = faker.name().nameWithMiddle();

		 // MiddleName.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
		  MiddleName.sendKeys(mname);
		  
		  WebElement FirstName = driver.findElement(By.id("firstName"));
		  FirstName.clear();
		 String fname = faker.name().firstName();

		  //FirstName.sendKeys("Saii");
		 FirstName.sendKeys(fname);
		  
		  
		  
		  WebElement MailId = driver.findElement(By.id("mailId"));
		  String mailid = faker.internet().emailAddress();

		  MailId.clear();
		//  MailId.sendKeys("Sai3312@gmail.com");
		 // String id = sheet.getRow(i).getCell(3).getStringCellValue();
		 MailId.sendKeys(mailid);
		 //System.out.println("id: "+id);
		  
		  WebElement MobileNumber = driver.findElement(By.id("phoneNumber"));
		  String mobileNumber = faker.phoneNumber().cellPhone();
		  //NumberToTextConverter.toText(sheet.getRow(i).getCell(5).getNumericCellValue() );
		  MobileNumber.clear();
		  MobileNumber.sendKeys(mobileNumber);
		 // MobileNumber.sendKeys("1234561091");
		  
		
		
		
		
	}

}
