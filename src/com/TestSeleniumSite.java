package com;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * A Selenium Java Project to understand  
 * 8 different types of locators namely 
 * id, name, className, tagName, linkText, 
 * partialLinkText, CSS selector and xpath.
 */

public class TestSeleniumSite {	
	WebDriver driver;
	JavascriptExecutor jse;
	WebElement image;
	
	public void invokeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\SONY\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get("https://www.amazon.com");
			System.out.println("Website is successfully lauched");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		TestSeleniumSite test = new TestSeleniumSite();
		test.invokeBrowser();
		Thread.sleep(6000);
		test.searchByName();
		test.checkCartCount();
		
		test.searchByLinkText(); 
		test.searchByPartialLinkText();
		test.searchByTagName(); 
		test.searchByCssSelector(); 
		test.searchByXPath();
	}
	
/**
 * A Test case to check the cart count is valid
 * @throws InterruptedException 
 */
private void checkCartCount() throws InterruptedException {
		int addedItem, cartCount = 0;
		addedItem = searchByClassName();
		cartCount = searchById();
		if (cartCount == addedItem) {
			System.out.println("Test Successfull");
		}
		else {
			System.out.println("Test Failed");
		}
		
	}

/**
 * Identify the WebElement using the ID attribute
 * driver.findElement(By.id(“IdValue”))
 * @throws InterruptedException 
 */
private int searchById() throws InterruptedException {
		WebElement cartCount;
		int cart;
		cartCount = driver.findElement(By.id("nav-cart"));
		String items =cartCount.getAttribute("aria-label");
		String[] itemsArray = items.split(" ");
		cart = Integer.parseInt(itemsArray[0]);
		return cart;
	}

/**
 * Identify the WebElement using the Name attribute
 * driver.findElement(By.name(“nameValue”));
 */
private void searchByName() {
	driver.findElement(By.name("field-keywords")).sendKeys("apple pencil");
	driver.findElement(By.id("nav-search-submit-button")).click();
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}

/**
 * Use the Class attribute for identifying the object
 * driver.findElement(By.className(“classValue”));
 */
private int searchByClassName() {
	int counter = 0;
	driver.findElement(By.className("s-image")).click();
	try {
		Thread.sleep(3000);
		jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0,500)");
		Thread.sleep(3000);
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(3000);
		counter++;
		driver.navigate().back();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	return counter;
}

/**
 * Use the text in hyperlinks to locate the WebElement
 * driver.findElement(By.linkText(“textofLink”));
 * @throws InterruptedException 
 */
private void searchByLinkText() throws InterruptedException {
	driver.findElement(By.linkText("Today's Deals")).click();
	Thread.sleep(3000);
	}

/**
 * Use a part of the text in hyperlinks to locate the desired WebElement
 * driver.findElement(By.partialLinkText(“PartialTextofLink”));
 */
private void searchByPartialLinkText() {
	driver.findElement(By.partialLinkText("Deals")).click();
	}

/**
 * Use the TagName to locate the desired WebElement
 * driver.findElement(By.tagName(“htmlTag”));
 */
private void searchByTagName() {
    image= driver.findElement(By.tagName("img"));
    System.out.println(image.getAttribute("src"));
	}

/**
 * CSS used to create style rules in web page is leveraged to locate the desired WebElement
 * driver.findElement(By.cssSelector(“cssValue”));
 */
private void searchByCssSelector() {
	}

/**
 * 	Use XPath to locate the WebElement
 *	driver.findElement(By.xpath(“xpathValue”));
 */
private void searchByXPath() {
	}
}
