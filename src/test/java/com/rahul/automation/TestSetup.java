package com.rahul.automation;

import com.rahul.automation.pages.FareyeLogin;
import com.rahul.automation.pages.GoogleLaunchPageActions;
import com.rahul.automation.pages.LoginPage;
import com.rahul.automation.utils.CustomFunctions;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.rahul.automation.utils.ReadProperty.getProperty;
import static com.rahul.automation.utils.TestDataFIleOperations.getTestData;
import static com.rahul.automation.utils.TestDataFIleOperations.setTestDataFilePath;

public class TestSetup {

    public static WebDriver driver;
    public static String appUrl=null;
    private WebDriverFactory webDriverFactory;

    String takeScreenshot;
    String screenshotPath;

    public CustomFunctions customFunctions;
    public FareyeLogin fareyeLogin;

    private void initPage(){
        customFunctions = new CustomFunctions(driver);
        fareyeLogin=new FareyeLogin(driver);
    }

    @BeforeSuite(alwaysRun = true)
    public void testSessionSetup(){
        setTestDataFilePath();
        appUrl=getTestData("app_url");
        Reporter.log("Application Url is :- " + appUrl, true);
    }

    @BeforeClass
    public void launchApplication(){
        webDriverFactory=new WebDriverFactory();
        testInitiator();
        launchApplication(appUrl);
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestMethod(Method method) {
        String className = method.getDeclaringClass().getName();
        className = className.substring(className.lastIndexOf('.') + 1);
        System.out.println(
                "**********************************************************" + "\n" + "Running Test:" + className + "."
                        + method.getName() + "\n" + "**********************************************************");
    }

    @AfterMethod(alwaysRun = true)
    public void take_screenshot_on_failure(ITestResult result) {
        takeScreenshot = getSessionConfig().get("take-screenshot").toString();
        screenshotPath = getSessionConfig().get("screenshot-path").toString();

        customFunctions.takeScreenShotOnException(result, takeScreenshot, screenshotPath, this.getClass().getSimpleName());
    }

    @AfterClass(alwaysRun = true)
    public void stop_test_session() {
        closeBrowserSession();
    }

    private void testInitiator(){
        setTestDataFilePath();
        configureBrowser();
        initPage();
    }

    private void configureBrowser(){
        driver = webDriverFactory.getDriver(getSessionConfig());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(getSessionConfig().get("timeout")),
                TimeUnit.SECONDS);
    }

    private Map<String, String> getSessionConfig() {
        String[] configKeys = { "tier", "browser", "seleniumserver", "seleniumserverhost", "timeout", "driverpath", "take-screenshot", "screenshot-path" };
        Map<String, String> config = new HashMap<String, String>();
        for (String string : configKeys) {
            config.put(string, getProperty("./Config.properties", string));
        }
        return config;
    }

    private void launchApplication(String baseUrl) {
        Reporter.log("The application url is :- " + baseUrl, true);
        driver.get(baseUrl);
    }

    public void closeBrowserSession() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
