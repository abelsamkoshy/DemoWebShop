package com.demowebshop.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.ErrorMessages;
import com.demowebshop.dataprovider.DataProviders;
import com.demowebshop.listeners.TestListener;
import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.LoginPage;
import com.demowebshop.pages.UserAccountPage;
import com.demowebshop.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginTest extends Base {
    HomePage home;
    LoginPage login;
    UserAccountPage useraccountpage;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 1,description = "TC001 Verify Login Page Title",groups = {"Smoke"})
    public void TC001_verifyLoginPageTitle() throws IOException {
        extentTest.get().assignCategory("Smoke");
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String expLoginPageTitle=data.get(1).get(0);
        home=new HomePage(driver);
        login=home.clickOnLoginMenu();
        String actLogingPageTitle=login.getLoginPageTitle();
        Assert.assertEquals(actLogingPageTitle,expLoginPageTitle, ErrorMessages.TITLE_FAILURE_MESSAGE);
        extentTest.get().log(Status.PASS,"Expected homepage title match with actual homepage title");

    }
    @Test(priority = 1,description = "TC002 Verify Valid Login",groups = {"Sanity"})
    public void TC002_verifyValidLogin() throws IOException {
        extentTest.get().assignCategory("Sanity");
        home=new HomePage(driver);
        login=home.clickOnLoginMenu();
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String userEmailId=data.get(1).get(1);
        login.enterUserEmailId(userEmailId);
        String userPassword=data.get(1).get(2);
        login.enterUserPassword(userPassword);
        useraccountpage = login.clickOnLoginButton();
        String actEmail=useraccountpage.getUserAccountEmailId();
        Assert.assertEquals(userEmailId,actEmail, ErrorMessages.INVALID_EMAIL_ID);
        extentTest.get().log(Status.PASS,"Expected homepage title match with actual homepage title");

    }
    @Test(priority = 1,description = "TC003 Verify Invalid Login error message", dataProvider = "InvalidUserCredentials", dataProviderClass = DataProviders.class,groups = {"Sanity"})
    public void TC003_verifyInValidLoginErrorMessage(String userName, String password) throws IOException {
        extentTest.get().assignCategory("Sanity");
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String expectedErrorMessage=data.get(1).get(3);
        home=new HomePage(driver);
        login=home.clickOnLoginMenu();
        login.enterUserEmailId(userName);
        login.enterUserPassword(password);
        login.clickOnLoginButton();
        String actualErrorMessage= login.getLoginErrorMessage();
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage,ErrorMessages.INVALID_ERROR_MESSAGE);
        extentTest.get().log(Status.PASS,"Expected homepage title match with actual homepage title");

    }
}
