package quickstart.employer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.reports.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import quickstart.Pages.constants;

public class EmployerProfile {

	WebDriver driver;
	String browser = null;
	String url = null;
	
	ExtentReports reports;
	ExtentTest test;
			
	

	@BeforeMethod
	public void setup() throws Exception {

		File file = new File(".\\properties\\config.properties");
		FileInputStream input = new FileInputStream(file);
		Properties prop = new Properties();

		prop.load(input);
		browser = prop.getProperty("browser");
		url = prop.getProperty("url");
		
		reports = ExtentManager.getReports(constants.REPORTS_PATH);
	    test = reports.createTest("Login Test");

	}
	
	@AfterMethod
	
	public void quit(){
		reports.flush();
		
	}
	
	

	@Test
	public void login() throws InterruptedException, FileNotFoundException {

		if (browser.contentEquals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		

		File file = new File(".\\data\\CREATE JOBS_EXCEL SHEET.xls");

		FileInputStream inputStream = new FileInputStream(file);

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {

			e.printStackTrace();
		}

		HSSFSheet sheet = wb.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		test.log(Status.INFO, "Create Job");

		driver.get(url);
		test.log(Status.INFO, "Open website");

		
			Thread.sleep(2000);

			WebElement UserName = driver.findElement(By.id("loginuser"));
			UserName.clear();
			UserName.sendKeys("official@bimbobimbotech.com");
			test.log(Status.INFO, "UserName entered");
			//System.out.println(" UserName read from excel");

			WebElement Password = driver.findElement(By.id("password"));
			Password.clear();
			Password.sendKeys("Prutech@321");
			test.log(Status.INFO, "Password entered");
			Thread.sleep(1000);
			//System.out.println("Password read from excel");

			WebElement LoginASButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
			Thread.sleep(1000);
			try {
				LoginASButton.click();
				test.log(Status.INFO, "LoginAs button clicked");
			  } catch (Exception e) {
			     JavascriptExecutor executor = (JavascriptExecutor) driver;
			     executor.executeScript("arguments[0].click();", LoginASButton);
			  }

			Thread.sleep(1000);

			WebElement NextButton1 = driver.findElement(By.xpath("//span[contains(text(),'Employer')]"));
			Thread.sleep(1000);
			NextButton1.click();
			test.log(Status.INFO, "Employer Module selected");
			
			WebDriverWait wait = new WebDriverWait(driver, 1000);
			WebElement profileName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='avatar-container'])[1]")));
			profileName.click();
			Thread.sleep(1000);
			
			WebElement UserProfile = driver.findElement(By.xpath("*//a[contains(text(),'User Profile')]"));
			UserProfile.click();
			Thread.sleep(1000);
			
			WebElement UserProfileEdit = driver.findElement(By.xpath("//button[@class='btn btn-link float-right']"));
			UserProfileEdit.click();
			Thread.sleep(1000);
			
			WebElement CompanyOfficialWebsite = driver.findElement(By.id("cmpnyurl"));
			CompanyOfficialWebsite.clear();
			CompanyOfficialWebsite.sendKeys("Prutech@321");
			
			WebElement OrganizationType = driver.findElement(By.id("cmpnyurl"));
			OrganizationType.clear();
			OrganizationType.sendKeys("PUBLIC");
			
			WebElement CompanyOperations = driver.findElement(By.id("CompanyOperations"));
			CompanyOperations.clear();
			CompanyOperations.sendKeys("Product_Base");
			
			WebElement AlternateEmail = driver.findElement(By.id("altremail"));
			AlternateEmail.clear();
			AlternateEmail.sendKeys("Product_Base");
			
			WebElement AlternatePhoneNumber  = driver.findElement(By.id("altrmobile"));
			AlternatePhoneNumber.clear();
			AlternatePhoneNumber.sendKeys("Product_Base");
			
			WebElement CrawlURLForJobs = driver.findElement(By.xpath("//label[@for='no-input']//span[@class='mat-radio-inner-circle']"));
			//js.executeScript("arguments[0].scrollIntoView();", CrawlURLForJobs);
			CrawlURLForJobs.click();
			
			WebElement OrganizationAddress = driver.findElement(By.xpath("//input[@placeholder='Enter a location']"));
			OrganizationAddress.clear();
			OrganizationAddress.sendKeys("Product_Base");
			
			/*
			 * WebElement CompanyOperations =
			 * driver.findElement(By.id("CompanyOperations")); CompanyOperations.clear();
			 * CompanyOperations.sendKeys("Product_Base");
			 * 
			 * WebElement CompanyOperations =
			 * driver.findElement(By.id("CompanyOperations")); CompanyOperations.clear();
			 * CompanyOperations.sendKeys("Product_Base");
			 * 
			 * WebElement CompanyOperations =
			 * driver.findElement(By.id("CompanyOperations")); CompanyOperations.clear();
			 * CompanyOperations.sendKeys("Product_Base");
			 * 
			 * WebElement CompanyOperations =
			 * driver.findElement(By.id("CompanyOperations")); CompanyOperations.clear();
			 * CompanyOperations.sendKeys("Product_Base");
			 */
			
			
			
			

			
	}

}
