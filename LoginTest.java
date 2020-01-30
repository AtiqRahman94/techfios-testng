package test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BasePage {

	WebDriver driver;

	@BeforeMethod
	public void startBrowser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Chrome/chromedriver");
		driver = new ChromeDriver();

		// Set certain properties
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		// Implicit wait applies to everything, only need to set once in beginning.
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// 2. Go to the TechFios website.
		driver.get("http://techfios.com/test/billing/?ng=admin/");

	}

	@Test(priority = 2)
	public void userShouldBeAbleToAddContact() throws InterruptedException {

		// 3. Type username and password and submit.
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("abc123");
		driver.findElement(By.xpath("//button[contains(text(),'Sign')]")).click();

		// Library of Elements
		By PAGE_TITLE_LOCATOR = By.xpath("//h2[contains(text(),'Dashboard')]");
		By CRM_SIDE_NAV_LOCATOR = By.xpath("//ul[@id='side-menu']/descendant::span[text()='CRM']");
		By ADD_CONTACT_SIDE_NAV_LOCATOR = By
				.xpath("//span[text()='CRM']/parent::*/following-sibling::ul/descendant::a[contains(@href,'add')]");
		By FULL_NAME_INPUT_FIELD_LOCATOR = By.id("account");
		By COMPANY_NAME_INPUT_FIELD_LOCATOR = By.id("company");
		By EMAIL_INPUT_FIELD_LOCATOR = By.id("email");
		By PHONE_INPUT_FIELD_LOCATOR = By.id("phone");
		By ADDRESS_INPUT_FIELD_LOCATOR = By.id("address");
		By CITY_INPUT_FIELD_LOCATOR = By.id("city");
		By STATE_INPUT_FIELD_LOCATOR = By.id("state");
		By ZIP_INPUT_FIELD_LOCATOR = By.id("zip");
		By SUBMIT_BUTTON_LOCATOR = By.id("submit");
		By LIST_CONTACT_SIDE_NAV_LOCATOR = By.linkText("List Contacts");
		// By NEWLY_ADDED_DISPLAYED_CONTACT_LOCATOR =
		// By.xpath("//table/tbody/descendant::tr[1]/td[2]/a");

		// Generate random numbers
		Random rnd = new Random();
		int randomNumber = rnd.nextInt(999);

		String fullName = "Belle" + randomNumber;
		String companyName = "LAFitness" + randomNumber;
		String email = "belle" + randomNumber + "gmail.com";
		String phone = "9175127899" + randomNumber;
		String address = randomNumber + "Lane";
		String city = "Fort Lauderdale" + randomNumber;
		String state = "Florida";
		String zip = "90210";

		// Explicit wait given to the driver
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE_LOCATOR));

		// 4. Open CRM
		driver.findElement(CRM_SIDE_NAV_LOCATOR).click();

		waitForElement(driver, 10, ADD_CONTACT_SIDE_NAV_LOCATOR);

		// 5. Click on Add Contact
		driver.findElement(ADD_CONTACT_SIDE_NAV_LOCATOR).click();

		// 6. Fill Out Form and Submit
		waitForElement(driver, 10, FULL_NAME_INPUT_FIELD_LOCATOR);
		driver.findElement(FULL_NAME_INPUT_FIELD_LOCATOR).sendKeys(fullName);
		driver.findElement(COMPANY_NAME_INPUT_FIELD_LOCATOR).sendKeys(companyName);
		driver.findElement(EMAIL_INPUT_FIELD_LOCATOR).sendKeys(email);
		driver.findElement(PHONE_INPUT_FIELD_LOCATOR).sendKeys(phone);
		driver.findElement(ADDRESS_INPUT_FIELD_LOCATOR).sendKeys(address);
		driver.findElement(CITY_INPUT_FIELD_LOCATOR).sendKeys(city);
		driver.findElement(STATE_INPUT_FIELD_LOCATOR).sendKeys(state);
		driver.findElement(ZIP_INPUT_FIELD_LOCATOR).sendKeys(zip);
		driver.findElement(SUBMIT_BUTTON_LOCATOR).click();

		// 7. Click on List Contact
		driver.findElement(LIST_CONTACT_SIDE_NAV_LOCATOR).click();

		// 8. Verify that Contact Added
		// String displayedContactName =
		// driver.findElement(NEWLY_ADDED_DISPLAYED_CONTACT_LOCATOR).getText();

		// Print to see
//		System.out.println("actual Name" + displayedContactName);
//		Assert.assertEquals("Contact name not found!", fullName, displayedContactName);

	}

	@Test(priority = 3)
	public void invalidLoginTestCase() {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Chrome/chromedriver");
		driver = new ChromeDriver();

		// Set certain properties
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		// Implicit wait applies to everything, only need to set once in beginning.
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// 2. Go to the TechFios website.
		driver.get("http://techfios.com/test/billing/?ng=admin/");

		// 3. Type username and password and submit.
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("abc");
		driver.findElement(By.xpath("//button[contains(text(),'Sign')]")).click();

		By ALERT_MSG_LOCATOR = By.xpath("//div[@class='alert alert-danger fade in']");

		waitForElement(driver, 10, ALERT_MSG_LOCATOR);

	}

	// This method will close browser after all failed test cases.
	@AfterMethod
	public void close() {
		driver.close();
		driver.quit();
	}

}
