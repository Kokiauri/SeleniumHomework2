package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

@Epic("Forms")
@Feature("Automation Practice Form")
public class FormTests extends BaseTest {

    @Test
    @Story("Submit student form")
    @Severity(SeverityLevel.NORMAL)
    public void submitForm() {
        PracticeFormPage form = new PracticeFormPage(driver, wait);
        form.open();
        form.fillBasicInfo("Bon", "Do", "Bondo@Bon.Do");
        form.selectGender("Male");
        form.submit();
    }
}
