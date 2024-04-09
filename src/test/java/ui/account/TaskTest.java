package ui.account;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;
import ui._init.AbstractTest;
import ui.pages.AgentPage;

import static ui._site.ServerSite.*;
import static utils.AllureAssert.assertEquals;

public class TaskTest extends AbstractTest {
    private static final String siteAgentUrl = "https://www.whatismybrowser.com/detect/what-is-my-user-agent/";
    private static final String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 16_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.3 Mobile/15E148 Safari/604.1";

    private static WebDriver getDriverChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-third-party-cookie-phaseout");
        return new ChromeDriver(options);
    }

    private static WebDriver getDriverFirefox() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override", userAgent);
        profile.setPreference("network.cookie.cookieBehavior", 2);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        return new FirefoxDriver(firefoxOptions);
    }

    @Test
    @Story("Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Go to Category, select product, complete checkout")
    public void checkoutTest() {
        homePage.open();
        homePage.openBagsSubcategory();
        categoryPage.addProductToCart(3);
        categoryPage.proceedCheckout();
        checkoutPage.fillCheckoutForm();
        checkoutPage.placeOrderAndGetId();
    }

    @Test
    @Story("Browsers")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check Chrome cookies")
    public void chromeTest() {
        ChromeDriver driver = (ChromeDriver) getDriverChrome();
        driver.manage().window().maximize();
        driver.get(siteAgentUrl);
        driver.get("chrome://flags/#test-third-party-cookie-phaseout");
    }

    @Test
    @Story("Browsers")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Change user agent to default")
    public void firefoxTest() {
        WebDriver driver = getDriverFirefox();
        driver.get(siteAgentUrl);
        ui.pages.AgentPage agentPage = new AgentPage();
        assertEquals("Check agents", agentPage.getAgentText(driver), userAgent, null);
        driver.get("about:config");
    }
}
