package com.JavaSelenium.Automation.core.ui;

import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;

public interface IHyperLink extends IElement {
    String getHref() throws FrameworkExceptions;
    void clickLink() throws FrameworkExceptions;
}
