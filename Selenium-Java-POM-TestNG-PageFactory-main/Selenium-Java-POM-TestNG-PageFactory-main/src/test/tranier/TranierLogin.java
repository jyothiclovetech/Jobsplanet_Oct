package tranier;

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

public class TranierLogin {

	WebDriver driver;
	String browser = null;
	String url = null;

	@BeforeMethod
	public void setup() throws Exception {

		File file = new File("E:\\workspace_Demo\\JobsPlanet\\config.properties");
		FileInputStream input = new FileInputStream(file);
		Properties prop = new Properties();

		prop.load(input);
		browser = prop.getProperty("browser");
		url = prop.getProperty("url");

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

		
		/*
		 * File file = new
		 * File("C:\\Users\\Jotirmayi\\Documents\\Employerpreregistration.xls");
		 * 
		 * FileInputStream inputStream = new FileInputStream(file);
		 * 
		 * HSSFWorkbook wb = null; try { wb = new HSSFWorkbook(inputStream); } catch
		 * (IOException e) {
		 * 
		 * e.printStackTrace(); }
		 * 
		 * HSSFSheet sheet = wb.getSheet("js_DATA"); int rowCount =
		 * sheet.getLastRowNum() - sheet.getFirstRowNum();
		 */

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

		driver.get(url);

		/*
		 * for (int i = 1; i <= rowCount; i++) {
		 */

		WebElement UserName = driver.findElement(By.id("loginuser"));
		UserName.clear();
		UserName.sendKeys("jinawi6311867763@yahoo.com");
		System.out.println(" UserName read from excel");

		WebElement Password = driver.findElement(By.id("password"));
		Password.clear();
		Password.sendKeys("Gmail@1867763");
		System.out.println("Password read from excel");

		WebElement NextButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
		NextButton.click();
		System.out.println("Next button clicked");

		Thread.sleep(1000);

		WebElement NextButton1 = driver.findElement(By.xpath("//span[contains(text(),'Individual Trainer')]"));
		NextButton1.click();
		System.out.println("Trainer button clicked");
		Thread.sleep(2000);

		

	}

}
