package com.JavaSelenium.Automation.core.utils;
import com.JavaSelenium.Automation.core.driver.DriverSetup;
import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.helper.Constants;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.JavaSelenium.Automation.core.utils.JSExecutor.getJavaScriptExec;
import static com.JavaSelenium.Automation.core.utils.JSExecutor.jsGetValue;
public class Waits {

    private static int waitTime = 0;
    public static long defaultTimeout = 2000;
    private static WebDriver driver = DriverSetup.getDriver();

    private Waits(){}

    // Fluent Wait
    public static WebElement fluentWait(final WebElement element, long duration) throws FrameworkExceptions {
        try {
            return new FluentWait<>(driver).withTimeout(duration, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .until(new Function<WebDriver, WebElement>() {
                        @Override
                        public WebElement apply(WebDriver input) {
                            boolean isPresent = element.isDisplayed() && element.isEnabled();
                            if (isPresent) {
                                return element;
                            } else {
                                return null;
                            }
                        } });
        } catch (Exception e) {
            throw new FrameworkExceptions("Element: "+element+" not found"+e);
        }
    }

    // Get Wait Time
    public static int getWaitTime() throws FrameworkExceptions {
        if(waitTime == 0)
            waitTime = Integer.parseInt(Config.getConfigProperty(Constants.ELEMENTWAITTIME));
        return waitTime;
    }

    // Wait for the Page to load
    public static boolean waitForPageLoadJS() {
        try{
            Thread.sleep(1000);
            new WebDriverWait(driver, getWaitTime())
                    .until(new Predicate<WebDriver>() {
                               @Override
                               public boolean apply(WebDriver webDriver) {
                                   return ("complete").equals(getJavaScriptExec()
                                           .executeScript("return document.readyState"));
                               }
                           }
                    );
            return ("complete").equals(getJavaScriptExec().executeScript("return document.readyState"));
        }
        catch (Exception e)
        {
            Logs.error("Page did not load");
            return false;
        }
    }

    // Explicit Wait
    public static void explicitWait()
    {
        try{
            Thread.sleep(defaultTimeout);
        }
        catch (Exception e)  {
            Logs.error("Failed in explicitWait method"+e);
        }
    }
    // Component Wait
    public static WebElement componentWait(final WebElement element, long duration) throws FrameworkExceptions {
        try {
            return new FluentWait<>(driver).withTimeout(duration, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .until(new Function<WebDriver, WebElement>() {
                        @Override
                        public WebElement apply(WebDriver input) {
                            boolean isPresent = element.isDisplayed() && (element.isEnabled() ||
                                    (element.getAttribute("readonly")!= null && (element.getAttribute("readonly").equals("true") ||
                                            element.getAttribute("readonly").equals("readonly")))
                                    || (element.getAttribute("disabled")!=null && element.getAttribute("disabled").equals("true")));
                            if (isPresent) {
                                return element;
                            } else {
                                return null;
                            }

                        }
                    });
        } catch (TimeoutException toe) {
            return null;
        } catch (Exception e) {
            throw new FrameworkExceptions("Element: "+element+" not found"+e);
        }
    }
}
