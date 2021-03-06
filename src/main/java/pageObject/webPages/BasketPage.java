package pageObject.webPages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageObject.BasePage;
import pageObject.Values;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BasketPage extends BasePage {

    ElementsCollection notebooksInBasket = $$("[class*='cart-form__description_font-weight_semibold cart-form__description_condensed-other']");
    SelenideElement fieldForInputQuantity = $("[class*='cart-form__input_max-width_xxxxsssss cart-form__input_nonadaptive']");
    SelenideElement deleteNotebook = $("[class='button-style button-style_auxiliary button-style_small cart-form__button cart-form__button_remove']");
    SelenideElement cityForDelivery = $("[class='cart-form__link cart-form__link_primary cart-form__link_base-alter']");
    SelenideElement inputCity = $("[class*='auth-input auth-input_primary']");
    SelenideElement changeBtn = $("[class*='auth-button auth-button_primary']");
    SelenideElement quantityNotebooksInBasket = $("[class*='cart-form__description_other cart-form__description_extended']");

    public BasketPage checkNotebooksInBasket() {
        notebooksInBasket.forEach(notebook -> notebook.shouldBe(visible));
        return this;
    }

    public BasketPage checkMaxNumberNotebook()  {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fieldForInputQuantity.clear();
        fieldForInputQuantity.setValue("100");
        quantityNotebooksInBasket.shouldBe(matchText("13"));
        return this;
    }

    public BasketPage deleteNotebooksFromBasket() {
        for (int i = 0; i < 3; i++) {
            fieldForInputQuantity.click();
            deleteNotebook.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        deleteNotebook.shouldNot(exist);
        return this;
    }

    public BasketPage changeCityForDelivery(Values cityName) {
        cityForDelivery.click();
        inputCity.setValue(cityName.getCityForDelivery()).pressEnter();
        changeBtn.click();
        cityForDelivery.shouldBe(matchText(cityName.getCityForDelivery()));
        return this;
    }

    public BasketPage elementNotDisplayed() {
        deleteNotebook.shouldNot(exist);
        return this;
    }
}
