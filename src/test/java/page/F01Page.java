package page;

import base.BasePage;
import base.DriverContext;
import com.aiatss.uplifttest.constant.Xpath_Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.ExcelUtil;
import utilities.ScreenShotUtil;
import utilities.WebDriverUtil;

import java.util.concurrent.TimeUnit;

public class F01Page extends BasePage {

	public void openJobsDB() {
		WebDriver driver = DriverContext.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://hk.jobsdb.com/hk?utm_campaign=hk-c-ao-[c]_dbhk_google_all_sem_brand_brand_tc_exact_ao&utm_source=google&utm_medium=cpc&utm_term=&pem=google&gclid=EAIaIQobChMI_PqCpaPZ9gIVh9CWCh2nMgv7EAAYASAAEgIg7vD_BwE");
		ScreenShotUtil.addScreenShot();
	}

	public void inputAutomation() throws InterruptedException {
		WebDriverUtil.waitElementClickable(Xpath_Locator.inputSearchingBox,20);
		delay(5);
		WebDriverUtil.sendKeyElement(Xpath_Locator.inputSearchingBox, ExcelUtil.getValue("jobSearch").trim());
		ScreenShotUtil.addScreenShot();
	}
	public void clickBtn(String text) throws InterruptedException {
		switch (text) {
			case "Search":
				WebDriverUtil.clickElement(Xpath_Locator.searchBtn);
				break;
		}
	}
	public void checkSearchResult(){
		Assert.assertTrue(WebDriverUtil.isElementVisible(By.xpath(Xpath_Locator.checkSearchResult),20));
		ScreenShotUtil.addScreenShot();
	}

	public void selectHKJockeyClub (){
		WebDriverUtil.scroll2elementPresent(Xpath_Locator.hkJockeyClub);
		WebDriverUtil.clickElement(Xpath_Locator.hkJockeyClub);
		WebDriverUtil.clickElement(Xpath_Locator.applyNowBtn);
		ScreenShotUtil.addScreenShot();
	}

	public void checkBlockedByVPN(){
		delay(3);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Assert.assertTrue(WebDriverUtil.isElementVisible(By.xpath(Xpath_Locator.checkBlockedByVPN), 20));
		String url = driver.getCurrentUrl();
		System.out.println(url);
		Assert.assertTrue(url.contains("https://careers.hkjc.com/psc/cgxprd/EMPLOYEE/HRMS/c/HRS_HRAM_FL.HRS_CG_SEARCH_FL.GBL?Page=HRS_APP_JBPST_FL&Action=U&FOCUS=Applicant&SiteId=20&PostingSeq=1&JobOpeningId=20007768&SourceId=1002&SubSourceId=1017"));
		ScreenShotUtil.addScreenShot();
	}
}
