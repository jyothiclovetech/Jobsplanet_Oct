package jobSeeker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PersonalDetails {

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
		
		if(browser.contentEquals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		File file = new File("C:\\Users\\Jotirmayi\\Documents\\personal_details.xls");
		
		FileInputStream inputStream = new FileInputStream(file);

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HSSFSheet sheet = wb.getSheet("js_data");
		  int rowCount = sheet.getLastRowNum() -  sheet.getFirstRowNum();	

		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get(url);
		for (int i = 1; i <= rowCount; i++)
		  { 
			Thread.sleep(2000);
			
			System.out.println("loop count: " + i);

			WebElement UserName = driver.findElement(By.id("loginuser"));
			UserName.clear();
			UserName.sendKeys("jinawi6313832249@yahoo.com");
			//UserName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			System.out.println(" UserName read from excel");

			WebElement Password = driver.findElement(By.id("password"));
			Password.clear();
			Password.sendKeys("Gmail@3832249");			
			//Password.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			System.out.println("Password read from excel");
			
			
			Thread.sleep(1000);
			WebElement NextButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
			NextButton.click();
			System.out.println("Next button clicked");
			
			Thread.sleep(1000);
			
			WebElement NextButton1 = driver.findElement(By.xpath("//span[contains(text(),'Jobseeker')]"));
			NextButton1.click();
			System.out.println("Jobseeker button clicked");
			
			
			Thread.sleep(2000);

			String captureValue = driver.findElement(By.xpath("//*[@class = 'card-header']")).getText();
			System.out.println("captureValue : " + captureValue);

			if (captureValue.trim().equalsIgnoreCase("Personal Details"))

			{	
				WebElement currentAddress = driver.findElement(By.xpath("(//input[@placeholder='Enter a location'])[1]"));
				currentAddress.sendKeys("Delhi");
				Thread.sleep(1000);
				currentAddress.sendKeys(Keys.ARROW_DOWN);
				currentAddress.sendKeys(Keys.ENTER);
												
				System.out.println("currentAddressstate Selected");
				
				Thread.sleep(2000);

				int ziprand = generateRandombetween(10000, 99999);
				String s = Integer.toString(ziprand);
				WebElement ZipCode = driver.findElement(By.xpath("//input[@id='currentAddressZipcode']"));
				ZipCode.clear();
				ZipCode.sendKeys(s);
				System.out.println("ZipCode given");
				Thread.sleep(2000);

				WebElement SameAsCurrentAddress = driver.findElement(By.xpath("//*[@id='mat-checkbox-1']"));
				js.executeScript("arguments[0].scrollIntoView();", SameAsCurrentAddress);
				SameAsCurrentAddress.click();
				System.out.println("SameAsCurrentAddress Selected");
				Thread.sleep(2000);
				WebElement FRESHER2 = driver.findElement(By.xpath("//*[@value='EXPERIENCE']"));
				js.executeScript("arguments[0].scrollIntoView();", FRESHER2);
				FRESHER2.click();
				System.out.println("EXPERIENCE Selected");

				WebElement UploadResume = driver.findElement(By.xpath("//input[@id='resume']"));
				UploadResume.sendKeys("E:\\workspace_Demo\\JobsPlanet\\Profiles\\"	+ sheet.getRow(i).getCell(2).getStringCellValue());
				System.out.println("UploadResume");
				
				Thread.sleep(2000);
				WebElement parentOccupation1 = driver.findElement(By.xpath("(//select[@id='parentOccupation'])[1]"));
				parentOccupation1.click();
				Select parentOccupation = new Select(driver.findElement(By.xpath("(//select[@id='parentOccupation'])[1]")));
				String sr = sheet.getRow(i).getCell(26).getStringCellValue();
				System.out.println(sr);
				parentOccupation.selectByVisibleText(sr);
				parentOccupation1.click();
				System.out.println("parent Occupation Selected");
				
				//26
				
				
				WebElement SaveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
				SaveButton.click();
				System.out.println("SaveButton clicked");
				
				Thread.sleep(2000);
			}
				
				String captureValue1 = driver.findElement(By.xpath("//*[@class = 'card-header']")).getText();
				System.out.println("captureValue : " + captureValue1);

				
				//**********
				if (captureValue.trim().equalsIgnoreCase("EDUCATION DETAILS"))
				
				{
					
					
					
					
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				Thread.sleep(5000);
				WebDriverWait wait = new WebDriverWait(driver, 1000);
				WebElement profileName = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn']")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", profileName);
				WebElement LogoutButton = driver.findElement(By.xpath("*//button[contains(text(),'Logout')]"));
				LogoutButton.click();
				System.out.println("LogoutButton clicked");

			} /*else

			{
				WebDriverWait wait = new WebDriverWait(driver, 1000);

				WebElement profileName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn']")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", profileName);
				WebElement LogoutButton = driver.findElement(By.xpath("/button[contains(text(),'Logout')]"));
				LogoutButton.click();
				System.out.println("LogoutButton clicked in else");
			}*/

		
		  
		// Close the workbook
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

// @SuppressWarnings("unused")
	private static int generateRandom(int range) {
		Random r = new Random();
		int result;
		int high = range;
		result = r.nextInt(high - 1) + 1;
		// System.out.println("range : " + range + " result: " + result);
		/*
		 * if (result == 1) { // result = r.nextInt(high - 1) + 1;
		 * System.out.println("range : " + range + " result: " + result); result=0; }
		 */
		return result;

	}

	private static int generateRandombetween(int range1, int range2) {
		Random r = new Random();
		int high = range2;
		int result = r.nextInt(high - range1) + range1;
		return result;

	}

}
