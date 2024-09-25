package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SuccessRegisterPage {
    private final SelenideElement successRegisterButton = $(".form_sign-in");
    private final SelenideElement congratsMessage = $(".form__paragraph_success");

    public LoginPage returnToLogin() {
        successRegisterButton.click();
        return new LoginPage();
    }

    public void checkSuccessMessageText(String expectedText) {
        congratsMessage.shouldHave(Condition.partialText(expectedText));
    }
}
