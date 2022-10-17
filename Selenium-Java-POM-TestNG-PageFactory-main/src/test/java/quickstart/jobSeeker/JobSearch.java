package quickstart.jobSeeker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JobSearch {

	WebDriver driver;
	String browser = null;
	String url = null;
	static Logger log = Logger.getLogger(JobSearch.class.getName());
	String searchWord = "python developer by renja" ;

	@BeforeMethod
	public void setup() throws Exception {

		File file = new File(".\\properties\\config.properties");
		FileInputStream input = new FileInputStream(file);
		Properties prop = new Properties();

		prop.load(input);
		browser = prop.getProperty("browser");
		url = prop.getProperty("url");
		log.info("UserName read from excel : ");

	}

	@Test
	public void login() throws InterruptedException, FileNotFoundException {

		if (browser.contentEquals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Chrome Driver Launched : ");
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		File file = new File("C:\\Users\\Jyothirmayee\\Downloads\\personal_details.xls");

		FileInputStream inputStream = new FileInputStream(file);

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HSSFSheet sheet = wb.getSheet("js_data");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get(url);
		for (int i = 3; i <= rowCount; i++) {
			Thread.sleep(2000);
			System.out.println("loop count: " + i);

			WebElement UserName = driver.findElement(By.id("loginuser"));
			UserName.clear();
			UserName.sendKeys("wirda5416626417@yahoo.com");
			// UserName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			log.info(" UserName read from excel");

			WebElement Password = driver.findElement(By.id("password"));
			Password.clear();
			Password.sendKeys("Admin@4190");
			// Password.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			log.info("Password read from excel");

			Thread.sleep(1000);
			WebElement NextButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
			NextButton.click();
			log.info("Next button clicked");

			Thread.sleep(1000);

			WebElement NextButton1 = driver.findElement(By.xpath("//span[contains(text(),'Jobseeker')]"));
			NextButton1.click();
			log.info("Jobseeker button clicked");

			Thread.sleep(2000);
			
            try {
				
				WebElement CancelPopup = driver.findElement(By.xpath("//mat-dialog-content//button[text()='Cancel']"));
				CancelPopup.click();
				log.info("Popup available, clicked on cancel");
						}
			
			catch(Exception e) {
				log.info("Popup not available");
				
			}
			

            Thread.sleep(2000);
			
			WebElement SearchBar = driver.findElement(By.id("searchSkill"));
			SearchBar.clear();
			SearchBar.sendKeys("python developer by renja");
			log.info("Search text entered in Search Bar");
			
			WebElement SearchButton = driver.findElement(By.xpath("//button[contains(text(),'Search')]"));
			SearchButton.click();
			log.info("Search Button clicked");
			
			String one = "(//span[contains(translate(.,'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'";
			String two = "')][@class='card-role word-align'])[1]\")";
			String xpath = one+searchWord+two;
			System.out.println(xpath);
			
			WebElement JobTitle = driver.findElement(By.xpath(xpath));
			System.out.println(JobTitle.getAttribute("value"));
			
			
			
			
			
			

		}
	}

// @SuppressWarnings("unused")
	private static int generateRandom(int range) {
		Random r = new Random();
		int result;
		int high = range;
		result = r.nextInt(high - 1) + 1;
		// log.info("range : " + range + " result: " + result);
		/*
		 * if (result == 1) { // result = r.nextInt(high - 1) + 1; log.info("range : " +
		 * range + " result: " + result); result=0; }
		 */
		return result;

	}

	private static int generateRandombetween(int range1, int range2) {
		Random r = new Random();
		int high = range2;
		int result = r.nextInt(high - range1) + range1;
		return result;

	}

}
