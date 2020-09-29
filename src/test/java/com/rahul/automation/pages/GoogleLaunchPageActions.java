package com.rahul.automation.pages;

import com.rahul.automation.base.Page;
import org.openqa.selenium.WebDriver;

public class GoogleLaunchPageActions extends Page {

    public GoogleLaunchPageActions(WebDriver driver) {
        super(driver, "GoogleLaunch");
    }

    public void verifyLaunchPage(){
        verifyPageTitle();
    }

    public void clickOnGmailIcon() {
        element("Gmail_Menu").click();
        frame(element("Gmail_frame"));
        element("Gmail_Icon").click();
        defaultContent();
    }

    public void gotoSignInPage() {
        elements("Gmail_Sign_In_Btn",1).click();
        windowSwitch();
    }



}
