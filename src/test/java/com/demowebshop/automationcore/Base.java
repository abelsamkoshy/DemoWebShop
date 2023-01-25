package com.demowebshop.automationcore;

import com.demowebshop.constants.Constants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {
    public WebDriver driver;
    public Properties prop;
    public FileInputStream fs;
    public Base()  {
        prop=new Properties();
        try {
            fs =new FileInputStream(System.getProperty("user.dir")+ Constants.CONFIG_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUP(){
        String browser= prop.getProperty("browser");
        String url= prop.getProperty("url");
        driver = DriverFactory.testInitialization(browser);
        driver.get(url);
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus()== ITestResult.FAILURE){
            TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
            File screenshot=takeScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("./Screenshots/"+result.getName()+".png"));
        }
        driver.quit();
    }
}
