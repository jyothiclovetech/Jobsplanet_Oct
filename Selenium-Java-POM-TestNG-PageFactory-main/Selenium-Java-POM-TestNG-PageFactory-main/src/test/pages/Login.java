package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	
	static WebDriver driver;
	@FindBy(id = "loginuser") 
	WebElement UserName;
	@FindBy(id = "password") 
	WebElement Password;
	@FindBy(xpath="//button[contains(text(),'Login')]") 
	WebElement Login;
	@FindBy(xpath= "//span[contains(text(),'Jobseeker')]") 
	WebElement JobSeeker;
	
	public Login() {
		PageFactory.initElements(driver, JobSeeker);
	}

}
