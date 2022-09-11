package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.page;

public class TemplatePage {
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Личный Кабинет')]")
    private SelenideElement personalInHeader;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Конструктор')]")
    private SelenideElement constructorInHeader;
    @FindBy(how = How.XPATH, using = "//div[contains(@class,'AppHeader_header__logo__2D0X2')]")
    private SelenideElement logoInHeader;
    @Step("Нажатие Личный кабинет в шапке (неавторизованный)")
    public void clickPersonalInHeader() {
        personalInHeader.shouldBe(Condition.enabled, Duration.ofSeconds(2)).click();
    }
    @Step("Нажатие Личный кабинет в шапке (авторизованый)")
    public PersonalPage clickPersonalInHeader(boolean isAuth) {
        personalInHeader.shouldBe(Condition.enabled, Duration.ofSeconds(3)).click();
        return page(PersonalPage.class);
    }
    @Step("Нажатие Конструктор в шапке сайта")
    public MainPage clickConstructorInHeader() {
        constructorInHeader.shouldBe(Condition.enabled, Duration.ofSeconds(3)).click();
        return page(MainPage.class);
    }
    @Step("Нажатие лого в шапке сайта")
    public MainPage clickLogoInHeader() {
        logoInHeader.shouldBe(Condition.enabled, Duration.ofSeconds(3)).click();
        return page(MainPage.class);
    }
}
