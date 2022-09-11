import api.client.CustomerClient;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//@RunWith(Parameterized.class)
public class RegistrationTest {
    String email = "rick@morthy.dg";
    String pass = "password";
    //    private final String testBrowser;
//
//    public RegistrationTest(String testBrowser) {
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
        RegistrationPage page = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        WebDriverRunner.getWebDriver().manage().window().maximize();
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
    @DisplayName("Корректная регистрация")
    public void checkCorrectRegistration() {
        RegistrationPage page = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        LoginPage loginPage = page.registration("Richard", email, pass);
        assertThat(loginPage.isLoginTitle(), equalTo(true));
    }
    @Test
    @DisplayName("Ошибка при регистрации с паролем менее 6 символов")
    public void checkShortPassError() {
        RegistrationPage page = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        page.registration("Richard", email + "r", "passw");
        assertThat(page.isPasswordCorrect(), equalTo(false));
    }
}
