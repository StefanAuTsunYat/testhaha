package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverContext{

//	private static ThreadLocal<ChromeDriver> driver = new ThreadLocal<>();
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//	private static ChromeDriver driver;

//	public static void setDriver(ChromeDriver inputDriver)
	public static synchronized void setDriver(WebDriver inputDriver)
	{
//		driver = inputDriver;
		driver.set(inputDriver);
	}
	
//	public static ChromeDriver getDriver()
	public static synchronized WebDriver getDriver()
	{
//		return driver;
		return driver.get();
	}
}