package jp.co.sss.book.test06;

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
//テストメソッドの名前
@DisplayName("07_セッション機能")
public class SessionTest {
	
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
	//引数の値が小さい順に実行される
	@Order(1)
	public void 正常系_セッション表示() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:2222/book_list/");
		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);

		//表示されている要素の取得
		WebElement loginIdElement = webDriver.findElement(By.name("bookUserId"));
		// 取得したフォームの入力値を削除
		loginIdElement.clear();
		//取得したフォームに1を入力
		loginIdElement.sendKeys("1");

		//表示されている要素の取得
		WebElement password = webDriver.findElement(By.name("password"));
		// 取得したフォームの入力値を削除
		password.clear();
		//取得したフォームに1111を入力
		password.sendKeys("1111");
		//submitボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();
		// 最大5秒間、ページが完全に読み込まれるまで待つ
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
		WebElement headerMsg = webDriver.findElement(By.cssSelector("header #loginInfo"));

		// 検証
		assertTrue(headerMsg.getText().contains("ようこそ、鈴木太郎さん"), "セッションにユーザ情報が登録されていない可能性があります");
	}
	/**
	 * テストメソッド
	 */
	@Test
	//2番目に実行される
	@Order(2)
	public void 正常系_ログアウト処理() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:2222/book_list/");
		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);

		//表示されている要素の取得
		WebElement loginIdElement = webDriver.findElement(By.name("bookUserId"));
		// 取得したフォームの入力値を削除
		loginIdElement.clear();
		//取得したフォームに1を入力
		loginIdElement.sendKeys("1");

		//表示されている要素の取得
		WebElement password = webDriver.findElement(By.name("password"));
		// 取得したフォームの入力値を削除
		password.clear();
		//取得したフォームに1111を入力
		password.sendKeys("1111");
		//submitボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		//aタグがログアウトになっているところを取得する
		WebElement logoutWebElement = webDriver.findElement(By.linkText("ログアウト"));
		//取ってきたリンクをクリックする
		logoutWebElement.click();
		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//表示されている要素の取得
		WebElement headerMsg = webDriver.findElement(By.cssSelector("header"));

		// 検証
		assertFalse(headerMsg.getText().contains("ようこそ"),"セッションに情報が残っています。");
	}
	
}
