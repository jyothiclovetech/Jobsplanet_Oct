package tranierNew;

	

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;


public class ChangePassword {

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
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			
			File file = new File("C:\\Users\\Jyothirmayee\\Documents\\trainerPreRegistration1.xls");
			
			FileInputStream inputStream = new FileInputStream(file);

			HSSFWorkbook wb = null;
			try {
				wb = new HSSFWorkbook(inputStream);
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			HSSFSheet sheet = wb.getSheet("login");
			  int rowCount = sheet.getLastRowNum() -  sheet.getFirstRowNum();	

			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(400, TimeUnit.SECONDS);
			
			driver.get(url);
			
			for (int i = 19; i <= rowCount; i++)
			  { 
				Thread.sleep(2000);
				WebElement UserName = driver.findElement(By.id("loginuser"));
				UserName.clear();
				UserName.sendKeys(sheet.getRow(i).getCell(3).getStringCellValue());
				
				//System.out.println(" UserName read from excel");

				WebElement Password = driver.findElement(By.id("password"));
				Password.clear();
				Password.sendKeys(sheet.getRow(i).getCell(7).getStringCellValue());
				
				Thread.sleep(1000);
				//System.out.println("Password read from excel");

				WebElement LoginButton = driver.findElement(By.xpath("//button[@type='submit']"));
				LoginButton.click();
				
				WebElement nameclick1 = driver.findElement(By.xpath("//div[@class='avatar-container']"));
				
				js.executeScript("arguments[0].click();", nameclick1);
				/*
				 * js.executeScript("arguments[0].scrollIntoView();", nameclick1); Actions
				 * action1 = new Actions(driver);
				 * action1.moveToElement(nameclick1).click().perform();
				 */
				
				Thread.sleep(2000);
				WebElement changePassword = driver.findElement(By.xpath("//*[contains(text(),'Change Password ')]")); 
				js.executeScript("arguments[0].click();", changePassword );
				
				WebElement currentpassword = driver.findElement(By.id("currentpassword"));
				currentpassword.clear();
				currentpassword.sendKeys(sheet.getRow(i).getCell(7).getStringCellValue());
				
				WebElement newpassword = driver.findElement(By.id("newpassword"));
				newpassword.clear();
				newpassword.sendKeys(sheet.getRow(i).getCell(8).getStringCellValue());
				
				WebElement conformpassword = driver.findElement(By.id("conformpassword"));
				conformpassword.clear();
				conformpassword.sendKeys(sheet.getRow(i).getCell(8).getStringCellValue());
				
				
				WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
				submitButton.click();
				Thread.sleep(2000);
	            WebElement nameclick = driver.findElement(By.xpath("//div[@class='avatar-container']"));
				js.executeScript("arguments[0].click();", nameclick);
				
				Thread.sleep(2000);
				WebElement LogoutButton  = driver.findElement(By.xpath("//*[contains(text(),'Change Password ')]")); 
				js.executeScript("arguments[0].click();",LogoutButton);
								
			  }
		}
}
