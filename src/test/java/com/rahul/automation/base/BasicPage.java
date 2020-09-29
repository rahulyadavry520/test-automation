package com.rahul.automation.base;

import com.rahul.automation.utils.ExplicitWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import static com.rahul.automation.base.ObjectFileReader.getPageTitle;
import static com.rahul.automation.utils.ReadProperty.getProperty;

public class BasicPage {
    WebDriver driver;
    protected ExplicitWait wait;
    private String pageName;

    protected BasicPage(WebDriver driver,String pageName){
        PageFactory.initElements(driver,this);
        this.driver=driver;
        this.pageName=pageName;
        this.wait=new ExplicitWait(driver,Long.parseLong(getProperty("Config.properties","timeout")));
    }

    protected void verifyPageTitle(){
        verifyPageTitle(getPageTitle(pageName));
    }

    protected void verifyPageTitle(String pageTitle){
        try {
            wait.waitForPageTitleToBe(pageTitle);
        } catch (TimeoutException ex) {
            Assert.fail("FAILED: PageTitle for " + pageName
                    + " is not exactly: '" + pageTitle
                    + "'!!!\n instead it is :- " + driver.getTitle());
        }
        log("PASSED: PageTitle for " + pageName + " is : '"+ pageTitle + "'");
    }

    protected String window(){
        return driver.getWindowHandle();
    }

    protected WebDriver windowSwitch(){
        String window=window();
        WebDriver newdriver=null;
        for (String name:driver.getWindowHandles()) {
            if (!name.equals(window)) {
                newdriver= driver.switchTo().window(name);
                break;
            }
        }
        return newdriver;
    }

    public void log(String message) {
        Reporter.log(message, true);
    }

    public void hardWait(int seconds){
        wait.hardWait(seconds);
    }
}
