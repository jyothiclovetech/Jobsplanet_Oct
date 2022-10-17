package employer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

public class EmployerLogin {
	
	WebDriver driver;
	String browser = null;
	String url = null ;
	
	@BeforeMethod
	public void setup() throws Exception {
		
		File file = new File("E:\\workspace_Demo\\JobsPlanet\\config.properties");
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
		
        File file = new File("C:\\Users\\Jotirmayi\\Documents\\Employer.xls");
		
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
		System.out.println(" UserName read from excel");

		WebElement Password = driver.findElement(By.id("password"));
		Password.clear();
		Password.sendKeys("Prutech@321");
		System.out.println("Password read from excel");
		
		
		
		WebElement NextButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
		NextButton.click();
		System.out.println("Next button clicked");
		
		Thread.sleep(1000);
		
		WebElement NextButton1 = driver.findElement(By.xpath("//span[contains(text(),'Employer')]"));
		NextButton1.click();
		System.out.println("Jobseeker button clicked");
		Thread.sleep(2000);
		
		//button[normalize-space()='Create Job']
		
		WebElement CreateJob = driver.findElement(By.xpath("//button[contains(text(),'Create Job')]"));
		CreateJob.click();
		System.out.println("Create Job	 button clicked");
		Thread.sleep(1000);
		
		WebElement jobTitle = driver.findElement(By.id("jobTitle"));
		jobTitle.clear();
		jobTitle.sendKeys("Prutech@321");
		System.out.println("jobTitle read from excel");
		
		WebElement skills = driver.findElement(By.id("skills"));
		skills.clear();
		skills.sendKeys("Prutech@321");
		System.out.println("skills read from excel");
		
		WebElement qualification = driver.findElement(By.id("qualification"));
		qualification.clear();
		qualification.sendKeys("Prutech@321");
		System.out.println("qualification read from excel");
		
		WebElement experience = driver.findElement(By.id("experience"));
		experience.clear();
		experience.sendKeys("Prutech@321");
		System.out.println("experience read from excel");
		
		WebElement expiryDate = driver.findElement(By.id("expiryDate"));
		expiryDate.clear();
		expiryDate.sendKeys("18/05/2022");
		System.out.println("expiryDate read from excel");
		
		WebElement jobRole = driver.findElement(By.id("jobRole"));
		jobRole.clear();
		jobRole.sendKeys("Prutech@321");
		System.out.println("jobRole read from excel");
		
		WebElement noticePeriod = driver.findElement(By.id("noticePeriod"));
		noticePeriod.clear();
		noticePeriod.sendKeys("Prutech@321");
		System.out.println("noticePeriod read from excel");
		
		WebElement contactPhoneNumber = driver.findElement(By.id("contactPhoneNumber"));
		contactPhoneNumber.clear();
		contactPhoneNumber.sendKeys("1234123554");
		System.out.println("contact PhoneNumber read from excel");
		
		WebElement contactMailId = driver.findElement(By.id("contactMailId"));
		contactMailId.clear();
		contactMailId.sendKeys("Prutech@321");
		System.out.println("contactMailId read from excel");
		
		WebElement salary = driver.findElement(By.id("salary"));
		salary.clear();
		salary.sendKeys("Prutech@321");
		System.out.println("salary read from excel");
		
		WebElement jobOpeningNumber = driver.findElement(By.id("jobOpeningNumber"));
		jobOpeningNumber.clear();
		jobOpeningNumber.sendKeys("Prutech@321");
		System.out.println("jobOpeningNumber read from excel");
		
		  }	

}}
