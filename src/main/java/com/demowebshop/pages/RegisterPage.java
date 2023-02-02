package com.demowebshop.pages;

import com.demowebshop.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegisterPage extends TestHelperUtility {
    public WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String _firstNameField = "//input[@id='FirstName']";
    @FindBy(xpath = _firstNameField)
    private WebElement firstNameField;
    private final String _lastNameField = "//input[@id='LastName']";
    @FindBy(xpath = _lastNameField)
    private WebElement lastNameField;
    private final String _emailField = "//input[@id='Email']";
    @FindBy(xpath = _emailField)
    private WebElement emailField;
    private final String _passwordField = "//input[@id='Password']";
    @FindBy(xpath = _passwordField)
    private WebElement passwordField;
    private final String _confirmpasswordField = "//input[@id='ConfirmPassword']";
    @FindBy(xpath = _confirmpasswordField)
    private WebElement confirmpasswordField;
    private final String _genderField = "//input[@name='Gender']/following-sibling::label";
    @FindBy(xpath = _genderField)
    private List<WebElement> genderField;
    private final String _registerButton = "//input[@id='register-button']";
    @FindBy(xpath = _registerButton)
    private WebElement registerButton;

    public void enterFirstName(String firstName) {
        page.enterText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        page.enterText(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        page.enterText(emailField, email);
    }

    public void enterPassword(String password) {
        page.enterText(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        page.enterText(confirmpasswordField, confirmPassword);
    }

    public void selectGender(String gender) {
        for (int i = 0; i < genderField.size(); i++) {
            String value = page.getElementText(genderField.get(i));
            if (value.equals(genderField)) {
                page.clickOnElement(genderField.get(i));
                break;
            }
        }
    }
    public UserAccountPage clickOnRegisterButton(){
        page.clickOnElement(registerButton);
        return new UserAccountPage(driver);
    }

}
