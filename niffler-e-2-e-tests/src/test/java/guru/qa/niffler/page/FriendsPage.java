package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FriendsPage {

    private final SelenideElement friendsTab = $x("//h2[text()='Friends']/parent::a");
    private final SelenideElement allPeopleTab = $x("//h2[text()='All people']/parent::a");
    private final ElementsCollection friendRequestTableRows = $$x("//tbody[@id='requests']/tr");
    private final ElementsCollection friendsTableRows = $$x("//tbody[@id='friends']/tr");
    private final ElementsCollection allPeopleTableRows = $$x("//tbody[@id='all']/tr");
    private final SelenideElement noUsersMessage = $x("//p[text()='There are no users yet']");


    public FriendsPage navigateToFriendsTab() {
        friendsTab.click();
        friendsTab.shouldHave(Condition.attribute("aria-selected", String.valueOf(true)));
        return this;
    }

    public FriendsPage navigateToAllPeopleTab() {
        allPeopleTab.click();
        allPeopleTab.shouldHave(attribute("aria-selected", String.valueOf(true)));
        return this;
    }

    public void checkUserDisplayedInIncomeRequestList(String userName) {
        friendRequestTableRows.findBy(text(userName)).shouldBe(visible);
    }

    public void checkUserDisplayedInFriendRequestList(String username) {
        friendsTableRows.findBy(text(username)).shouldBe(visible);
    }

    public void checkNoUsersMessageIsDisplayed() {
        noUsersMessage.shouldBe(visible);
    }

    public void checkUserDisplayedInAllPeopleList(String userName) {
        allPeopleTableRows.findBy(text(userName)).shouldBe(visible);
    }

}
