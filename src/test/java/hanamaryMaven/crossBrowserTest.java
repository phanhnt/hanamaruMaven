package hanamaryMaven;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class crossBrowserTest {
	WebDriver driver;

	public String url, admin_username, admin_password;

	@Test
	public void login() {
		try {
			InputStream input = new FileInputStream("propertyFile.properties");
			Properties prop = new Properties();
			prop.load(input);
			url = prop.getProperty("url");
			admin_username = prop.getProperty("admin_username");
			admin_password = prop.getProperty("admin_password");
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver.get(url);
		driver.findElement(By.xpath("//*[@id='id']")).sendKeys(admin_username);
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(admin_password);
		driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
	}

	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception {
		if (browser.equalsIgnoreCase("Firefox")) {
			// create firefox instance
			System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		// Check if parameter passed as 'chrome'
		else if (browser.equalsIgnoreCase("Chrome")) {
			// set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			// create chrome instance
			driver = new ChromeDriver();
		}
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
