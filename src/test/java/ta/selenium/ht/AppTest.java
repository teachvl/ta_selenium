package ta.selenium.ht;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);
	public String baseUrl = "https://demo.opencart.com/";
	String driverPath = "/home/vl/java-2023-03/webdriver/chromedriver";
	public WebDriver driver;

	@BeforeClass
	public void setup() {
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	@Test
	public void testScript() {
		driver.findElement(By.id("form-currency")).findElement(By.className("dropdown-toggle")).click();
		driver.findElement(By.xpath("//a[@href='EUR']")).click();
		WebElement menu = driver.findElement(By.id("menu"));
		menu.findElement(By.className("dropdown-toggle")).click();
		menu.findElement(By.xpath("//*[contains(text(), 'Mac')]")).click();
		WebElement res = driver.findElement(By.className("product-thumb")).findElement(By.className("price-new"));
		LOGGER.info(res.getText());
		Assert.assertEquals(res.getText(), "111.55€");
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
