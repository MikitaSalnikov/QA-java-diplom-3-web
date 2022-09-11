import api.client.CustomerClient;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//@RunWith(Parameterized.class)
public class AuthTest {
    static String email = "rick@morthy.dg";
    static String pass = "password";
//    private final String testBrowser;
//
//    public AuthTest(String testBrowser) {
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
    @DisplayName("Авторизация через Войти в аккаунт на главной")
    public void checkLoginFromMainPage() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        LoginPage loginPage = mainPage.clickEnterAccauntButton();
        mainPage = loginPage.auth(email, pass);
        assertThat(mainPage.isAuthorizedUserOnMain(), equalTo(true));
    }
    @Test
    @DisplayName("Авторизация через Личный кабинет в шапке сайта")
    public void checkLoginFromHeader() {
        LoginPage loginPage = open("https://stellarburgers.nomoreparties.site", LoginPage.class);
        loginPage.clickPersonalInHeader();
        MainPage mainPage = loginPage.auth(email, pass);
        assertThat(mainPage.isAuthorizedUserOnMain(), equalTo(true));
    }
    @Test
    @DisplayName("Авторизация через ссылку Войти со страницы регистрации")
    public void checkLoginFromPassReg() {
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        LoginPage loginPage = registrationPage.clickEnterAuthButton();
        MainPage mainPage = loginPage.auth(email, pass);
        assertThat(mainPage.isAuthorizedUserOnMain(), equalTo(true));
    }
    @Test
    @DisplayName("Авторизация через ссылку Войти со страницы посстановления пароля")
    public void checkLoginFromPassReset() {
        RegistrationPage registrationPage = open("https://stellarburgers.nomoreparties.site/forgot-password", RegistrationPage.class);
        LoginPage loginPage = registrationPage.clickEnterAuthButton();
        MainPage mainPage = loginPage.auth(email, pass);
        assertThat(mainPage.isAuthorizedUserOnMain(), equalTo(true));
    }
}
