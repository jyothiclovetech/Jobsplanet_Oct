package quickstart.platformUser;

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

public class PlatformUserLogin {

	
	WebDriver driver;
	String browser = null;
	String url = null ;
	
	@BeforeMethod
	public void setup() throws Exception {
		
		File file = new File(".\\properties\\config.properties");
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
		
		//for(int i = 0; )
		
		WebElement TotalRecords = driver.findElement(By.xpath("//div[@class='total-records ng-star-inserted']"));
		String typeValue= TotalRecords.getText();
		System.out.println("Value of type attribute: "+typeValue);
		
		
		//get number from string value
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
		}
		
		  }}
	
//}
