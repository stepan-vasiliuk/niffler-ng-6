package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.jupiter.annotation.DisabledByIssue;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CategoryWebTest {
    private static final Config CFG = Config.getInstance();
    private static final String GITHUB_TOKEN = "GITHUB_TOKEN";

    @Category(
            username = "duck",
            title = "",
            isArchived = true)
    @Test
    void archivedCategoryShouldBeVisibleInCategoriesList(CategoryJson categoryJson) {
        SelenideElement archived = Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "12345")
                .navigateToUserProfile()
                .switchShowArchiveCheckBox()
                .checkCategoryIsArchived(categoryJson.name());
    }

    @Category(
            username = "duck",
            title = "",
            isArchived = false)
    @DisabledByIssue("2")
    @Test
    void activeCategoryShouldBeVisibleInCategoriesList(CategoryJson categoryJson) {
        System.getenv(GITHUB_TOKEN);
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "12345")
                .navigateToUserProfile()
                .checkCategoryIsActive(categoryJson.name());
    }
}
