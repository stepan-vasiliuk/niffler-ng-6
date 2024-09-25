package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final ElementsCollection tableRows = $("#spendings tbody").$$("tr");
    private final SelenideElement mainMenuButton = $x("//button[@aria-label='Menu']");
    private final SelenideElement profileButton = $x("//a[contains(text(), 'Profile')]");

    public EditSpendingPage editSpending(String spendingDescription) {
        tableRows.find(text(spendingDescription)).$$("td").get(5).click();
        return new EditSpendingPage();
    }

    public MainPage checkMainPageLoaded() {
        mainMenuButton.shouldBe(visible);
        return this;
    }

    public void checkThatTableContainsSpending(String spendingDescription) {
        tableRows.find(text(spendingDescription)).should(visible);
    }

    public UserProfilePage navigateToUserProfile() {
        mainMenuButton.click();
        profileButton.click();
        return new UserProfilePage();
    }

}
