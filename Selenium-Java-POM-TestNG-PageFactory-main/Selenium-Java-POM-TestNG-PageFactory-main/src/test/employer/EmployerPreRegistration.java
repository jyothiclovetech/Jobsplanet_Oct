package employer;

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

public class EmployerPreRegistration {
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
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		
		driver.get("http://jobsplanet.co/home");
		
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		File file = new File(".\\data\\Employer.xls");
	    FileInputStream inputStream = new FileInputStream(file);
		  
		  //creating workbook instance that refers to .xls file
		  
		  HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  // creating a Sheet object 
		  HSSFSheet sheet = wb.getSheet("login");
		  
		  // get all rows in the sheet
		  //int rowCount = sheet.getLastRowNum() -  sheet.getFirstRowNum();
		    
	
			  for (int i =  13; i <= 14; i++)
		  { 
		  System.out.println("loop: " + i);
		  
		  WebElement Signup =  driver.findElement(By.xpath("//button[contains(text(),'Signup')]"));
		  Signup.click();
		  
		  WebElement SignupasEmployer =  driver.findElement(By.xpath("//button[contains(text(),'Signup as Employer')]"));
		  SignupasEmployer .click();
		  Thread.sleep(1000);
		  
		  // Identify the WebElements for the js registration form 
		  WebElement CompanyName= driver.findElement(By.id("companyName"));
		  
		  WebElement Officialmailid = driver.findElement(By.id("officialmailid"));
		  
		  WebElement PhoneNumber = driver.findElement(By.id("phoneNumber"));
		  
		  WebElement CorrespondantName = driver.findElement(By.id("correspondantName"));
		  
		  WebElement CorrespondantEmailid = driver.findElement(By.id("correspondantemailid"));
		  WebElement CorrespondantPhoneNumber = driver.findElement(By.id("correspondantPhoneNumber")); 
		  WebElement CheckBox =  driver.findElement(By.xpath("//input[@type='checkbox']"));
		  WebElement NextButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		  
		  CompanyName.clear();
		  CompanyName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
		  
		  Officialmailid.clear();
		  Officialmailid.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
		  
		  String mobileNumber =  NumberToTextConverter.toText(sheet.getRow(i).getCell(2).getNumericCellValue() );
		  PhoneNumber.clear();
		  PhoneNumber.sendKeys(mobileNumber);
		  
		  CorrespondantName.clear();
		  CorrespondantName.sendKeys(sheet.getRow(i).getCell(3).getStringCellValue());
		  
		  CorrespondantEmailid.clear();
		  CorrespondantEmailid.sendKeys(sheet.getRow(i).getCell(4).getStringCellValue());
		  
		  String mobileNumber1 =  NumberToTextConverter.toText(sheet.getRow(i).getCell(5).getNumericCellValue() );
		  CorrespondantPhoneNumber.clear();
		  CorrespondantPhoneNumber.sendKeys(mobileNumber1);
		  
		 // js.executeScript("arguments[0].scrollIntoView();", CheckBox);
		 // CheckBox.click();
		  
		  NextButton.click();
		  
		  WebElement enterOtp =	 driver.findElement(By.id("otpNumber")); 
		  
		  enterOtp.clear();
		  String otp = NumberToTextConverter.toText(sheet.getRow(i).getCell(6).getNumericCellValue());
		  enterOtp.sendKeys(otp);
		  
		  WebElement otpNextButton = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
		  otpNextButton.click(); 
		  
		  
		  WebElement CertificateChooseFile = driver.findElement(By.xpath("//*[@id='test-l-3']/div[1]/input"));
	 		Thread.sleep(2000);	 
	 		CertificateChooseFile.sendKeys(".\\data\\Resume+1.99+MB (1).docx");
		  
	 		 WebElement CertificateSubmitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
	 		CertificateSubmitButton.click(); 
		  
		  WebElement Register = driver.findElement(By.linkText("Register Here"));
			  
		  Register.click();
		  Thread.sleep(1000);  
		
		  

}}
}
