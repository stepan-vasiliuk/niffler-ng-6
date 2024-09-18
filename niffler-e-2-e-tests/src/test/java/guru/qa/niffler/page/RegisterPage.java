package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;


public class RegisterPage {
    private final SelenideElement userNameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement passwordSubmitInput = $("#passwordSubmit");
    private final SelenideElement formSubmitButton = $(".form__submit");

    public RegisterPage setUserName(String userNane) {
        userNameInput.sendKeys(userNane);
        return new RegisterPage();
    }

    public RegisterPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return new RegisterPage();
    }

    public RegisterPage submitPassword(String password) {
        passwordSubmitInput.sendKeys(password);
        return new RegisterPage();
    }

    public SuccessRegisterPage submitRegister() {
        formSubmitButton.click();
        return new SuccessRegisterPage();
    }

}
