package newUI.corporate;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
	
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 110; i <= 150; i++)
		  { 
			Thread.sleep(2000);
			System.out.println("loop: "+i);
			/*
			 * WebElement Register = driver.findElement(By.linkText("Register Here"));
			 * Register.click();
			 */
		 WebElement Signup =  driver.findElement(By.xpath("//*[contains(text(),'Sign Up')]"));
		  Signup.click();
		  
		  WebElement SignupasCorporate =  driver.findElement(By.xpath("//button[contains(text(),'Signup as Training')]"));
		  SignupasCorporate.click();
		  Thread.sleep(2000);
		
		  Thread.sleep(2000);
			
			
		  WebElement CorporateTraining  =  driver.findElement(By.xpath("//*[contains(text(),'Corporate Training')]"));
		  js.executeScript("arguments[0].click();", CorporateTraining);
	      System.out.println("CorporateTraining Selected");
		  Thread.sleep(2000);
		
			
			
			 WebElement corporateName = driver.findElement(By.xpath("//input[@id='corporateName']"));
			 corporateName.clear();
			 corporateName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			 //LastName.sendKeys("Sai");
			  
			  WebElement officalMailId = driver.findElement(By.id("officalMailId"));
			  officalMailId.clear();
			  officalMailId.sendKeys(sheet.getRow(i).getCell(3).getStringCellValue());
			   
			 // MiddleName.sendKeys("Saim");
			  
			  WebElement corporatePhoneNumber = driver.findElement(By.id("corporatePhoneNumber"));
			  String PhoneNumber =  NumberToTextConverter.toText(sheet.getRow(i).getCell(5).getNumericCellValue() );
			  corporatePhoneNumber.clear();
			  corporatePhoneNumber.sendKeys(PhoneNumber);
			  
			  WebElement correspondentName = driver.findElement(By.id("correspondentName"));
			  correspondentName.clear();
			  //FirstName.sendKeys("Saii");
			  correspondentName.sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
			  
			  
			  
			  WebElement correspondentEmail = driver.findElement(By.id("correspondentEmail"));
			  correspondentEmail.clear();
			//  MailId.sendKeys("Sai3312@gmail.com");
			  correspondentEmail.sendKeys(sheet.getRow(i).getCell(3).getStringCellValue());
			 System.out.println("correspondentEmail" + sheet.getRow(i).getCell(8).getStringCellValue() );
			 
			 			  
			  WebElement correcpondentPhoneNumber = driver.findElement(By.id("correcpondentPhoneNumber"));
			  String PhoneNumber1 =  NumberToTextConverter.toText(sheet.getRow(i).getCell(5).getNumericCellValue() );
			  correcpondentPhoneNumber.clear();
			  correcpondentPhoneNumber.sendKeys(PhoneNumber1);
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
