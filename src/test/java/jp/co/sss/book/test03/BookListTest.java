package jp.co.sss.book.test03;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
@DisplayName("03_書籍一覧表示機能")
public class BookListTest {
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
	public void 正常系_書籍一覧表示() {
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
		
		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//テーブルの４行目（見出し列も含む）１列目の情報を取得 
		WebElement bookId = webDriver.findElement(By.cssSelector("table tr:nth-of-type(4) td:nth-of-type(1)"));
		//テーブルの４行目（見出し列も含む）2列目の情報を取得 
		WebElement bookName = webDriver.findElement(By.cssSelector("table tr:nth-of-type(4) td:nth-of-type(2)"));
		//テーブルの４行目（見出し列も含む）3列目の情報を取得 
		WebElement author = webDriver.findElement(By.cssSelector("table tr:nth-of-type(4) td:nth-of-type(3)"));
		//テーブルの４行目（見出し列も含む）4列目の情報を取得 
		WebElement publicationDate = webDriver.findElement(By.cssSelector("table tr:nth-of-type(4) td:nth-of-type(4)"));
		//テーブルの４行目（見出し列も含む）5列目の情報を取得 
		WebElement stock = webDriver.findElement(By.cssSelector("table tr:nth-of-type(4) td:nth-of-type(5)"));
		//テーブルの４行目（見出し列も含む）6列目の情報を取得 
		WebElement genre = webDriver.findElement(By.cssSelector("table tr:nth-of-type(4) td:nth-of-type(6)"));

		// 検証
		//それぞれ取ってきた情報があっているかどうか確認
		assertEquals("3", bookId.getText());
		assertEquals("日本経済史2016", bookName.getText());
		assertEquals("宮本良太", author.getText());
		assertEquals("2016/07/20", publicationDate.getText());
		assertEquals("23", stock.getText());
		assertEquals("経済", genre.getText());

	}
}
