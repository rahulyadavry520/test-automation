package com.rahul.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class WebDriverFactory {

    private static String browser;
    private static DesiredCapabilities capabilities=new DesiredCapabilities();

    public WebDriver getDriver(Map<String,String> seleniumConfig){
        browser=seleniumConfig.get("browser");
        if(seleniumConfig.get("seleniumserver").equalsIgnoreCase("local")){
            if(browser.equalsIgnoreCase("chrome")){
                return getChromeDriver(seleniumConfig.get("driverpath"));
            }
            if(browser.equalsIgnoreCase("firefox")){
                return getFirefoxDriver(seleniumConfig.get("driverpath"));
            }
        }
        return new FirefoxDriver();
    }

    private WebDriver getChromeDriver(String driverPath){
        driverPath=driverPath+"chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);
        capabilities.setJavascriptEnabled(true);
        return new ChromeDriver(capabilities);
    }

    private static WebDriver getFirefoxDriver(String driverPath) {
        driverPath=driverPath+"geckodriver";
        System.setProperty("webdriver.gecko.driver", driverPath);
        return new FirefoxDriver();
    }
}
