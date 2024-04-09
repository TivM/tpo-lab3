package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VacancyPage {

    public WebDriver webDriver;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[4]/div[1]/div[1]/div/div/div/div/div[1]/div[1]/div/div[1]/h1")
    WebElement nameElement;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[4]/div[1]/div[1]/div/div/div/div/div[1]/div[1]/div/div[3]/div[4]/div/div/a")
    WebElement respondButton;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[4]/div[1]/div/div/div[1]/div/div[2]/div[2]/div[1]/button")
    WebElement writing;

    @FindBy(xpath = "/html/body/div[6]/div/div[1]/div[4]/div/div/div/div/div[1]/div[3]/div[3]/div/div/div[2]/div/div[1]/div/div/form/div[1]/div/input")
    WebElement stayContactsInput;

    @FindBy(xpath = "/html/body/div[6]/div/div[1]/div[4]/div/div/div/div/div[1]/div[3]/div[3]/div/div/div[2]/div/div[1]/div/div/form/div[4]/div/button")
    WebElement stayContactsButton;

    @FindBy(xpath = "/html/body/div[6]/div/div[1]/div[4]/div/div/div/div/div[1]/div[3]/div[3]/div/div/div[2]/div/div[1]/div/div/form/div[1]/p")
    WebElement sentOnEmail;

    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div[1]/div/div/div/div[1]/a")
    WebElement myCV;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[3]/div[1]/div/div/div[1]/div[1]/div[2]/div/div[1]/a")
    WebElement orderCV;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[3]/div[1]/div/div/div[1]/div[1]/div[2]/div/div[2]/a")
    WebElement createCV;


    public VacancyPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public WebElement getNameElement() {
        return nameElement;
    }

    public WebElement getRespondButton() {
        return respondButton;
    }

    public WebElement getWriting() {
        return writing;
    }

    public WebElement getMyCV() {
        return myCV;
    }

    public WebElement getOrderCV() {
        return orderCV;
    }

    public WebElement getCreateCV() {
        return createCV;
    }

    public WebElement getStayContactsInput() {
        return stayContactsInput;
    }

    public WebElement getStayContactsButton() {
        return stayContactsButton;
    }

    public WebElement getSentOnEmail() {
        return sentOnEmail;
    }
}
