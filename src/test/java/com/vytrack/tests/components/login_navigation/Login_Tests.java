package com.vytrack.tests.components.login_navigation;

import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VyTrackUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login_Tests extends TestBase {


    @Test
    public void LoginTest(){
        //we are instantiating page class inside a test class
        // because for second test, if we run all tests in a row, driver will have null season
        LoginPage loginPage=new LoginPage();
        String username= ConfigurationReader.getProperty("username");
        String password=ConfigurationReader.getProperty("password");
        loginPage.clickRememberMeTest();
        loginPage.login(username,password);

        //To verify Dashboard page is open
        //Once page Dashboard displays, means that we are logged successfully
        Assert.assertEquals(VyTrackUtilities.getSubTitle(),"Dashboard");


    }
    @Test
    public void negativeLoginTest1(){
        LoginPage loginPage=new LoginPage();
        loginPage.login("wrongusername","wrongpassword");
        String actualMessage=loginPage.getErrorMessage();
        String expectingMessage="Invalid user name or password.";
        Assert.assertEquals(actualMessage,expectingMessage);

    }




}
