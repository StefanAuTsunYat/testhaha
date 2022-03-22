package base;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import com.aventstack.extentreports.gherkin.model.Scenario;
//import io.cucumber.java.Scenario;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import utilities.ExcelUtil;

public class Base {
	public static Scenario scenario;

	private static String caseID;
	public static String platform;
	private static long start_time;

	public static void setStartTime() {
		start_time = System.currentTimeMillis();
	}

	public static String getRunTime() {
		long run_time = System.currentTimeMillis() - start_time;
		long currentMS = run_time % 1000;
		long totalSeconds = run_time / 1000;
		long currentSecond = totalSeconds % 60;
		long totalMinutes = totalSeconds / 60;
		long currentMinute = totalMinutes % 60;

		String use_time = String.valueOf(currentMinute) + "m" + String.valueOf(currentSecond) + "."
				+ String.valueOf(currentMS) + "s";
		return use_time;
	}

//	public static void setErrorMsg(String msg) {
//		error_msg += msg;
//	}

	public static String getCaseID() {
		return caseID;
	}

	public static void setCaseID(String caseID) {
		Base.caseID = caseID;
	}

	public static boolean checkIfServerIsRunnning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void setChromeCapabilities() throws IOException {
		ITestContext itc = test.TestRun.itc;
		String headlessFlag = itc.getCurrentXmlTest().getParameter("headlessFlag");
      

		ChromeOptions options = new ChromeOptions();

		Proxy proxy = new Proxy();
		proxy.setProxyType(Proxy.ProxyType.PAC);
		proxy.setProxyAutoconfigUrl("http://pac.zscalertwo.net/aia.com/AIA_HK_PROD.pac");
//      options.setProxy(proxy);

//		options.addArguments("user-data-dir=C:\\Users\\hlcla01\\AppData\\Local\\Google\\Chrome\\User Data"); // CC
      	String userDataDir = itc.getCurrentXmlTest().getParameter("userDataDir"); // to handle the run time parameter
		if("".equalsIgnoreCase(userDataDir) || "True".equalsIgnoreCase(userDataDir)) {
			options.addArguments("--user-data-dir=" + userDataDir);
		}
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("window-size=1920,1080");
		if("".equalsIgnoreCase(headlessFlag) || "True".equalsIgnoreCase(headlessFlag)) {
			options.addArguments("--headless");
		}
//		options.addArguments("--incognito");

		String downloadFilepath = System.getProperty("user.dir") +File.separator +"resource"+ File.separator +"download";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		options.setExperimentalOption("prefs", chromePrefs);

//		String driverPath = System.getProperty("user.dir") + File.separator + "resource" + File.separator + "driver" + File.separator + "chromedriver";
		String driverPath = System.getProperty("user.dir") + File.separator + "resource" + File.separator + "driver" + File.separator + "chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
//		DriverContext.setDriver (new ChromeDriver(options));
		DriverContext.setDriver (new ChromeDriver(options));
	}

	public static void setEdgeCapabilities() throws IOException {

		EdgeOptions options = new EdgeOptions();

		Proxy proxy = new Proxy();
		proxy.setProxyType(Proxy.ProxyType.PAC);
		proxy.setProxyAutoconfigUrl("http://pac.zscalertwo.net/aia.com/AIA_HK_PROD.pac");
//		options.setProxy(proxy);
//		options.addArguments("--ignore-certificate-errors");

		String downloadFilepath = System.getProperty("user.dir") +File.separator +"resource"+ File.separator +"download";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//		chromePrefs.put("profile.default_content_settings.popups", 0);
//		chromePrefs.put("download.default_directory", downloadFilepath);
//		options.setExperimentalOption("prefs", chromePrefs);

		String driverPath = System.getProperty("user.dir") + File.separator + "resource" + File.separator + "driver" + File.separator + "msedgedriver.exe";
		System.setProperty("webdriver.edge.driver", driverPath);
		DriverContext.setDriver (new EdgeDriver(options));
	}


	public void delay(long delaySec) {
		try {
			Thread.sleep(delaySec * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public static void renameReportFolder() {
		String oldPath = System.getProperty("user.dir") + File.separator + "output" + File.separator + "report.html";
		String newPath = System.getProperty("user.dir") + File.separator + "output" + File.separator + "screenshots" + File.separator + caseID + File.separator + "report.html";
		File newFile = new File(newPath);
		new File(oldPath).renameTo(newFile);
		System.out.println("report renamed to " + caseID + "_report.html");
	}

}
