package tranier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

public class TranierPreRegistration {

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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		File file = new File("C:\\Users\\Jotirmayi\\Documents\\TranierPreregistration.xls");
		
		FileInputStream inputStream = new FileInputStream(file);

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		HSSFSheet sheet = wb.getSheet("js_DATA");
		  int rowCount = sheet.getLastRowNum() -  sheet.getFirstRowNum();	

		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		
		driver.get("http://jobsplanet.co/home");
		
		for (int i = 26; i <= rowCount; i++)
		  { 
			Thread.sleep(2000);
		
		//Register.click();
		 WebElement Signup =  driver.findElement(By.xpath("//button[contains(text(),'Signup')]"));
		  Signup.click();
		  Thread.sleep(2000);
		  WebElement SignupasTraining =  driver.findElement(By.xpath("//button[contains(text(),'Signup as Training')]"));
		  SignupasTraining.click();
		  Thread.sleep(2000);
		  
		
		  
		  WebElement IndividualTrainer  =  driver.findElement(By.xpath("//*[contains(text(),'Individual Trainer')]"));
		  js.executeScript("arguments[0].click();", IndividualTrainer);
	      System.out.println("IndividualTrainer Selected");
		  Thread.sleep(2000);
		 
		  
			
			//WebElement Register = driver.findElement(By.linkText("Register Here"));
			 WebElement LastName = driver.findElement(By.xpath("//input[@id='traingLastName']"));
			 LastName.clear();
			 LastName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			 //LastName.sendKeys("Sai");
			  
			  WebElement MiddleName = driver.findElement(By.id("traingMiddleName"));
			  MiddleName.clear();
			  MiddleName.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			 // MiddleName.sendKeys("Saim");
			  
			  WebElement FirstName = driver.findElement(By.id("traingFirstName"));
			  FirstName.clear();
			  //FirstName.sendKeys("Saii");
			 FirstName.sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
			  
			  
			  
			  WebElement MailId = driver.findElement(By.id("traingMailId"));
			  MailId.clear();
			//  MailId.sendKeys("Sai3312@gmail.com");
			 MailId.sendKeys(sheet.getRow(i).getCell(3).getStringCellValue());
			 System.out.println("traingMailId: " + sheet.getRow(i).getCell(3).getStringCellValue() );
			 
			 
			 Select currentAddressCountry = new Select(driver.findElement(By.xpath("//select[@id='countryCode1']")));
				currentAddressCountry.selectByValue("+91");
				//.selectByVisibleText(currentcountry);
				
				//option[@value='+91']
			  
			  WebElement MobileNumber = driver.findElement(By.id("traingPhoneNumber"));
			  String mobileNumber =  NumberToTextConverter.toText(sheet.getRow(i).getCell(5).getNumericCellValue() );
			  MobileNumber.clear();
			  MobileNumber.sendKeys(mobileNumber);
			 // MobileNumber.sendKeys("1234561091");
			  
			  Thread.sleep(2000);
			  
			WebElement NextButton =  driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
			  NextButton.click();
			  
			  WebElement  PopUPsubmitButton = driver.findElement(By.className("submit-button"));
			  PopUPsubmitButton.click();
			  
			  WebElement enterOtp = driver.findElement(By.id("otpNumber"));
			  enterOtp.clear(); 
				String otp =  NumberToTextConverter.toText(sheet.getRow(i).getCell(6).getNumericCellValue()  );
				enterOtp.sendKeys(otp);
				  
				  WebElement otpNextButton =  driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
				  otpNextButton.click(); //Create Password
				  Thread.sleep(2000);
				  WebElement password = driver.findElement(By.id("password"));
				  password.clear();
				 // password.sendKeys("Prutech@123");
				 password.sendKeys(sheet.getRow(i).getCell(7).getStringCellValue());
				  
				  WebElement confirmpassword = driver.findElement(By.id("confirmpassword"));
				  confirmpassword.clear();
				  //confirmpassword.sendKeys("Prutech@123");
				 confirmpassword.sendKeys(sheet.getRow(i).getCell(7).getStringCellValue());
				 System.out.println("password: " + sheet.getRow(i).getCell(7).getStringCellValue() );

				  
				  WebElement submitBtn =  driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
				  
				  submitBtn.click();
				  
				  Thread.sleep(2000);
				  WebElement Register = driver.findElement(By.linkText("Register Here"));  
				  Register.click();
				  Thread.sleep(2000);
				  
				  
				  
		
	
		  }
	}
}
