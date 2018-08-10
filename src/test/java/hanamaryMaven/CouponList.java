package hanamaryMaven;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class CouponList {
	loginTest test;

	@Test(priority = 1)
	public void CheckSortedCouponList() {
		boolean isSorted = true;
		int count = test.driver.findElements(By.cssSelector("tr")).size()-1;
	    List<Integer> int_couponList = new ArrayList<Integer>();
		for (int index = 1 ; index <= count; index++ ){
			String couponNoString = test.driver.findElement(By.cssSelector("tr.hanamaru-item:nth-child("+ index +") > td:nth-child(2)")).getText();
			int_couponList.add(Integer.parseInt(couponNoString));
		}
		for (int i = 0; i <int_couponList.size()-1 ; i++){		
			if (int_couponList.get(i) > int_couponList.get(i+1)){
				isSorted = false;
				break;
			}
			else{ 
				isSorted=true;
			}
		}
		if (isSorted == true){
			System.out.println("Coupon list is ascending sorted");
		}else if (isSorted == false){
			System.out.println("Error: Coupon list is NOT ascending sorted");
		}
	}
	@Test(priority = 2)
	public void CheckCouponInfoWithDB() throws ClassNotFoundException, IOException, SQLException {
		connectDB con = new connectDB();
		String arrayField[]= {"id", "number", "coupon_code","name", "image_url", "information", "regulations", "coupon_limit_days"};
		ArrayList<String> couponInfoDB = con.QueryRowfromDB("SELECT * FROM hanamaru.coupon_masters WHERE id= 2;", arrayField);
	
		for (String i : couponInfoDB){
			System.out.println(i);
		}
		
		int count = test.driver.findElements(By.cssSelector("tr.hanamaru-item:nth-child(1)")).size();
		System.out.println(count);
		ArrayList<String> couponInfoUI = null;
		
		for (int i =1;  i <= count ;  i++){ 
			System.out.println(test.driver.findElement(By.cssSelector("tr.hanamaru-item:nth-child(1) > td:nth-child("+i+")")).getText());
			//couponInfoUI.add(test.driver.findElement(By.cssSelector("tr.hanamaru-item:nth-child(1) > td:nth-child("+i+")")).getText());
		}
		/*for (String index: couponInfoUI){
			System.out.println("get value from row in UI: "+ index);
		}
*/
	}
	@Test(priority = 3) //? So sanh value add-button thôi hay phải so sánh các trường của coupon đó trog màn edit?
	public void CheckTabEditButton() {
		WebElement editButton = test.driver.findElement(By.cssSelector("tr.hanamaru-item:nth-child(1) > td:nth-child(7) > button:nth-child(1)"));
		editButton.click();
		WebElement addButtonCheckedinEditScreen = test.driver.findElement(By.id("btn-add"));
		if (addButtonCheckedinEditScreen.getText().equals(editButton.getAttribute("value"))){
			System.out.println("EditButton goto Edit Screen");
		}else {
			System.out.println("NOT Goto Edit Screen");
			}
	}
	
	@Test(priority = 4)
	public void CheckDeleteButton() {
		WebElement deleteButton = test.driver.findElement(By.cssSelector("tr.hanamaru-item:nth-child(1) > td:nth-child(7) > form:nth-child(2) > input:nth-child(2)"));
		deleteButton.click();
		Alert alert = test.driver.switchTo().alert();
		alert.dismiss(); //alert.accept();
	}

	@BeforeTest
	public void beforeTest() {
		test = new loginTest();
		test.login();
		WebElement couponMenuButton = test.driver
				.findElement(By.xpath("//a[contains(@href, 'http://app15.lifetimetech.vn/admin/coupon')]"));
		couponMenuButton.click();
	}

	@AfterTest
	public void afterTest() {
		test.driver.close();
	}

}
