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
public class CreateAndSearchRepoTest extends TestBase {

    private SignInPage getSignInPage() {return AllPages.getPage(SignInPage.class);  }
    private HomePage getHomePage() {return AllPages.getPage(HomePage.class);  }
    private DashboardPage getDashboardPage() {return AllPages.getPage(DashboardPage.class);  }
    private NewRepositoryPage getNewRepoPage() {return AllPages.getPage(NewRepositoryPage.class);  }
    private RepositoryPage getRepoPage() {return AllPages.getPage(RepositoryPage.class);  }
    String TestCaseID = "";

    @Title("TestCaseID - Test to create new repository")
    @Test(enabled = true, description = "Test to Create a New Repository in GITHUB")
    public void createRepository(ITestContext testContext) throws Exception{
        testContext.setAttribute("WebDriver", driver);
        Assertions.setSoftAssert(new SoftAssert());
        TestCaseID = "RallyTestCaseID";
        prepareLoginTestData();
        getHomePage().clickSignIN();
        Waits.waitForPageLoadJS();
        Assertions.logInformation("Successfully clicked on SignIn at Home page");
        getSignInPage().signIN(strUsername,strPassword);
        Waits.waitForPageLoadJS();
        getDashboardPage().clickNewRepository();
        Assertions.logInformation("Successfully clicked on New repository link");
        Waits.waitForPageLoadJS();
        getNewRepoPage().enterRepositoryName("Sample-Repo");
        Assertions.logInformation("Successfully entered the repo name");
        getNewRepoPage().enterRepositoryDescription("This is Test Repo");
        Assertions.logInformation("Successfully entered the repo description");
        getNewRepoPage().initializeRepository();
        Assertions.logInformation("Successfully checked the initialization repo");
        getNewRepoPage().enterLicense("Apache License 2.0");
        Assertions.logInformation("Successfully entered the License");
        getNewRepoPage().clickCreateRepository();
        Assertions.logInformation("Successfully clicked on Create Repository button");
        Waits.waitForPageLoadJS();
        boolean blnPageTitle = getRepoPage().getPageTitle().contains("Sample-Repo");
        Assertions.softAssertTrue(blnPageTitle,"New Repo created",
                "Failed to create new repository");
        Assertions.assertAll();
    }

    @Title("TestCaseID - Test to search an existing repository")
    @Test(enabled = true, description = "Test to search an existing Repository in GITHUB")
    public void searchRepository(ITestContext testContext) throws Exception{
        testContext.setAttribute("WebDriver", driver);
        Assertions.setSoftAssert(new SoftAssert());
        TestCaseID = "RallyTestCaseID";

        getDashboardPage().enterRepoName("Sample-Repo");
        Assertions.logInformation("Entered the repo name to be searched");

        Assertions.softAssertTrue(getDashboardPage().searchRepo("Sample-Repo"),"Repository is found in the list",
                "Failed to find the repo in the list");
        Assertions.assertAll();
    }


    @AfterMethod(alwaysRun = true)
    public void updateTestCaseStatus(ITestResult result) throws Exception{
        getDashboardPage().clickHomePage();

//        if(result.getStatus() == ITestResult.SUCCESS ){
//            RallyIntegration.updatedTestCaseStatusInRally(TestCaseID,result.getStatus(),"Hackthon 1.0","Automated features");
//        }
//        else if(result.getStatus() == ITestResult.FAILURE){
//            result.getMethod();
//            RallyIntegration.updatedTestCaseStatusInRally(TestCaseID,result.getStatus(),"Hackthon 1.0","Automated features");
//        }
//        else{
//            Logs.error("Exception Occurred");
//        }
    }

}



