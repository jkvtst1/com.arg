package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class FirstTest {
	static WebDriver driver;
	@Test
	public void main() {
		 try {
			
			String projectPath = System.getProperty("user.dir");

			System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1440x900");
			options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
			System.out.println("Running on headless chrome");
			driver=new ChromeDriver(options);
			driver.get("https://www.quackquack.in/");
			System.out.println("Navigated to website");
			MyElements my=PageFactory.initElements(driver, MyElements.class);
			my.logintosite("rahulzqwerty6@gmail.com", "08moth42");
			my.likesfor(60);
			
			driver.quit();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
