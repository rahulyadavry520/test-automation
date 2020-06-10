package com.rahul.automation.utils;

import org.openqa.selenium.WebDriver;
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
}
