import api.client.CustomerClient;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.RegistrationPage;
import com.codeborne.selenide.WebDriverRunner;

import java.lang.reflect.Executable;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
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
    public void checkCorrectRegistration() {
        RegistrationPage page = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        LoginPage loginPage = page.registration("Richard", email, pass);
        assertThat(loginPage.isLoginTitle(), equalTo(true));
    }

    @Test
    public void checkShortPassError() {
        RegistrationPage page = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        page.registration("Richard", email + "r", "passw");
        assertThat(page.isPasswordCorrect(), equalTo(false));
    }
}
