package test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyElements {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	Date date = new Date();

	WebDriver driver;
	@FindBy(xpath = "//input[@id='mbl_login']")
	private WebElement login;

	@FindBy(xpath = "//input[@id='mobileuname']")
	private WebElement user;
	@FindBy(xpath = "//input[@id='mobilepassword']")
	private WebElement pass;
	@FindBy(xpath = "//input[@id='msubmit']")
	private WebElement submit;

	@FindBy(xpath = "//div[@class='popNotify']")
	private WebElement notifyPop;
	@FindBy(xpath = "//button[@class='nothanks btn']")
	private WebElement nothanksBtn;

	@FindBy(xpath = "//span[@class='like_ico']")
	private List<WebElement> likeBtn;

	@FindBy(xpath = "//span[@class='profileName']/strong[1]")
	private List<WebElement> name;

	@FindBy(xpath = "//span[@class='profileName']/small[1]")
	private List<WebElement> ageCity;

	public MyElements(WebDriver driver) {
		this.driver = driver;
	}

	public void waitforElement(WebElement element) {
		try {
			Thread.sleep(100);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block

		}

	}

	public void sleepFor(long milisecs) {
		try {
			Thread.sleep(milisecs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void logintosite(String userName, String password) {
		try {
			waitforElement(login);
			login.click();
			waitforElement(user);
			user.sendKeys(userName);
			waitforElement(pass);
			pass.sendKeys(password);
			waitforElement(submit);
			submit.click();
			System.out.println("Logged in");
			sleepFor(2000);
			waitforElement(notifyPop);
			if (notifyPop.isDisplayed())
				nothanksBtn.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception caught");
		}

	}

	public void likesfor(int timeinSeconds) {
		int count=0;
		long currentTime = System.currentTimeMillis();
		long waitTime = timeinSeconds * 1000;
		while ((System.currentTimeMillis() - currentTime) < waitTime) {
			waitforElement(likeBtn.get(0));
			if (likeBtn.get(0).isEnabled()) {
				
				String fullname = name.get(0).getText() + " " + ageCity.get(0).getText();
				TakeMyScreenShot.getScreenshot(driver);
				SendEmail.send("Liked profile " + fullname, "You liked " + fullname + " at "+dateFormat.format(date)+ "\n Photo is <img src='"+TakeMyScreenShot.filepath+"'/> ", TakeMyScreenShot.filepath, fullname);
				System.out.println("Liked profile: " + fullname);
				likeBtn.get(0).click();
				count++;
				TakeMyScreenShot.deleteFile();
				sleepFor(1000);
			}
		}
		System.out.println("Total profiles liked: "+count);
		System.out.println("Time complete. exiting");
	}

	

}
