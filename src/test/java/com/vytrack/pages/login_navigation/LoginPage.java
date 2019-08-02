package com.vytrack.pages.login_navigation;

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    static private WebDriver driver= Driver.getDriver();
    private WebDriverWait wait=new WebDriverWait(driver,Long.valueOf(ConfigurationReader.getProperty("excplicitwait")));

    @FindBy(id="prependedInput")
    public WebElement userNameElement;

    @FindBy(name="_password")
    public WebElement passwordElement;

    @FindBy(id="_submit")
    public WebElement loginButton;

    @FindBy(css="span[class='custom-checkbox__icon']")
    public WebElement rememberMeElement;

    @FindBy(partialLinkText = "Forgot your password?")
    public WebElement forgotPasswordElement;

    @FindBy(css = "div[class=title-box]>h2")
    public WebElement titleElement;

    @FindBy(css = "div[class^=alert]>div")
    public WebElement errorMessageElement;

    public LoginPage(){

        PageFactory.initElements(driver,this);
    }

    public void login(String username,String password){
        userNameElement.sendKeys(username);
        passwordElement.sendKeys(password);

        //VyTrackUtilities.waitUntilLoaderScreenDisappear(driver);

        loginButton.click();
    }
    public String getErrorMessage(){

        return errorMessageElement.getText();
    }
    public void clickRememberMeTest(){
        wait.until(ExpectedConditions.elementToBeClickable(rememberMeElement));
        if(!rememberMeElement.isSelected()){
            rememberMeElement.click();
        }
    }

}
