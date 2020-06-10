package com.rahul.automation.utils;

import org.testng.Reporter;

import static com.rahul.automation.utils.ReadProperty.getProperty;

public class TestDataFIleOperations {

    public static String testDataFile="src/main/resources/testdata/DEV_TestData.properties";

    public static String setTestDataFilePath(){
        String tier = getProperty("Config.properties", "tier").trim();
        if (tier.equalsIgnoreCase("dev")) {
            testDataFile = "src/main/resources/testdata/DEV_TestData.properties";
        } else if (tier.equalsIgnoreCase("e2e") || tier.equalsIgnoreCase("E2E")) {
            testDataFile = "src/main/resources/testdata/E2E_TestData.properties";
        }else if (tier.equalsIgnoreCase("qa")) {
            testDataFile = "src/main/resources/testdata/QA_TestData.properties";
        } else if (tier.equalsIgnoreCase("pr")
                || tier.equalsIgnoreCase("pristine")) {
            testDataFile = "src/main/resources/testdata/PR_TestData.properties";
        } else if (tier.equalsIgnoreCase("prod")
                || tier.equalsIgnoreCase("production")) {
            testDataFile = "src/main/resources/testdata/PROD_TestData.properties";
        } else {
            Reporter.log(
                    "YOU HAVE PROVIDED WRONG TIER IN CONFIG!!! using dev test data",
                    true);
        }
        return testDataFile;
    }

    public static String getTestData(String key){
        return getProperty(testDataFile,key);
    }
}
