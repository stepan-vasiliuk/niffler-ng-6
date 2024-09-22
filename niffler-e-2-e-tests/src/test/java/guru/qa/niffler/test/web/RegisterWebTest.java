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

        String successMessage = Selenide.open(CFG.frontUrl(), LoginPage.class)
                .pressRegisterButton()
                .setUserName(userName)
                .setPassword("qqww11")
                .submitPassword("qqww11")
                .submitRegisterSuccess()
                .getCongratsMessageText();

        Assertions.assertTrue(successMessage.startsWith("Congratulations!"));
    }

    @Test
    void negativePasswordAreNotEqualsTest() {
//        String userName = faker.name().firstName();
//        String expectedErrorMessage = String.format("Username `%s` already exists", userName);

        String actualErrorMessage = Selenide.open(CFG.frontUrl(), LoginPage.class)
                .pressRegisterButton()
                .setUserName(faker.name().username())
                .setPassword("newPassw")
                .submitPassword("differentPsw")
                .negativeSubmitRegister()
                .getFormErrorMessage();

        Assertions.assertEquals("Passwords should be equal", actualErrorMessage);
    }

    @Test
    void negativeRegisterExistingUserTest() {
        String userName = "svuser";
        String expectedErrorMessage = String.format("Username `%s` already exists", userName);

        String actualErrorMessage = Selenide.open(CFG.frontUrl(), LoginPage.class)
                .pressRegisterButton()
                .setUserName(userName)
                .setPassword("qqww11")
                .submitPassword("qqww11")
                .negativeSubmitRegister()
                .getFormErrorMessage();

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
