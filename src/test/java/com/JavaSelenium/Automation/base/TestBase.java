package com.JavaSelenium.Automation.base;

import com.JavaSelenium.Automation.core.driver.DriverSetup;
import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.helper.Constants;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.utils.CommonUtils;
import com.JavaSelenium.Automation.core.utils.Config;
import com.JavaSelenium.Automation.core.utils.Waits;
import com.jacob.com.LibraryLoader;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.JavaSelenium.Automation.core.utils.Assertions;

import java.awt.*;
import java.io.File;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.VideoFormatKeys.*;

public class TestBase {
    protected Assertions Assertions;
    protected WebDriver driver;
    protected String strUsername;
    protected String strPassword;
    protected String baseURL;
    private ScreenRecorder screenRecorder;

    @BeforeClass(alwaysRun = true)
    public void init() {
        try {
            Assertions = new Assertions();
            Logs.info("================================ TEST STARTS NOW  =========================================");
        } catch (Exception e) {
            System.out.println("Exceptions:  " + e);
        }
    }

    @BeforeClass(alwaysRun = true)
    public void startingMethodPreReq() {
        try {
            File file = new File("src\\main\\resources\\", "jacob-1.15-M4-x64.dll"); //path to the jacob dll
            System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

            String browserEnvironmentVariable = System.getenv("BROWSER");
            String browser;
            if (browserEnvironmentVariable == null || browserEnvironmentVariable.equalsIgnoreCase("null")) {
                browser = Config.getConfigProperty(Constants.BROWSERTYPE);
            } else {
                browser = browserEnvironmentVariable;
            }
            startRecording();
            DriverSetup.initializeDriver(browser);
            driver = DriverSetup.getDriver();
            driver.manage().window().maximize();
            baseURL = Config.getConfigProperty(Constants.APPURL);
            Logs.info(baseURL);
            Logs.info("================================ TEST METHOD STARTS  =========================================");
            gotoUrl(baseURL);
            Waits.waitForPageLoadJS();
            Waits.explicitWait();
        } catch (final Exception e) {
            Logs.error("Failed to start the scenario"+ e);
        }
    }

    @AfterClass(alwaysRun = true)
    public void logoutAndCloseOpenedBrowsers() throws FrameworkExceptions {
        try {
            if (DriverSetup.browserInitialized && driver != null) {
                driver.close();
                driver.quit();
            }
            Logs.info("================================ TEST METHOD ENDS  =========================================");
            stopRecording();
        }
        catch (Exception fe)
        {
            driver.quit();
            throw new FrameworkExceptions("Failed to Close the Opened browsers"+fe);
        }
    }

    // Login to TP Application
    protected void gotoUrl(String url) {
        driver.get(url);
        Waits.waitForPageLoadJS();
        Logs.info("Navigated to " + url + " link");
    }

    // Prepare test data for Login
    protected void prepareLoginTestData() throws FrameworkExceptions{
        strUsername = Config.getConfigProperty(Constants.USERNAME);
        strPassword = Config.getConfigProperty(Constants.USERPASSWORD);
    }

    public void startRecording() throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        Rectangle captureSize = new Rectangle(0, 0, width, height);
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice().getDefaultConfiguration();
        this.screenRecorder = new ScreenRecorder(gc,
                new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, FormatKeys.MediaType.VIDEO, EncodingKey,
                        ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null);
        this.screenRecorder.start();
    }

    public void stopRecording() throws Exception {
        this.screenRecorder.stop();
    }
}



