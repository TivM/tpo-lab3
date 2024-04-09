package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompanyPage {

    @FindBy(xpath = "/html/body/div[5]/div/div/div[4]/div[1]/div[2]/div[2]/div/div[1]/div[1]/div[2]/h1/span")
    WebElement companyName;

    public WebDriver webDriver;

    public CompanyPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public WebElement getCompanyName() {
        return companyName;
    }
}
