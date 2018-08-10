package hanamaryMaven;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class loginTest {

	public WebDriver driver;
	public String url, admin_username, admin_password;

	@Test(priority = 0, alwaysRun = true)
	public void login() {
		System.out.println("launching firefox browser");
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		driver = new FirefoxDriver();
		try {
			readPropertyFile prop = new readPropertyFile();
			url = prop.getUrl();
			admin_username = prop.getAdmin_username();
			admin_password = prop.getAdmin_password();
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver.get(url);
		driver.findElement(By.xpath("//*[@id='id']")).sendKeys(admin_username);
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(admin_password);
		driver.findElement(By.xpath("//*[@id='btnLogin']")).click();

	}

}

