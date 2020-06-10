package com.rahul.automation.base;

import com.rahul.automation.utils.ExplicitWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import static com.rahul.automation.utils.ReadProperty.*;

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
}
