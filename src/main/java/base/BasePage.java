package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
	protected static WebDriver driver = DriverContext.getDriver();
	
	public void delay(long delaySec) {
		try {
			Thread.sleep(delaySec * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	public BasePage() {
		PageFactory.initElements(DriverContext.getDriver(), this);
	}
	
}

