package com.swaglabs.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import com.swaglabs.pages.SauceDemoHome;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SwagLabsHomeSteps {

	WebDriver driver;
	SauceDemoHome sDHome;

	public SwagLabsHomeSteps(CommonHooks common) {
		this.driver = common.getDriver();
		this.sDHome = new SauceDemoHome(driver);
	}

	@Given("Using username and password")
	public void using_username_and_password() {
		System.out.println("Login Scenario");
	}

	@When("I login using the username {string} and  password {string}")
	public void i_login_using_the_username_and_password(String username, String password) {
		boolean isLoggedin = sDHome.login(username, password);
		if (!isLoggedin) {
			Assert.fail("Login failed");
		}
	}

	@Then("I will be nagivated to home page")
	public void i_will_be_nagivated_to_home_page() {
		String homeURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + homeURL);
		Assert.assertEquals("https://www.saucedemo.com/inventory.html", homeURL);
	}
}