package com.vytrack.tests.components.activities;

import com.vytrack.pages.activities.CalendarEventsPage;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.SeleniumUtils;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VyTrackUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarEventsTests extends TestBase {





    @Test
    public void verifyTitleColmun(){
        LoginPage loginPage=new LoginPage();
        CalendarEventsPage calendarEventsPage=new CalendarEventsPage();

        //to login
        loginPage.login(ConfigurationReader.getProperty("username"),ConfigurationReader.getProperty("password"));
        //go to Calendar Events page
        VyTrackUtilities.waitUntilLoaderScreenDisappear();
        VyTrackUtilities.navigateToModule("Activities","Calendar Events");

        //deselect title option from grid settings
        SeleniumUtils.waitPlease(2);
        calendarEventsPage.selectGridSetting("Title", false);
        SeleniumUtils.waitPlease(5);
        Assert.assertFalse(calendarEventsPage.verifyHeaderExist("Title"),"Title column name still visible ");

        //to close grid settings menu
       calendarEventsPage.gridSettingElement.click();

        //select title option again
        calendarEventsPage.selectGridSetting("Title", true);
        SeleniumUtils.waitPlease(5);

        //verify that title column name is visible
        Assert.assertTrue(calendarEventsPage.verifyHeaderExist("Title"),"Title column name not  visible");
        //
    }



}
