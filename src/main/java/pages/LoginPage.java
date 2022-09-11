package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends TemplatePage {
    @FindBy(how = How.XPATH, using = ".//label[contains(string(), 'Email')]/../input")
    private SelenideElement loginInputEmail;
    @FindBy(how = How.XPATH, using = ".//label[contains(string(), 'Пароль')]/../input")
    private SelenideElement loginInputPass;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Войти')]")
    private SelenideElement loginSubmitButton;
    @FindBy(how = How.XPATH, using = "//h2[contains(text(),'Вход')]")
    private SelenideElement titleLogin;
    public boolean isLoginTitle() {
        if (titleLogin.shouldBe(Condition.visible, Duration.ofSeconds(2)).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
    @Step("Ввод email в поле авторизации")
    public void setLoginInputEmail(String email) {
        loginInputEmail.setValue(email);
    }
    @Step("Ввод пароля в поле авторизации")
    public void setLoginInputPass(String pass) {
        loginInputPass.setValue(pass);
    }
    @Step("Нажатие кнопки Вход")
    public void LoginSubmitButtonClick() {
        loginSubmitButton.click();
    }
    public MainPage auth(String email, String pass) {
        setLoginInputEmail(email);
        setLoginInputPass(pass);
        LoginSubmitButtonClick();
        return page(MainPage.class);
    }
}
