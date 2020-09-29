package com.rahul.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {
    WebDriver driver;
    WebDriverWait wait;
    long timeout;

    public ExplicitWait(WebDriver driver, long timeout){
        this.driver=driver;
        this.timeout=timeout;
        this.wait=new WebDriverWait(driver,timeout);
    }

    public boolean waitForPageTitleToBe(String expectedTitle){
        return wait.until(ExpectedConditions.titleIs(expectedTitle));
    }

    public WebElement waitForElementToBeVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBePresentAndVisible(By by){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void hardWait(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
