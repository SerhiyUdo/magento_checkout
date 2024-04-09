package ui.pages;

import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.complex.JList;
import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.XPath;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.complex.RadioButtons;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v120.network.Network;
import org.openqa.selenium.devtools.v120.network.model.Request;

import java.util.List;
import java.util.Optional;

import static utils.AllureAssert.assertTrue;
import static utils.FileWriter.writeToFile;
import static utils.PageWaiters.waitForJStoLoad;

@Url("checkout/#shipping")
@XPath("//*[@id='shipping']")
public class CheckoutPage extends WebPage {
    private static final String filePath = "src/test/resources/files/";
    private static final String fileName = "test_data_";
    private static final String email = "noam@gmail.com";
    private static final String firstName = "noam";
    private static final String lastName = "Ben Ishay";
    private static final String company = "IntentIQ";
    private static final String street = "Velykoukrainska";
    private static final String city = "Chicago";
    private static final String state = "Illinois";
    private static final String postCode = "12345";
    private static final String phoneNumber = "+380661119113";
    private static final String shippingMethod = "Fixed";

    @XPath("//*[@id='customer-email-fieldset']//*[@type='email']")
    private UIElement emailField;

    @XPath("//*[@name='firstname']")
    private UIElement firstNameField;

    @XPath("//*[@name='lastname']")
    private UIElement lastNameField;

    @XPath("//*[@name='street[0]']")
    private UIElement streetField;

    @XPath("//*[@name='company']")
    private UIElement companyField;
    @XPath("//*[@name='city']")
    private UIElement cityField;

    @XPath("//*[@name='postcode']")
    private UIElement postcodeField;

    @XPath("//*[@name='telephone']")
    private UIElement telephoneField;

    @XPath("//*[@name='region_id']")
    private Dropdown statesDropdown;

    @XPath("//*[text()='%s']/parent::*//input")
    private JList<RadioButtons> shippingMethodRadioBtn;

    @XPath("//*[@data-role='opc-continue']")
    private Button nextBtn;

    @XPath("//*[@class='action primary checkout']")
    private Button placeOrderBtn;

    @XPath("//*[@class='checkout-success']/p/span")
    private UIElement orderId;


    public void fillCheckoutForm() {
        waitForJStoLoad();
        emailField.waitFor().visible();
        statesDropdown.select(state);
        emailField.sendKeys(email);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        streetField.sendKeys(street);
        companyField.sendKeys(company);
        cityField.sendKeys(city);
        postcodeField.sendKeys(postCode);
        telephoneField.sendKeys(phoneNumber);
        shippingMethodRadioBtn.get(shippingMethod).get(1).click();
        nextBtn.waitFor().enabled();
        nextBtn.click();
        waitForJStoLoad();
        verifyHttpRequest();
    }

    public void placeOrderAndGetId() {
        placeOrderBtn.waitFor().displayed();
        placeOrderBtn.click();
        orderId.waitFor().visible();
        String order = orderId.getText();
        writeToFile(filePath + fileName + order + ".txt", order);
    }

    private void verifyHttpRequest() {
        ChromeDriver driver = (ChromeDriver) this.driver();
        List<String> expectedData = List.of(email, firstName, lastName, company, street, city, state, postCode, phoneNumber, shippingMethod);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(), requestConsumer -> {
            Request httpData = requestConsumer.getRequest();
            if (httpData.getPostData().toString().contains("cartId")) {
                expectedData.forEach(data -> assertTrue("Check request contains " + data + " input data ", httpData.getPostData().toString().contains(data)));
            }
        });
    }

}
