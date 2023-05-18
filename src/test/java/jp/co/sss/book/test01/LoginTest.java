package jp.co.sss.book.test01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("01_ログイン表示")
public class LoginTest {
	private WebDriver webDriver;
	
	/**
	 * テストメソッドを実行する前に実行されるメソッド
	 */
	@BeforeEach
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		webDriver = new ChromeDriver();
	}
	/**
	 * テストメソッドが実行された後に実行されるメソッド
	 */
	@AfterEach
	public void quitDriver() {
		webDriver.close();
	}
	/**
	 * テストメソッド
	 */
	@Test
	@Order(1)
	public void 正常系_ログイン操作() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:2222/book_list/");
		// 最大5秒間待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		// 指定したURLに遷移する
		// 表示されている要素の取得
		WebElement loginIdElement = webDriver.findElement(By.name("bookUserId"));
		// 取得したフォームの入力値を削除
		loginIdElement.clear();
		//取得したフォームに1を入力
		loginIdElement.sendKeys("1");
		// 表示されている要素の取得
		//headerタグのclass="content"のclass="title"の部分を取ってくる
		WebElement title = webDriver.findElement(By.cssSelector("header .content .title"));
		//表示されている要素の取得
		WebElement password = webDriver.findElement(By.name("password"));
		// 取得したフォームの入力値を削除
		password.clear();
		//取得したフォームに1111を入力
		password.sendKeys("1111");

		//submitボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();

		//5秒待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		
		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//headerタグのclass="user_info"で囲まれた部分の情報を取得
		WebElement headerMsg = webDriver.findElement(By.cssSelector("header #loginInfo"));
		
		// 検証
		//それぞれ取ってきた情報があっているかどうか確認
		//タイトルが「社員管理システム」になっているか確認
		assertEquals("書籍一覧画面", webDriver.getTitle());
		//headerの中に「ようこそ」が含まれているかどうか（含まれていればログインできているはず）
		assertTrue(headerMsg.getText().contains("ようこそ"));

	}
	
	/**
	 * テストメソッド
	 */
	@Test
	@Order(2)
	public void 正常系_ログイン操作_エラーメッセージ() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:2222/book_list/");
		// 最大5秒間待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		// 指定したURLに遷移する
		// 表示されている要素の取得
		WebElement loginIdElement = webDriver.findElement(By.name("bookUserId"));
		// 取得したフォームの入力値を削除
		loginIdElement.clear();
		//取得したフォームに1を入力
		loginIdElement.sendKeys("100");
		// 表示されている要素の取得
		//headerタグのclass="content"のclass="title"の部分を取ってくる
		WebElement title = webDriver.findElement(By.cssSelector("header .content .title"));
		//表示されている要素の取得
		WebElement password = webDriver.findElement(By.name("password"));
		// 取得したフォームの入力値を削除
		password.clear();
		//取得したフォームに1111を入力
		password.sendKeys("1111");

		//submitボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();

		//5秒待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		
		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//表示されている要素の取得
		WebElement errElement = webDriver.findElement(By.cssSelector(".error"));

		String errMsg = "ユーザID、またはパスワードが間違っています。";

		// 検証
		assertTrue(errElement.getText().contains(errMsg),"エラーメッセージが違います："+errElement.getText());

	}
	
}
