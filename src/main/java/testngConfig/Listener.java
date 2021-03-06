package testngConfig;

import baseTest.SelenideConfigurations;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.PropertyReader;

public class Listener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        byte[] file = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onStart(ITestContext context) {
        PropertyReader propertyReader = new PropertyReader();
        propertyReader.setProperties(context.getSuite().getParameter("env") == null ? System.getProperties().getProperty("env") : context.getSuite().getParameter("env"));
        new SelenideConfigurations(propertyReader);
    }

    @Attachment(value = "Screenshots", type = "image/png")
    private byte[] saveScreenshots(byte[] s) {
        return s;
    }
}
