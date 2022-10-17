package employer;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateJob {

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
		JavascriptExecutor js = (JavascriptExecutor) driver;

		File file = new File("C:\\Users\\Jotirmayi\\Documents\\CREATE JOBS_EXCEL SHEET.xls");

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

		driver.get(url);

		for (int i = 1; i <= rowCount; i++) {

			System.out.println("loop: " + i);

			Thread.sleep(2000);

			WebElement UserName = driver.findElement(By.id("loginuser"));
			UserName.clear();
			UserName.sendKeys("official@abtechsolutionspvtltd.com");
			System.out.println(" UserName read from excel");

			WebElement Password = driver.findElement(By.id("password"));
			Password.clear();
			Password.sendKeys("Prutech@321");
			System.out.println("Password read from excel");

			WebElement NextButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
			Thread.sleep(1000);
			NextButton.click();
			System.out.println("Next button clicked");

			Thread.sleep(1000);

			WebElement NextButton1 = driver.findElement(By.xpath("//span[contains(text(),'Employer')]"));
			Thread.sleep(1000);
			NextButton1.click();
			System.out.println("Jobseeker button clicked");
			Thread.sleep(2000);

			// button[normalize-space()='Create Job']

			WebElement CreateJob = driver.findElement(By.xpath("//button[contains(text(),'Create Job')]"));
			CreateJob.click();
			System.out.println("Create Job	 button clicked");
			Thread.sleep(1000);

			WebElement jobTitle = driver.findElement(By.id("jobTitle"));
			jobTitle.clear();
			jobTitle.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			// jobTitle.sendKeys("Prutech@321");
			System.out.println("jobTitle read from excel");
			Thread.sleep(1000);

			// jobType dropdown
			Select jobType = new Select(driver.findElement(By.id("jobType")));
			String level = sheet.getRow(i).getCell(2).getStringCellValue();
			jobType.selectByVisibleText(level);
			// jobTitle.sendKeys("Prutech@321");
			System.out.println("jobType read from excel");
			Thread.sleep(1000);

			Select location = new Select(driver.findElement(By.id("location")));
			String location1 = sheet.getRow(i).getCell(3).getStringCellValue();
			location.selectByVisibleText(location1);
			// jobTitle.sendKeys("Prutech@321");
			System.out.println("location read from excel");
			Thread.sleep(2000);

			WebElement skills = driver.findElement(By.xpath("//input[@placeholder='Skills Used']"));

			skills.sendKeys(sheet.getRow(i).getCell(4).getStringCellValue());
			System.out.println("skills read from excel");
			Thread.sleep(1000);

			WebElement qualification = driver.findElement(By.id("qualification"));
			qualification.sendKeys(sheet.getRow(i).getCell(5).getStringCellValue());
			System.out.println("qualification read from excel");
			Thread.sleep(1000);

			WebElement experience = driver.findElement(By.id("experience"));
			String experienceNumber = NumberToTextConverter.toText(sheet.getRow(i).getCell(6).getNumericCellValue());
			experience.sendKeys(experienceNumber);
			System.out.println("experience read from excel");
			Thread.sleep(1000);

			WebElement expiryDate = driver.findElement(By.id("expiryDate"));
			DataFormatter formatter = new DataFormatter();
			String s = formatter.formatCellValue(sheet.getRow(i).getCell(7));

			System.out.println(s);
			expiryDate.sendKeys(s);
			System.out.println("expiryDate read from excel");
			Thread.sleep(1000);

			WebElement jobRole = driver.findElement(By.id("jobRole"));
			jobRole.clear();
			jobRole.sendKeys(sheet.getRow(i).getCell(8).getStringCellValue());
			System.out.println("jobRole read from excel");
			Thread.sleep(1000);
			
			WebElement salary = driver.findElement(By.id("salary"));
			int secduration = generateRandombetween(0, 12);
			String s2 = Integer.toString(secduration);
			String sal = String.valueOf(s2);
			System.out.println(sal);
			salary.sendKeys(sal);
			System.out.println("salary read from excel");
			Thread.sleep(1000);

			WebElement noticePeriod = driver.findElement(By.id("noticePeriod"));
			String notice = NumberToTextConverter.toText(sheet.getRow(i).getCell(10).getNumericCellValue());
			noticePeriod.sendKeys(notice);
			System.out.println("noticePeriod read from excel");
			Thread.sleep(1000);

			WebElement contactPhoneNumber = driver.findElement(By.id("contactPhoneNumber"));
			String PhoneNumber = NumberToTextConverter.toText(sheet.getRow(i).getCell(11).getNumericCellValue());
			contactPhoneNumber.sendKeys(PhoneNumber);
			System.out.println("contact PhoneNumber read from excel");
			Thread.sleep(1000);

			
			  WebElement contactMailId = driver.findElement(By.id("contactMailId")); String
			  MailId = String.valueOf(sheet.getRow(i).getCell(12).getStringCellValue());
			  System.out.println(MailId); contactMailId.sendKeys(MailId );
			  System.out.println("contactMailId read from excel"); Thread.sleep(1000);
			 

			

			Select shifts = new Select(driver.findElement(By.id("shifts")));
			String shift = sheet.getRow(i).getCell(14).getStringCellValue();
			System.out.println(shift);
			shifts.selectByVisibleText(shift);

			/*
			 * String shifts1 = sheet.getRow(i).getCell(14).getStringCellValue();
			 * shifts.selectByVisibleText(shifts1);
			 */
			// jobTitle.sendKeys("Prutech@321");
			System.out.println("shifts read from excel");
			Thread.sleep(1000);

			WebElement jobOpeningNumber = driver.findElement(By.id("jobOpeningNumber"));
			String jobOpening = NumberToTextConverter.toText(sheet.getRow(i).getCell(15).getNumericCellValue());
			System.out.println(jobOpening);
			jobOpeningNumber.sendKeys(jobOpening);
			System.out.println("jobOpeningNumber read from excel");
			Thread.sleep(1000);

			WebElement virtual = driver.findElement(By.xpath("(//*[contains(text(),'virtual')])[4]"));
			js.executeScript("arguments[0].click();", virtual);
			System.out.println("virtual Selected");
			Thread.sleep(2000);
		}
	}
	
	private static int generateRandombetween(int range1, int range2) {
		Random r = new Random();
		int high = range2;
		int result = r.nextInt(high - range1) + range1;
		return result;

	}


}
