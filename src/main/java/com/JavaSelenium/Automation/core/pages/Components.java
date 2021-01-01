package com.JavaSelenium.Automation.core.pages;

import com.JavaSelenium.Automation.core.helper.Constants;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.ui.IElement;
import com.JavaSelenium.Automation.core.utils.Config;
import com.JavaSelenium.Automation.core.utils.Waits;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Components {
    private static BidiMap locatorsMap;

    public Components() {
        locatorsMap = new TreeBidiMap();
        if( !Waits.waitForPageLoadJS() ) {
            Logs.error("Page did not load completely");
        } else {
            Logs.info("Page loaded successfully");
        }
    }

    /**
     * Create an object for a particular element which is passed as a parameter.
     * @param   elementClass
     *          - element ( e.g. Button, TextField etc)
     * @return  constructor.newInstance()
     *          - new instance object for the parameter(element class)
     */
    public <E extends IElement> E getComponent(Class<E> elementClass) {
        try {
            Waits.waitForPageLoadJS();
            Constructor<E> constructor = elementClass.getConstructor();
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            Logs.error(String.valueOf(e));
            Logs.error("Failed in getComponents() method of Components class");
        }
        return null;
    }

    /**
     * Create an object for a particular element which is passed as a parameter.
     * @param   elementClass
     *          - element(webelement)
     * @param   elementClass
     *          - element ( e.g. Button, TextField etc)
     * @param   referenceClass
     *          - To get the variable name for logging purpose
     * @return  constructor.newInstance()
     *          - new instance object for the parameter(element class)
     */
    public <E extends IElement> E getComponent(final WebElement element, Class<E> elementClass, Class referenceClass) {
        try {
            Waits.waitForPageLoadJS();
            WebElement waitElementStatus = Waits.componentWait(element,
                    Integer.parseInt(Config.getConfigProperty(Constants.ELEMENTWAITTIME)));
            if(waitElementStatus != null) {
                String xp = element.toString().split("-> ")[1].trim();
                String elementName = getVariableNameFromValue(referenceClass, xp.substring(0, xp.length() - 1).split(": ")[1]);
                Constructor<E> constructor = elementClass.getConstructor(WebElement.class, String.class);
                return constructor.newInstance(element, elementName);
            } else {
                Constructor<E> constructor = elementClass.getConstructor();
                return constructor.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException e) {
            Logs.error(String.valueOf(e));
        } catch (Exception fe) {
            Logs.error("Failed in getComponents() method of Components class. Element: "
                    +getVariableCausedException(referenceClass, element) + fe);
        }
        return null;
    }

    /**
     * To get the variable name.
     * @param   value
     *          - variable Name as per selenium internals
     * @param   referenceClass
     *          - Send the class where webElement is located
     *          - To get the variable name for logging purpose
     * @return  value
     *          - variable Name as per selenium internals
     */
    public String getVariableNameFromValue(Class referenceClass, String value) {
        if(locatorsMap.containsValue(value)) {
            String key = (String) locatorsMap.getKey(value);
            String[] arrOfPackages = key.split(referenceClass.getName() + ".");
            return arrOfPackages[arrOfPackages.length-1];
        } else {
            Field[] fields = referenceClass.getDeclaredFields();
            for (Field fld : fields)
                try {
                    String variableName = fld.getDeclaredAnnotations()[0].toString();
                    if (variableName.contains(value)) {
                        locatorsMap.put(referenceClass.getName() + "." + fld.getName(), value);
                        return fld.getName();
                    }
                } catch (Exception e) {
                    Logs.error(String.valueOf(e));
                }

            return  value;
        }
    }

    /**
     * To get the exception caused by variables.
     * @param   callClass
     *          - Send the class where webElement is located
     *          - To get the variable name for logging purpose
     * @param    element
     *          - WebElement
     * @return  var
     *          - variable Name as per selenium internals
     */
    public String getVariableCausedException(Class callClass, WebElement element) {
        Field [] field = callClass.getDeclaredFields();
        String var = null;
        for (Field fld:field) {
            if(fld.isAnnotationPresent(FindBy.class))
            {
                fld.setAccessible(true);
                try {
                    if(fld.get(this).toString().equalsIgnoreCase(element.toString())) {
                        var = fld.getName();
                        break;
                    }
                } catch (IllegalAccessException e) {
                    Logs.error(String.valueOf(e));
                }
            }
        }
        return var;
    }

    public static String getElementName(String how) {
        return (String)locatorsMap.getKey(how);
    }
}
