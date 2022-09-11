package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class PersonalPage extends TemplatePage {
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Выход')]")
    private SelenideElement logoutButton;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'В этом разделе вы можете изменить свои персональны')]")
    private SelenideElement personalAccText;
    @Step("Нажатие кнопки Выход")
    public LoginPage logout() {
        logoutButton.shouldBe(Condition.visible, Duration.ofSeconds(3)).click();
        return page(LoginPage.class);
    }
    public PersonalPage AuthInPersonalAccount(String email, String pass) {
        LoginPage loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.auth(email, pass);
        return loginPage.clickPersonalInHeader(true);
    }
    public boolean isPersonalAccount() {
        if (personalAccText.shouldBe(Condition.visible, Duration.ofSeconds(2)).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}
