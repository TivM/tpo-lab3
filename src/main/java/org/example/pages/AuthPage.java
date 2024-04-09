package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage {

    @FindBy(xpath = "/html/body/div[6]/div/div[1]/div[4]/div/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div/div/form/div[1]/input")
    private WebElement loginField;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[4]/div[1]/div/div/div/div/div/div[1]/div/div/form/div[1]/fieldset/input")
    private WebElement loginFieldWithPassword;

    @FindBy(xpath = "/html/body/div[6]/div/div[1]/div[4]/div/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div/div/form/div[4]/button")
    private WebElement loginButton;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[4]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/form/div[4]/button[2]")
    private WebElement loginWithPasswordButton;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[4]/div[1]/div/div/div/div/div/div[1]/div/div/form/div[2]/fieldset/input")
    private WebElement passwdField;

    @FindBy(xpath = "/html/body/div[6]/div/div[1]/div[4]/div/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div/form/div[4]/div/button")
    private WebElement passwdButton;


    @FindBy(xpath = "/html/body/div[5]/div/div/div[4]/div[1]/div/div/div/div/div/div[1]/div/div/form/div[6]/button[1]")
    private WebElement inButton;

    @FindBy(xpath = "/html/body/div[6]/div/div[1]/div[4]/div/div/div/div/div/div/div[1]/div[2]/div/div/div/span[1]/a")
    private WebElement VKButton;

    @FindBy(xpath = "/html/body/div[6]/div/div[1]/div[4]/div/div/div/div/div/div/div[1]/div[2]/div/div/div/span[2]/a")
    private WebElement GoogleButton;

    @FindBy(xpath = "/html/body/div[6]/div/div[1]/div[4]/div/div/div/div/div/div/div[1]/div[2]/div/div/div/span[3]/a")
    private WebElement MyWorldButton;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div[1]/div/div/span/div[4]")
    private WebElement captcha;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[3]/div[1]/div/div/div/div/div/div[1]/div/div/form/div[5]/div[2]")
    private WebElement wrongCredMessage;

    public WebDriver webDriver;

    public AuthPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public WebElement getLoginField() {
        return loginField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getPasswdField() {
        return passwdField;
    }

    public WebElement getPasswdButton() {
        return passwdButton;
    }

    public WebElement getLoginWithPasswordButton() {
        return loginWithPasswordButton;
    }

    public WebElement getVKButton() {
        return VKButton;
    }

    public WebElement getGoogleButton() {
        return GoogleButton;
    }

    public WebElement getMyWorldButton() {
        return MyWorldButton;
    }

    public WebElement getLoginFieldWithPassword() {
        return loginFieldWithPassword;
    }

    public WebElement getInButton() {
        return inButton;
    }

    public WebElement getCaptcha() {
        return captcha;
    }

    public WebElement getWrongCredMessage() {
        return wrongCredMessage;
    }
}
