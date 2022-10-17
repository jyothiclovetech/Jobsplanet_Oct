package quickstart.employer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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

public class CreateJob {

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
			UserName.sendKeys("dav@mailinator.com");
			test.log(Status.INFO, "UserName entered");
			//System.out.println(" UserName read from excel");

			WebElement Password = driver.findElement(By.id("password"));
			Password.clear();
			Password.sendKeys("Vpyay&98");
			test.log(Status.INFO, "Password entered");
			Thread.sleep(1000);
			//System.out.println("Password read from excel");

			WebElement LoginButton = driver.findElement(By.xpath("//button[@type='submit']"));
			LoginButton.click();

			Thread.sleep(1000);
			test.log(Status.INFO, "Employer loggedin");
		
			

			// button[normalize-space()='Create Job']
		//	for (int i = 4; i <= rowCount; i++) {
			for (int i = 1; i <= 4; i++) {
				System.out.println("loop: " + i);
				
				Thread.sleep(2000);

			WebElement CreateJob = driver.findElement(By.xpath("//*[contains(text(),'Create Job')]"));
			CreateJob.click();
			test.log(Status.INFO, "Create Job button clicked");
			//System.out.println("Create Job	 button clicked");
			Thread.sleep(1000);

			WebElement jobTitle = driver.findElement(By.id("jobTitle"));
			jobTitle.clear();
			jobTitle.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			// jobTitle.sendKeys("Prutech@321");
			test.log(Status.INFO, "JobTitle read from excel");
			//System.out.println("jobTitle read from excel");
			Thread.sleep(1000);
			
			Select location = new Select(driver.findElement(By.id("location")));
			String location1 = sheet.getRow(i).getCell(3).getStringCellValue();
			location.selectByVisibleText(location1);
			// jobTitle.sendKeys("Prutech@321");
			test.log(Status.INFO, "location read from excel");
			//System.out.println("location read from excel");
			Thread.sleep(2000);
			
			/*
			 * Select interviewRounds = new Select(driver.findElement(By.xpath("//Span[@class='ng-star-inserted']| //* [@placeholder='Select Interview Rounds']")));
			 *  String interviewRounds1 =
			 * sheet.getRow(i).getCell(3).getStringCellValue();
			 * interviewRounds.selectByVisibleText(interviewRounds1);
			 */
			
			
			WebElement dropdown = driver.findElement(By.xpath("//Span[@class='ng-star-inserted']"));
			// Putting in a loop to select different values every time
			
			// Click on drop down
			dropdown.click();
			
			for (int q = 0; q < 3; q++) {

				

				// Waiting till options in drop down are visible
				WebDriverWait wait = new WebDriverWait(driver, 15);
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='dropdown-list']"))));

				// Getting list of options
				List<WebElement> itemsInDropdown = driver.findElements(By.xpath("//div[@class='dropdown-list']//ul[2]//li"));

				// Getting size of options available
				int size = itemsInDropdown.size();
				System.out.println(size);

				// Generate a random number with in range
				int randnNumber = ThreadLocalRandom.current().nextInt(0, size);

				
				// Selecting random value
				itemsInDropdown.get(randnNumber).click();

				Thread.sleep(2000);
				//break;

			}
			//dropdown.click();
			Thread.sleep(2000);
			WebElement skills = driver.findElement(By.xpath("//input[@placeholder='Search/Add New ']"));
			skills.sendKeys(sheet.getRow(i).getCell(4).getStringCellValue());
			skills.sendKeys(Keys.ENTER);;
			test.log(Status.INFO, "skills read from excel");
			//System.out.println("skills read from excel");
			Thread.sleep(1000);
			

			  WebElement contactMailId = driver.findElement(By.id("contactMailId"));
			  String MailId = String.valueOf(sheet.getRow(i).getCell(17).getStringCellValue());
			  System.out.println(MailId); 
			  contactMailId.sendKeys(MailId);
			  test.log(Status.INFO, "contactMailId read from excel");
			  //System.out.println("contactMailId read from excel"); 
			  Thread.sleep(1000);
			  
			  WebElement contactPhoneNumber = driver.findElement(By.id("contactPhoneNumber"));
				String PhoneNumber = NumberToTextConverter.toText(sheet.getRow(i).getCell(11).getNumericCellValue());
				contactPhoneNumber.sendKeys(PhoneNumber);
				test.log(Status.INFO, "contact PhoneNumber read from excel");
				//System.out.println("contact PhoneNumber read from excel");
				Thread.sleep(1000);

			WebElement qualification = driver.findElement(By.id("qualification"));
			qualification.sendKeys(sheet.getRow(i).getCell(5).getStringCellValue());
			test.log(Status.INFO, "qualification read from excell");
			//System.out.println("qualification read from excel");
			Thread.sleep(1000);

			
			WebElement experienceMin = driver.findElement(By.id("experiencemin"));
			
