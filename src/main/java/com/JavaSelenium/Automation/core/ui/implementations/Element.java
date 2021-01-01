package com.JavaSelenium.Automation.core.ui.implementations;

import com.JavaSelenium.Automation.core.driver.DriverSetup;
import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.helper.Constants;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.ui.IElement;
import com.JavaSelenium.Automation.core.utils.Config;
import com.JavaSelenium.Automation.core.utils.JSExecutor;
import com.JavaSelenium.Automation.core.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.Assertion;

public class Element implements IElement {
    protected WebElement element;
    protected WebDriver driver;
    public String elementOriginalName = "";

    public Element(){
        // TODO Auto-generated method stub
        getInitializedDriver();
    }

    public Element(WebElement element) {
        this.element = element;
        getInitializedDriver();
    }

    public Element(WebElement element, String elementName) {
        this.element = element;
        elementOriginalName = elementName;
        getInitializedDriver();
    }

    // Get the initialized Driver
    private void getInitializedDriver() {
        driver = DriverSetup.getDriver();
    }

    // Verify the element is loaded
    @Override
    public boolean isLoaded() throws FrameworkExceptions {
        try {
            boolean flag = false;
            if (Waits.fluentWait(element, Integer.parseInt(Config.getConfigProperty(Constants.ELEMENTWAITTIME))) != null) {
                flag = true;
                Logs.info(Constants.ELEMENTLOGMESSAGE + elementOriginalName + " loaded");
            } else {
                Assertion hardAssert = new Assertion();
                hardAssert.assertFalse(true, Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE);
            }
            return flag;
        } catch (FrameworkExceptions ex) {
            Logs.error(Constants.FORMATTER + Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE);
            throw new FrameworkExceptions("Element: " + elementOriginalName + " not loaded"+ex);
        }
    }

    // Verify the element is Displayed
    @Override
    public boolean isDisplayed() throws FrameworkExceptions {
        boolean flag = false;
        try {
            flag = element.isDisplayed();
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " isDisplayed() of Element class");
            return false;
        }
        return flag;
    }

    // Click on Element
    @Override
    public boolean clickElement() throws FrameworkExceptions {
        try {
            if (isLoaded()) {
                element.click();
                return true;
            } else {
                Logs.error(Constants.FORMATTER + Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE);
                return false;
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class");
            return false;
        }
    }


    // Return Web Element
    @Override
    public WebElement getElement(){ return element; }

    // Get Text from the Element
    @Override
    public String getText() throws FrameworkExceptions {
        try {
            if (isLoaded()) {
                String text = null;
                if (getAttribute("value") != null)
                    text = getAttribute("value");
                else if (getAttribute("text") != null)
                    text = getAttribute("text");
                else if (getAttribute("textContent") != null)
                    text = getAttribute("textContent");
                Logs.info(Constants.ELEMENTLOGMESSAGE + elementOriginalName + " get text successfully");
                return text;
            } else {
                Logs.error(Constants.FORMATTER + Constants.ELEMENTLOGMESSAGE + elementOriginalName + " failed to get text");
                throw new FrameworkExceptions("Element Element: " + elementOriginalName + " not loaded in method getText()");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class." + fe);
        }
    }

    // Get Text from the element with out verifying
    @Override
    public String getTextWithOutVerify() throws FrameworkExceptions {
        String text = null;
        try {
            if(getAttribute("value") != null)
                text = getAttribute("value");
            else if(getAttribute("text") != null)
                text = getAttribute("text");
            else if(getAttribute("textContent") != null)
                text = getAttribute("textContent");
            Logs.info(Constants.ELEMENTLOGMESSAGE + elementOriginalName + " get text successfully");
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class." + fe);
        }
        return text;
    }

    // Click on Element
    @Override
    public void click() throws FrameworkExceptions {
        try {
            if (clickElement()) {
                Logs.info("Button element: " + elementOriginalName + " clicked");
            } else {
                Logs.error(Constants.FORMATTER + " Button element: " + elementOriginalName
                        + " click failed");
                throw new FrameworkExceptions("Button element: " + elementOriginalName + " not found");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " click() of Element class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE +
                    " click() of Element class." + fe);
        }
    }

    // Get the Attribute of an Element
    @Override
    public String getAttribute(String value) throws FrameworkExceptions {
        try {
            return element.getAttribute(value);
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getAttribute() of Element class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getAttribute() of Element class." + fe);
        }
    }

    // Verify the Element is Read only
    @Override
    public boolean isReadOnly(String value) throws FrameworkExceptions {
        try {
            if (element.getAttribute("readonly")!=null){
                return element.getAttribute("readonly").equals(value);
            }
            else if (element.getAttribute("disabled")!=null){
                return element.getAttribute("disabled").equals(value);
            }
            else
                return false;
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " isReadOnly() of Element class");
            return false;
        }
    }

    // Click on Element using Java Script
    @Override
    public boolean jsClick() {
        try {
            if (isLoaded()) {
                JSExecutor.getJavaScriptExec().executeScript("arguments[0].click();", element);
                return true;
            } else {
                Logs.error(Constants.FORMATTER + Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE);
                return false;
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class");
            return false;
        }
    }

    // Verify the element is Edit able
    @Override
    public boolean isEditable() throws FrameworkExceptions {
        return element.isEnabled();
    }

    // To Get the Element TagName
    /**
     * To Get the Element Tag Name
     * @return strTagName
     *           - Element Tag Name
     */
    @Override
    public String getTagName() throws FrameworkExceptions {
        try {
            if (isLoaded()) {
                String strTagName = element.getTagName();
                return strTagName;
            } else {
                Logs.error(Constants.FORMATTER + Constants.ELEMENTLOGMESSAGE + elementOriginalName + Constants.ISLOADEDLOGMESSAGE_FAILURE);
                return "";
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " getText() of Element class");
            return "";
        }
    }
}
