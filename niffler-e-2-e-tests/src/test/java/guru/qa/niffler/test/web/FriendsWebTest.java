package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.extension.BrowserExtension;
import guru.qa.niffler.jupiter.extension.UsersQueueExtension;
import guru.qa.niffler.jupiter.extension.UsersQueueExtension.StaticUser;
import guru.qa.niffler.jupiter.extension.UsersQueueExtension.UserType;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static guru.qa.niffler.jupiter.extension.UsersQueueExtension.UserType.Type.*;

@ExtendWith(BrowserExtension.class)
public class FriendsWebTest {
    private static final Config CFG = Config.getInstance();

    @Test
    @ExtendWith(UsersQueueExtension.class)
    void friendIncomeRequestTest(@UserType(WITH_INCOME_REQUEST) StaticUser user) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login(user.userName(), user.password())
                .navigateToFriendsPage()
                .navigateToFriendsTab()
                .checkUserDisplayedInIncomeRequestList(user.income());
    }

    @Test
    @ExtendWith(UsersQueueExtension.class)
    void friendVisibleInFriendsListTest(@UserType(WITH_FRIEND) StaticUser user) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login(user.userName(), user.password())
                .navigateToFriendsPage()
                .navigateToFriendsTab()
                .checkUserDisplayedInFriendRequestList(user.friend());
    }

    @Test
    @ExtendWith(UsersQueueExtension.class)
    void emptyFriendsListTest(@UserType StaticUser user) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login(user.userName(), user.password())
                .navigateToFriendsPage()
                .navigateToFriendsTab()
                .checkNoUsersMessageIsDisplayed();
    }

    @Test
    @ExtendWith(UsersQueueExtension.class)
    void outcomeRequestUserIsVisibleInAllPeopleListTest(
            @UserType(WITH_OUTCOME_REQUEST) StaticUser user
    ) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login(user.userName(), user.password())
                .navigateToFriendsPage()
                .navigateToAllPeopleTab()
                .checkUserDisplayedInAllPeopleList(user.outcome());
    }
}
