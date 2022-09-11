package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.page;

public class MainPage extends TemplatePage {
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Войти в аккаунт')]")
    private SelenideElement enterAccauntButton;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Оформить заказ')]")
    private SelenideElement makeOrderButton;
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Булки')]/..")
    private SelenideElement bunTab;
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Соусы')]/..")
    private SelenideElement sauceTab;
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Начинки')]/..")
    private SelenideElement ingredientTab;
    @Step("Нажатие кнопки Войти в аккаунт")
    public LoginPage clickEnterAccauntButton() {
        enterAccauntButton.click();
        return page(LoginPage.class);
    }
    @Step("Нажатие по вкладке Булки")
    public void clickBunTab() {
        bunTab.shouldBe(Condition.interactable, Duration.ofSeconds(3)).click();
    }
    @Step("Нажатие по вкладке Соусы")
    public void clickSauceTab() {
        sauceTab.shouldBe(Condition.interactable, Duration.ofSeconds(3)).click();
    }
    @Step("Нажатие по вкладке Начинки")
    public void clickIngredientTab() {
        ingredientTab.shouldBe(Condition.interactable, Duration.ofSeconds(3)).click();
    }
    public boolean isAuthorizedUserOnMain() {
        if (makeOrderButton.shouldBe(Condition.visible, Duration.ofSeconds(3)).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isBunTab() {
        if (bunTab.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc")) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isSauceTab() {
        if (sauceTab.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc")) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isIngredientTab() {
        if (ingredientTab.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc")) {
            return true;
        } else {
            return false;
        }
    }
}
