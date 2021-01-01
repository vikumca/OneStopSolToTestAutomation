package com.JavaSelenium.Automation.core.helper;

import com.JavaSelenium.Automation.core.utils.FileUtility;

public class Constants {

    /* The Constant CONFIGFILE. */
    public static final String CONFIGFILEPATH = FileUtility.getFileSeparatedPath("src/main/resources/Config.xml");

    /** The Constant BrowserType. */
    public static final String BROWSERTYPE="BrowserType";

    /** The Constant Application URL. */
    public static final String APPURL="AppURL";

    /** Main User Credentials */
    public static final String USERNAME="UserName";
    public static final String USERPASSWORD ="Password";

    /**The Driver path**/
    public static final String CHROMEDRIVERPATH= "ChromeDriverPath";
    public static final String IEDRIVERPATH="IEDriverPath";
    public static final String FIREFOXDRIVERPATH = "FireFoxDriverPath";

    /** Element Wait Time**/
    public static final String ELEMENTWAITTIME ="ElementWaitTime";

    /**Logger**/
    public static final String ELEMENTLOGMESSAGE = "Element element: ";
    public static final String ISLOADEDLOGMESSAGE_FAILURE = " loading failed";
    public static final String SELECTLOGMESSAGE = " is Selected";
    public static final String SELECTLOGMESSAGE_SUCCESSS = " selected successfully";
    public static final String SELECTLOGMESSAGE_FAILURE = " selection failed";
    public static final String TEXTFIELDLOGMESSAGE="TextField element: ";
    public static final String SETTEXTLOGMESSAGE = " set text successfully";
    public static final String TEXTFIELDCLEARTEXTMESSAGE = "Text field text is erased";
    public static final String FORMATTER = "#### ";
    public static final String FAILURE_METHOD_MESSAGE = "Failure at the method: ";


    private Constants(){}
}
