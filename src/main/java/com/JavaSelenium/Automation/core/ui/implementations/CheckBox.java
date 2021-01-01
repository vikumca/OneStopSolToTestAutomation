package com.JavaSelenium.Automation.core.ui.implementations;

import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.helper.Constants;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.ui.ICheckBox;
import org.openqa.selenium.WebElement;

public class CheckBox extends Element implements ICheckBox {

    public CheckBox() {  super();   }
    public CheckBox(WebElement element, String elementName) {
        super(element, elementName);
    }

    // Click on CheckBox
    @Override
    public void check() throws FrameworkExceptions {
        try {
            if (isLoaded()) {
                if (!isChecked()) {
                    element.click();
                    Logs.info("Checkbox element: " + elementOriginalName + " checked");

                } else {
                    Logs.info("Checkbox element: " + elementOriginalName + " already checked");
                }
            } else {
                Logs.error(Constants.FORMATTER + " Checkbox element: " + elementOriginalName
                        + " click failed");
                throw new FrameworkExceptions("Checkbox Element: " + elementOriginalName
                        + " not loaded");
            }
        } catch(Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE + " check() of Checkbox class");
            throw new FrameworkExceptions(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE +
                    " check() of Checkbox class." + fe);
        }
    }

    // Verify whether the CheckBox is checked
    @Override
    public boolean isChecked() throws FrameworkExceptions {
        try {
            if (isLoaded()) {
                String checkedAttr = element.getAttribute("checked");
                return null != checkedAttr;
            } else {
                Logs.error(Constants.FORMATTER + " Checkbox element: " + elementOriginalName
                        + " checking failed");
                return false;
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
                    + " isChecked() of Checkbox class"+fe);
            return false;
        }
    }
}

