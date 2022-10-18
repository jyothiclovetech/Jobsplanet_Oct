package quickstart.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import quickstart.employer.EmployerLogin;

public class ScrapingCourse {

	WebDriver driver;
	String browser = null;
	String url = null ;
	private static final Logger log = LogManager.getLogger(EmployerLogin.class);
	@BeforeMethod
	public void setup() throws Exception {
		
		File file = new File("C:\\Users\\Jyothirmayee\\eclipse-workspace\\Selenium-Java-POM-TestNG-PageFactory-main\\Selenium-Java-POM-TestNG-PageFactory-main\\properties\\config.properties");
		FileInputStream input = new FileInputStream(file);
		Properties prop  = new Properties();
		
		prop.load(input);
		browser = prop.getProperty("browser");
	  //  url = prop.getProperty("url");

			
	}
	
	@Test
	public void login() throws InterruptedException, FileNotFoundException {
		
		if(browser.contentEquals("firefox")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
        File file = new File("C:\\Users\\Jyothirmayee\\Documents\\Employer.xls");
		
		FileInputStream inputStream = new FileInputStream(file);

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		HSSFSheet sheet = wb.getSheet("login");
		  int rowCount = sheet.getLastRowNum() -  sheet.getFirstRowNum();	

		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		driver.get("https://swayam.gov.in/");
		Thread.sleep(1000);
		try {
			
			WebElement CancelPopup = driver.findElement(By.xpath("*//button[@id='closebtn']"));
			CancelPopup.click();
			log.info("Popup available, clicked on cancel");
					}
		
		catch(Exception e) {
			log.info("Popup not available");
			
		}
		
		Thread.sleep(1000);
		
		
		WebElement Register = driver.findElement(By.xpath("//*[contains(text(),'Sign-In / Register')]"));
		
		Register.click();
		
		WebElement Google = driver.findElement(By.xpath("//button[@id='GoogleExchange']"));
		Google.click();
		
		WebElement UserName = driver.findElement(By.id("identifierId"));
		UserName.clear();
		UserName.sendKeys("sunkarajyothirmai9@gmail.com");
		
		WebElement nextbutton = driver.findElement(By.xpath("//span[normalize-space()='Next']"));
		nextbutton.click();
		
		WebElement Pwd = driver.findElement(By.id("loginuser"));
		Pwd.clear();
		Pwd.sendKeys("Admin@1984");
		
	
}
}
