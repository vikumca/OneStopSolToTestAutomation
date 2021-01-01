package com.JavaSelenium.Automation.core.ui.implementations;

import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.helper.Constants;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.ui.IHyperLink;
import org.openqa.selenium.WebElement;

public class HyperLink extends Element implements IHyperLink {
    public HyperLink() {
        super();
    }
    public HyperLink(WebElement element, String elementName) {
        super(element, elementName);
    }


    // Get HREF attribute of an Element
    @Override
    public String getHref() throws FrameworkExceptions {
        try {
            return element.getAttribute("href");
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
                    + " getHref() of Frame class");
            throw new FrameworkExceptions(Constants.FORMATTER +
                    Constants.FAILURE_METHOD_MESSAGE + " getHref() of Frame class." + fe);
        }
    }

    // Click on Link Element
    @Override
    public void clickLink() throws FrameworkExceptions {
        try {
            if (clickElement()) {
                Logs.info("HyperLink element: " + elementOriginalName + " clicked");
            } else {
                Logs.error(Constants.FORMATTER + " HyperLink element: "
                        + elementOriginalName + " click failed");
                throw new FrameworkExceptions("HyperLink element: "
                        + elementOriginalName + " not found");
            }
        } catch (Exception fe) {
            Logs.error(Constants.FORMATTER + Constants.FAILURE_METHOD_MESSAGE
                    + " clickLink() of Frame class");
            throw new FrameworkExceptions(Constants.FORMATTER
                    + Constants.FAILURE_METHOD_MESSAGE + " clickLink() of Frame class." + fe);
        }
    }
}


