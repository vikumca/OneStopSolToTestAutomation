package com.JavaSelenium.Automation.GitHubPages;

import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.pages.Components;
import com.JavaSelenium.Automation.core.ui.*;
import com.JavaSelenium.Automation.core.ui.implementations.*;
import com.JavaSelenium.Automation.core.ui.implementations.Button;
import com.JavaSelenium.Automation.core.ui.implementations.TextField;
import com.JavaSelenium.Automation.core.utils.JSExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.event.KeyEvent;

public class NewRepositoryPage extends Components {

    @CacheLookup
    @FindBy(xpath = "//a[text()='Import a repository.']")
    private WebElement lnkImportRepository;

    @CacheLookup
    @FindBy(id = "repository_name")
    private WebElement txtRepositoryName;

    @CacheLookup
    @FindBy(id = "repository_description")
    private WebElement txtDescription;

    @CacheLookup
    @FindBy(id = "repository_auto_init")
    private WebElement chkInitializeRepository;

    @CacheLookup
    @FindBy(xpath = "//*[contains(text(),'Choose a license')]")
    private WebElement lnkAddLicense;

    @CacheLookup
    @FindBy(xpath = "//span[contains(text(),'License:')]")
    private WebElement selectFilterLicense;

    @CacheLookup
    @FindBy(xpath = "//input[@placeholder='Filter licenses...']")
    private WebElement txtLicenseName;



    @CacheLookup
    @FindBy(xpath = "//button[contains(text(),'Create repository')]")
    private WebElement btnCreateRepository;

    public ITextField getRepositoryName() {
        return getComponent(txtRepositoryName, TextField.class, this.getClass());
    }
    public ITextField getRepositoryDescription() {
        return getComponent(txtDescription, TextField.class, this.getClass());
    }
    public ICheckBox getInitializeCheck() {
        return getComponent(chkInitializeRepository, CheckBox.class, this.getClass());
    }
    public IElement getLicense() {
        return getComponent(lnkAddLicense, Element.class, this.getClass());
    }
    public IElement getLicenseFilter() {
        return getComponent(selectFilterLicense, Element.class, this.getClass());
    }

    public ITextField setLicenseName() {
        return getComponent(txtLicenseName, TextField.class, this.getClass());
    }

    public IButton getCreateRepository() {
        return getComponent(btnCreateRepository, Button.class, this.getClass());
    }

    public void enterRepositoryName(String repoName){
        try{
            getRepositoryName().setText(repoName);
            Logs.info("Entered New repository name "+repoName);
        }
        catch (FrameworkExceptions fe){
            Logs.error("Failed to enter the repository name: " +fe);
        }
    }

    public void enterRepositoryDescription(String description){
        try{
            getRepositoryDescription().setText(description);
            Logs.info("Entered repository description "+description);
        }
        catch (FrameworkExceptions fe){
            Logs.error("Failed to enter the repository description: " +fe);
        }
    }

    public void initializeRepository(){
        try{
            getInitializeCheck().check();
            Logs.info("Checked the repository initialization");
        }
        catch (FrameworkExceptions fe){
            Logs.error("Failed to initialize the repository: " +fe);
        }
    }

    public void enterLicense(String licenseName) throws Exception{
        try{
            Robot robot = new Robot();
            getLicense().clickElement();
            JSExecutor.jsScrollIntoView(selectFilterLicense);
            Thread.sleep(1000);
            getLicenseFilter().clickElement();
            setLicenseName().setTextWithSendKey(licenseName, Keys.TAB);
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Logs.info("Successfully entered the License");
        }
        catch (FrameworkExceptions fe){
            Logs.error("Failed to enter the license: "+fe);
        }
    }

    public void clickCreateRepository(){
        try{
            getCreateRepository().click();
            Logs.info("");
        }
        catch (FrameworkExceptions fe){
            Logs.error("Failed to click on create repository");
        }
    }




}
