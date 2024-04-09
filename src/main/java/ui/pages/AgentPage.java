package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AgentPage {
    public String getAgentText(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id='detected_value']")).getText();
    }

}
