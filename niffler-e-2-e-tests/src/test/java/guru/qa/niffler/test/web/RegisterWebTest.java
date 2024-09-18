package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterWebTest {
    private static final Config CFG = Config.getInstance();

    @Test
    void newUserSuccessRegisterTest() {
        String successMessage = Selenide.open(CFG.frontUrl(), LoginPage.class)
                .pressRegisterButton()
                .setUserName("newUser")
                .setPassword("newPassw")
                .submitPassword("newPassw")
                .submitRegister().getCongratsMessageText();

        Assertions.assertTrue(successMessage.startsWith("Congratulations!"));
    }

    void negativePasswordAreNotEqualsTest() {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .pressRegisterButton()
                .setUserName("test3")
                .setPassword("newPassw")
                .submitPassword("differentPsw");
    }
}
