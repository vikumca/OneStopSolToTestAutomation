package com.JavaSelenium.Automation.core.ui;

import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import org.openqa.selenium.Keys;

public interface ITextField extends IElement {
    void setText(String text) throws FrameworkExceptions;
    void clearText() throws FrameworkExceptions;
    void setTextWithSendKey(String text, Keys key) throws FrameworkExceptions;
    void sendKey(Keys key) throws FrameworkExceptions;
}
