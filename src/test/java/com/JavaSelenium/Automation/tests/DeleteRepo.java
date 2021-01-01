package com.JavaSelenium.Automation.tests;

import com.JavaSelenium.Automation.GitHubPages.*;
import com.JavaSelenium.Automation.base.TestBase;
import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.JavaSelenium.Automation.core.pages.AllPages;
import com.JavaSelenium.Automation.core.utils.ListenerClass;
import com.JavaSelenium.Automation.core.utils.RallyIntegration;
import com.JavaSelenium.Automation.core.utils.Waits;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Title;

@Listeners({ ListenerClass.class })
public class DeleteRepo extends TestBase{

    private SignInPage getSignInPage() {return AllPages.getPage(SignInPage.class);  }
    private HomePage getHomePage() {return AllPages.getPage(HomePage.class);  }
    private DashboardPage getDashboardPage() {return AllPages.getPage(DashboardPage.class);  }
    private RepositoryPage getRepoPage() {return AllPages.getPage(RepositoryPage.class);  }
    String TestCaseID = "";

    @Title("TestCaseID - Test to delete an existing repository")
    @Test(enabled = true, description = "Test to delete an existing Repository in GITHUB")
    public void deleteRepository(ITestContext testContext) throws Exception{
        testContext.setAttribute("WebDriver", driver);
        Assertions.setSoftAssert(new SoftAssert());
        TestCaseID = "RallyTestCaseID";
        prepareLoginTestData();
        getHomePage().clickSignIN();
        Waits.waitForPageLoadJS();
        Assertions.logInformation("Successfully clicked on SignIn at Home page");
        getSignInPage().signIN(strUsername,strPassword);
        Waits.waitForPageLoadJS();
        getDashboardPage().enterRepoName("Sample-Repo");
        Waits.explicitWait();
        Assertions.hardAssertTrue(getDashboardPage().searchRepo("Sample-Repo"),
                "Repository is found in the list",
                "Failed to find the repo in the list, hence the execution is stopped from proceeding further");
        getDashboardPage().searchAndClickOnRepo("Sample-Repo");
        Assertions.logInformation("Successfully clicked on the searched repository");
        getRepoPage().clickSettings();
        boolean blnPageTitle = getRepoPage().getPageTitle().contains("Options");
        Assertions.softAssertTrue(blnPageTitle,"Clicked on Settings button",
                "Failed to navigate to settings page");
        getRepoPage().clickDeleteRepository();
        Assertions.logInformation("Successfully clicked on the delete repository");
        getRepoPage().enterRepoConfirmationName("vikumca/Sample-Repo");
        Assertions.logInformation("Successfully entered the repository name to be deleted");
        getRepoPage().clickDeleteRepoConfirmation();
        Assertions.softAssertFalse(getDashboardPage().searchRepo("Sample-Repo"),
                "Repository is deleted from the list","Failed to delete the repo from the list");
        Assertions.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void updateTestCaseStatus(ITestResult result) throws Exception{
        getDashboardPage().clickHomePage();

//        if(result.getStatus() == ITestResult.SUCCESS ){
//            RallyIntegration.updatedTestCaseStatusInRally(TestCaseID,result.getStatus(),
//                    "Hackthon 1.0","Automated features");
//        }
//        else if(result.getStatus() == ITestResult.FAILURE){
//            result.getMethod();
//            RallyIntegration.updatedTestCaseStatusInRally(TestCaseID,result.getStatus(),
//                    "Hackthon 1.0","Automated features");
//        }
//        else{
//            Logs.error("Exception Occurred");
//        }
    }


}
