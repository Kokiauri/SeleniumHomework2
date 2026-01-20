package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertsPage;

@Epic("Alerts")
@Feature("Prompt Alert")
public class AlertTests extends BaseTest {

    @Test
    @Story("Send text to alert")
    @Severity(SeverityLevel.CRITICAL)
    public void promptAlertTest() {
        AlertsPage page = new AlertsPage(driver);
        page.open();
        page.openPromptAlert();
        String name = "Kokiauri";
        page.sendName(name);
        Assert.assertEquals(page.getResultName(), name);
    }
}
