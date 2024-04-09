import org.example.ConfProperties;
import org.example.pages.AuthPage;
import org.example.pages.CompanyPage;
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

public class SearchCompanyTest {

    private static WebDriver chromeDriver;
    private static MainPage mainPage;
    private static SearchPage searchPage;
    public static AuthPage authpage;
    private static final String workExample = "Java junior";

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

    @BeforeEach
    public void setSearchPage(){
        chromeDriver.get(ConfProperties.getProperty("mainpage"));
        mainPage.getLoginButton().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        authpage = new AuthPage(chromeDriver);
        authpage.getLoginWithPasswordButton().click();
        authpage.getLoginFieldWithPassword().sendKeys(ConfProperties.getProperty("auth_email"));
        authpage.getPasswdField().sendKeys(ConfProperties.getProperty("auth_passwd"));
        authpage.getInButton().click();
        mainPage.getSearchInput().sendKeys(workExample);
        mainPage.getSearchButton().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        searchPage = new SearchPage(chromeDriver);
        searchPage.getCompanyButton().click();
    }

    public String openCompany(){
        String name = searchPage.getFirstCompanyHeader().getText();
        searchPage.getFirstCompanyHeader().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return name;
    }

    @DisplayName("Test switch to Company search")
    @Test
    public void testSwitch(){
        String searchInput = searchPage.getCompanyInput().getAttribute("value");
        assertEquals(workExample, searchInput);
    }

    @DisplayName("Test search of company")
    @Test
    public void testSearchNew(){
        String newCompany = "Apple";
        searchPage.getCompanyInput().clear();
        searchPage.getCompanyInput().sendKeys(newCompany);
        searchPage.getCompanySearchButton().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String result = searchPage.getCompanySearchResult().getText();
        String pattern = ".*(\\d) компаний найдено";
        assertTrue(result.matches(pattern));
    }

    @DisplayName("Test choose Company")
    @Test
    public void testChooseCV(){
        String newCompany = "Apple";
        searchPage.getCompanyInput().clear();
        searchPage.getCompanyInput().sendKeys(newCompany);
        searchPage.getCompanySearchButton().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String cvName = openCompany();
        CompanyPage page = new CompanyPage(chromeDriver);
        String nameOnVacancyPage = page.getCompanyName().getText();
        assertEquals(cvName, nameOnVacancyPage);
    }
}
