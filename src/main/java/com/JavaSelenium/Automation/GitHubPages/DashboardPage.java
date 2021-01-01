package com.JavaSelenium.Automation.GitHubPages;

import com.JavaSelenium.Automation.core.driver.DriverSetup;
import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.pages.Components;
import com.JavaSelenium.Automation.core.ui.IHyperLink;
import com.JavaSelenium.Automation.core.ui.ITextField;
import com.JavaSelenium.Automation.core.ui.implementations.HyperLink;
import com.JavaSelenium.Automation.core.ui.implementations.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends Components {

    @CacheLookup
    @FindBy(xpath = "(//a[@href='/new'])[2]")
    private WebElement lnkNewRepository;

    @CacheLookup
    @FindBy(xpath = "/html/body/div[1]/header/div[1]/a")
    private WebElement lnkHomePage;

    @CacheLookup
    @FindBy(id = "dashboard-repos-filter-left")
    private WebElement txtSearchRepo;

    public String getPageTitle(){
        return DriverSetup.getDriver().getTitle();
    }

    public IHyperLink getNewRepository() {
        return getComponent(lnkNewRepository, HyperLink.class, this.getClass());
    }

    public IHyperLink getHomePage() {
        return getComponent(lnkHomePage, HyperLink.class, this.getClass());
    }

    public ITextField getSearchRepository() {
        return getComponent(txtSearchRepo, TextField.class, this.getClass());
    }

    public void clickNewRepository() {
        try{
            getNewRepository().clickLink();
        }
        catch (FrameworkExceptions fe){
            Logs.error("Failed to click on New RepositoryPage link "+fe);
        }
    }

    public void clickHomePage() {
        try{
            getHomePage().clickLink();
        }
        catch (FrameworkExceptions fe){
            Logs.error("Failed to click on Home page link "+fe);
        }
    }

    public void enterRepoName(String repoName) throws FrameworkExceptions {
        try {
            getSearchRepository().setText(repoName);
        }
        catch (FrameworkExceptions fe) {
            Logs.error("Failed to enter text in Search Repository text field "+fe);
        }
    }

    public boolean searchRepo(String expectedRepoName)  {
        String actualRepoName = "";
        boolean blnFlag=false;
        try {
            List<WebElement> ulList = DriverSetup.getDriver().findElements(
                    By.xpath("//ul[@class='list-style-none filterable-active']"));
            if(ulList.size() > 0) {
                for (int counter = 0; counter < ulList.size(); counter++) {
                    List<WebElement> liList = ulList.get(counter).findElements(By.tagName("li"));
                    if(liList.size() > 0) {
                        for (int liCounter=0; liCounter < liList.size(); liCounter++) {
                            WebElement element = liList.get(liCounter).findElement(By.cssSelector("span:nth-child(3)"));
                            actualRepoName = element.getAttribute("title").toString();
                            if(actualRepoName.contains(expectedRepoName))
                            {
                                blnFlag = true;
                                break;
                            }
                        }
                    }
                    if(blnFlag) { break; }
                }
            }
        }
        catch (Exception fe) {
            Logs.error("Failed to Repository in the list: "+fe);
        }
        return blnFlag;
    }

    public void searchAndClickOnRepo(String expectedRepoName)  {
        String actualRepoName = "";
        boolean blnFlag = false;
        try {
            List<WebElement> ulList = DriverSetup.getDriver().findElements(By.xpath("//ul[@class='list-style-none filterable-active']"));
            if(ulList.size() > 0) {
                for (int counter = 0; counter < ulList.size(); counter++) {
                    List<WebElement> liList = ulList.get(counter).findElements(By.tagName("li"));
                    if(liList.size() > 0) {
                        for (int liCounter=0; liCounter < liList.size(); liCounter++) {
                            WebElement element = liList.get(liCounter).findElement(By.cssSelector("span:nth-child(3)"));
                            actualRepoName = element.getAttribute("title").toString();
                            if(actualRepoName.contains(expectedRepoName))
                            {
                                element.click();
                                blnFlag = true;
                                break;
                            }
                        }
                    }
                    if(blnFlag) {
                        break;
                    }
                }
            }
        }
        catch (Exception fe) {
            Logs.error("Failed to click on the Searched Repository "+fe);
        }
    }



}
