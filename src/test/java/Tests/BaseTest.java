package Tests;

import Utils.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected AppiumDriver<MobileElement> driver;

    @BeforeMethod
    protected void setUp() throws MalformedURLException {
        URL remoteAddress = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(remoteAddress, createAppiumCapabilities());
        BasicConfigurator.configure();
        resetApplication();
    }

    protected void resetApplication() {
        driver.resetApp();
    }

    private DesiredCapabilities createAppiumCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        File appDir = new File("src/test/resources");
        File app = new File(appDir, "speedtest.apk");
        capabilities.setCapability("platformName", MobilePlatform.ANDROID);
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("appPackage", "org.zwanoo.android.speedtest");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability("appActivity", "com.ookla.mobile4.screens.main.MainActivity");
        capabilities.setCapability("skipUnlock", true);
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("autoGrantPermissions", true);
        return capabilities;
    }

    @AfterMethod
    protected void tearDown(ITestContext iTestContext, @Optional String generateReport) {
        if (driver != null) {
            Log.info("After suite: ");
            driver.quit();
        }
        System.out.println("AfterSuite: ");
        System.out.println("===============================================");
        System.out.println("  passed tests:  " + iTestContext.getPassedTests().size());
        System.out.println("  skipped tests: " + iTestContext.getSkippedTests().size());
        System.out.println("  failed tests:  " + iTestContext.getFailedTests().size());
        System.out.println("===============================================\n");
        System.out.println("SUITE completed!");
    }


}
