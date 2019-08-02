package com.vytrack.pages.activities;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CalendarEventsPage {

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement creatCalendarButtonElement;

    @FindBy(css = "input[id^='oro_calendar_event_form_title']")
    public WebElement titleInputElement;

    @FindBy(css = "input[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDate;

    @FindBy(css = "input[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDateElement;

    @FindBy(css = "a[title='Grid Settings']")
    public WebElement gridSettingElement;




    @FindBy(css = "a[title='Reset']")
    public WebElement resetelement;


    @FindBy(css = "[class='grid-header-cell__label']")
    public List<WebElement>headers;





    public CalendarEventsPage(){
        PageFactory.initElements(Driver.getDriver(),this);

    }

    public void selectGridSetting(String name,boolean yesOrno){
        //click on grid options
        gridSettingElement.click();
        //create locator for grid options based on name
        String locator="//td//label[text()='"+name+"']/ ../following-sibling::td/input";
        //find element
        //u can also call Driver.get();
        WebElement gridOptions=Driver.getDriver().findElement(By.xpath(locator));
        //if parameter yesOrno is true , and checkbox is not selected yet
        //click on it or check box is selected u want to unselected
        if((yesOrno==true && !gridOptions.isSelected() || yesOrno==false && gridOptions.isSelected())){
            gridOptions.click();

        }
    }


    /*
    let's write a method that will take a headerName as a parameter and will try to look up for header name in this
    collection of headers, if header name was not found return false, otherwise return true.
     */

    public boolean verifyHeaderExist(String headerNameOrColumnName){

        for (WebElement tableHeader:headers) {
            System.out.println(tableHeader.getText());
            if(tableHeader.getText().equalsIgnoreCase(headerNameOrColumnName)){
                return true;
                
            }
        }
        return false;
        
    }
}
