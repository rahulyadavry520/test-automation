package com.rahul.automation.pages;

import com.rahul.automation.base.Page;
import org.openqa.selenium.WebDriver;

public class FareyeLogin extends Page {
    public FareyeLogin(WebDriver driver) {
        super(driver, "FareyeLogin");
    }

    public void login(){
        element("Username").sendKeys("postipl_admin");
        element("Next_btn").click();
        element("Password").sendKeys("Fareye@123");
        element("Sign_In_Btn").click();
        //hardWait(100);
        element("Old_ui_button").click();
        element("old_ui_link").click();
        windowSwitch();
        element("Users_link").click();
        element("Last_Mile_Exec_link").click();
        hardWait(5);
    }
}
