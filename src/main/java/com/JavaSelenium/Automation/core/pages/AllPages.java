package com.JavaSelenium.Automation.core.pages;

import com.JavaSelenium.Automation.core.driver.DriverSetup;
import org.openqa.selenium.support.PageFactory;

public class AllPages {
    private AllPages(){}

    public static <T> T getPage(Class<T> clazz) {
        return PageFactory.initElements(DriverSetup.getDriver(), clazz);
    }
}


