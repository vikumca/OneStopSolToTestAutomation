package com.JavaSelenium.Automation.GitHubPages;

import com.JavaSelenium.Automation.core.driver.DriverSetup;
import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.pages.Components;
import com.JavaSelenium.Automation.core.ui.IButton;
import com.JavaSelenium.Automation.core.ui.IHyperLink;
import com.JavaSelenium.Automation.core.ui.ITextField;
import com.JavaSelenium.Automation.core.ui.implementations.Button;
import com.JavaSelenium.Automation.core.ui.implementations.HyperLink;
import com.JavaSelenium.Automation.core.ui.implementations.TextField;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class RepositoryPage extends Components{


    @CacheLookup
    @FindBy(xpath = "//span[contains(text(),'Settings')]")
    private WebElement lnkSettings;

    @CacheLookup
    @FindBy(xpath = "//summary[contains(text(), 'Delete this repository')]")
    private WebElement btnDeleteRepo;

    @CacheLookup
    @FindBy(xpath = "//*[@id='options_bucket']/div[10]/ul/li[4]/details/details-dialog/div[3]/form/p/input")
    private WebElement txtConfirmRepoName;

    @CacheLookup
    @FindBy(xpath = "//span[contains(text(),'I understand the consequences, delete this repository')]")
    private WebElement btnDeleteRepoConfirmation;


    public IHyperLink getSettings() { return getComponent(lnkSettings, HyperLink.class, this.getClass());    }

    public IButton getDeleteRepo() { return getComponent(btnDeleteRepo, Button.class, this.getClass());    }

    public ITextField getDeleteConfirmation() { return getComponent(txtConfirmRepoName, TextField.class, this.getClass());    }

    public IButton getDeleteRepoconfirmation() { return getComponent(btnDeleteRepoConfirmation, Button.class, this.getClass());    }

    public void clickSettings(){
        try{
            getSettings().clickLink();
        }
        catch (FrameworkExceptions fe){
            Logs.error("Failed to click on Settings "+fe);
        }
    }

    public void clickDeleteRepository(){
        try{
            getDeleteRepo().click();
        }
        catch (FrameworkExceptions fe){
            Logs.error("Failed to click on Delete repository "+fe);
        }
    }

    public void enterRepoConfirmationName(String repoName){
        try {
            //Thread.sleep(2000);
            getDeleteConfirmation().setText(repoName);
        }
        catch (Exception fe){
            Logs.error("Failed to enter the confirmation "+fe);
        }
    }

    public void clickDeleteRepoConfirmation() {
        try {
            getDeleteRepoconfirmation().click();
        } catch (FrameworkExceptions fe) {
            Logs.error("Failed to click on the delete confirmation button "+fe);
        }
    }

    public String getPageTitle(){
        return DriverSetup.getDriver().getTitle();
    }

}
