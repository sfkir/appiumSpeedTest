package Pages;

import Utils.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    protected AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WebElement waitForElementToBePresent(WebElement element, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.visibilityOf(element)
        );
    }

    public String waitForElementAndGetAttribute(WebElement locator, String attribute, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementToBePresent(locator, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public String waitForElementAndGetText(WebElement locator, String errorMessage) {
        MobileElement element = (MobileElement) waitForElementToBePresent(locator, errorMessage, Constants.SMALL_TIMEOUT);
        return element.getText();
    }

    public WebElement waitForElementAndClick(WebElement locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementToBePresent(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public boolean isElementPresent(String id) {
        try {
            driver.findElementById(id);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
