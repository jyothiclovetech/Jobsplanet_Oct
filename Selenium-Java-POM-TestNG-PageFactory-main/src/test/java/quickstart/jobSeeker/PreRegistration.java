package quickstart.jobSeeker;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PreRegistration {
	
	
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
		
		File file = new File("C:\\Users\\Jyothirmayee\\Documents\\preregistration.xls");
		
		FileInputStream inputStream = new FileInputStream(file);

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HSSFSheet sheet = wb.getSheet("js_DATA");
		  int rowCount = sheet.getLastRowNum() -  sheet.getFirstRowNum();	

		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(url);
		
		for (int i = 4911; i <= 5000; i++)
		  { 
			Thread.sleep(2000);
			System.out.println("loop: "+i);
			/*
			 * WebElement Register = driver.findElement(By.linkText("Register Here"));
			 * Register.click();
			 */
		 WebElement Signup =  driver.findElement(By.xpath("//*[contains(text(),'Sign Up')]"));
		  Signup.click();
		 
		//div[@class='section-jobseeker']//button[@class='submit-button content-button'][normalize-space()='Click here to Signup']
		  
		 // WebElement SignupasJobSeeker =  driver.findElement(By.xpath("//button[contains(text(),'Signup as JobSeeker')]"));
		  WebElement SignupasJobSeeker =  driver.findElement(By.xpath("//div[@class='section-jobseeker']//button[@class='submit-button content-button'][normalize-space()='Click here to Signup']"));
		  SignupasJobSeeker.click();
		  Thread.sleep(2000);
		
		
		//WebElement Register = driver.findElement(By.linkText("Register Here"));
		 WebElement LastName = driver.findElement(By.xpath("//input[@id='lastName']"));
		 LastName.clear();
		 LastName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
		 //LastName.sendKeys("Sai");
		  
		  WebElement MiddleName = driver.findElement(By.id("middleName"));
		  MiddleName.clear();
		 // MiddleName.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
		 // MiddleName.sendKeys("Saim");
		  
		  WebElement FirstName = driver.findElement(By.id("firstName"));
		  FirstName.clear();
		  //FirstName.sendKeys("Saii");
		 FirstName.sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
		  
		  
		  
		  WebElement MailId = driver.findElement(By.id("mailId"));
		  MailId.clear();
		//  MailId.sendKeys("Sai3312@gmail.com");
		  String id = sheet.getRow(i).getCell(3).getStringCellValue();
		 MailId.sendKeys(id);
		 System.out.println("id: "+id);
		  
		  WebElement MobileNumber = driver.findElement(By.id("phoneNumber"));
		  String mobileNumber =  NumberToTextConverter.toText(sheet.getRow(i).getCell(5).getNumericCellValue() );
		  MobileNumber.clear();
		  MobileNumber.sendKeys(mobileNumber);
		 // MobileNumber.sendKeys("1234561091");
		  Thread.sleep(1000); 
		WebElement NextButton =  driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
		  NextButton.click();
		  
		  WebElement  PopUPsubmitButton = driver.findElement(By.className("submit-button"));
		  PopUPsubmitButton.click();	
		  
		  WebElement cancelButton = driver.findElement(By.className("cancel-button"));
		  cancelButton.click();
		  
		  Thread.sleep(1000); 
		  WebElement NextButton1 =  driver.findElement(By.cssSelector("button.btn.btn-success"));
		  NextButton1.click();
		  Thread.sleep(1500);
		  WebElement enterOtp = driver.findElement(By.id("otpNumber"));
		  
		  enterOtp.clear(); 
		  Thread.sleep(1000);
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
		  String pwd = sheet.getRow(i).getCell(7).getStringCellValue();
		  //confirmpassword.sendKeys("Prutech@123");
		 confirmpassword.sendKeys(sheet.getRow(i).getCell(7).getStringCellValue());
		  System.out.println("pwd: "+pwd);
		  WebElement submitBtn =  driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		  
		  submitBtn.click();
		 		  
		Thread.sleep(2000);
		 // WebElement Register = driver.findElement(By.linkText("Register Here"));  
			/*
			 * Register.click(); Thread.sleep(2000);
			 */
		  }  
			
			
			
		}
		}

//}
