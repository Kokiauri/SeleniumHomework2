import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    }

    @Test
    public void test(){
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Bon");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Do");
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("Bondo@Bon.Do");

        WebElement genderOption = driver.findElement(By.xpath("//label[text()='" + "Male" + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderOption);

        driver.findElement(By.xpath("//*[@id=\"userNumber\"]")).sendKeys("0123456789");

        WebElement birthDate = driver.findElement(By.xpath("//*[@id=\"dateOfBirthInput\"]"));
        birthDate.sendKeys(Keys.CONTROL + "a");
        birthDate.sendKeys("31 Oct 2005");
        birthDate.sendKeys(Keys.ENTER);

        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys("English");
        subjectsInput.sendKeys(Keys.ENTER);

        WebElement hobbies = driver.findElement(By.xpath("//label[text()='" + "Reading" + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hobbies);
        driver.findElement(By.xpath("//*[@id=\"currentAddress\"]")).sendKeys("Mojave Wasteland");


        WebElement stateInput = driver.findElement(By.id("react-select-3-input"));
        stateInput.sendKeys("NCR");
        stateInput.sendKeys(Keys.ENTER);

        WebElement cityInput = driver.findElement(By.id("react-select-4-input"));
        cityInput.sendKeys("Delhi");
        cityInput.sendKeys(Keys.ENTER);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();

        List<WebElement> rows = driver.findElements(By.xpath("table[contains(@class, 'table-dark')]//tbody/tr"));

        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("Student Name", "Bon Do");
        expectedData.put("Student Email", "Bondo@Bon.Do");
        expectedData.put("Gender", "Male");
        expectedData.put("Mobile", "0123456789");
        expectedData.put("Date of Birth", "31 October,2005");
        expectedData.put("Subjects", "English");
        expectedData.put("Hobbies", "Reading");
        expectedData.put("Address", "Mojave Wasteland");
        expectedData.put("State and City", "NCR Delhi");

        for (WebElement row : rows) {
            String label = row.findElement(By.xpath("./td[1]")).getText();
            String value = row.findElement(By.xpath("./td[2]")).getText();
            if (expectedData.containsKey(label)) {
                Assert.assertEquals(value, expectedData.get(label), "mismatch: " + label);
            }
        }

    }
    @AfterClass
    public void tearDown(){
    driver.quit();
    }
}