			experienceMin.sendKeys("0");
			
			WebElement experienceMax = driver.findElement(By.id("experiencemax"));
			String experienceNumber = NumberToTextConverter.toText(sheet.getRow(i).getCell(6).getNumericCellValue());
			experienceMax.sendKeys(experienceNumber);
			test.log(Status.INFO, "experience read from excel");
			//System.out.println("experience read from excel");
			Thread.sleep(1000);

			WebElement expiryDate = driver.findElement(By.id("expiryDate"));
			DataFormatter formatter = new DataFormatter();
			String s = formatter.formatCellValue(sheet.getRow(i).getCell(7));

			System.out.println(s);
			expiryDate.sendKeys(s);
			test.log(Status.INFO, "Job expiryDate read from excel");
			//System.out.println("expiryDate read from excel");
			Thread.sleep(1000);

			WebElement jobRole = driver.findElement(By.id("jobRole"));
			jobRole.clear();
			jobRole.sendKeys(sheet.getRow(i).getCell(8).getStringCellValue());
			test.log(Status.INFO, "jobRole read from excel");
			//System.out.println("jobRole read from excel");
			Thread.sleep(1000);
			
			// jobType dropdown
			Select jobType = new Select(driver.findElement(By.id("jobType")));
			String level = sheet.getRow(i).getCell(2).getStringCellValue();
			jobType.selectByVisibleText(level);
			test.log(Status.INFO, "jobType read from excel");
			Thread.sleep(1000);

			
			WebElement salary = driver.findElement(By.id("salary"));
			int secduration = generateRandombetween(0, 12);
			String s2 = Integer.toString(secduration);
			String sal = String.valueOf(s2);
			System.out.println(sal);
			salary.sendKeys(sal);
			test.log(Status.INFO, "salary read from excel");
			Thread.sleep(1000);
			
			Select shifts = new Select(driver.findElement(By.id("shifts")));
			String shift = sheet.getRow(i).getCell(15).getStringCellValue();
			System.out.println(shift);
			shifts.selectByVisibleText(shift);
			test.log(Status.INFO, "shift selected");
			Thread.sleep(1000);
			
					
			WebElement iframe1 = driver.findElement(By.xpath("//*[starts-with(@id, 'tiny-angular_') and contains(@class,'tox-edit-area__iframe')]"));
			driver.switchTo().frame(iframe1);
			WebElement element1 = driver.findElement(By.xpath("//*[@id='tinymce']//p"));
			String shift1 = sheet.getRow(i).getCell(9).getStringCellValue();
			System.out.println(shift1);
			element1.sendKeys(shift1);
			
			//element1.sendKeys("Seeking a remote hourly employee to join us to support the QA Analysis needs for an ongoing project. This project will last 6 to 12 months and the right candidate can work remote with occasional onsite travel.");
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			
			

			WebElement jobOpeningNumber = driver.findElement(By.id("jobOpeningNumber"));
			String jobOpening = NumberToTextConverter.toText(sheet.getRow(i).getCell(16).getNumericCellValue());
			System.out.println(jobOpening);
			jobOpeningNumber.sendKeys(jobOpening);
			test.log(Status.INFO, "jobOpeningNumber read from excel");
			//System.out.println("jobOpeningNumber read from excel");
			Thread.sleep(1000);
			
			
			WebElement noticePeriod = driver.findElement(By.id("noticePeriod"));
			String notice = NumberToTextConverter.toText(sheet.getRow(i).getCell(10).getNumericCellValue());
			noticePeriod.sendKeys(notice);
			test.log(Status.INFO, "noticePeriod read from excel");
			//System.out.println("noticePeriod read from excel");
			Thread.sleep(1000);		


			WebElement virtual = driver.findElement(By.xpath("//span[contains(text(),'Virtual')]"));
			js.executeScript("arguments[0].click();", virtual);
			test.log(Status.INFO, "virtual Selected");
			//System.out.println("virtual Selected");
			Thread.sleep(2000);
			
			//button[@type='button'][normalize-space()='Save']
			WebElement CreateJobSaveButton = driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Save']"));
			CreateJobSaveButton.click();
			test.log(Status.INFO, "Create Job Save Button clicked");
			//System.out.println("Create Job	 button clicked");
			Thread.sleep(1000);
						
			test.log(Status.PASS, "Job Created Sucessfully");
			
			reports.flush();
			
		}
	}
	
	private static int generateRandombetween(int range1, int range2) {
		Random r = new Random();
		int high = range2;
		int result = r.nextInt(high - range1) + range1;
		return result;

	}


}
