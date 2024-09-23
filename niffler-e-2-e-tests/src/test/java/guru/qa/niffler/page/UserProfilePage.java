package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class UserProfilePage {

    private final SelenideElement profilePageHeader = $x("//h2[text()='Profile']");
    private final SelenideElement userNameInputField = $("#username");
    private final SelenideElement nameInputField = $("#name");
    private final SelenideElement categoryInputField = $("#category");
    private final SelenideElement showArchiveCheckBox = $x("//input[@type='checkbox']");
    private final ElementsCollection categoriesList = $$(".MuiChip-colorPrimary");
    private final ElementsCollection archivedCategoriesList = $$(".MuiChip-colorDefault");

    public UserProfilePage switchShowArchiveCheckBox() {
        showArchiveCheckBox.click();
        return this;
    }

    public UserProfilePage findArchivedCategoryAndExtract(String category) {
        checkCategoryIsArchived(category)
                .shouldBe(Condition.visible)
                .$x("following::span[1]/button[@aria-label='Unarchive category']")
                .click();
        $x("//button[contains(text(), 'Unarchive')]").click();
        return this;
    }

    public UserProfilePage findActiveCategoryAndArchive(String category) {
        checkCategoryIsActive(category)
                .shouldBe(Condition.visible)
                .$x("following::div/button[@aria-label='Archive category']")
                .click();
        $x("//button[contains(text(), 'Archive')]").click();
        return this;
    }

    public SelenideElement checkCategoryIsArchived(String category) {
        return archivedCategoriesList.find(Condition.text(category));
    }

    public SelenideElement checkCategoryIsActive(String category) {
        return categoriesList.find(Condition.text(category));
    }
}
