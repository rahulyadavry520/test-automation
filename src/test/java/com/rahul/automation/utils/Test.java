package com.rahul.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		String FirefoxDriverPath="/home/rahulyadav/Downloads/geckodriver";
		System.setProperty("webdriver.gecko.driver", FirefoxDriverPath);
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get("https://codepen.io/brenzy/pen/zxMZmO");
		System.out.println(driver.findElements(By.tagName("iframe")).size());
		driver.switchTo().frame(0);  //outer frame
		driver.switchTo().frame(0);  //inner frame
		WebElement source=driver.findElement(By.id("dragFrame-5"));

		Actions builder=new Actions(driver);
		Actions action = builder.clickAndHold(source);
		builder.build();
		action.perform();

		driver.switchTo().defaultContent();

		driver.switchTo().frame(0);  //outer frame
		driver.switchTo().frame(1);  //inner frame
		WebElement target=driver.findElement(By.id("element2-2"));

		builder = new Actions(driver);
		builder.moveToElement(target).perform();
		builder.build();
		builder.release();
		builder.release(target).perform();
	}
}
