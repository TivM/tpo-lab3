package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CVPage {

    public WebDriver webDriver;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[4]/div[1]/div/div[2]/div/div/div[2]/div/div/div/div[1]/div[1]/div/div[1]/div[1]/h2/span")
    WebElement nameElement;

    public CVPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public WebElement getNameElement() {
        return nameElement;
    }
}
