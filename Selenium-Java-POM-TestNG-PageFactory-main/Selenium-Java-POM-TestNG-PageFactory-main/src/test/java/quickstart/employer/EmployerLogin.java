package quickstart.employer;

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
import lombok.extern.slf4j.Slf4j;

public class EmployerLogin {
	
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
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		
		driver.get(url);
		
		for (int i = 1; i <= rowCount; i++)
		  {
		
		WebElement UserName = driver.findElement(By.id("loginuser"));
		UserName.clear();
		UserName.sendKeys("official@abtechsolutionspvtltd.com");
		log.info(" UserName read from excel");
		log.info("syso-- UserName read from excel");

		WebElement Password = driver.findElement(By.id("password"));
		Password.clear();
		Password.sendKeys("Prutech@321");
		log.info("Password read from excel");
		
		
		
		WebElement NextButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
		NextButton.click();
		log.info("Next button clicked");
		
		Thread.sleep(1000);
		
		WebElement NextButton1 = driver.findElement(By.xpath("//span[contains(text(),'Employer')]"));
		NextButton1.click();
		log.info("Jobseeker button clicked");
		Thread.sleep(2000);
		
		//button[normalize-space()='Create Job']
		
		WebElement CreateJob = driver.findElement(By.xpath("//button[contains(text(),'Create Job')]"));
		CreateJob.click();
		log.info("Create Job button clicked");
		Thread.sleep(1000);
		
		WebElement jobTitle = driver.findElement(By.id("jobTitle"));
		jobTitle.clear();
		jobTitle.sendKeys("Prutech@321");
		log.info("jobTitle read from excel");
		
		WebElement skills = driver.findElement(By.id("skills"));
		skills.clear();
		skills.sendKeys("Prutech@321");
		log.info("skills read from excel");
		
		WebElement qualification = driver.findElement(By.id("qualification"));
		qualification.clear();
		qualification.sendKeys("Prutech@321");
		log.info("qualification read from excel");
		
		WebElement experience = driver.findElement(By.id("experience"));
		experience.clear();
		experience.sendKeys("Prutech@321");
		log.info("experience read from excel");
		
		WebElement expiryDate = driver.findElement(By.id("expiryDate"));
		expiryDate.clear();
		expiryDate.sendKeys("18/05/2022");
		log.info("expiryDate read from excel");
		
		WebElement jobRole = driver.findElement(By.id("jobRole"));
		jobRole.clear();
		jobRole.sendKeys("Prutech@321");
		log.info("jobRole read from excel");
		
		WebElement noticePeriod = driver.findElement(By.id("noticePeriod"));
		noticePeriod.clear();
		noticePeriod.sendKeys("Prutech@321");
		log.info("noticePeriod read from excel");
		
		WebElement contactPhoneNumber = driver.findElement(By.id("contactPhoneNumber"));
		contactPhoneNumber.clear();
		contactPhoneNumber.sendKeys("1234123554");
		log.info("contact PhoneNumber read from excel");
		
		WebElement contactMailId = driver.findElement(By.id("contactMailId"));
		contactMailId.clear();
		contactMailId.sendKeys("Prutech@321");
		log.info("contactMailId read from excel");
		
		WebElement salary = driver.findElement(By.id("salary"));
		salary.clear();
		salary.sendKeys("Prutech@321");
		log.info("salary read from excel");
		
		WebElement jobOpeningNumber = driver.findElement(By.id("jobOpeningNumber"));
		jobOpeningNumber.clear();
		jobOpeningNumber.sendKeys("Prutech@321");
		log.info("jobOpeningNumber read from excel");
		
		  }	

}}
