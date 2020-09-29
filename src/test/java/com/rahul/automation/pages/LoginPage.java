package com.rahul.automation.pages;

import com.rahul.automation.base.Page;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

    public LoginPage(WebDriver driver) {
        super(driver, "Login");
    }

    public void login(){
        element("Gmail_Input_Username").sendKeys("rahulyadavry520");
        hardWait(5);
        element("Gmail_Username_Next_btn").click();
        hardWait(5);
        element("Gmail_Input_Password").sendKeys("R$hul15900");
        hardWait(5);
        element("Gmail_Username_Next_btn").click();
        hardWait(5);
    }
}
