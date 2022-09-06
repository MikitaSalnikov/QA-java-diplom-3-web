package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

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

    public void setRegInputName(String name) {
        regInputName.setValue(name);
    }

    public void setRegInputEmail(String email) {
        regInputEmail.setValue(email);
    }

    public void setRegInputPass(String pass) {
        regInputPass.setValue(pass);
    }

    public void regSubmitButtonClick() {
        regSubmitButton.click();
    }

    public void clickLinkToAuth() {
        regAuthLink.click();
    }

    public LoginPage clickEnterAuthButton() {
        regAuthLink.click();
        return page(LoginPage.class);
    }

    public LoginPage goToAuth() {
        clickLinkToAuth();
        return page(LoginPage.class);
    }

    public LoginPage registration(String name, String email, String pass) {
        setRegInputName(name);
        setRegInputEmail(email);
        setRegInputPass(pass);
        regSubmitButtonClick();
        //sleep(1000);
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
