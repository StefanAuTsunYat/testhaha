package stepdefinition;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import page.*;
import utilities.ScreenShotUtil;

public class F01Steps extends Base {


	private F01Page f01page = new F01Page();

	@Given("^User opens jobsDB page$")
	public void openJobsDBPage(){
		System.out.println("^open page$");
		ScreenShotUtil.resetNoOfSteps();
		f01page.openJobsDB();
	}

	@When("User inputs Job in Searching box")
	public void userInputsQAAutomationInSearchingBox() throws InterruptedException {
		System.out.println("^inputs Automation in Searching box$");
		ScreenShotUtil.resetNoOfSteps();
		f01page.inputAutomation();
	}

	@And("User clicks {string} button")
	public void userClicksButton(String btn) throws InterruptedException {
		f01page.clickBtn(btn);
	}

	@Then("User will see the search result")
	public void userWillSeeTheSearchResult() {
		f01page.checkSearchResult();
	}

	@Then("User selects a job at HK Jockey Club")
	public void userSelectsAJobAtHKJockeyClub() {
		f01page.selectHKJockeyClub();
	}

	@Then("User is blocked by vpn problem")
	public void userIsBlockedByVpnProblem() {
		f01page.checkBlockedByVPN();
	}
}

