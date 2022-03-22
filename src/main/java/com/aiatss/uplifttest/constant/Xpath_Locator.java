package com.aiatss.uplifttest.constant;

import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.ExcelUtil;
import utilities.WebDriverUtil;

public class Xpath_Locator {

	public static final String inputSearchingBox = "//input[@type='search']";
	public static final String searchBtn = "//button[@type='submit']";
	public static final String checkSearchResult = "//span[contains(text(),'jobs for you')]";
	public static final String hkJockeyClub = "(//a[contains(text(),'The Hong Kong Jockey Club')])[1]";
	public static final String applyNowBtn = "//a[@data-automation='applyNowButton']";
	public static final String checkBlockedByVPN = "//b[contains(.,'Website blocked. Please contact your local end user helpdesk for assistance')]";



}

