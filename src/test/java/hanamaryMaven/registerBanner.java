package hanamaryMaven;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.support.ui.FluentWait;

public class registerBanner {
	
	loginTest test;
	
	@Test(priority = 1)
	public void imageName() {
		WebElement imageName = test.driver.findElement(By.xpath("//input[@id='image_name']"));
		imageName.sendKeys("imageNo1");
	}

	//upload file không dùng click() mà dùng senKeys("file path"); oimeoi
	@Test(priority = 2)
	public void uploadFile() {
		WebElement uploadFile = test.driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys("E:\\IMAGES\\studentmanager.pdf"); // invalid
		// selectFile.sendKeys("E:\\IMAGES\\tnvtihoay.jpg"); //valid
	}

	@Test(priority = 3)
	public void dropdownSelection() {
		Select link_destination = new Select(
				test.driver.findElement(By.xpath("//select[@id = 'link_destination_type']")));
		if (!link_destination.isMultiple()) {
			link_destination.selectByValue("2");
		} else {
			link_destination.selectByIndex(0);
			link_destination.selectByVisibleText("メニュー");
		}
		// nên chọn value or index gì đó vì nó tĩnh, còn text nó động thay đổi
		// cái là fail luôn
		Select link_destination_id = new Select(test.driver.findElement(By.id("link_destination_id")));
		if (!link_destination_id.isMultiple()) {
			link_destination_id.selectByVisibleText("天ぷら５０円引き");
		} else {
			link_destination_id.selectByIndex(0);
			link_destination_id.selectByVisibleText("天ぷら３０円引き");
		}
	}

	// text hyper_link
	@Test(priority = 4)
	public void hyper_link() {
		WebElement hyper_link = test.driver.findElement(By.id("hyper_link"));
		hyper_link.sendKeys("hyper_link.com");
	}

	// thứ tự hiển thị
	@Test(priority = 5)
	public void number_display() {
		WebElement number = test.driver.findElement(By.id("number"));
		number.sendKeys("1");
	}

	@Test(priority = 7)
	public void datePickerTest() throws InterruptedException {
		test.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// điền không dùng calender
		WebElement datebox = test.driver.findElement(By.id("start_time"));
		datebox.clear();
		datebox.sendKeys(Keys.TAB); // press tab to shift focus to time field
		datebox.sendKeys("2017/11/01");
		datebox.sendKeys("01:10");
		}
		
	WebDriverWait wait;
	public void selectDayFromMultiDateCalendar(String date) throws InterruptedException {
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.className("bootstrap-datetimepicker-widget
		// dropdown-menu usetwentyfour top")));
		WebElement table = test.driver.findElement(By.className("form-control"));
		// bootstrap-datetimepicker-widget dropdown-menu usetwentyfour top
		System.out.println("Calendar picker");
		List<WebElement> tableRows = table.findElements(By.xpath("//tr"));
		for (WebElement row : tableRows) {
			List<WebElement> cells = row.findElements(By.xpath("td"));
			for (WebElement cell : cells) {
				if (cell.getText().equals(date)) {
					test.driver.findElement(By.linkText(date)).click();
				}
			}
		}
		// intentional pause for 2 second
		Thread.sleep(2000);
	}

	@Test(priority = 7)
	public void submitButton() {
		WebElement submit_button = test.driver.findElement(By.id("btn-add"));
		submit_button.click();
	}

	@BeforeTest
	public void beforeTest() {
		test = new loginTest();
		test.login();
	}

}
