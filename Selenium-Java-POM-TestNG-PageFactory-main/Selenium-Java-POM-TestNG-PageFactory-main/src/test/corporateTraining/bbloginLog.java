package log4j_demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class bbloginLog {
	 static Logger log = Logger.getLogger("bbloginLog.class");
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
	BasicConfigurator.configure();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("chrome.switches","--disable-extensions");
	System.setProperty("webdriver.chrome.driver", "E://jyothi//Selenium//chromedriver.exe");
	WebDriver webdriver=  new ChromeDriver(options);
	Logger log = Logger.getLogger("devpinoyLogger");

	//Properties log4jProperties = new Properties();
    PropertyConfigurator.configure("log4j.properties");
   //configure("log4j.properties");
	
	webdriver.manage().window().maximize();
	webdriver.manage().deleteAllCookies();
	webdriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	
	
	webdriver.get("http://bbadmin.factotumsol.com/");
	
	String actualTitle = webdriver.getTitle();
	System.out.println(actualTitle);
	String expectedTitle = "Biryani Bowl";
	if(actualTitle.equalsIgnoreCase(expectedTitle)){
		
		System.out.println("ActualTitle and expectedTitle are equal");
		
	}
	log.debug("Page Title");
	
	//Login 
	
	WebElement Outletname = webdriver.findElement(By.xpath("//div[@id='content']/div/div[2]/div/div[1]/div[1]/div[1]/div/div[1]/form/div/div[2]/div/select"));
	
	if (Outletname.isDisplayed() & Outletname.isEnabled()){
		
		Select select = new Select(Outletname);
		select.selectByValue("2");
				
	}
	else{
		
		System.out.println("given value is not selected value");
	}
	log.debug("Selected BTM");
	
	WebElement username = webdriver.findElement(By.xpath("//input[@placeholder = 'EmployeeID']"));
	username.clear();
	username.sendKeys("BB010068");
	log.debug("Entered username");
	
	
	WebElement password = webdriver.findElement(By.xpath("//input[@placeholder='Password']"));
	password.clear();
	password.sendKeys("BB010068");
	log.debug("Entered password");
		
	WebElement loginbutton = webdriver.findElement(By.xpath("//input[@type= 'submit']"));
	loginbutton.click();
	log.debug("Click on Login");
	
	Thread.sleep(2000);
	WebElement Location = webdriver.findElement(By.xpath("//div[@id='DivDate']//label/b"));
	String Loc = Location.getText();
	System.out.println("Sucessfully login to "+Loc);
	log.debug("Selected location");
	
	String ExpectedLoc = "Atm";
	if(Loc.equalsIgnoreCase(ExpectedLoc)){
	System.out.println("Actual Location is Equal to Expected Location");
	}else{
		System.out.println("Actual Location is not Equal to Expected Location");
	}
}

}
