package jp.co.sss.book.test04;

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
@DisplayName("04_書籍名検索機能")
public class SearchBookNameTest {
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
	 * ログイン処理のメソッド
	 */
	private void doLogin() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:2222/book_list/");
		// 最大5秒間待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		// 表示されている要素の取得
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

		//5秒待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);

	}

	/**
	 * テストメソッド
	 */
	@Test
	//引数の値が小さい順に実行される
	@Order(1)
	public void 正常系_書籍名検索操作_入力_日本() {
		//ログイン処理
		doLogin();
		//表示されている要素の取得
		WebElement searchEmpNameElement = webDriver.findElement(By.name("bookName"));
		// 取得したフォームの入力値を削除
		searchEmpNameElement.clear();
		//取得したフォームに日本を入力
		searchEmpNameElement.sendKeys("日本");
		//submitボタンをクリック
		searchEmpNameElement.submit();
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
		WebElement empNameOfKessakusen = webDriver.findElement(By.cssSelector("table tr:nth-of-type(2) td:nth-of-type(2)"));
		WebElement empNameOfKeizaishi = webDriver.findElement(By.cssSelector("table tr:nth-of-type(3) td:nth-of-type(2)"));

		// 検証
		assertEquals("日本文学傑作選", empNameOfKessakusen.getText());
		assertEquals("日本経済史2016", empNameOfKeizaishi.getText());

	}
	
	/**
	 * テストメソッド
	 */
	@Test
	//２番目に実行される
	@Order(2)
	public void 正常系_書籍名検索操作_入力_空文字() {
		//ログイン処理
		doLogin();
		//表示されている要素の取得
		WebElement searchEmpNameElement = webDriver.findElement(By.name("bookName"));
		// 取得したフォームの入力値を削除
		searchEmpNameElement.clear();
		//submitボタンをクリック
		searchEmpNameElement.submit();
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
		//表の要素を列と行を指定して取得
		WebElement empNameOfKeizai = webDriver.findElement(By.cssSelector("table tr:nth-of-type(2) td:nth-of-type(2)"));
		WebElement empNameOfKessakusen = webDriver.findElement(By.cssSelector("table tr:nth-of-type(3) td:nth-of-type(2)"));
		WebElement empNameOfKeizaishi = webDriver
				.findElement(By.cssSelector("table tr:nth-of-type(4) td:nth-of-type(2)"));

		// 検証
		assertEquals("よくわかる経済", empNameOfKeizai.getText());
		assertEquals("日本文学傑作選", empNameOfKessakusen.getText());
		assertEquals("日本経済史2016", empNameOfKeizaishi.getText());

	}
	/**
	 * テストメソッド
	 */
	@Test
	//３番目に実行
	@Order(3)
	public void 正常系_書籍名検索操作_入力_サーブレット() {
		//ログイン処理
		doLogin();

		// 表示されている要素の取得
		WebElement searchEmpNameElement = webDriver.findElement(By.name("bookName"));
		// 取得したフォームの入力値を削除
		searchEmpNameElement.clear();
		//取得したフォームにサーブレットを入力
		searchEmpNameElement.sendKeys("サーブレット");
		//submitボタンをクリック
		searchEmpNameElement.submit();
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
		WebElement msgOfNoneBook = webDriver.findElement(By.className("message"));
		WebElement backToListLink = webDriver.findElement(By.className("listLink"));

		// 検証
		assertEquals("該当する書籍は存在しません。", msgOfNoneBook.getText());
		assertEquals("一覧表示に戻る", backToListLink.getText());

	}
}
