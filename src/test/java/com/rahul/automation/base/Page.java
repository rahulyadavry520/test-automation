package com.rahul.automation.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.rahul.automation.base.ObjectFileReader.getElementFromFile;
import static org.testng.Assert.fail;

public class Page extends BasicPage {

    protected WebDriver driver;
    String pageName;

    public Page(WebDriver driver,String pageName){
        super(driver,pageName);
        this.driver=driver;
        this.pageName=pageName;
    }

    private By getLocators(String locatorType, String locator){
        switch (Locators.valueOf(locatorType)){
            case id:
                return By.id(locator);
            case xpath:
                return By.xpath(locator);
            case name:
                return By.name(locator);
            case classname:
                return By.className(locator);
            case css:
                return By.cssSelector(locator);
            case linktext:
                return By.linkText(locator);
            default:
                return By.id(locator);
        }
    }

    protected By getLocator(String elementName){
        String[] str=getElementFromFile(this.pageName, elementName);
        return getLocators(str[0],str[1]);
    }

    protected WebElement element(String locatorName){
        WebElement element=null;
        try{
            //element=wait.waitForElementToBeVisible(driver.findElement(getLocator(locatorName)));
            element=wait.waitForElementToBePresentAndVisible(getLocator(locatorName));
        }catch (NoSuchElementException ex){
            ex.printStackTrace();
            System.out.println("FAILED: Element locator: "+locatorName+" not found");
            fail("FAILED: Element "+ locatorName + " not found on the " + this.pageName + " !!!");
        }
        return element;
    }

    protected WebElement elements(String locatorName,int index){
        WebElement element=null;
        try{
            element=wait.waitForElementToBeVisible(driver.findElements(getLocator(locatorName)).get(index));
        }catch (NoSuchElementException ex){
            ex.printStackTrace();
            System.out.println("FAILED: Element locator: "+locatorName+" not found");
            fail("FAILED: Element "+ locatorName + " not found on the " + this.pageName + " !!!");
        }
        return element;
    }

    protected WebDriver frame(WebElement element){
        return driver.switchTo().frame(element);
    }
    protected WebDriver defaultContent(){
        return driver.switchTo().defaultContent();
    }
}
