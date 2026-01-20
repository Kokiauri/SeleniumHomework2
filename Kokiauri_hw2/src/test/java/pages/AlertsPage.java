package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsPage {

    private WebDriver driver;

    private By textboxTab = By.xpath("//a[@href='#Textbox']");
    private By textboxButton = By.xpath("//*[@id='Textbox']/button");
    private By resultText = By.id("demo1");

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open alerts page")
    public void open() {
        driver.get("https://demo.automationtesting.in/Alerts.html");
    }

    @Step("Open prompt alert")
    public void openPromptAlert() {
        driver.findElement(textboxTab).click();
        driver.findElement(textboxButton).click();
    }

    @Step("Send name to alert")
    public void sendName(String name) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(name);
        alert.accept();
    }

    @Step("Get result name")
    public String getResultName() {
        String text = driver.findElement(resultText).getText();
        return text.replace("Hello ", "").replace(" How are you today", "");
    }
}
