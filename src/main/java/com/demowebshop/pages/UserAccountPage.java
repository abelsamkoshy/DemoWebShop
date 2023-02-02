package com.demowebshop.pages;

import com.demowebshop.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserAccountPage extends TestHelperUtility {
    public WebDriver driver;
    public UserAccountPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _userAccountEmailId="//div[@class='header-links']//a[@class='account']";
    @FindBy(xpath = _userAccountEmailId ) private WebElement userAccountEmailId;

    public String getUserAccountEmailId(){
        String emailId=page.getElementText(userAccountEmailId);
        return emailId;
    }
}
