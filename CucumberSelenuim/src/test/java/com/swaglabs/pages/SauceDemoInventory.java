package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swaglabs.utilities.WebElementUtils;

import lombok.Getter;

@Getter
public class SauceDemoInventory {
	WebDriver driver;
	WebElementUtils wutil = new WebElementUtils();

	public SauceDemoInventory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Add to cart']")
	private WebElement addToCartButton;

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	private WebElement shoppingCartBtn;

	@FindBy(xpath = "//div[@class='inventory_item_name']")
	private WebElement inventoryAddedItem;

	public WebElement getInventoryItem(String itemName) {
		String dynamicXPath = "//div[text()='" + itemName + "']";
		return driver.findElement(By.xpath(dynamicXPath));
	}

	public boolean addItemToCart(String itemName) {
		try {
			WebElement item = getInventoryItem(itemName);
			item.click(); 
			wutil.waitAndClick(addToCartButton);
			System.out.println(itemName + " successfully added to cart");
			return true;
		} catch (Exception e) {
			System.err.println("Failed to add item to cart: " + itemName);
			e.printStackTrace();
			return false;
		}
	}
}