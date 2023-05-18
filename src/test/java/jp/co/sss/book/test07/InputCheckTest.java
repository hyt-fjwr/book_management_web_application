package jp.co.sss.book.test07;

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
@DisplayName("06_入力チェック機能")
public class InputCheckTest {
	
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
	public void 異常系_ログイン操作_ユーザID_空文字入力メッセージ出力() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:2222/book_list/");

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		//表示されている要素の取得
		WebElement empIdElement = webDriver.findElement(By.name("bookUserId"));
		// 取得したフォームの入力値を削除
		empIdElement.clear();

		//表示されている要素の取得
		WebElement password = webDriver.findElement(By.name("password"));
		// 取得したフォームの入力値を削除
		password.clear();
		//取得したフォームに2222を入力
		password.sendKeys("2222");
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
		WebElement errElement = webDriver.findElement(By.cssSelector("span.error"));

		String errMsg = "ユーザID、またはパスワードが間違っています。";

		// 検証
		assertTrue(errElement.getText().contains(errMsg),"エラーメッセージが違います："+errElement.getText());

	}
	/**
	 * テストメソッド
	 */
	@Test
	//2番目に実行される
	@Order(2)
	public void 異常系_ログイン操作_ユーザID_桁数超過() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:2222/book_list/");

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		//表示されている要素の取得
		WebElement empIdElement = webDriver.findElement(By.name("bookUserId"));
		// 取得したフォームの入力値を削除
		empIdElement.clear();
		//取得したフォームに111111を入力
		empIdElement.sendKeys("111111");

		//表示されている要素の取得
		WebElement password = webDriver.findElement(By.name("password"));
		// 取得したフォームの入力値を削除
		password.clear();
		//取得したフォームに2222を入力
		password.sendKeys("2222");
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
		WebElement errElement = webDriver.findElement(By.cssSelector("span.error"));

		String errMsgInputNUllOfEmpId = "ユーザID、またはパスワードが間違っています。";

		// 検証
		assertTrue(errElement.getText().contains(errMsgInputNUllOfEmpId),"エラーメッセージが違います："+errElement.getText());

	}
	/**
	 * テストメソッド
	 */
	@Test
	//3番目に実行される
	@Order(3)
	public void 異常系_ログイン操作_ユーザID_未登録() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:2222/book_list/");

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		//表示されている要素の取得
		WebElement empIdElement = webDriver.findElement(By.name("bookUserId"));
		// 取得したフォームの入力値を削除
		empIdElement.clear();
		//取得したフォームに5を入力
		empIdElement.sendKeys("5");

		//表示されている要素の取得
		WebElement password = webDriver.findElement(By.name("password"));
		// 取得したフォームの入力値を削除
		password.clear();
		//取得したフォームに2222を入力
		password.sendKeys("2222");
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
		WebElement errElement = webDriver.findElement(By.cssSelector(".error"));

		String errMsg = "ユーザID、またはパスワードが間違っています。";

		// 検証
		assertTrue(errElement.getText().contains(errMsg),"エラーメッセージが違います："+errElement.getText());

	}
	/**
	 * テストメソッド
	 */
	@Test
	//4番目に実行される
	@Order(4)
	public void 異常系_ログイン操作_パスワード_空文字入力メッセージ出力() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:2222/book_list/");

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		//表示されている要素の取得
		WebElement empIdElement = webDriver.findElement(By.name("bookUserId"));
		// 取得したフォームの入力値を削除
		empIdElement.clear();
		//取得したフォームに111111を入力
		empIdElement.sendKeys("111111");

		//表示されている要素の取得
		WebElement password = webDriver.findElement(By.name("password"));
		// 取得したフォームの入力値を削除
		password.clear();
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
		WebElement errElement = webDriver.findElement(By.cssSelector("span.error"));

		String errMsg = "ユーザID、またはパスワードが間違っています。";

		// 検証
		assertTrue(errElement.getText().contains(errMsg),"エラーメッセージが違います："+errElement.getText());

	}
	/**
	 * テストメソッド
	 */
	@Test
	//5番目に実行される
	@Order(5)
	public void 異常系_ログイン操作_パスワード_不一致() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:2222/book_list/");

		// 最大5秒間、ページが完全に読み込まれるまで待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		//表示されている要素の取得
		WebElement empIdElement = webDriver.findElement(By.name("bookUserId"));
		// 取得したフォームの入力値を削除
		empIdElement.clear();
		//取得したフォームに1を入力
		empIdElement.sendKeys("1");

		//表示されている要素の取得
		WebElement password = webDriver.findElement(By.name("password"));
		// 取得したフォームの入力値を削除
		password.clear();
		//取得したフォームに5555を入力
		password.sendKeys("5555");
		//表示されている要素の取得
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
		WebElement errElement = webDriver.findElement(By.cssSelector(".error"));

		String errMsg = "ユーザID、またはパスワードが間違っています。";

		// 検証
		assertTrue(errElement.getText().contains(errMsg),"エラーメッセージが違います："+errElement.getText());

	}

}
