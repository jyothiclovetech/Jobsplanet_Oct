package quickstart.jobSeeker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class createGoogle {
	
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	  
	       driver.manage().window().maximize();
	  
	       driver.get("https://accounts.google.com/SignUp");
	  
	       driver.findElement(By.id("firstName")).sendKeys("Selenium");
	       driver.findElement(By.id("lastName")).sendKeys("ByArun");
	       driver.findElement(By.id("username")).sendKeys("SeleniumByArunDemoThree");
	       driver.findElement(By.xpath("//input[@name='Passwd']")).sendKeys("DemoPassword");
	       driver.findElement(By.xpath("//input[@name='ConfirmPasswd']")).sendKeys("DemoPassword");
	       Thread.sleep(1000);
	       driver.findElement(By.xpath("//span[normalize-space()='Next']")).click();
	       driver.findElement(By.xpath("(//input[@id='phoneNumberId'])[1]")).sendKeys("9123455432");
	       driver.findElement(By.xpath("//span[normalize-space()='Next']")).click();
	       
	       
	       driver.findElement(By.cssSelector("div[title='Birthday']")).sendKeys("March");
	       driver.findElement(By.id("BirthDay")).sendKeys("12");
	       driver.findElement(By.id("BirthYear")).sendKeys("1990");
	       driver.findElement(By.cssSelector(" div[title='Gender']")).sendKeys("Male");
	     
	       driver.findElement(By.id("RecoveryEmailAddress")).sendKeys("seleniumtraining11292017@gmail.com");
	       driver.findElement(By.xpath("#CountryCode div[title='Location']")).sendKeys("India");
	  
	 }


}
