package com.JavaSelenium.Automation.core.ui;

import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import org.openqa.selenium.WebElement;

public interface IElement {
    WebElement getElement();
    boolean clickElement() throws FrameworkExceptions;
    boolean isLoaded() throws FrameworkExceptions;
    String getText() throws FrameworkExceptions;
    String getTextWithOutVerify() throws FrameworkExceptions;
    String getAttribute(String value) throws FrameworkExceptions;
    void click() throws FrameworkExceptions;
    boolean isDisplayed() throws FrameworkExceptions;
    boolean isReadOnly(String value) throws FrameworkExceptions;
    boolean jsClick();
    boolean isEditable() throws FrameworkExceptions;
    String getTagName() throws FrameworkExceptions;

}
