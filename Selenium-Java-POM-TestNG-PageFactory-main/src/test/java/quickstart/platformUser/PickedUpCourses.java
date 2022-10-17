package quickstart.platformUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PickedUpCourses {
	WebDriver driver;
	String browser = null;
	String url = null ;
	
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
		
		/*for (int i = 1; i <= rowCount; i++)
		  {*/
	
					
			
		WebElement UserName = driver.findElement(By.id("loginuser"));
		UserName.clear();
		UserName.sendKeys("platformuser@mailinator.com");
		System.out.println(" UserName read from excel");

		WebElement Password = driver.findElement(By.id("password"));
		Password.clear();
		Password.sendKeys("hvcDSc@1");
		System.out.println("Password read from excel");
						
		WebElement NextButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
		NextButton.click();
		System.out.println("Next button clicked");
		
		Thread.sleep(1000);
		
		WebElement NextButton1 = driver.findElement(By.xpath("//span[contains(text(),'Employee')]"));
		NextButton1.click();
		System.out.println("Employee button clicked");
		Thread.sleep(2000);
		
		WebElement PickedUpCoursesTab = driver.findElement(By.xpath("//div[contains(text(),'Picked Up Courses')]"));
		PickedUpCoursesTab.click();
		System.out.println("PickedUpCoursesTab clicked");
		Thread.sleep(2000);
		
		WebElement TotalRecords = driver.findElement(By.xpath("//div[@class='total-records ng-star-inserted']"));
		String typeValue= TotalRecords.getText();
		System.out.println("Value of type attribute: "+typeValue);
		
		String numString = "";

		for (int i = 0; i < typeValue.length(); i++) {
			int ascii = typeValue.charAt(i);

			// ascii value of 0 is 48 and of 9 is 57
			if (ascii >= 48 && ascii <= (57)) {
				numString += (char) ascii;
			}
		}
		// Convert number in string form to
		//an integer value
		int rowNnumbers = Integer.parseInt(numString);

		System.out.println(rowNnumbers);
		
		for (int k = 0; k < rowNnumbers; k++) {
			//div[@class='total-records ng-star-inserted']
			
			WebElement pickUp = driver.findElement(By.xpath("(//i[@aria-hidden='true'])[1]"));
			pickUp.click();
			Thread.sleep(1000);
			
			WebElement ApproveButton = driver.findElement(By.xpath("//button[contains(text(),'Approve')]"));
			ApproveButton.click();
			System.out.println("Approve Button clicked");
			Thread.sleep(2000);
			 
			WebElement Comments = driver.findElement(By.xpath("(//textarea[@placeholder='Comments....'])[2]"));
			Comments.clear();
			Comments.sendKeys("Approve");
			Thread.sleep(2000);
			
			WebElement CommentsSaveButton = driver.findElement(By.xpath("(//button[@type='button'][normalize-space()='Save'])[2]"));
			CommentsSaveButton.click();
			System.out.println("CommentsSaveButton clicked");
			Thread.sleep(2000);
			
			WebElement PickedUpCoursesTab1 = driver.findElement(By.xpath("//div[contains(text(),'Picked Up Courses')]"));
			PickedUpCoursesTab1.click();
			System.out.println("PickedUpCoursesTab clicked");
			Thread.sleep(3000);
			
		}
		
		
		
		
		
	}
}
