package jp.co.sss.book.test00;

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
@DisplayName("00_index表示")
public class IndexTest {
	
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
	public void 正常系_ログイン画面表示_タイトル() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:2222/book_list/");
		// 最大5秒間待つ
		webDriver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		// スクリーンショット
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.move(tempFile, new File("screenshots\\" + new Object() {
			}.getClass().getEnclosingMethod().getName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 表示されている要素の取得
		//headerタグのclass="content"のclass="title"の部分を取ってくる
		WebElement title = webDriver.findElement(By.cssSelector("header .content .title"));
		
		// 検証
		//それぞれ取ってきた情報があっているかどうか確認
		assertEquals("ログイン画面",webDriver.getTitle());
		assertEquals("書籍一覧システム", title.getText());

	}

}
