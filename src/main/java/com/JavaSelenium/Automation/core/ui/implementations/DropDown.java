package com.JavaSelenium.Automation.core.ui.implementations;

import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.helper.Constants;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.ui.IDropDown;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DropDown extends Element implements IDropDown {
    private String strExceptionText  = "DropDown Element: ";
    private Select select = null;

    public DropDown() {
        super();
    }
    public DropDown(WebElement element, String elementName) {
        super(element, elementName);
        select = new Select(element);
    }

    // Select Item in Drop Down by Value
    @Override
    public void selectByValue(String valueToBeSelected) throws FrameworkExceptions {
        try {
            if (valueToBeSelected == null)
                return;
            if (isLoaded()) {
                select = new Select(element);
                select.selectByValue(valueToBeSelected);
                Logs.info(strExceptionText + elementOriginalName + " => "
                        + valueToBeSelected + Constants.SELECTLOGMESSAGE);
            } else {
                Logs.error(Constants.FORMATTER + strExceptionText + elementOriginalName
                        + Constants.SELECTLOGMESSAGE_FAILURE);
                throw new FrameworkExceptions(strExceptionText + elementOriginalName
                        + " not loaded in method selectByValue()");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
                    + " selectByValue() of DropDown class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
                    + " selectByValue() of DropDown class." + fe);
        }
    }

    // Select Item in Drop Down by Visible Text
    @Override
    public void selectByVisibleText(String valueToBeSelected) throws FrameworkExceptions{
        try {
            if (valueToBeSelected == null)
                return;
            if (isLoaded()) {
                select.selectByVisibleText(valueToBeSelected);
                Logs.info(strExceptionText + elementOriginalName + " => "
                        + valueToBeSelected + Constants.SELECTLOGMESSAGE);
            } else {
                Logs.error(Constants.FORMATTER + strExceptionText + elementOriginalName
                        + Constants.SELECTLOGMESSAGE_FAILURE);
                throw new FrameworkExceptions(strExceptionText + elementOriginalName
                        + " not loaded in method selectByVisibleText()");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
                    + " selectByVisibleText() of DropDown class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
                    + " selectByVisibleText() of DropDown class." + fe);
        }
    }

    // Select Item in Drop Down by Index
    @Override
    public void selectByIndex(int valueToBeSelected) throws FrameworkExceptions {
        try {
            if (isLoaded()) {
                select.selectByIndex(valueToBeSelected);
                Logs.info(strExceptionText + elementOriginalName + " => " + valueToBeSelected + Constants.SELECTLOGMESSAGE);
            } else {
                Logs.error(Constants.FORMATTER + strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_FAILURE);
                throw new FrameworkExceptions(strExceptionText + elementOriginalName + " not loaded in method selectByIndex()");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " selectByIndex() of DropDown class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " selectByIndex() of DropDown class." + fe);
        }
    }

    // Select Item in Drop Down by Ignoring Special Characters
    @Override
    public void selectByIgnoringSpecialCharacters(String text)	throws FrameworkExceptions {
        if(text == null)
            return;
        try {
            if (isLoaded()) {
                List<WebElement> optionElements = select.getOptions();

                for (WebElement optionElement : optionElements) {
                    String option = optionElement.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
                    String partValue = text.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
                    if (option.contains(partValue)) {
                        String optionIndex = optionElement.getAttribute("index");
                        selectByIndex(Integer.parseInt(optionIndex));
                        break;
                    }
                }
                Logs.info(strExceptionText + elementOriginalName + " => " + text + Constants.SELECTLOGMESSAGE);
            } else {
                Logs.error(Constants.FORMATTER + strExceptionText + elementOriginalName + Constants.SELECTLOGMESSAGE_FAILURE);
                throw new FrameworkExceptions(strExceptionText + elementOriginalName + " not loaded in method selectByIgnoringSpecialCharacters()");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " selectByIgnoringSpecialCharacters() of DropDown class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " selectByIgnoringSpecialCharacters() of DropDown class." + fe);
        }
    }
}
