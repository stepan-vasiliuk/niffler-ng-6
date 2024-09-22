package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class UserProfilePage {

    private final SelenideElement profilePageHeader = $x("//h2[text()='Profile']");
    private final SelenideElement userNameInputField = $("#username");
    private final SelenideElement nameInputField = $("#name");
    private final SelenideElement categoryInputField = $("#category");

}
