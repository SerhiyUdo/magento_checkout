package ui.pages;

import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.XPath;

@Url("")
public class HomePage extends WebPage {
    @XPath("//ul[@role='menu']//a[contains(@href, '/gear.html')]")
    private UIElement gearCategory;
    @XPath("//ul[@role='menu']//a[contains(@href, '/gear/bags.html')]")
    private UIElement bagsSubCategory;

    public void openBagsSubcategory() {
        gearCategory.hover();
        bagsSubCategory.click();
    }

}
