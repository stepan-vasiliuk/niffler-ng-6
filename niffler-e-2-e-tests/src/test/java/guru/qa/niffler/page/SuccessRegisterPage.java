package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

public class SuccessRegisterPage {
    private final SelenideElement successRegisterButton = $(".form_sign-in");
    private final SelenideElement congratsMessage = $(".form__paragraph_success");

    public LoginPage returnToLogin() {
        successRegisterButton.click();

        return new LoginPage();
    }

    public String getCongratsMessageText() {
        return congratsMessage.getOwnText();
    }
}
