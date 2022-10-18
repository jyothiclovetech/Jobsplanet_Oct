  package quickstart.tranier;

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
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCourseNew {

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
		UserName.sendKeys("premg10004@gmail.com");
		//UserName.sendKeys("jahir7@mailinator.com");
		//UserName.sendKeys("prem600@mailinator.com");
		System.out.println(" UserName read from excel");
		
		WebElement Password = driver.findElement(By.id("password"));
		Password.clear();
		//Password.sendKeys("Prem@123");
		//Password.sendKeys("Mounika@1");
		Password.sendKeys("Prem@123");
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

		for (int i =29; i <= rowCount; i++) {
			
			System.out.println("loop: " +i);

			Thread.sleep(2000);


			WebElement CreateCourse = driver.findElement(By.xpath("//button[contains(text(),'Create Course')]"));
			CreateCourse.click();
			System.out.println("Create Course button clicked");
			Thread.sleep(2000);

			WebElement Recorded = driver.findElement(By.xpath("//*[contains(text(),'Recorded')]"));
			js.executeScript("arguments[0].click();", Recorded);
			System.out.println("Recorded Selected");
			Thread.sleep(2000);

			
			// Course Details

			WebElement CourseTitle = driver.findElement(By.xpath("//input[@placeholder='Course Title']"));
			CourseTitle.clear();
			CourseTitle.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			System.out.println("Course Title entered");

			Thread.sleep(2000);
			Select CourseLevel = new Select(driver.findElement(By.xpath("//select[@id='recordLevel']")));
			String level = sheet.getRow(i).getCell(1).getStringCellValue();
			CourseLevel.selectByVisibleText(level);
			System.out.println("CourseLevel Selected");

			Thread.sleep(2000);
			Select Category = new Select(driver.findElement(By.xpath("//select[@id='recordCategory']")));
			String category1 = String.valueOf(sheet.getRow(i).getCell(2).getStringCellValue());
			System.out.println(category1);
			Category.selectByVisibleText(category1.trim());
			System.out.println("recordCategory Selected");

			Thread.sleep(2000);
			Select SubCategory = new Select(driver.findElement(By.xpath("//select[@id='recordSubCategory']")));
			String subcat = sheet.getRow(i).getCell(4).getStringCellValue();
			SubCategory.selectByVisibleText(subcat);
			System.out.println("SubCategory Selected");

			Thread.sleep(1000);

			WebElement CourseCompletionAssessment = driver.findElement(By.xpath("*//*[contains(text(),'Course Completion Assessment')]//following::span[@class='mat-radio-label-content'][2]"));
			js.executeScript("arguments[0].click();", CourseCompletionAssessment);
			System.out.println(" Completion Assessment Radio button No Selected");
			Thread.sleep(2000);

			WebElement PrerequisiteAssessment = driver.findElement(By.xpath("*//*[contains(text(),'Prerequisite Assessment ')]//following::span[@class='mat-radio-label-content'][2]"));
			js.executeScript("arguments[0].click();", PrerequisiteAssessment);
			System.out.println("Prerequisite Assessment Radio button No Selected");
			Thread.sleep(2000);

			WebElement Tags = driver.findElement(By.xpath("//input[@placeholder='Skills']"));
			String tag = sheet.getRow(i).getCell(7).getStringCellValue();
			Tags.sendKeys(tag);
			Thread.sleep(1000);
			Tags.sendKeys(Keys.ARROW_DOWN);
			Tags.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			System.out.println("Tags Selected");

			/*
			 * WebElement CourseDuration =
			 * driver.findElement(By.xpath("//input[@placeholder='Course Duration']"));
			 * CourseDuration.clear(); int duration = generateRandombetween(10, 999); String
			 * s = Integer.toString(duration); //
			 * CourseTitle.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			 * CourseDuration.sendKeys(s); Thread.sleep(1000);
			 */

			/*
			 * WebElement cutoffQuestions = driver.findElement(By.
			 * xpath("//input[@placeholder='Number Of Cut of Questions']"));
			 * cutoffQuestions.clear(); int cutoffQ = generateRandombetween(1, 20); String
			 * s1 = Integer.toString(cutoffQ); //
			 * CourseTitle.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			 * cutoffQuestions.sendKeys(s1); Thread.sleep(1000);
			 * 
			 * WebElement NumberOfAssessmentQuestions =
			 * driver.findElement(By.xpath("//input[@id='numberOfAssessmentQuestions']"));
			 * NumberOfAssessmentQuestions.clear(); //
			 * CourseTitle.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			 * NumberOfAssessmentQuestions.sendKeys(s1); Thread.sleep(1000);
			 * 
			 * WebElement TestReappearDays =
			 * driver.findElement(By.xpath("//input[@id='testReappearDays']"));
			 * TestReappearDays.clear(); //
			 * CourseTitle.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			 * TestReappearDays.sendKeys("5"); Thread.sleep(1000);
			 */

			WebElement PrerequisitesSkills = driver.findElement(By.xpath("//*[contains(text(),'Prerequisites skills')]//following::input[1]"));
			PrerequisitesSkills.sendKeys(tag);
			Thread.sleep(1000);
			PrerequisitesSkills.sendKeys(Keys.ARROW_DOWN);
			PrerequisitesSkills.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			System.out.println("Prerequisites Skills Selected");

			WebElement iframe = driver.findElement(By.xpath("*//*[@class='tox-edit-area__iframe']"));
			driver.switchTo().frame(iframe);

			Thread.sleep(1000);
			WebElement element = driver.findElement(By.xpath("//*[@id='tinymce']//p"));
			element.sendKeys("Course Description");
			//js.executeScript("arguments[0].innerHTML = '<p>Hello world!</p>'", element);

			driver.switchTo().defaultContent();
			Thread.sleep(1000);

			WebElement CourseDetailsSaveButton = driver.findElement(By.xpath("//*[@class='submit-button mr-3']"));
			CourseDetailsSaveButton.click();
			System.out.println("CourseDetailsSaveButton button clicked");

			Thread.sleep(2000);
//Section

			WebElement sectionTitle = driver.findElement(By.xpath("//input[@id='sectionTitle']"));
			sectionTitle.clear();
			sectionTitle.sendKeys(sheet.getRow(i).getCell(14).getStringCellValue());
			System.out.println("section Title");


			/*
			 * WebElement CourseSectionDuration =
			 * driver.findElement(By.xpath("//input[@id='sectionDuration']"));
			 * CourseSectionDuration.clear(); int secduration = generateRandombetween(10,
			 * 99); String s2 = Integer.toString(secduration);
			 * CourseSectionDuration.sendKeys(s2);
			 * System.out.println("Course Section Duration");
			 */

			WebElement IntermediateAssesment = driver.findElement(By.xpath("*//*[contains(text(),'Intermediate Assesment')]//following::span[@class='mat-radio-label-content'][2]"));
			js.executeScript("arguments[0].click();", IntermediateAssesment);
			System.out.println("No Selected");
			Thread.sleep(2000);
			System.out.println("Intermediate Assesment");
			

			WebElement iframe1 = driver.findElement(By.xpath("(//*[starts-with(@id, 'tiny-angular_') and contains(@class,'tox-edit-area__iframe')])[1]"));
			driver.switchTo().frame(iframe1);

			WebElement element1 = driver.findElement(By.xpath("//*[@id='tinymce']//p"));
			js.executeScript("arguments[0].innerHTML = '<p>Hello world!</p>'", element1);

			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			System.out.println("Intermediate Assesment iframe"); 
			// Add Section
			WebElement CourseSectionAddSection = driver.findElement(By.xpath("//button[contains(text(),'Add Section')]"));
			CourseSectionAddSection.click();
			System.out.println("CourseSectionAddSection button clicked");
			Thread.sleep(2000);
			System.out.println("Course Section Add Section"); 
			
			WebElement sectionTitle1 = driver.findElement(By.xpath("(//input[@id='sectionTitle'])[2]"));
			sectionTitle1.clear(); //
			// CourseTitle.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			sectionTitle1.sendKeys("section Title1");
			System.out.println("section Title");

			/*
			 * WebElement CourseSectionDuration1 =
			 * driver.findElement(By.xpath("(//input[@id='sectionDuration'])[2]"));
			 * CourseSectionDuration1.clear(); // //
			 * CourseTitle.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			 * CourseSectionDuration1.sendKeys("10");
			 * System.out.println("Course Section Duration1");
			 */

			WebElement IntermediateAssesment1 = driver.findElement(By.xpath("*//*[contains(text(),'Intermediate Assesment')]//following::span[@class='mat-radio-label-content'][4]"));
			js.executeScript("arguments[0].click();", IntermediateAssesment1);
			System.out.println("No Selected");
			Thread.sleep(2000);			
			System.out.println("section Title");

			// WebElement CloseBtn =
			// driver.findElement(By.xpath("*//button[@class='tox-notification__dismiss
			/*
			 * // tox-button tox-button--naked tox-button--icon']")); if (CloseBtn != null)
			 * { CloseBtn.click(); }
			 */

			Thread.sleep(1000);

			WebElement iframe2 = driver.findElement(By.xpath("(//*[starts-with(@id, 'tiny-angular_') and contains(@class,'tox-edit-area__iframe')])[1]"));
			driver.switchTo().frame(iframe2);

			Thread.sleep(1000);
			WebElement element2 = driver.findElement(By.xpath("//*[@id='tinymce']//p"));
			js.executeScript("arguments[0].innerHTML = '<p>Hello world!</p>'", element2);
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			System.out.println("section description");

			WebElement CourseSectionSaveButton = driver.findElement(By.xpath("//button[@type='submit'][@class='submit-button ng-star-inserted']"));
			CourseSectionSaveButton.click();
			System.out.println("CourseSectionSaveButton button clicked");
			Thread.sleep(2000);
//
			List<WebElement> Section = driver.findElements(By.xpath("//select[@id='section']//option[@class ='ng-star-inserted']"));
			int staterandNumb = generateRandom(Section.size());
			System.out.println(Section.size());
			WebElement e2 = driver.findElement(By.xpath("//select[@id='section']"));
			e2.click();
			Select currentSection = new Select(driver.findElement(By.xpath("//select[@id='section']")));
			currentSection.selectByIndex(staterandNumb);
			Thread.sleep(1000);
			System.out.println("CourseSectionSaveButton button clicked");
			// WebElement CloseBtn =
			// driver.findElement(By.xpath("*//button[@class='tox-notification__dismiss
			// tox-button tox-button--naked tox-button--icon']"));
			/*
			 * if (CloseBtn != null) { CloseBtn.click(); }
			 */

			Thread.sleep(1000);

			WebElement iframe3 = driver.findElement(By.xpath("(//*[starts-with(@id, 'tiny-angular_') and contains(@class,'tox-edit-area__iframe')])[1]"));
			driver.switchTo().frame(iframe3);

			Thread.sleep(1000);
			WebElement ContentTitle = driver.findElement(By.xpath("//*[@id='tinymce']//p"));
			ContentTitle.sendKeys("Content title");
			/*
			 * js.executeScript("arguments[0].innerHTML = '<p>Hello world!</p>'",
			 * ContentTitle); 
			 */
			driver.switchTo().defaultContent(); 
			Thread.sleep(1000);

			WebElement Duration = driver.findElement(By.xpath("//input[@placeholder='Duration']"));
			Duration.clear(); //
			// CourseTitle.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			Duration.sendKeys("5");

			WebElement OrderNumber = driver.findElement(By.xpath("//input[@placeholder='Order Number']"));
			OrderNumber.clear(); //
			// CourseTitle.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			OrderNumber.sendKeys("5");
			Thread.sleep(1000);

			
			WebElement VideoEmbeddedLink = driver.findElement(By.xpath("//button[@title='Insert/edit media']"));
			VideoEmbeddedLink.click();
			Thread.sleep(1000);
			WebElement Embed = driver.findElement(By.xpath("(//*[starts-with(@id, 'form-field_') and contains(@class,'tox-textfield')])[1]"));
			Embed.sendKeys("https://www.youtube.com/watch?v=PLLbGltmkpE&list=PLUDwpEzHYYLs6G9WOfFxiH5zMfgNxgmSV&index=1");
			WebElement Width = driver.findElement(By.xpath("(//*[starts-with(@id, 'form-field_') and contains(@class,'tox-textfield')])[2]"));
			Width.sendKeys("4");
			WebElement Height = driver.findElement(By.xpath("(//*[starts-with(@id, 'form-field_') and contains(@class,'tox-textfield')])[3]"));
			Height.sendKeys("4");
			
			Thread.sleep(1000);
			WebElement videoSave = driver.findElement(By.xpath("//button[@title='Save']"));
			videoSave.click();
						
			Thread.sleep(1000);
			WebElement iframe5 = driver.findElement(By.xpath("(//*[starts-with(@id, 'tiny-angular_') and contains(@class,'tox-edit-area__iframe')])[3]"));
			driver.switchTo().frame(iframe5);
			Thread.sleep(1000);
			WebElement References = driver.findElement(By.xpath("//*[@id='tinymce']//p"));
			js.executeScript("arguments[0].innerHTML = '<p>Hello world!</p>'", References);
			driver.switchTo().defaultContent();
			Thread.sleep(1000);

			// WebElement CloseBtn =
			// driver.findElement(By.xpath("*//button[@class='tox-notification__dismiss
			// tox-button tox-button--naked tox-button--icon']"));
			/*
			 * if (CloseBtn != null) { CloseBtn.click(); }
			 */

			Thread.sleep(1000);
			WebElement iframe6 = driver.findElement(By.xpath("(//*[starts-with(@id, 'tiny-angular_') and contains(@class,'tox-edit-area__iframe')])[4]"));
			driver.switchTo().frame(iframe6);
			Thread.sleep(1000);
			WebElement Description = driver.findElement(By.xpath("//*[@id='tinymce']//p"));
			js.executeScript("arguments[0].innerHTML = '<p>Hello world!</p>'", Description);
			driver.switchTo().defaultContent();
			Thread.sleep(1000);

			/*
			 * WebElement UploadDocument =
			 * driver.findElement(By.xpath("//*[starts-with(@id, 'dynamicFile-')]"));
			 * UploadDocument.sendKeys(
			 * "C:\\Users\\Jotirmayi\\Desktop\\resume\\Resume_QA.pdf");
			 */

			Thread.sleep(1000);
			WebElement CourseContentSaveButton = driver  .findElement(By.xpath("//*[@class='float-right']//*[@type='submit'][normalize-space()='Save']"));
			CourseContentSaveButton.click();
			System.out.println("CourseContentSaveButton button clicked");
			
			Thread.sleep(2000);
			//button[contains(text(),'Submit')]
			
			WebElement previewSaveButton = driver  .findElement(By.xpath("//button[contains(text(),'Submit')]"));
			previewSaveButton.click();
			System.out.println("previewSaveButton button clicked");
			
			Thread.sleep(2000);
			
			

		}

		WebDriverWait wait = new WebDriverWait(driver, 2000);
		WebElement profileName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='mb-1 text-black d-flex']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", profileName);
		System.out.println("profileName clicked");
		Thread.sleep(1000);
		WebElement LogoutButton = driver.findElement(By.xpath("*//a[contains(text(),'Signout')]"));
		LogoutButton.click();
		System.out.println("LogoutButton clicked");
		Thread.sleep(2000);
	}

	// }
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
