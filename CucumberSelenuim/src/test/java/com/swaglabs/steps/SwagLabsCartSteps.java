package com.swaglabs.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.swaglabs.pages.SauceDemoInventory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SwagLabsCartSteps {

	WebDriver driver;
	SauceDemoInventory inventorypage;

	public SwagLabsCartSteps(CommonHooks common) {
		this.driver = common.getDriver();
		this.inventorypage = new SauceDemoInventory(driver);
	}

	@Given("I am logged in to Swag Labs")
	public void i_am_logged_in_to_swag_labs() {
		String homeURL = driver.getCurrentUrl();
		Assert.assertEquals("Home Page Verification failed", "https://www.saucedemo.com/inventory.html", homeURL);
	}

	@When("I want to add item to the cart")
	public void i_want_to_add_item_to_the_cart(DataTable dataTable) {
		boolean isAdded = inventorypage.addItemToCart(dataTable.cell(1, 0));
		if (!isAdded) {
			Assert.fail("Failed to add item to cart");
		}
	}

	@Then("I am sucessfully able to add Item to the cart")
	public void i_am_sucessfully_able_to_add_item_to_the_card() {
		inventorypage.getShoppingCartBtn().click();
		String item = inventorypage.getInventoryAddedItem().getText();
		System.out.println("Inventory Item name: " + item);
		if (!item.equals("Sauce Labs Bike Light")) {
			Assert.fail("Cart item validation failed");
		}

	}

	@When("I want to add {string} to the cart")
	public void i_want_to_add_to_the_cart(String item) {
		inventorypage = new SauceDemoInventory(driver);
		boolean isAdded = inventorypage.addItemToCart(item);
		if (!isAdded) {
			Assert.fail("Failed to add item to cart");
		}
	}

	@Then("I am sucessfully able to add {string} to the cart")
	public void i_am_sucessfully_able_to_add_to_the_card(String itemName) {
		inventorypage.getShoppingCartBtn().click();
		String item = inventorypage.getInventoryAddedItem().getText();
		System.out.println("Inventory Item name: " + item);
		if (!item.equals(itemName)) {
			Assert.fail("Cart item validation failed");
		}
	}

}
