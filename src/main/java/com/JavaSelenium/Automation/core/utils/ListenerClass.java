package com.JavaSelenium.Automation.core.utils;

import com.JavaSelenium.Automation.core.logger.Logs;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;

public class ListenerClass extends TestListenerAdapter {

    @Attachment
    public byte[] captureScreenshot(WebDriver d) {
        return ((TakesScreenshot) d).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    // Capture the Screen shot on Failure
    public void onTestFailure(ITestResult tr) {
        Object webDriverAttribute = tr.getTestContext().getAttribute("WebDriver");
        if (webDriverAttribute instanceof WebDriver) {
            Logs.info("Screenshot captured for test case:" +
                    tr.getMethod().getMethodName());
            captureScreenshot((WebDriver) webDriverAttribute);
        }
    }
}


