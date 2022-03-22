package stepdefinition;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import base.DriverContext;
import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.testng.annotations.Parameters;
import utilities.ExcelUtil;
import base.Base;
import utilities.ScreenShotUtil;

public class Hook extends Base {

	@Before
	public void beforeScenario(Scenario scenario) throws IOException {
		ScreenShotUtil.setScenario(scenario);
		String testResultFolder = ExtentService.getScreenshotFolderName().split("/")[1];
		System.setProperty("currReportFolder", testResultFolder);
		Collection<String> tags = scenario.getSourceTagNames();
		String path = null;
		if(System.getProperty("browser").equals("Chrome")){
			path = System.getProperty("user.dir") +File.separator +"resource"+ File.separator +"download";
		}else if(System.getProperty("browser").equals("Edge")){
			path = System.getProperty("user.home") +File.separator +"Downloads";
		}
		Arrays.stream(new File(path).listFiles())
				.filter(File->(File.getName().contains("CPD_agents")&&File.getName().contains(".xls")))
				.forEach(File::delete);

		for(String tag: tags){
			System.out.println(tag);
		}
		Base.setCaseID(scenario.getName());
		// System.out.println("This will run before the Scenario");
		System.out.println("-----------------------------------");
		System.out.println("Starting - " + scenario.getName());
		System.out.println("-----------------------------------");

		if(System.getProperty("browser").equals("Chrome")){
			setChromeCapabilities();
		}else if(System.getProperty("browser").equals("Edge")){
			setEdgeCapabilities();
		}
		ExcelUtil.readExcel(scenario.getName());
		scenario.log("Using "+System.getProperty("browser")+" browser");
		scenario.attach("[Using "+System.getProperty("browser")+" browser]","text/plain","");
	}

//	@AfterStep
//	public void afterStep(Scenario scenario) {
//		System.out.println("AFTER STEP");
////		TakesScreenshot ts = (TakesScreenshot) DriverContext.getDriver();
////		byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
////		scenario.attach(screenshot, "image/png","image");
//
//	}

	@After
	public void afterScenario(Scenario scenario) {

		if("FAILED".equals(scenario.getStatus().toString())){
			ScreenShotUtil.saveScreenShotForStep("error");
			scenario.log("Error occurs");
		}
//		DriverContext.getDriver().quit();

//		DriverContext.getDriver().close();
//		DriverContext.setDriver(null);
	}


}
