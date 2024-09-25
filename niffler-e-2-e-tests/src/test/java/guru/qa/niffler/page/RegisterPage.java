package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;


public class RegisterPage {
    private final SelenideElement userNameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement passwordSubmitInput = $("#passwordSubmit");
    private final SelenideElement formSubmitButton = $(".form__submit");
    private final SelenideElement formErrorTextSpan = $(".form__error");

    public RegisterPage setUserName(String userNane) {
        userNameInput.setValue(userNane);
        return this;
    }

    public RegisterPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public RegisterPage submitPassword(String password) {
        passwordSubmitInput.setValue(password);
        return this;
    }

    public SuccessRegisterPage submitRegisterSuccess() {
        formSubmitButton.click();
        return new SuccessRegisterPage();
    }

    public RegisterPage negativeSubmitRegister() {
        formSubmitButton.click();
        return this;
    }

    public void checkErrorMessage(String errorText) {
        formErrorTextSpan.shouldHave(Condition.text(errorText));
    }

}
