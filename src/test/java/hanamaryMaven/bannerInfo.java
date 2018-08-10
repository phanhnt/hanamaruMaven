package hanamaryMaven;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import java.sql.Date;

//tc #1.9
public class bannerInfo {
	loginTest test = new loginTest();

	@Test
	public void bannerInfo() throws ClassNotFoundException, IOException, SQLException {
		connectDB con = new connectDB();
		// mảng chứa giá trị lấy đc từ query trong cột cần so sánh
		ArrayList<String> list_id, list_number, list_image_url = null;
		ArrayList<Timestamp> list_start_datetime, list_end_datetime = null;
		list_id = con.QueryDB("SELECT * FROM hanamaru.carousel_banner_masters order by number asc limit 1;", "id");
		list_number = con.QueryDB("SELECT * FROM hanamaru.carousel_banner_masters order by number asc limit 1;",
				"number");
		list_image_url = con.QueryDB("SELECT * FROM hanamaru.carousel_banner_masters order by number asc limit 1;",
				"image_url");
		list_start_datetime = con.QueryDatetimeDB(
				"SELECT * FROM hanamaru.carousel_banner_masters order by number asc limit 1;", "start_datetime");
		list_end_datetime = con.QueryDatetimeDB("SELECT * FROM hanamaru.carousel_banner_masters order by number asc limit 1;",
				"end_datetime");

		WebElement banner_id = test.driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[4]/div/table/tbody/tr[1]/td[1]"));
		WebElement banner_number = test.driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[4]/div/table/tbody/tr[1]/td[2]"));
		WebElement banner_image_url = test.driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[4]/div/table/tbody/tr[1]/td[3]/img"));
		WebElement start_end_datetime = test.driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[4]/div/table/tbody/tr[1]/td[4]"));

		// mỗi phần tử của list.get(index) dạng String, còn nếu dạng ArrayList
		// thì là list thôi!
		for (String item : list_id) {
			System.out.println(item.toString());
		}
		// check ID
		if (!banner_id.getText().equals(list_id.get(0)))
			System.out.println(" ID values doesn't match to DB");
		else
			System.out.println("ID : OK");
		// check number
		if (!banner_number.getText().equals(list_number.get(0))) {
			System.out.println(" Number values doesn't match to DB");
		} else
			System.out.println(" Number values : OK");
		// check image_url
		if (!banner_image_url.getAttribute("src").contains(list_image_url.get(0))) {
			System.out.println(" Image_url values doesn't match to DB");
		} else
			System.out.println("Image_url values : OK");
		
		// tách datetime ra thành start và end.
		String[] start_end = start_end_datetime.getText().replace("" + "", "").split("〜 ");
		String start_datetime = start_end[0];
		System.out.print(start_datetime);
		
		String end_datetime = start_end[1];
		System.out.println(end_datetime);

		System.out.println(list_start_datetime.get(0).toString());
		System.out.println(list_end_datetime.get(0));
		// check start_datetime
		if (!list_start_datetime.get(0).toString().contains(start_datetime)) { ////////// ??????////
			System.out.println("start_datetime values doesn't match to DB");
		} else {
			System.out.println("start_time values: OK");
		}
		// check end_datetime
		if (!list_end_datetime.get(0).toString().contains(end_datetime)) {
			System.out.println("end_datetime values doesn't match to DB");
		} else {
			System.out.println("end_time values: OK");
		}
	}

	@BeforeTest
	public void beforeTest() {
		test.login();
		WebElement menuClick = test.driver
				.findElement(By.xpath("//a[contains(@href,'http://app15.lifetimetech.vn/admin/banner/carouse')]"));
		menuClick.click();
	}

	@AfterTest
	public void afterTest() {
		test.driver.close();
	}

}
