package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;


public class CategoryWebTest {
    private static final Config CFG = Config.getInstance();

    @Category(
            username = "duck",
            isArchived = true
    )
    @Test
    void archivedCategoryCanBeExtracted(CategoryJson categoryJson) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "12345")
                .navigateToUserProfile()
                .switchShowArchiveCheckBox()
                .findArchivedCategoryAndExtract(categoryJson.name())
                .checkCategoryIsActive(categoryJson.name());
    }

    @Category(
            username = "duck",
            isArchived = false
    )
    @Test
    void activeCategoryCanBeArchived(CategoryJson categoryJson) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("duck", "12345")
                .navigateToUserProfile()
                .findActiveCategoryAndArchive(categoryJson.name())
                .checkCategoryIsArchived(categoryJson.name());
    }

}
