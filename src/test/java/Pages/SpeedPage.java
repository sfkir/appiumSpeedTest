package Pages;

import Utils.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpeedPage extends BasePage {

    @AndroidFindBy(id = "go_button")
    private MobileElement goButton;

    @AndroidFindBy(id = "txt_test_result_value")
    private List<MobileElement> speedResultValue;

    @AndroidFindBy(id = "speedtest_completed_rating_bar")
    private MobileElement completedRatingBar;

    @AndroidFindBy(id = "txt_test_result_title")
    private List<MobileElement> speedResultTitle;

    @AndroidFindBy(id = "ll_test_result_placeholder_container")
    private List<MobileElement> emptyResultPlaceholder;

    public SpeedPage(AppiumDriver driver) {
        super(driver);
    }

    public SpeedPage clickOnGoButton() {
        waitForElementAndClick(goButton, "'Go' button is not found on the page", Constants.MEDIUM_TIMEOUT);
        return this;
    }

    public SpeedPage waitForSpeedTestToBeDone() {
        waitForElementToBePresent(completedRatingBar, "Speed Test isn't done in time", Constants.LONG_TIMEOUT);
        return this;
    }

    public Map<String, String> getTextFromSpeedTestResult() {
        Map<String, String> results = IntStream.range(0, speedResultValue.size())
                .boxed()
                .collect(Collectors.toMap(i -> speedResultTitle.get(i).getText(), i -> speedResultValue.get(i).getText()));
        return results;
    }

}
