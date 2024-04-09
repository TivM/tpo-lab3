import org.example.ConfProperties;
import org.example.pages.AuthPage;
import org.example.pages.CVPage;
import org.example.pages.MainPage;
import org.example.pages.SearchPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchCVTest {

    private static WebDriver chromeDriver;
    private static MainPage mainPage;
    private static SearchPage searchPage;
    public static AuthPage authpage;

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
        searchPage.getVacancyButton().click();
    }

    @DisplayName("Test switch to CV search")
    @Test
    public void testSwitch(){
        String header = searchPage.getCvHeader().getText();
        String pattern = "Найдено .*(\\d) резюме у .*(\\d) соискателей";
        assertTrue(header.matches(pattern));
    }

    public String openCV(){
        String name = searchPage.getFirstCVHeader().getText();
        searchPage.getFirstCVHeader().click();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ArrayList<String> tabs = new ArrayList<>(chromeDriver.getWindowHandles());
        String currentTab = chromeDriver.getWindowHandle();
        chromeDriver.switchTo().window(tabs.get(tabs.indexOf(currentTab)+1));
        return name;
    }

    @DisplayName("Test choose CV")
    @Test
    public void testChooseCV(){
        String cvName = openCV();
        CVPage page = new CVPage(chromeDriver);
        String nameOnVacancyPage = page.getNameElement().getText();
        assertEquals(cvName, nameOnVacancyPage);
    }
}
