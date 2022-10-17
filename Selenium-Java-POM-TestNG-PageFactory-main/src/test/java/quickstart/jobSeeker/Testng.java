package quickstart.jobSeeker;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testng {

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
		
		File file = new File("C:\\Users\\Jotirmayi\\Documents\\personal_details.xls");
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "test-output\\chromedriver.exe"); WebDriver driver = new ChromeDriver();
		 */
		FileInputStream inputStream = new FileInputStream(file);

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HSSFSheet sheet = wb.getSheet("js_DATA");

		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		
		driver.get(url);
		
		String currentcountry = "India";

		for (int i = 1760; i < 1832; i++) {
//check 1735
			System.out.println("loop count: " + i);

			WebElement UserName = driver.findElement(By.id("loginuser"));
			UserName.clear();
			UserName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			System.out.println(" UserName read from excel");

			WebElement Password = driver.findElement(By.id("password"));
			Password.clear();
			Password.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			System.out.println("Password read from excel");
			
			
			
			  Thread.sleep(1000); WebElement NextButton =
			  driver.findElement(By.xpath("//button[contains(text(),'Login As')]"));
			  NextButton.click(); System.out.println("Next button clicked");
			 
			
			Thread.sleep(1000);
			
			WebElement NextButton1 = driver.findElement(By.xpath("//span[contains(text(),'Jobseeker')]"));
			NextButton1.click();
			System.out.println("Jobseeker button clicked");
			
			
			Thread.sleep(2000);


			String captureValue = driver.findElement(By.xpath("//*[@class = 'card-header']")).getText();
			System.out.println("captureValue : " + captureValue);

			if (captureValue.trim().equalsIgnoreCase("Personal Details"))

			{
				Select currentAddressCountry = new Select(
						driver.findElement(By.xpath("//select[@id='currentAddressCountry']")));
				currentAddressCountry.selectByVisibleText(currentcountry);
				System.out.println("currentAddressCountry Selected");

				List<WebElement> stateCount = driver.findElements(
						By.xpath("//select[@id='currentAddressState']//option[@class ='ng-star-inserted']"));

				int staterandNumb = generateRandom(stateCount.size());

				WebElement e2 = driver.findElement(By.xpath("//select[@id='currentAddressState']"));
				e2.click();
				Select currentAddressstate = new Select(driver.findElement(By.xpath("//select[@id='currentAddressState']")));

				currentAddressstate.selectByIndex(staterandNumb);
				
				//Chandigarh value = 6					
				if(staterandNumb == 6) {
					currentAddressstate.selectByIndex(7);
				}
				
				System.out.println("currentAddressstate Selected");
				
				

				e2.click();
				Thread.sleep(2000);
		
			}
		
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
