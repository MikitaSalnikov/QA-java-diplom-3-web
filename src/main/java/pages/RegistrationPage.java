package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage extends TemplatePage {
    @FindBy(how = How.XPATH, using = ".//label[contains(string(), 'Имя')]/../input")
    private SelenideElement regInputName;
    @FindBy(how = How.XPATH, using = ".//label[contains(string(), 'Email')]/../input")
    private SelenideElement regInputEmail;
    @FindBy(how = How.XPATH, using = ".//label[contains(string(), 'Пароль')]/../input")
    private SelenideElement regInputPass;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Некорректный пароль')]")
    private SelenideElement regPassError;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Зарегистрироваться')]")
    private SelenideElement regSubmitButton;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Войти')]")
    private SelenideElement regAuthLink;
    @Step("Ввод имени в поле регистрации")
    public void setRegInputName(String name) {
        regInputName.setValue(name);
    }
    @Step("Ввод email в поле регистрации")
    public void setRegInputEmail(String email) {
        regInputEmail.setValue(email);
    }
    @Step("Ввод пароля в поле регистрации")
    public void setRegInputPass(String pass) {
        regInputPass.setValue(pass);
    }
    @Step("Нажатие кнопки Зарегистрироваться")
    public void regSubmitButtonClick() {
        regSubmitButton.click();
    }
    @Step("Нажатие кнопки Войти")
    public LoginPage clickEnterAuthButton() {
        regAuthLink.click();
        return page(LoginPage.class);
    }
    public LoginPage registration(String name, String email, String pass) {
        setRegInputName(name);
        setRegInputEmail(email);
        setRegInputPass(pass);
        regSubmitButtonClick();
        return page(LoginPage.class);
    }
    public boolean isPasswordCorrect() {
        if (regPassError.shouldBe(Condition.visible, Duration.ofSeconds(2)).isDisplayed()) {
            return false;
        } else {
            return true;
        }
    }
}
