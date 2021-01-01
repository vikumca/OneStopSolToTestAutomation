package com.JavaSelenium.Automation.core.ui.implementations;

import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.helper.Constants;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.ui.ITextField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TextField extends Element implements ITextField {
    private static final String AUTO_COMPLETE_FRAME = ".ac_table_completer";
    private static final String COLUMNS_OF_AUTO_COMPLETE = "td.ac_cell";

    public TextField() {
        super();
    }

    public TextField(WebElement element, String elementName) {
        super(element, elementName);
    }

    // Set Text in Text Field Element
    @Override
    public void setText(String text) throws FrameworkExceptions {
        if(text == null)
            return;
        try {
            if (isLoaded()) {
                element.sendKeys(text);
                Logs.info(Constants.TEXTFIELDLOGMESSAGE + elementOriginalName
                        + " => " + text + Constants.SETTEXTLOGMESSAGE);
            } else {
                Logs.error(Constants.FORMATTER + Constants.TEXTFIELDLOGMESSAGE
                        + elementOriginalName + " failed to enter text");
                throw new FrameworkExceptions(Constants.TEXTFIELDLOGMESSAGE
                        + elementOriginalName + " not loaded in method setText()");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
                    + " setText() of TextField class");
            throw new FrameworkExceptions(Constants.FORMATTER
                    + Constants.FAILURE_METHOD_MESSAGE + " setText() of TextField class." + fe);
        }
    }

    // Clear Text from Text Field
    @Override
    public void clearText() throws FrameworkExceptions {
        try {
            if (isLoaded()) {
                element.clear();
                Logs.info(Constants.TEXTFIELDCLEARTEXTMESSAGE);
            } else {
                Logs.error(Constants.FORMATTER + " " + Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " failed to clear text");
                throw new FrameworkExceptions(Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " not loaded in method clearText()");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " clearText() of TextField class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " clearText() of TextField class." + fe);
        }
    }



    // Using Keyboard keys
    @Override
    public void sendKey(Keys key) throws FrameworkExceptions {
        try {
            if (isLoaded()) {
                element.sendKeys(key);
            } else {
                Logs.error(Constants.FORMATTER + Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " failed to send keys");
                throw new FrameworkExceptions(Constants.TEXTFIELDLOGMESSAGE + elementOriginalName + " not loaded in method setTextWithSendKey()");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " sendKey() of TextField class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " sendKey() of TextField class." + fe);
        }
    }

    // Set Text with Send Keys
    @Override
    public void setTextWithSendKey(String text, Keys key) throws FrameworkExceptions {
        if(text == null)
            return;
        try {
            if (isLoaded()) {
                element.sendKeys(text);
                Thread.sleep(2000);
                Logs.info(Constants.TEXTFIELDLOGMESSAGE + elementOriginalName
                        + " => " + text + Constants.SETTEXTLOGMESSAGE);
                element.sendKeys(key);
            } else {
                Logs.error(Constants.FORMATTER + Constants.TEXTFIELDLOGMESSAGE
                        + elementOriginalName + " failed to enter text");
                throw new FrameworkExceptions(Constants.TEXTFIELDLOGMESSAGE
                        + elementOriginalName + " not loaded in method setTextWithSendKey()");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
                    + " setTextWithSendKey() of TextField class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
                    + " setTextWithSendKey() of TextField class." + fe);
        }
    }


}
