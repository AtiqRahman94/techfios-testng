package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewTest {
	WebDriver driver;

	public void startBrowser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Chrome/chromedriver");
		driver = new ChromeDriver();
	}
}
