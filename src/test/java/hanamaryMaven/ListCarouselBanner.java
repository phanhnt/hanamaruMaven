package hanamaryMaven;

import org.testng.annotations.Test;

import com.google.common.collect.Table;

import org.testng.annotations.BeforeTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ListCarouselBanner {
	loginTest test; 

	@BeforeTest
	public void beforeTest() {
		test = new loginTest();
		test.login();
		WebElement menuClick = test.driver
				.findElement(By.xpath("//a[contains(@href, 'http://app15.lifetimetech.vn/admin/banner/carousel')]"));
		menuClick.click();
	}

	@AfterTest
	public void afterTest() {
		test.driver.close();
	}

	// TC #1.2 check addButton hiển thị + màu
	@Test(priority = 1)
	public void addButton_TestContent() {
		WebElement addButton = test.driver.findElement(By.cssSelector("a.btn"));
		System.out.println(addButton.getText());
		if (addButton.getText().equals("新規登録")) {
			System.out.println("Match value of button");
		} else
			System.out.println("Not match value of button");
	}

	// check màu addButton
	@Test(priority = 2)
	public void addButton_TestColor() {
		if (IsSameColor()) {
			System.out.println("Match color of button");
		} else
			System.out.println("Not match color");
	}

	// TC #1.12 check Button đăng kí banner
	@Test(priority = 3)
	public void addButton_TestClick() {
		WebElement addButton = test.driver.findElement(By.cssSelector("a.btn"));
		addButton.click();
		System.out.println(test.driver.getCurrentUrl());
		if (test.driver.getCurrentUrl().equals("http://app15.lifetimetech.vn/admin/banner/carousel/add")) {
			System.out.println("Click of add button is enabled");
		} else
			System.out.println("Click of add button is Unenabled");
		test.driver.navigate().back(); //sau mỗi click là phải navigate lại page trc
	}

	// TC #1.11+ #1.13: check detailButton
	@Test(priority = 4)
	public void detailButton_TestContent() {
		WebElement detailButton = test.driver
				.findElement(By.xpath("//a[contains(@href,'http://app15.lifetimetech.vn/admin/banner/carousel/56')]"));
		if (detailButton.getText().equals("詳細 &gt;")) {
			System.out.println("Content of detail button is correct");
		} else
			System.out.println("Content of detail button is incorrect");
	}

	@Test(priority = 5)
	public void detailButton_TestClick() {
		WebElement detailButton_testClick = test.driver
				.findElement(By.xpath("//a[contains(@href,'http://app15.lifetimetech.vn/admin/banner/carousel/56')]"));
		detailButton_testClick.click();
		System.out.println(test.driver.getCurrentUrl());
		if (test.driver.getCurrentUrl().equals("http://app15.lifetimetech.vn/admin/banner/carousel/56")) {
			System.out.println("Click of detail button is correct");
		} else
			System.out.println("Click of detail button is incorrect");
		test.driver.navigate().back(); //nhớ nha sau mỗi click là phai navigate lại backpage
	}

	// TC #1.8: Check thứ tự hiển thị banner
	@Test(priority = 6)
	public void BannerList_AscendingOrder() {
		List<WebElement> bannerList = test.driver.findElements(By.cssSelector(".table > tbody:nth-child(2) > tr > td:nth-child(2)")); //mảng lấy ptu trong cột TT
		List<String> ascendingList = new ArrayList<String>(); // mảng giá trị cột tt hiển thị trả về String
		// mảng lấy gtri hiển thị -> 1 mảng String -> ép về mảng int để so sánh giá trị -> kiểm tra mảng int đc sxep tăng dần hay chưa!! k cần phải so sánh 2 mang cái khỉ gì cả!!!
		for (WebElement item : bannerList) { 
			ascendingList.add(item.getText()); 
		}
		List<Integer> int_asc_list = new ArrayList<Integer>(); // mảng int lấy gtri từ mảng String
		for (int i = 0; i < ascendingList.size(); i++) {
			int a = Integer.parseInt(ascendingList.get(i)); 
			int_asc_list.add(a);
		}		
		for (Integer index : int_asc_list) { //in ra sau khi chuyển mảng String về Int
			System.out.println(index);
		}
		IsAscList(int_asc_list);
	}
	//hàm ktra 1 mảng int đã sắp sếp tăng dần hay chưa
	public void IsAscList(List<Integer> list) { 
		for (int i = 0; i < (list.size()-1);i++) {
			if (list.get(i) > list.get(i + 1)) 
				System.out.println("Display list is NOT ascending Order ");
			if( (i == list.size()-2))
				System.out.println("Display list is ascending Order");
		}
	}
	
	@Test(priority=7)
	public void test1_8(){
		//*[@id="page-wrapper"]/div[2]/div[4]/div/table/tbody/tr[2]/td[2]
		int count = test.driver.findElements(By.cssSelector("tr")).size() - 1;
		System.out.println("count " + count);
		String xpath1 = "//*[@id=\"page-wrapper\"]/div[2]/div[4]/div/table/tbody/tr[";
		String xpath2 = "]/td[2]";
		int[] numbers = new int[count];
		boolean check = true;
		for (int i=0;i< count-1;i++ ){
			String numberStr = test.driver.findElement(By.xpath(xpath1+(i+1)+xpath2)).getText();
			numbers[i] = Integer.parseInt(numberStr);
			if(numbers[i] > numbers[i+1]){
				check = false;
				break;
			}
		}
		Assert.assertEquals(true, check);
	}
	
	// chuyen kieu rbg thanh kieu hexa de so sanh
	public String ConvertRbgIntoHexColor() {
		WebElement addButton = test.driver.findElement(By.cssSelector("a.btn"));
		String rbg_color = addButton.getCssValue("background-color").toString();
		// trời ơi cả đoạn code bên dưới optimize bằng 1 dòng== muốn hét lên==
		String hex_color = Color.fromString(rbg_color).asHex();
		/*
		 * String[] hex_color = rbg_color.replace("rgb","").replace("(",
		 * "").replace(")", "").split(", "); int no1=
		 * Integer.parseInt(hex_color[0]); hex_color[0]=hex_color[0].trim(); int
		 * no2= Integer.parseInt(hex_color[1]); hex_color[1] =
		 * hex_color[1].trim(); int no3= Integer.parseInt(hex_color[2]);
		 * hex_color[2] = hex_color[2].trim(); String hex =
		 * String.format("#%02x%02x%02x", no1, no2,no3);
		 */
		return hex_color;
	}

	// ham check mau có đúng là f0ad4e hay không. so sánh cả dấu # nên khi
	// chuyển thành hex phải định dạng lại format!!
	public boolean IsSameColor() {
		if (ConvertRbgIntoHexColor().equals("#f0ad4e"))
			return true;
		else
			return false;
	}

}
