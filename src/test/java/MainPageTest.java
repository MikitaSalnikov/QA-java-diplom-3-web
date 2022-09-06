import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//@RunWith(Parameterized.class)
public class MainPageTest {
//    private final String testBrowser;
//
//    public MainPageTest(String testBrowser) {
//        this.testBrowser = testBrowser;
//    }
//
//    @Parameterized.Parameters
//    public static Object[] getSumData() {
//        return new Object[][]{
//                {"chrome"},
//                {"firefox"}, // передали тестовые данные
//        };
//    }

    @Before
    public void setUp() {
//        Configuration.browser = testBrowser;
        RegistrationPage page = open("https://stellarburgers.nomoreparties.site", RegistrationPage.class);
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
    @After
    public void tearDown(){
        webdriver().driver().close();
    }

    @Test
    public void checkBunTab() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        mainPage.clickSauceTab();
        mainPage.clickBunTab();
        assertThat(mainPage.isBunTab(), equalTo(true));
    }

    @Test
    public void checkSauceTab() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        mainPage.clickSauceTab();
        assertThat(mainPage.isSauceTab(), equalTo(true));
    }

    @Test
    public void checkIngredientTab() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        mainPage.clickIngredientTab();
        assertThat(mainPage.isIngredientTab(), equalTo(true));
    }
}
