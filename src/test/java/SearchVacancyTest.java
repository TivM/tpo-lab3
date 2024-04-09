import org.example.ConfProperties;
import org.example.pages.AuthPage;
import org.example.pages.MainPage;
import org.example.pages.SearchPage;
import org.example.pages.VacancyPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchVacancyTest {

    private static WebDriver chromeDriver;
    private static MainPage mainPage;
    private static SearchPage searchPage;
    private static AuthPage authpage;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("gekodriver"));
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
        String workExample = "java";
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
    }

    public String openVacancy(){
        String name = searchPage.getFirstVacancyHeader().getText();
        searchPage.getFirstVacancyHeader().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ArrayList<String> tabs = new ArrayList<String> (chromeDriver.getWindowHandles());
        String currentTab = chromeDriver.getWindowHandle();
        chromeDriver.switchTo().window(tabs.get(tabs.indexOf(currentTab)+1));
        return name;
    }

    @DisplayName("Test Choose Vacancy")
    @Test
    public void testChooseVacancy(){
        String name = openVacancy();
        VacancyPage page = new VacancyPage(chromeDriver);
        String nameOnVacancyPage = page.getNameElement().getText();
        assertEquals(name, nameOnVacancyPage);
    }

    @DisplayName("Test Respond to Vacancy")
    @Test
    public void testRespond(){
        openVacancy();
        VacancyPage page = new VacancyPage(chromeDriver);
        page.getRespondButton().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertTrue(page.getWriting().isDisplayed());
    }
}
