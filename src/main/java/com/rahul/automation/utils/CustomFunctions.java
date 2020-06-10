package com.rahul.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomFunctions {

    static WebDriver driver;

    public CustomFunctions(WebDriver driver) {
        CustomFunctions.driver = driver;
    }

    public void takeScreenShotOnException(ITestResult result, String takeScreenshot, String path, String testname) {
        System.out.println(result);
        System.out.println(takeScreenshot);
        System.out.println(path);
        System.out.println(testname);

        if (result.getStatus() == ITestResult.FAILURE) {
            if (takeScreenshot.equalsIgnoreCase("true")) {
                if (driver != null) {
                    takeScreenshot(path, testname);
                }
            }
        }
    }

    private void takeScreenshot(String path, String testname) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_a");
        Date date = new Date();
        String date_time = dateFormat.format(date);
        System.out.println("PATH.............. " + path);

        File file = new File(path + File.separator + date_time);
        boolean exists = file.exists();
        if (!exists) {
            new File(path + File.separator + testname + File.separator
                    + date_time).mkdir();
        }

        File scrFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        try {
            String saveImgFile = path + File.separator + testname
                    + File.separator + date_time + File.separator
                    + "screenshot.png";
            FileUtils.copyFile(scrFile, new File(saveImgFile));
            // if (uploadImage.equalsIgnoreCase("true")) {
            // uploadScreenshotToServer(ftpUrl, userid, password, date_time,
            // saveImgFile);
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
