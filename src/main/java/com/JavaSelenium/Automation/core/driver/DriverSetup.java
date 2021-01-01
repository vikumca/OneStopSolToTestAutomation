package com.JavaSelenium.Automation.core.driver;

import com.JavaSelenium.Automation.core.helper.Constants;
import com.JavaSelenium.Automation.core.logger.LogEventListener;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.utils.Config;
import com.JavaSelenium.Automation.core.utils.FileUtility;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class DriverSetup {

    private static final ThreadLocal<WebDriver> webDriverThreadLocal;
    public static boolean browserInitialized = false;

    static {
        webDriverThreadLocal = new ThreadLocal<>();
    }

    private DriverSetup() {}

    /**
     * To initialize the Driver
     * @param   browser
     *          - Browser (e.g. Chrome, Firefox IE etc)
     * @return
     */
    public static void initializeDriver(String browser) {
        WebDriver driver = null;
        if (("firefox").equalsIgnoreCase(browser)) {
            driver = initiateFireFoxDriver();
            Logs.info("Started Firefox Browser successfully.");
        } else if (("chrome").equalsIgnoreCase(browser)) {
            driver = initiateChromeDriver();
            Logs.info("Started Chrome Browser successfully.");
        } else if (("ie").equalsIgnoreCase(browser)) {
            driver = initiateIEDriver();
            Logs.info("Started IE Browser successfully.");
        }
        driver = new EventFiringWebDriver(driver).register(new LogEventListener());
        setDriver(driver);
        browserInitialized = true;
    }


    /**
     * To initialize the Chrome Driver
     * @param
     * @return  driver
     *          - chrome driver
     */
    private static WebDriver initiateChromeDriver() {
        WebDriver driver = null;
        try {
            String chromePath = Config.getConfigProperty(Constants.CHROMEDRIVERPATH);
            System.setProperty("webdriver.chrome.driver", chromePath);
            ChromeDriverService chromeService = ChromeDriverService.createDefaultService();
            String commandSwitches = "WebDriverCommandSwitch";
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
            if (!commandSwitches.isEmpty() || commandSwitches.contains("--")) {
                Logs.info("User had specified [" + commandSwitches + "] command switch for Chrome Browser");
                ChromeOptions options = new ChromeOptions();
                String[] commandList = commandSwitches.split(",");
                for (String command : commandList) {
                    options.addArguments(command);
                    options.addArguments("--test-type");
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.addArguments("--start-maximized");
                    options.addArguments("chrome.switches",	"--disable-extensions");
                }
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(chromeService, capabilities);
                Logs.info("Started Google Chrome Driver with command switches successfully");
            } else {
                driver = new ChromeDriver(chromeService);
                Logs.info("Started Google Chrome Browser successfully");
            }
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            Logs.error("Failed to initiate Chrome Driver"+e);
        }
        return driver;
    }

    /**
     * To initialize the FireFox Driver
     * @param
     * @return  driver
     *          - firefox driver
     */
    private static WebDriver initiateFireFoxDriver() {
        WebDriver driver = null;
        try {
            String firefoxPath = FileUtility.getFileSeparatedPath(Config.getConfigProperty(Constants.FIREFOXDRIVERPATH));
            System.setProperty("webdriver.gecko.driver", firefoxPath);
            driver = new FirefoxDriver();
            Logs.info("Started FireFox Driver");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            Logs.error("Failed to initiate FireFox Driver"+e);
        }
        return driver;
    }

    /**
     * To initialize the IE Driver
     * @param
     * @return  driver
     *          - IE driver
     */
    private static WebDriver initiateIEDriver() {
        WebDriver driver = null;
        try {
            String iePath = FileUtility.getFileSeparatedPath(Config.getConfigProperty(Constants.IEDRIVERPATH));
            System.setProperty("webdriver.ie.driver", iePath);
            driver = new InternetExplorerDriver();
            Logs.info("Started Internet Explorer Driver");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            Logs.error("Failed to initiate IE Driver"+e);
        }
        return driver;
    }

    //Setter
    private static void setDriver(WebDriver driver) {
        webDriverThreadLocal.set(driver);
    }

    //Getter
    public static WebDriver getDriver() {
        WebDriver driver = null;
        driver = webDriverThreadLocal.get();
        if (driver == null)
            throw new IllegalStateException("Driver not set...");
        return driver;
    }
}
