package com.rahul.projectname.automation.tests;

import com.rahul.automation.TestSetup;
import com.rahul.automation.utils.ExtentTestManager;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class MyTest extends TestSetup {
    @Test
    public void Test01_Farye_Login(Method method) {
        ExtentTestManager.startTest(method.getName(), "Google Click Gmail Icon Scenario");
        fareyeLogin.login();
    }


}
