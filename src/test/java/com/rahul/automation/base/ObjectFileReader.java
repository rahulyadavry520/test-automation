package com.rahul.automation.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.rahul.automation.utils.ReadProperty.getProperty;

public class ObjectFileReader {

    static String tier;
    static String filePath="src/test/resources/PageObjectRepository/";

    public static String getPageTitle(String pageName){
        setTier();
        String title="";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath+tier+"/"+pageName+".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String key=line.split(":",3)[0].trim();
                if(key.equalsIgnoreCase("pageTitle")||key.equalsIgnoreCase("title"))
                    title=line.split(":",3)[1].trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return title;
    }

    public static String[] getElementFromFile(String pageName,String locatorName){
        setTier();
        String str[]=new String[2];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath+tier+"/"+pageName+".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String key=line.split(":",3)[0].trim();
                if(key.equalsIgnoreCase(locatorName)) {
                    str[0]=line.split(":",3)[1].trim();
                    str[1]=line.split(":",3)[2].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void setTier(){
        switch (Tiers.valueOf(getProperty("Config.properties", "tier"))) {
            case production:
            case PROD:
            case PRODUCTION:
            case Production:
            case prod:
                tier = "PROD/";
                break;
            case qa:
            case QA:
            case Qa:
                tier = "QA/";
                break;
            case Dev:
            case DEV:
            case dev:
                tier = "DEV/";
                break;
            case e2e:
            case E2E:
                tier = "E2E/";
        }
    }

}
