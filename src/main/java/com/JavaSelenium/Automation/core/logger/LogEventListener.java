package com.JavaSelenium.Automation.core.logger;

import com.JavaSelenium.Automation.core.pages.Components;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.TestListenerAdapter;

public class LogEventListener extends TestListenerAdapter implements WebDriverEventListener {

    private By lastFindBy;

    @Override
    public void beforeNavigateTo(String url, WebDriver webDriver) {
        Logs.info("WebDriver navigating to:'"+url+"'");
    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        lastFindBy = by;
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver webDriver) {
        String locator = element.toString().split("-> ")[1];
        String elementName = Components.getElementName(locator.substring(0, locator.length() - 1).split(": ")[1]);
        Logs.info("WebDriver clicking on: '"+ (elementName == null? locator: elementName) +"'");
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onException(Throwable error, WebDriver webDriver) {
        if (error.getClass().equals(NoSuchElementException.class)){
            Logs.error("WebDriver error: Element not found "+lastFindBy);
        } else if(error.getClass().equals(StaleElementReferenceException.class)){
            Logs.error("Stale element exception:");
        } else if(error.getClass().equals(UnhandledAlertException.class)) {
            Logs.error("Alert exception: ");
        } else {
            Logs.error("WebDriver error:" + error);
        }
    }

    @Override
    public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterNavigateBack(WebDriver arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterNavigateForward(WebDriver arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterNavigateTo(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeClickOn(WebElement arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeNavigateBack(WebDriver arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeNavigateForward(WebDriver arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
    }
}
