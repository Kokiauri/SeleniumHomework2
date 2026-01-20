package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticeFormPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public PracticeFormPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Open practice form")
    public void open() {
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Step("Fill name and email")
    public void fillBasicInfo(String first, String last, String email) {
        driver.findElement(By.id("firstName")).sendKeys(first);
        driver.findElement(By.id("lastName")).sendKeys(last);
        driver.findElement(By.id("userEmail")).sendKeys(email);
    }

    @Step("Select gender")
    public void selectGender(String gender) {
        WebElement option = driver.findElement(By.xpath("//label[text()='" + gender + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
    }

    @Step("Submit form")
    public void submit() {
        WebElement submit = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
    }

}
