package utils;

import base.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Listeners implements ITestListener {
    BaseClass baseClass = new BaseClass();

    public void onTestFailure(ITestResult result) {
        if(result.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);

            System.out.println(sw);
        }

        File file = baseClass.getDriver().getScreenshotAs(OutputType.FILE);

        String imagePath = "Screenshots" + File.separator + new ConfigLoader().initializeProperty().getProperty("Platform")
                + "_" + new ConfigLoader().initializeProperty().getProperty("Device") + File.separator + baseClass.dateTime() + File.separator
                + result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + ".png";

        String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;
        System.out.println(completeImagePath);

        try {
            FileUtils.copyFile(file, new File(imagePath));
            Reporter.log("This is the "+result.getName()+"'s failed screenshot");
            Reporter.log("<a href='"+ completeImagePath + "'> <img src='"+ completeImagePath + "' height='400' width='400'/> </a>");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
