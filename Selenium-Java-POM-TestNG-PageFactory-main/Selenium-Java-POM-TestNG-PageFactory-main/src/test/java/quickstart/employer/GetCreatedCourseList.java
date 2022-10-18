package quickstart.employer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



//get Created course list
public class GetCreatedCourseList {
	
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
	public void login() throws InterruptedException, FileNotFoundException {

		if (browser.contentEquals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;

		File file = new File("C:\\Users\\Jyothirmayee\\Documents\\Individual Trainer Automation Data.xls");

		FileInputStream inputStream = new FileInputStream(file);

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {

			e.printStackTrace();
		}

		HSSFSheet sheet = wb.getSheet("Course");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get(url);
		

		WebElement UserName = driver.findElement(By.id("loginuser"));
		UserName.clear();
		UserName.sendKeys("mutia7216347992@yahoo.com");
		System.out.println(" UserName read from excel");
		
		WebElement Password = driver.findElement(By.id("password"));
		Password.clear();
		Password.sendKeys("Gmail@6347992");
		System.out.println("Password read from excel");
		Thread.sleep(1000);
		WebElement NextButton = driver.findElement(By.xpath("//button[contains(text(),'Login As')]"));
		Thread.sleep(2000);
		NextButton.click();
		System.out.println("Next button clicked");

		Thread.sleep(3000);

		WebElement NextButton1 = driver.findElement(By.xpath("//span[contains(text(),'Individual Trainer')]"));
		Thread.sleep(2000);
		NextButton1.click();
		System.out.println("Trainer button clicked");
		Thread.sleep(2000);
		
		 List<WebElement> choice = driver.findElements(By.xpath("//h4[@class='title word-align description-crap']"));
		 System.out.println(choice.size());
		 for(WebElement e : choice){
			 
	         System.out.println(e.getText());
	           for(int i = 1 ; i <= choice.size(); i++) {
	            	// System.out.println(i);
	        	   
	        	   String courseMode1 = "(*//*[@class='d-flex']//span[@class='course-mode'])["+i+"]";
	        	   System.out.println(courseMode1);
	            WebElement courseMode = driver.findElement(By.xpath(courseMode1));
	            System.out.println(courseMode.getText());
	            WebElement courselevel = driver.findElement(By.xpath("//*[@class='course-level company-name-label']"));
	            System.out.println(courselevel.getText());
	            WebElement reviewStatus = driver.findElement(By.xpath("//*[@class='align-self-end ng-star-inserted']"));
	            System.out.println(reviewStatus.getText());
	            Thread.sleep(1000);
	            
	            }
	        }
		
	}

}
