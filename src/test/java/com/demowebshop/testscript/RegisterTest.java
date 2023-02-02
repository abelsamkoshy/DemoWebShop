package com.demowebshop.testscript;

import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.ErrorMessages;
import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.RegisterPage;
import com.demowebshop.pages.UserAccountPage;
import com.demowebshop.utilities.ExcelUtility;
import com.demowebshop.utilities.RandomDataUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegisterTest extends Base {
    HomePage home;
    RegisterPage register;
    UserAccountPage useraccountpage;
    @Test(priority = 1,description = "TC001 Verify Register Page Title",groups = {"Smoke"})
    public void TC001_verifyUserRegistration() throws IOException {
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("RegisterPage");
        String gender=data.get(0).get(1);
        String fName=RandomDataUtility.getfName();
        String lName=RandomDataUtility.getlName();
        String email=RandomDataUtility.getRandomEmail();
        String password=fName+"@123";
        String confirmPassword=password;
        home=new HomePage(driver);
        home.clickOnRegisterMenu();
        register=new RegisterPage(driver);
        register.selectGender(gender);
        register.enterFirstName(fName);
        register.enterLastName(lName);
        register.enterEmail(email);
        register.enterPassword(password);
        register.enterConfirmPassword(confirmPassword);
        useraccountpage = register.clickOnRegisterButton();
        String actEmail=useraccountpage.getUserAccountEmailId();
        Assert.assertEquals(email,actEmail, ErrorMessages.INVALID_EMAIL_ID);
    }
}
