package com.JavaSelenium.Automation.GitHubPages;
import com.JavaSelenium.Automation.core.pages.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends Components{

    @CacheLookup
    @FindBy(id = "login_field")
    private WebElement txtUserName;

    @CacheLookup
    @FindBy(id = "password")
    private WebElement txtPassword;

    @CacheLookup
    @FindBy(name = "commit")
    private WebElement btnSignIn;

    @CacheLookup
    @FindBy(xpath = "//a[text()='Create an account']")
    private WebElement lnkCreateAnAccount;

    @CacheLookup
    @FindBy(xpath = "//a[contains(text(), 'Forgot password')]")
    private WebElement lnkForgotPassword;

    public SignInPage setUserName(String uName){
        txtUserName.sendKeys(uName);
        return this;
    }
    public SignInPage setPassword(String password){
        txtPassword.sendKeys(password);
        return this;
    }
    public void clickSignIn(){
        btnSignIn.click();
    }

    public void signIN(String userName, String password){
        setUserName(userName);
        setPassword(password);
        clickSignIn();
    }
}

