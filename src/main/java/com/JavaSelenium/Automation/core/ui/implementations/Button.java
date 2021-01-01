package com.JavaSelenium.Automation.core.ui.implementations;

import com.JavaSelenium.Automation.core.ui.IButton;
import org.openqa.selenium.WebElement;

public class Button extends Element implements IButton {
    public Button()
    {
        super();
    }
    public Button(WebElement element, String elementName) {
        super(element, elementName);
    }
}
