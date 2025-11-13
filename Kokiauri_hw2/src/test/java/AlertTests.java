import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTests {
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
    public void test() {
        driver.get("https://demo.automationtesting.in/Alerts.html");

        driver.findElement(By.xpath("//a[@href='#Textbox']")).click();
        driver.findElement(By.xpath("//*[@id=\"Textbox\"]/button")).click();

        Alert alert = driver.switchTo().alert();
        String nameToSend = "Kokiauri";
        alert.sendKeys(nameToSend);
        alert.accept();

        String fullText = driver.findElement(By.xpath("//*[@id='demo1']")).getText();
        String name = fullText.replace("Hello ", "").replace(" How are you today", "");
        System.out.println("Received name: " + name);
        Assert.assertEquals(name, nameToSend, "Sent name is not same as the output.");

    }

    @AfterClass
    public void tearDown(){
    driver.quit();
    }
}
