import org.example.ConfProperties;
import org.example.pages.AuthPage;
import org.example.pages.MainPage;
import org.example.pages.SearchPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {

    private static WebDriver chromeDriver;
    private static MainPage mainPage;


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

    @BeforeEach
    public void setAuthPage(){
        chromeDriver.get(ConfProperties.getProperty("mainpage"));
        mainPage.getLoginButton().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AuthPage authpage = new AuthPage(chromeDriver);
        authpage.getLoginWithPasswordButton().click();
        authpage.getLoginFieldWithPassword().sendKeys(ConfProperties.getProperty("auth_email"));
        authpage.getPasswdField().sendKeys(ConfProperties.getProperty("auth_passwd"));
        authpage.getInButton().click();
    }

    @AfterAll
    public static void close(){
        chromeDriver.close();
    }

    @Test
    @DisplayName("Test Search Button exist")
    public void testSearchButtonExist(){
        assertTrue(mainPage.getSearchButton().isDisplayed());
    }

    @Test
    @DisplayName("Test Search Input exist")
    public void testSearchInputExist(){
        assertTrue(mainPage.getSearchInput().isDisplayed());
    }

    @Test
    @DisplayName("Test Search Vacancies")
    public void testSearchVacancies(){
        String workExample = "Tester";
        String resultExpect = "Работа " + workExample.toLowerCase() + " в Москве";
        mainPage.getSearchInput().sendKeys(workExample);
        mainPage.getSearchButton().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        SearchPage searchPage = new SearchPage(chromeDriver);
        assertEquals(resultExpect, searchPage.getSearchResultHeader().getText());
    }
}


