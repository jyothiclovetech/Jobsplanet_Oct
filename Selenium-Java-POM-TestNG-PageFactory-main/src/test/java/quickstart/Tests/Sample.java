package quickstart.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Sample {

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
	public void login() throws InterruptedException, FileNotFoundException, Exception {

		if (browser.contentEquals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		File file = new File("C:\\Users\\Jyothirmayee\\Documents\\Individual Trainer Automation Data11.xls");

		FileInputStream inputStream = new FileInputStream(file);

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {

			e.printStackTrace();
		}

		HSSFSheet sheet = wb.getSheet("Course");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		for (int i = 1; i <= 1; i++) {

			System.out.println("loop: " + i);

			String section = sheet.getRow(i).getCell(9).getStringCellValue();
			String[] sectionSplit = section.split(",");
			 int d = sectionSplit.length;
			 System.out.println("section-----"+d);
			for (int j = 0; j < sectionSplit.length; j++) {
				
				System.out.println("section-----"+sectionSplit[j]);
				
				String content = sheet.getRow(i).getCell(12).getStringCellValue();
				String[] contentSplit = content.split("::");

				//for (int z = 0; z <=contentSplit.length-1;z++) {

					System.out.println("content-----");
					System.out.println(contentSplit[j]);

						System.out.println("------------------------------------");
						
						int h = contentSplit.length;

						System.out.println("h:" + h);
						
						for(int x = 0; x<h;x++) {

							String sectioncontent = contentSplit[x];
							String[] sectioncontentSplit = sectioncontent.split(",");
							int a = sectioncontentSplit.length;
							System.out.println("sectioncontent:a" +a);
							System.out.println("sectioncontent----");
							for (int y = 0; y < a; y++) {

								System.out.println(sectioncontentSplit[y]);
								

							}
					}
				//}
		}
			}

	}
	
}
/*//
	
	 * 
	 * 
	 * 
	 * 
	 * 
	 * String content=sheet.getRow(i).getCell(12).getStringCellValue(); String []
	 * contentSplit = content.split( "_" ); int h = contentSplit.length;
	 * 
	 * 
	 * // System.out.println(contentSplit);
	 * 
	 * 
	 * for(int x = 0;x<h;x++) {
	 * 
	 * String sectioncontent= contentSplit[x]; String [] sectioncontentSplit =
	 * sectioncontent.split( "," ); int y = sectioncontentSplit.length;
	 * 
	 * 
	 * WebElement e2 = driver.findElement(By.xpath("//select[@id='section']"));
	 * e2.click(); Select currentSection = new
	 * Select(driver.findElement(By.xpath("//select[@id='section']")));
	 * Thread.sleep(1000); currentSection.selectByVisibleText(sectionSplit[x+1]);
	 * 
	 * 
	 * WebElement iframe3 =
	 * driver.findElement(By.xpath("(//*[@allowtransparency='true'])[1]"));
	 * driver.switchTo().frame(iframe3);
	 * 
	 * Thread.sleep(1000); WebElement ContentTitle =
	 * driver.findElement(By.xpath("//*[@id='tinymce']//p"));
	 * ContentTitle.sendKeys(sectioncontentSplit[1]);
	 * 
	 * driver.switchTo().defaultContent(); Thread.sleep(1000);
	 * 
	 * 
	 * WebElement OrderNumber =
	 * driver.findElement(By.xpath("//input[@placeholder='Order Number']"));
	 * OrderNumber.clear(); OrderNumber.sendKeys(sectioncontentSplit[0]);
	 * Thread.sleep(1000);
	 * 
	 * 
	 * WebElement VideoEmbeddedLink =
	 * driver.findElement(By.xpath("(//i[@class='mce-ico mce-i-media'])[1]"));
	 * VideoEmbeddedLink.click(); Thread.sleep(1000); WebElement Embed =
	 * driver.findElement(By.
	 * xpath("(//*[starts-with(@id, 'mceu_') and contains(@class,'mce-combobox mce-filepicker mce-abs-layout-item mce-last')])[1]"
	 * )); Embed.sendKeys(sectioncontentSplit[2]);
	 * 
	 * Thread.sleep(1000); WebElement videoSave =
	 * driver.findElement(By.xpath("//span[normalize-space()='Ok']"));
	 * videoSave.click(); //-------------references---- WebElement iframe4 =
	 * driver.findElement(By.xpath("(//*[@allowtransparency='true'])[3]"));
	 * driver.switchTo().frame(iframe4);
	 * 
	 * Thread.sleep(1000); WebElement references =
	 * driver.findElement(By.xpath("//*[@id='tinymce']//p"));
	 * references.sendKeys(sectioncontentSplit[3]);
	 * 
	 * driver.switchTo().defaultContent(); Thread.sleep(1000);
	 * //-------------description---- WebElement iframe5 =
	 * driver.findElement(By.xpath("(//*[@allowtransparency='true'])[4]"));
	 * driver.switchTo().frame(iframe5);
	 * 
	 * Thread.sleep(1000); WebElement description =
	 * driver.findElement(By.xpath("//*[@id='tinymce']//p"));
	 * description.sendKeys(sectioncontentSplit[4]);
	 * 
	 * driver.switchTo().defaultContent(); Thread.sleep(1000);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * WebElement iframe3 =
	 * driver.findElement(By.xpath("(//*[@allowtransparency='true'])[1]"));
	 * driver.switchTo().frame(iframe3);
	 * 
	 * Thread.sleep(1000); WebElement ContentTitle =
	 * driver.findElement(By.xpath("//*[@id='tinymce']//p"));
	 * ContentTitle.sendKeys("Content title");
	 * 
	 * driver.switchTo().defaultContent(); Thread.sleep(1000);
	 * 
	 * 
	 * WebElement OrderNumber =
	 * driver.findElement(By.xpath("//input[@placeholder='Order Number']"));
	 * OrderNumber.clear(); // //
	 * CourseTitle.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
	 * OrderNumber.sendKeys("5"); Thread.sleep(1000);
	 * 
	 * 
	 * WebElement VideoEmbeddedLink =
	 * driver.findElement(By.xpath("//button[@title='Insert/edit media']"));
	 * VideoEmbeddedLink.click(); Thread.sleep(1000); WebElement Embed =
	 * driver.findElement(By.
	 * xpath("(//*[starts-with(@id, 'form-field_') and contains(@class,'tox-textfield')])[1]"
	 * )); Embed.sendKeys(
	 * "https://www.youtube.com/watch?v=PLLbGltmkpE&list=PLUDwpEzHYYLs6G9WOfFxiH5zMfgNxgmSV&index=1"
	 * ); WebElement Width = driver.findElement(By.
	 * xpath("(//*[starts-with(@id, 'form-field_') and contains(@class,'tox-textfield')])[2]"
	 * )); Width.sendKeys("4"); WebElement Height = driver.findElement(By.
	 * xpath("(//*[starts-with(@id, 'form-field_') and contains(@class,'tox-textfield')])[3]"
	 * )); Height.sendKeys("4");
	 * 
	 * Thread.sleep(1000); WebElement videoSave =
	 * driver.findElement(By.xpath("//button[@title='Save']")); videoSave.click();
	 * 
	 * Thread.sleep(1000); WebElement iframe5 = driver.findElement(By.
	 * xpath("(//*[starts-with(@id, 'tiny-angular_') and contains(@class,'tox-edit-area__iframe')])[3]"
	 * )); driver.switchTo().frame(iframe5); Thread.sleep(1000); WebElement
	 * References = driver.findElement(By.xpath("//*[@id='tinymce']//p"));
	 * js.executeScript("arguments[0].innerHTML = '<p>Hello world!</p>'",
	 * References); driver.switchTo().defaultContent(); Thread.sleep(1000);
	 * 
	 * // WebElement CloseBtn = // driver.findElement(By.xpath("
	 //button[@class='tox-notification__dismiss
// tox-button tox-button--naked tox-button--icon']"));

	*//**
	 * if(CloseBtn!=null){CloseBtn.click();}
	 * 
	 * Thread.sleep(1000);
	 * 
	 * WebElement iframe6 = driver.findElement(By.xpath( "(//*[starts-with(@id,
	 * 'tiny-angular_') and
	 * contains(@class,'tox-edit-area__iframe')])[4]"));driver.switchTo().frame(iframe6);Thread.sleep(1000);
	 * WebElement Description = driver.findElement(By.xpath(
	 * "//*[@id='tinymce']//p"));js.executeScript("arguments[0].innerHTML = '
	 * <p>
	 * Hello world!
	 * </p>
	 * '",Description);driver.switchTo().defaultContent();Thread.sleep(1000);
	 *
	 * 
	 * WebElement UploadDocument = driver.findElement(By.xpath("//*[starts-with(@id,
	 * 'dynamicFile-')]")); UploadDocument.sendKeys(
	 * "C:\\Users\\Jotirmayi\\Desktop\\resume\\Resume_QA.pdf");
	 * 
	 * 
	 * Thread.sleep(1000); WebElement CourseContentSaveButton =
	 * driver.findElement(By.xpath(
	 * "//*[@class='float-right']//*[@type='submit'][normalize-space()='Save']"));CourseContentSaveButton.click();System.out.println("CourseContentSaveButton
	 * button clicked");
	 * 
	 * Thread.sleep(2000); //button[contains(text(),'Submit')]
	 * 
	 * WebElement previewSaveButton = driver.findElement(By.xpath(
	 * "//button[contains(text(),'Submit')]"));previewSaveButton.click();System.out.println("previewSaveButton
	 * button clicked");
	 * 
	 * Thread.sleep(2000);
	 * 
	 * }
	 * 
	 * WebDriverWait wait = new WebDriverWait(driver, 2000); WebElement profileName
	 * = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//span[@class='mb-1 text-black
	 * d-flex']")));((JavascriptExecutor)driver).executeScript("arguments[0].click();",profileName);System.out.println("profileName
	 * clicked");Thread.sleep(1000); WebElement LogoutButton =
	 * driver.findElement(By.xpath( "
	 *//*
	/a[contains(text(),'Signout')]"));LogoutButton.click();System.out.println("LogoutButton clicked");Thread.sleep(2000);}

// }
private static int generateRandom(int range) {
Random r = new Random();
int result;
int high = range;
result = r.nextInt(high - 1) + 1;
// System.out.println("range : " + range + " result: " + result);

* if (result == 1) { // result = r.nextInt(high - 1) + 1;
* System.out.println("range : " + range + " result: " + result); result=0; }

return result;

}

private static int generateRandombetween(int range1, int range2) {
Random r = new Random();
int high = range2;
int result = r.nextInt(high - range1) + range1;
return result;

}*/

