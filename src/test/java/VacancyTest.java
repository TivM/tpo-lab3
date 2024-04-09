import org.example.ConfProperties;
import org.example.pages.AuthPage;
import org.example.pages.MainPage;
import org.example.pages.ProfilePage;
import org.example.pages.VacancyPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VacancyTest {

    public static AuthPage authpage;
    public static WebDriver chromeDriver;
    private static MainPage mainPage;
    private static VacancyPage vacancyPage;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.get(ConfProperties.getProperty("mainpage"));
        mainPage = new MainPage(chromeDriver);
    }

    @AfterAll
    public static void close(){
        chromeDriver.close();
    }

    public void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @BeforeEach
    public void setAuthPage(){
        chromeDriver.get(ConfProperties.getProperty("mainpage"));
        mainPage.getLoginButton().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        authpage = new AuthPage(chromeDriver);
        authpage.getLoginWithPasswordButton().click();
        authpage.getLoginFieldWithPassword().sendKeys(ConfProperties.getProperty("auth_email"));
        authpage.getPasswdField().sendKeys(ConfProperties.getProperty("auth_passwd"));
        authpage.getInButton().click();
    }

    @DisplayName("Test show CV")
    @Test
    public void testCreateVacancy(){
        vacancyPage = new VacancyPage(chromeDriver);
        vacancyPage.getMyCV().click();
        assertTrue(vacancyPage.getOrderCV().isDisplayed());
        assertTrue(vacancyPage.getCreateCV().isDisplayed());
    }


    @DisplayName("Test create Vacancy wo fields")
    @Test
    public void testCreateVacancyWOFillFields(){

    }
}
