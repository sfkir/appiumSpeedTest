package Tests;

import Pages.SpeedPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Map;

public class SpeedTest extends BaseTest {
    SpeedPage speedPage;

    @Test
    public void checkTestResultValues() {
        speedPage = new SpeedPage(driver);
        speedPage.clickOnGoButton()
                .waitForSpeedTestToBeDone();
        Map<String, String> results = speedPage.getTextFromSpeedTestResult();
        results.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }


}
