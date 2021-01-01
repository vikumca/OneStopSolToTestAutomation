package com.JavaSelenium.Automation.tests;

import com.JavaSelenium.Automation.base.TestBase;
import com.JavaSelenium.Automation.core.driver.DriverSetup;
import com.JavaSelenium.Automation.core.helper.Constants;
import com.JavaSelenium.Automation.core.utils.Config;
import com.JavaSelenium.Automation.core.utils.Waits;
import com.jacob.com.LibraryLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import autoitx4java.AutoItX;
import org.testng.asserts.Assertion;

import java.io.File;

public class AutoIT {

    @Test
    public void autoITIntegrationTest() throws Exception{
        AutoItX autoItX = new AutoItX();

        File file = new File("src\\main\\resources\\", "jacob-1.14.3-x64.dll"); //path to the jacob dll
        System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

        String chromePath = Config.getConfigProperty(Constants.CHROMEDRIVERPATH);
        System.setProperty("webdriver.chrome.driver", chromePath);

        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/upload");
        driver.findElement(By.id("file-upload")).click();
        Thread.sleep(1000);

        autoItX.ControlSetText("Open","","[CLASS:Edit;INSTANCE:1]","C:\\Test\\AutoITTest.txt");

        autoItX.controlClick("Open","&Open","[CLASS:Button;INSTANCE:1]", "" );

        Thread.sleep(1000);
        driver.findElement(By.id("file-submit")).click();
        Assert.assertEquals("File Uploaded!",driver.findElement(By.cssSelector("#content > div > h3")).getText());
    }
}

