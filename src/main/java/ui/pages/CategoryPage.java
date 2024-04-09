package ui.pages;

import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.complex.JList;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.XPath;
import com.epam.jdi.light.ui.html.elements.common.Button;

@Url("/feed/")
public class CategoryPage extends WebPage {
    @Css(".product-item-info")
    private JList<UIElement> productInfo;

    @XPath("//button[@title='Add to Cart']")
    private JList<UIElement> addToCartBtn;

    @XPath("//*[@class='counter-number']")
    private UIElement miniCartCounter;

    @XPath("//*[@class='action showcart']")
    private UIElement miniCartIcon;

    @Css("#top-cart-btn-checkout")
    private Button proceedToCheckoutBtn;

    public void addProductToCart(int i) {
        productInfo.get(i).hover();
        addToCartBtn.get(i).click();
    }

    public void proceedCheckout() {
        miniCartCounter.waitFor().text(String.valueOf(1));
        miniCartIcon.waitFor().displayed();
        miniCartIcon.click();
        proceedToCheckoutBtn.waitFor().displayed();
        proceedToCheckoutBtn.click();
    }

}



