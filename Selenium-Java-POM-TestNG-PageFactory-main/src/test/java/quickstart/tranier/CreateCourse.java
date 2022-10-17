  package quickstart.tranier;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCourse {

	WebDriver driver;
	String browser = null;
	String url = null;

	@BeforeMethod
	public void setup() throws Exception {

		File file = new File(".\\properties\\config.properties");
		FileInputStream input = new FileInputStream(file);
		Properties prop = new Properties();

		prop.load(input);
		browser = prop.getProperty("browser");
		url = prop.getProperty("url");

	}

	@Test
	public void login() throws InterruptedException, FileNotFoundException, Exception {

		if (browser.contentEquals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;

		File file = new File("C:\\Users\\Jyothirmayee\\Documents\\JP data\\trainer\\Individual Trainer Automation Data (21).xls");

		FileInputStream inputStream = new FileInputStream(file);

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {

			e.printStackTrace();
		}

		HSSFSheet sheet = wb.getSheet("Sheet3");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		/*
		 * Robot robot = new Robot(); robot.keyPress(KeyEvent.VK_F12);
		 * robot.keyRelease(KeyEvent.VK_F12);
		 */

		driver.get(url);
			
		WebElement UserName = driver.findElement(By.id("loginuser"));
		UserName.clear();
		UserName.sendKeys("prem4765@gmail.com");
		
		WebElement Password = driver.findElement(By.id("password"));
		Password.clear();
		Password.sendKeys("Prem@123");
		
		Thread.sleep(1000);
		
		/*
		 * individual trainer: mailto:1)vinay@mailinator.com Prutech@123
		 * 
		 * mailto:2)akhil12@gmail.com Prutech@123
		 */

		WebElement LoginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		LoginButton.click();
		

		for (int i =79; i <= 90; i++) {
			
			System.out.println("loop: " +i);

			Thread.sleep(2000);
			WebElement Course = driver.findElement(By.xpath("//*[contains(text(),'Courses')]"));
			Course.click();

			WebElement CreateCourse = driver.findElement(By.xpath("//*[contains(text(),'Create Course')]"));
			CreateCourse.click();
			System.out.println("Create Course button clicked");
			Thread.sleep(2000);

			WebElement Recorded = driver.findElement(By.xpath("//*[contains(text(),'Recorded Lectures')]"));
			js.executeScript("arguments[0].click();", Recorded);
			System.out.println("Recorded Selected");
			Thread.sleep(2000);

			
			// Course Details

			WebElement CourseTitle = driver.findElement(By.xpath("//input[@placeholder='Course Title']"));
			CourseTitle.clear();
			CourseTitle.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			System.out.println("Course Title entered");

			Thread.sleep(2000);
			Select CourseLevel = new Select(driver.findElement(By.xpath("//select[@id='recordLevel']")));
			String level = sheet.getRow(i).getCell(1).getStringCellValue();
			CourseLevel.selectByVisibleText(level);
			System.out.println("CourseLevel Selected");

			Thread.sleep(2000);
			Select Category = new Select(driver.findElement(By.xpath("//select[@id='recordCategory']")));
			String category1 = String.valueOf(sheet.getRow(i).getCell(2).getStringCellValue());
			System.out.println(category1);
			Category.selectByVisibleText(category1.trim());
			System.out.println("recordCategory Selected");

			//Thread.sleep(2000);
			Select SubCategory = new Select(driver.findElement(By.xpath("//select[@id='recordSubCategory']")));
			String subcat = sheet.getRow(i).getCell(3).getStringCellValue();
			Thread.sleep(2000);
			SubCategory.selectByVisibleText(subcat.trim());
			System.out.println("SubCategory Selected");

			Thread.sleep(1000);

			WebElement CourseCompletionAssessment = driver.findElement(By.xpath("*//*[contains(text(),'Course Completion Assessment')]//following::span[@class='mat-radio-label-content'][2]"));
			js.executeScript("arguments[0].click();", CourseCompletionAssessment);
			System.out.println(" Completion Assessment Radio button No Selected");
			Thread.sleep(2000);

			WebElement PrerequisiteAssessment = driver.findElement(By.xpath("*//*[contains(text(),'Prerequisite Assessment ')]//following::span[@class='mat-radio-label-content'][2]"));
			js.executeScript("arguments[0].click();", PrerequisiteAssessment);
			System.out.println("Prerequisite Assessment Radio button No Selected");
			Thread.sleep(2000);

			WebElement Tags = driver.findElement(By.xpath("//input[@placeholder='Skills']"));
			String tag = sheet.getRow(i).getCell(6).getStringCellValue();
			Tags.sendKeys(tag);
			Thread.sleep(1000);
			Tags.sendKeys(Keys.ARROW_DOWN);
			Tags.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			System.out.println("Tags Selected");
			
			WebElement iframe = driver.findElement(By.xpath("(//*[@allowtransparency='true'])[1]"));
			driver.switchTo().frame(iframe);

			Thread.sleep(1000);
			WebElement element = driver.findElement(By.xpath("//*[@id='tinymce']//p"));
			element.sendKeys(sheet.getRow(i).getCell(7).getStringCellValue());
				
		    driver.switchTo().defaultContent();
			Thread.sleep(1000);			 

			WebElement CourseDetailsSaveButton = driver.findElement(By.xpath("//*[@class='submit-button mr-3']"));
			CourseDetailsSaveButton.click();
			System.out.println("CourseDetailsSaveButton button clicked");

			Thread.sleep(2000);

			String section=sheet.getRow(i).getCell(8).getStringCellValue();
			String [] sectionSplit = section.split( "," );
		    int j = sectionSplit.length;
					
			for(int k=1;k<=sectionSplit.length;k++)
			{
				System.out.println("k="+k);
				WebElement sectionTitle = driver.findElement(By.xpath("(//input[@id='sectionTitle'])["+k+"]"));
				sectionTitle.clear();
				sectionTitle.sendKeys(sectionSplit[k-1]);
				System.out.println("section Title");

				WebElement IntermediateAssesment = driver.findElement(By.xpath("(//span[@class='mat-radio-outer-circle'])["+(k+k)+"]"));
				js.executeScript("arguments[0].click();", IntermediateAssesment);
				System.out.println("Intermediate Assesment No Selected");
				Thread.sleep(2000);
				
				WebElement iframe1 = driver.findElement(By.xpath("(//*[@allowtransparency='true'])["+k+"]"));
				driver.switchTo().frame(iframe1);

				WebElement element1 = driver.findElement(By.xpath("//*[@id='tinymce']//p"));
				element1.sendKeys(sheet.getRow(i).getCell(10).getStringCellValue());

				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				System.out.println("Overview entered"); 
					
				
				if(j>k)
				{
					
				WebElement CourseSectionAddSection = driver.findElement(By.xpath("//button[contains(text(),'Add Section')]"));
				CourseSectionAddSection.click();
				System.out.println("AddSection button clicked");
				 }
				   
				System.out.println("------------------");
				
			}
			
						

			WebElement CourseSectionSaveButton = driver.findElement(By.xpath("//button[@class='submit-button ng-star-inserted'][contains(text(),'Save')]"));
			CourseSectionSaveButton.click();
			System.out.println("Course Section Save Button clicked");
			Thread.sleep(2000);
//----------------------------------------------
			WebElement Dashboard = driver.findElement(By.xpath("*//*[contains(text(),'Dashboard')]"));
			Dashboard.click();
			System.out.println("Dashboard clicked");
			Thread.sleep(2000);
			
		}}
}
