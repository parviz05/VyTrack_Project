package com.vytrack.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    public Actions actions;

    @BeforeMethod
    public void setUp(){
        driver= Driver.getDriver();
        actions=new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Long.valueOf(ConfigurationReader.getProperty("implicitmait")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.getProperty("url"));
    }
    @AfterMethod
    public void teardown(ITestResult result){
        if(ITestResult.FAILURE==result.getStatus()) {
            //we are creatin object to take a screenshots
            TakesScreenshot screenshot = (TakesScreenshot) driver; //we create Takescreenshot object and casted in driver object

            //call method to take screenshot
            File src = screenshot.getScreenshotAs(OutputType.FILE);//use getScreenShotsAs method"which comes form selenium" and stores output in to File src
            try {
                //getName()- will return of the name of test  method
                //and save screenshot under project >screenshots with date/test name tag
                FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "/screeshots/" + LocalTime.now() +"_"+ result.getName() + ".png"));
            } catch (IOException e) { //copied the screenshots in specific folder ->"/screeshots/" + LocalTime.now() +"_"+ result.getName() + ".png")
                e.printStackTrace();

            }
        }

        Driver.closeDriver();
    }

}
