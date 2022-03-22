package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.stream.Stream;

@CucumberOptions(
//		monochrome = true,
//		plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//		plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json" },
//		plugin = { "json:target/cucumber-reports/Cucumber.json" },
		plugin = {
				"pretty",
				"json:output/json-report/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
		features = "src/feature",
		glue = { "stepdefinition" },
		tags = "@Testhaha-01"
)
public class TestRun extends AbstractTestNGCucumberTests {
	public static ITestContext itc;

	@BeforeSuite
	public void beforeSuite(ITestContext itc) throws Exception {
		this.itc = itc;
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("");//System.getProperty("user.dir")+output/reportX.html
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(itc.getStartDate());

		Properties props = System.getProperties();
		props.setProperty("extent.reporter.spark.start","true");
		props.setProperty("extent.reporter.spark.config","resource/extent-config.xml");
		props.setProperty("extent.reporter.spark.out", "output/"+timeStamp+"/sparkReport.html");
		props.setProperty("screenshot.dir", "output/"+timeStamp+"/screenshots/");
//		props.setProperty("extent.reporter.spark.out", "report/");
//		props.setProperty("screenshot.dir", "screenshots/");
		props.setProperty("screenshot.rel.path", "screenshots/");
//		props.setProperty("basefolder.name","output/Test");
//		props.setProperty("basefolder.datetimepattern","yyyy.MM.dd_HH.mm.ss");

		extent.attachReporter(spark);

	}

	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser){
		Properties props = System.getProperties();
		props.setProperty("browser",browser);
	}

//	@Override
//	@DataProvider(parallel = true)
//	public Object[][] features() {
//		Object[][] a = super.features();
//		return a;
//	}
	@AfterSuite
	public void afterClass(ITestContext itc) throws Exception {
		String srcPath= ExtentService.getScreenshotFolderName().replace("/"+ExtentService.getScreenshotReportRelatvePath(),"");
		copyFolder(Paths.get("output/json-report/"),Paths.get(srcPath+ File.separator +"json-report"));
		if(itc.getCurrentXmlTest().getParameter("jiraUploadFlag").equalsIgnoreCase("true")){
			System.out.println("******Start Jira Upload********");
			jiraUpload();
		}else{
			System.out.println("******Jira Upload Skipped********");
			System.out.println(itc.getCurrentXmlTest().getParameter("jiraUploadFlag"));
		}
	}
	private void jiraUpload(){
		try {
			String resultJson=String.join(File.separator,System.getProperty("user.dir"),"output",System.getProperty("currReportFolder"),"json-report","cucumber.json");
			System.out.println(resultJson);
			String infoJson=String.join(File.separator,System.getProperty("user.dir"),"resource","jiraInfo.json");
			String cmd = String.format("curl -u PnCBot:PnCAdmin! -X POST -F info=@%s -F result=@%s http://aiahk-jira.aia.biz/rest/raven/1.0/import/execution/cucumber/multipart",
					infoJson,
					resultJson);
			ProcessBuilder PB = new ProcessBuilder("curl","-u","PnCBot:PnCAdmin!","-X","POST","-F","info=@"+infoJson,"-F","result=@"+resultJson,"http://aiahk-jira.aia.biz/rest/raven/1.0/import/execution/cucumber/multipart");
			System.out.println(PB.command());
			PB.redirectErrorStream(true);
			Process process = PB.start();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void copyFolder(Path src, Path dest) throws IOException {
		try (Stream<Path> stream = Files.walk(src)) {
			stream.forEach(source -> copy(source, dest.resolve(src.relativize(source))));
		}
	}
	private void copy(Path source, Path dest) {
		try {
			Files.deleteIfExists(dest);
			Files.copy(source, dest, REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
