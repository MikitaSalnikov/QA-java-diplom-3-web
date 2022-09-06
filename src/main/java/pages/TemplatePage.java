package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.page;


import java.time.Duration;

public class TemplatePage {
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Личный Кабинет')]")
    private SelenideElement personalInHeader;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Конструктор')]")
    private SelenideElement constructorInHeader;
    @FindBy(how = How.XPATH, using = "//div[contains(@class,'AppHeader_header__logo__2D0X2')]")
    private SelenideElement logoInHeader;

    public void clickPersonalInHeader() {
        personalInHeader.shouldBe(Condition.enabled, Duration.ofSeconds(2)).click();
    }

    public PersonalPage clickPersonalInHeader(boolean isAuth) {
        personalInHeader.shouldBe(Condition.enabled, Duration.ofSeconds(3)).click();
        return page(PersonalPage.class);
    }

    public MainPage clickConstructorInHeader() {
        constructorInHeader.shouldBe(Condition.enabled, Duration.ofSeconds(3)).click();
        return page(MainPage.class);
    }

    public MainPage clickLogoInHeader() {
        logoInHeader.shouldBe(Condition.enabled, Duration.ofSeconds(3)).click();
        return page(MainPage.class);

    }
}
