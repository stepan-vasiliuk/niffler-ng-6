package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginWebTest {
    private static final Config CFG = Config.getInstance();
    private static final Faker faker = new Faker();

    @Test
    void successfulUserLoginTest() {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "12345")
                .checkMainPageLoaded();
    }

    @Test
    void negativeUserDoesNotExistsTest() {
        String expectedErrorMessage = "Неверные учетные данные пользователя";
        String errorMessage = Selenide.open(CFG.frontUrl(), LoginPage.class)
                .userNameInput(faker.name().username())
                .passwordInput("11111")
                .pressSubmitButton()
                .getErrorMessageText();
        Assertions.assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    void negativeIncorrectPasswordTest() {
        String expectedErrorMessage = "Неверные учетные данные пользователя";
        String errorMessage = Selenide.open(CFG.frontUrl(), LoginPage.class)
                .userNameInput("duck")
                .passwordInput(faker.elderScrolls().firstName())
                .pressSubmitButton()
                .getErrorMessageText();
        Assertions.assertEquals(expectedErrorMessage, errorMessage);
    }
}
