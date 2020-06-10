package com.rahul.automation.base;

import org.openqa.selenium.WebDriver;

public class Page extends BasicPage{

    protected WebDriver driver;
    String pageName;

    public Page(WebDriver driver,String pageName){
        super(driver,pageName);
        this.driver=driver;
        this.pageName=pageName;
    }
}
