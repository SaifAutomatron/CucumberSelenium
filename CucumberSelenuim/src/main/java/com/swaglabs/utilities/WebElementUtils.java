package com.swaglabs.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot; // Updated from EventFiringWebDriver
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;


public class WebElementUtils   {

	/**
	 * Select dropdown element by index number
	 */
	public void selectDropdownOption(WebElement selectele, int index) {
		Select sel = new Select(selectele);
		sel.selectByIndex(index);
	}

	/**
	 * Select dropdown element by visible text
	 */
	public void selectDropdownOption(WebElement selectele, String text) {
		Select sel = new Select(selectele);
		sel.selectByVisibleText(text);
	}

	/**
	 * Select dropdown element by value
	 */
	public void selectDropdownOption(String text, WebElement selectele) {
		Select sel = new Select(selectele);
		sel.selectByValue(text);
	}
	
	/**
	 * used to wait & click for expected element in GUI(customised wait)
	 * 
	 * @param element
	 * @throws Throwable
	 */
	public void waitAndClick( WebElement element) throws Exception { int count =
			0; while(count < 40) { try { element.click(); break; }catch (Throwable e) {
				Thread.sleep(500); count++; } } }
	/**

	/**
	 * Wait for element to be clickable and then click it (Explicit wait)
	 */
	public void waitForElementToclick(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(PathConst.EXPLICITWAIT));
		WebElement elementclickable = wait.until(ExpectedConditions.elementToBeClickable(locator));
		elementclickable.click();
	}

	/**
	 * Wait for element to be visible
	 */
	public WebElement waitForElementVisibility(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(PathConst.EXPLICITWAIT));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Custom wait for an element to appear
	 */
	public void waitforElement(WebElement element) throws InterruptedException {
		int count = 0;
		while (count < 40) {
			try {
				if (element.isDisplayed())
					break;
			} catch (Exception e) {
				Thread.sleep(500);
				count++;
			}
		}
	}

	/**
	 * Scroll to a WebElement using JavaScriptExecutor
	 */
	public void jsScrollToWebElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Scroll by specific coordinates
	 */
	public void jsScroll(WebDriver driver, int x, int y) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
	}

	/**
	 * Click using JavaScriptExecutor
	 */
	public void jsClick(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	/**
	 * Perform a hover action
	 */
	public void mouseActionHoverTo(WebDriver driver, WebElement element) {
		new Actions(driver).moveToElement(element).perform();
	}

	/**
	 * Drag and Drop using Actions class
	 */
	public void mouseActionDragandDrop(WebDriver driver, WebElement sourceElement, WebElement targetElement) {
		new Actions(driver).dragAndDrop(sourceElement, targetElement).perform();
	}

	/**
	 * Switch to frame by frame index
	 */
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * Switch to frame by WebElement
	 */
	public void switchToFrame(WebDriver driver, WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}

	/**
	 * Switch to another window based on partial/complete title
	 */
	public void switchToWindow(WebDriver driver, String browserTitle) {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
			if (driver.getTitle().contains(browserTitle)) {
				break;
			}
		}
	}

	/**
	 * Accept alert popup
	 */
	public void alertOK(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * Dismiss alert popup
	 */
	public void alertCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * Refresh the page
	 */
	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	/**
	 * Wait for the page to load (Updated for Selenium 4)
	 */
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(PathConst.IMPLICITWAIT));
	}

	/**
	 * Take Screenshot (Updated for Selenium 4)
	 */
	public String takeScreenShot(String methodName, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + methodName + ".png";
		Files.copy(src, new File(screenshotPath));
		return screenshotPath;
	}

	/**
	 * Flash WebElement for visibility (Updated JavaScript Executor)
	 */
	public static void flashWebElement(WebDriver driver, WebElement element) {
		for (int i = 0; i < 5; i++) {
			highlightWebElement("yellow", element, driver);
		}
	}

	/**
	 * Highlight WebElement using JavaScript Executor
	 */
	public static void highlightWebElement(String colour, WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: " + colour + "; border: 2px solid red;');",
				element);

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}