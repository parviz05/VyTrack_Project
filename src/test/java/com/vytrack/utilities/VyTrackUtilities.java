package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VyTrackUtilities {

//  private  static String usernameLocator="prependedInput";
//  private static String passwordLocator="prependedInput2";
//  private static String loaderMaskLocator="div[class='loader-mask shown']";

    private static String usernamelocator = "prependedInput";
    private static String passwordLocator = "prependedInput2";
    private static String loaderMaskLocator = "div[class='loader-mask shown']";
    private static String pageSubTitleLocator ="h1[class='oro-subtitle']";



    /**
     * Login to vyTrack Application
     * @param driver
     * @param username
     * @param password
     */
    public static void login(WebDriver driver, String username,String password){
        driver.findElement(By.id(usernamelocator)).sendKeys(username);
        //Keys.Enter means click enter after entering password
        driver.findElement(By.id(passwordLocator)).sendKeys(password, Keys.ENTER);
        SeleniumUtils.waitPlease(2);


    }

    /**
     * this method will navigate  user to the specific module in vytrack application
     * For example: if tab is  equals to Activities, and module is equals to Calls,
     * then this method will navigate user to this page:http://qa2.vytrack.com/call/
     *
     * @param tab
     * @param module
     */

    public static void navigateToModule( String tab,String module){
        String tabLocator="//span[contains(text(),'"+tab+"') and contains(@class,'title title-level-1')]";
        String moduleLocator="//span[contains(text(),'"+module+"') and contains(@class,'title title-level-2')]";
       // Driver.getDriver().findElement(By.xpath(tabLocator)).click();
        SeleniumUtils.clickWithWait(Driver.getDriver(),By.xpath(tabLocator),5);
       // Driver.getDriver().findElement(By.xpath(moduleLocator)).click();
        SeleniumUtils.clickWithWait(Driver.getDriver(),By.xpath(moduleLocator),5);


    }

    /**
     * Wait until loader screen disappear. if Loader screen will not pop up at all,
     * NoSuchElementException will be handled but try/catch bloack
     * Thus, we can continue in any case.

     */
    public static void waitUntilLoaderScreenDisappear(){

        try {
            WebDriverWait wait=new WebDriverWait(Driver.getDriver(),Long.valueOf(ConfigurationReader.getProperty("excplicitwait")));
            wait.until(ExpectedConditions.visibilityOf(Driver.getDriver().findElement(By.cssSelector(loaderMaskLocator))));

        }catch (Exception e){
            System.out.println(e+" :: Loader don't present.");
        }

    }

    /**
     *
     * @return page name, for example : Dashboard
     */


    public static String getSubTitle(){

        //any time we are verifying page name, or subtutle, loader ask appear
        waitUntilLoaderScreenDisappear();

        return Driver.getDriver().findElement(By.cssSelector(pageSubTitleLocator)).getText();
    }


}
