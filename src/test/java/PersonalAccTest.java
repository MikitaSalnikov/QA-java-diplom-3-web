import api.client.CustomerClient;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalPage;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//@RunWith(Parameterized.class)
public class PersonalAccTest {
    static String email = "rick@morthy.dg";
    static String pass = "password";
//    private final String testBrowser;
//
//    public PersonalAccTest(String testBrowser) {
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
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        CustomerClient customerClient = new CustomerClient();
        customerClient.createCustomer(email, pass);
    }
    @After
    public void tearDown() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        CustomerClient customerClient = new CustomerClient();
        try {
            customerClient.deleteCustomer(customerClient.loginCustomer(email, pass));
        } catch (Exception e) {
        }
    }
    @Test
    @DisplayName("Входа в личный кабинет пользователя")
    public void enterPersonalAccount() {
        PersonalPage personalPage = new PersonalPage();
        personalPage = personalPage.AuthInPersonalAccount(email, pass);
        assertThat(personalPage.isPersonalAccount(), equalTo(true));
    }
    @Test
    @DisplayName("Переход в Конструктор из личного кабинета")
    public void checkLinkToConstructorFromPersonalAcc() {
        PersonalPage personalPage = new PersonalPage();
        personalPage = personalPage.AuthInPersonalAccount(email, pass);
        MainPage mainPage = personalPage.clickConstructorInHeader();
        assertThat(mainPage.isAuthorizedUserOnMain(), equalTo(true));
    }
    @Test
    @DisplayName("Переход на главную страницу из личного кабинета")
    public void checkLinkToMainLogoFromPersonalAcc() {
        PersonalPage personalPage = new PersonalPage();
        personalPage = personalPage.AuthInPersonalAccount(email, pass);
        MainPage mainPage = personalPage.clickLogoInHeader();
        assertThat(mainPage.isAuthorizedUserOnMain(), equalTo(true));
    }
    @Test
    @DisplayName("Разавторизация из личного кабинета")
    public void checkLogout() {
        PersonalPage personalPage = new PersonalPage();
        personalPage = personalPage.AuthInPersonalAccount(email, pass);
        LoginPage loginPage = personalPage.logout();
        assertThat(loginPage.isLoginTitle(), equalTo(true));
    }
}
