package quickstart.jobSeeker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class login {
	WebDriver driver;
	String browser = null;
	String url = null;
	static Logger log = Logger.getLogger(PersonalDetails.class.getName());

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
			UserName.sendKeys("sheri.mullins14408442@yahoo.com");
			// UserName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			log.info(" UserName read from excel");

			WebElement Password = driver.findElement(By.id("password"));
			Password.clear();
			Password.sendKeys("Gmail@4408442");
			// Password.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			log.info("Password read from excel");

			Thread.sleep(1000);
			WebElement LoginButton = driver.findElement(By.xpath("//*[contains(text(),'Login')]"));
			LoginButton.click();
			log.info("Login Button clicked");

			Thread.sleep(1000);
			
			WebElement Jobs = driver.findElement(By.xpath("//*[contains(text(),'Jobs')]"));
			Jobs.click();
			log.info("Jobs dropdown clicked");
}
}}
