package com.JavaSelenium.Automation.core.ui;

import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import org.openqa.selenium.WebElement;

public interface ICheckBox extends IElement {
    void check() throws FrameworkExceptions;
    boolean isChecked() throws FrameworkExceptions;
}
