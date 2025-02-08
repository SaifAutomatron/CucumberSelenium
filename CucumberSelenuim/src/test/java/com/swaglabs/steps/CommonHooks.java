package com.swaglabs.steps;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.swaglabs.pages.SauceDemoHome;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.Getter;

@Getter
public class CommonHooks {

	private WebDriver driver;
	private SauceDemoHome sDHome;

	@Before(order = 0)
	public void first() {
		System.out.println("CUCUMBER BDD FRAMEWORK...");
	}

	@Before(order = 1)
	public void setUp() {
		if (driver == null) {
			System.out.println("Initializing WebDriver...");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.saucedemo.com/");
		}
	}

	@Before(value = "@login", order = 2)
	public void login() {
		try {
			sDHome = new SauceDemoHome(driver); // Ensure WebDriver is not null
			boolean isLoggedin = sDHome.login("standard_user", "secret_sauce");
			if (!isLoggedin) {
				Assert.fail("Login failed");
			}
		} catch (Exception e) {
			System.err.println("Login failed due to an exception.");
			e.printStackTrace();
			Assert.fail("Login failed due to an exception.");
		}
	}

	@After
	public void tearDown(Scenario scenario) {

		if (scenario.isFailed()) {
			// Take Screenshot
			final byte[] shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			// Embed into Report
			scenario.attach(shot, "image/png", scenario.getName());
		}
		if (driver != null) {
			System.out.println("Closing WebDriver...");
			driver.quit();
			driver = null;
		}
	}

	@After("@finish")
	public void afterTest() {
		System.out.println("Test Case Completed!!");
	}
}