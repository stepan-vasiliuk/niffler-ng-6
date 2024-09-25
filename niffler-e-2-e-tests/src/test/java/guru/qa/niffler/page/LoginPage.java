package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameInputField = $("input[name='username']");
    private final SelenideElement passwordInputField = $("input[name='password']");
    private final SelenideElement submitButton = $("button[type='submit']");
    private final SelenideElement registerButton = $(".form__register");
    private final SelenideElement formErrorMessage = $(".form__error");

    public MainPage login(String username, String password) {
        usernameInputField.setValue(username);
        passwordInputField.setValue(password);
        submitButton.click();
        return new MainPage();
    }

    public RegisterPage pressRegisterButton() {
        registerButton.click();
        return new RegisterPage();
    }

    public LoginPage userNameInput(String username) {
        usernameInputField.setValue(username);
        return this;
    }

    public LoginPage passwordInput(String password) {
        passwordInputField.setValue(password);
        return this;
    }

    public LoginPage pressSubmitButton() {
        submitButton.click();
        return this;
    }

    public String getErrorMessageText() {
        return formErrorMessage.shouldBe(visible).getOwnText();
    }
}
