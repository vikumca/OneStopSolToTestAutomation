package com.JavaSelenium.Automation.GitHubPages;

import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.pages.Components;
import com.JavaSelenium.Automation.core.ui.IHyperLink;
import com.JavaSelenium.Automation.core.ui.implementations.HyperLink;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Components {

    @CacheLookup
    @FindBy(xpath = "//a[text()='Explore GitHubPages']")
    private WebElement lnkExploreGitHub;

    @CacheLookup
    @FindBy(xpath = "(//a[text()='Enterprise' and @href='/enterprise'])[position()=1]")
    private WebElement lnkEnterprise;

    @CacheLookup
    @FindBy(xpath = "//a[text()='Marketplace']")
    private WebElement lnkMarketPlace;

    @CacheLookup
    @FindBy(xpath = "//a[@href='/login']")
    private WebElement lnkSignIn;

    public IHyperLink getSignInLink() {
        return getComponent(lnkSignIn, HyperLink.class, this.getClass());
    }


    public void clickSignIN(){
        try{
            getSignInLink().clickLink();
            Logs.info("Clicked on Sign In Link");
        }
        catch (FrameworkExceptions fe){
            Logs.error("Failed to Click on the Sign IN link"+fe);
        }


    }

}



