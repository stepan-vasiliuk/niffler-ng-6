package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterWebTest {
    private static final Config CFG = Config.getInstance();
    private static final Faker faker = new Faker();

    @Test
    void newUserSuccessRegisterTest() {
        String userName = faker.name().firstName();

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .pressRegisterButton()
                .setUserName(userName)
                .setPassword("qqww11")
                .submitPassword("qqww11")
                .submitRegisterSuccess()
                .checkSuccessMessageText("Congratulations");
    }

    @Test
    void negativePasswordAreNotEqualsTest() {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .pressRegisterButton()
                .setUserName(faker.name().username())
                .setPassword("newPassw")
                .submitPassword("differentPsw")
                .negativeSubmitRegister()
                .checkErrorMessage("Passwords should be equal");
    }

    @Test
    void negativeRegisterExistingUserTest() {
        String userName = "svuser";
        String expectedErrorMessage = String.format("Username `%s` already exists", userName);

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .pressRegisterButton()
                .setUserName(userName)
                .setPassword("qqww11")
                .submitPassword("qqww11")
                .negativeSubmitRegister()
                .checkErrorMessage(expectedErrorMessage);
    }
}
