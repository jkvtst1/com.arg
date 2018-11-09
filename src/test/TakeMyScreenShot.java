package test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeMyScreenShot {
	static String projectPath = System.getProperty("user.dir");
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	static Date date = new Date();
	public static String filename=dateFormat.format(date);
	public static String filepath=projectPath+"/interests/"+filename+".png";
	public static void getScreenshot(WebDriver driver)
	{
		
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(filepath));
			System.out.println("Screenshot taken and saved at "+filepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void deleteFile()
	{
		File fl=new File(filepath);
		fl.delete();
		System.out.println("Screenshot deleted");
	}
}
