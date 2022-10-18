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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UserProfile {


	WebDriver driver;
	String browser = null;
	String url = null;
	static Logger log = Logger.getLogger(PersonalDetails.class.getName());


	@BeforeMethod
	public void setup() throws Exception {
		
        System.out.println(System.getProperty("user.dir"));
		File file = new File(System.getProperty("user.dir")+"\\properties\\config.properties");
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

		/*
		 * File file = new
		 * File("C:\\Users\\Jyothirmayee\\Downloads\\personal_details.xls");
		 * 
		 * FileInputStream inputStream = new FileInputStream(file);
		 * 
		 * HSSFWorkbook wb = null; try { wb = new HSSFWorkbook(inputStream); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		//HSSFSheet sheet = wb.getSheet("js_data");
		//int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get(url);
		//for (int i = 1; i <= rowCount; i++) {
			Thread.sleep(2000);
			//System.out.println("loop count: " + i);

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
			
			WebDriverWait wait = new WebDriverWait(driver, 1000);
			WebElement profileName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='avatar-container'])[1]")));
			profileName.click();
			log.info("Profile Name clicked");
			Thread.sleep(1000);
			
			WebElement UserProfile = driver.findElement(By.xpath("*//a[contains(text(),'User Profile')]"));
			UserProfile.click();
			log.info("User Profile clicked");
			Thread.sleep(1000);
		
	//**************************OnlineProfile**************************
			
			WebElement OnlineProfile = driver.findElement(By.xpath("*//li[contains(text(),'Online Profile')]"));
			OnlineProfile.click();
			log.info("Online Profile clicked");
			Thread.sleep(1000);
			
			WebElement AddOnlineProfile = driver.findElement(By.xpath("//button[normalize-space()='+ Add Online Profile']"));
			AddOnlineProfile.click();
			log.info("Add Online Profile clicked");
			Thread.sleep(1000);
			
			WebElement OnlineName = driver.findElement(By.id("profileName"));
			OnlineName.clear();
			OnlineName.sendKeys("wirda");
			log.info("profileName entered");
			Thread.sleep(1000);

			WebElement OnlineUrl = driver.findElement(By.id("onUrl"));
			OnlineUrl.clear();
			OnlineUrl.sendKeys("www.wirda.com");
			log.info("Online Url entered");
			Thread.sleep(1000);
			
			WebElement OnlineProfileDescription = driver.findElement(By.id("feedback"));
			OnlineProfileDescription.clear();
			OnlineProfileDescription.sendKeys("wirda OnlineProfile Description");	
			log.info("Online Profile Description entered");
			Thread.sleep(1000);
			
			WebElement OnlineProfileSave = driver.findElement(By.xpath("//div[@id='onlineDetailsModal']//button[@class='submit-button'][normalize-space()='Save']"));
			OnlineProfileSave.click();
			log.info("Online Profile Save clicked");
			Thread.sleep(1000);
			//WebElement OnlineProfileCancle = driver.findElement(By.xpath("//div[@id='onlineDetailsModal']//button[@class='cancel-button'][normalize-space()='Cancel']"));
			//OnlineProfileCancle.click();
//			log.info("Online Profile cancle clicked");
//			Thread.sleep(1000);
			
				
			//**************************AttachResume**************************	
			
			WebElement AttachResume = driver.findElement(By.xpath("*//li[contains(text(),'Attach Resume')]"));
			AttachResume.click();
			log.info("Attach Resume clicked");	
			Thread.sleep(1000);
			
			//**************************ResumeHeadLine**************************	
			WebElement ResumeHeadLine = driver.findElement(By.xpath("*//li[contains(text(),'Resume HeadLine')]"));
			ResumeHeadLine.click();
			log.info("Resume HeadLine clicked");
			Thread.sleep(1000);
			
			
			//**************************Projects**************************	
			WebElement Projects = driver.findElement(By.xpath("*//li[contains(text(),'Projects')]"));
			Projects.click();
			log.info("Projects clicked");
			Thread.sleep(1000);
			
			//**************************ProfileSummary**************************	
			WebElement ProfileSummary = driver.findElement(By.xpath("*//li[contains(text(),'Profile Summary')]"));
			ProfileSummary.click();
			log.info("Profile Summary clicked");
			Thread.sleep(1000);
			
			//**************************PersonalDetails**************************	
			WebElement PersonalDetails = driver.findElement(By.xpath("*//li[contains(text(),'Personal Details')]"));
			PersonalDetails.click();
			log.info("Personal Details clicked");
			Thread.sleep(1000);
			//**************************EducationDetails**************************	
			WebElement EducationDetails = driver.findElement(By.xpath("*//li[contains(text(),'Education Details')]"));
			EducationDetails.click();
			log.info("Education Details clicked");
			Thread.sleep(1000);
			//**************************Experience**************************	
			WebElement Experience = driver.findElement(By.xpath("*//li[contains(text(),'Experience')]"));
			Experience.click();
			log.info("Experience clicked");
			Thread.sleep(1000);
			//**************************Skills**************************	
			WebElement Skills = driver.findElement(By.xpath("*//li[contains(text(),'Skills')]"));
			Skills.click();
			log.info("Skills clicked");
			Thread.sleep(1000);
			//**************************Certifications**************************	
			WebElement Certifications = driver.findElement(By.xpath("*//li[contains(text(),'Certifications')]"));
			Certifications.click();
			log.info("Certifications clicked");
			Thread.sleep(1000);
			//**************************TrainingsTaken**************************	
			WebElement TrainingsTaken = driver.findElement(By.xpath("*//li[contains(text(),'Trainings Taken')]"));
			TrainingsTaken.click();
			log.info("Trainings Taken clicked");
			Thread.sleep(1000);
			//**************************PreferredJobs**************************	
			WebElement PreferredJobs = driver.findElement(By.xpath("*//li[contains(text(),'Preferred Jobs')]"));
			PreferredJobs.click();
			log.info("Preferred Jobs clicked");
			Thread.sleep(1000);
			//**************************HelpNeeded**************************	
			WebElement HelpNeeded = driver.findElement(By.xpath("*//li[contains(text(),'Help Needed')]"));
			HelpNeeded.click();
			log.info("Help Needed clicked");
			Thread.sleep(1000);
			//**************************RecentJobInterviews**************************	
			WebElement RecentJobInterviews = driver.findElement(By.xpath("*//li[contains(text(),'Recent Job Interviews')]"));
			RecentJobInterviews.click();
			log.info("Recent Job Interviews clicked");
			Thread.sleep(1000);
			//**************************TechnicalPublications**************************	
			WebElement TechnicalPublications = driver.findElement(By.xpath("*//li[contains(text(),'Technical Publications')]"));
			TechnicalPublications.click();
			log.info("Technical Publications clicked");
			Thread.sleep(1000);
			//**************************WorkSamples**************************	
			WebElement WorkSamples = driver.findElement(By.xpath("*//li[contains(text(),'Work Samples')]"));
			WorkSamples.click();
			log.info("Work Samples clicked");
			Thread.sleep(1000);
			//**************************Blogs**************************	
			WebElement Blogs = driver.findElement(By.xpath("*//li[contains(text(),'Blogs')]"));
			Blogs.click();
			log.info("Blogs clicked");
			Thread.sleep(1000);
			//**************************Internships**************************	
			WebElement Internships = driver.findElement(By.xpath("*//li[contains(text(),'Internships')]"));
			Internships.click();
			log.info("Internships clicked");
			Thread.sleep(1000);
			//**************************Goals**************************	
			WebElement Goals = driver.findElement(By.xpath("*//li[contains(text(),'Goals')]"));
			Goals.click();
			log.info("Goals clicked");
			Thread.sleep(1000);
			//**************************Hobbies**************************	
			WebElement Hobbies = driver.findElement(By.xpath("*//li[contains(text(),'Hobbies')]"));
			Hobbies.click();
			log.info("Hobbies clicked");
			Thread.sleep(1000);
			
		
			
			
			
			
		//WebElement LogoutButton = driver.findElement(By.xpath("*//button[contains(text(),'Logout')]"));
		//	LogoutButton.click();
			//log.info("LogoutButton clicked");
			
			
		}
	}

