package quickstart.Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UdemyLogin {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.get("https://www.udemy.com/?ranMID=39197&ranEAID=0EOJOrTo2D4&ranSiteID=0EOJOrTo2D4-mBwDct3DhZDZI0rR7hWVcg&LSNPUBID=0EOJOrTo2D4&utm_source=aff-campaign&utm_medium=udemyads");
	    driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	    driver.manage().window().maximize();

	    
	    WebElement UserName = driver.findElement(By.id("loginuser"));
		UserName.clear();
		UserName.sendKeys("jm.vallabhaneni@gmail.com");
		

		WebElement Password = driver.findElement(By.id("password"));
		Password.clear();
		Password.sendKeys("Gmail@4408442");
		

		Thread.sleep(1000);
		WebElement LoginButton = driver.findElement(By.xpath("//*[contains(text(),'Login')]"));
		LoginButton.click();
		

		Thread.sleep(1000);
	}

}
