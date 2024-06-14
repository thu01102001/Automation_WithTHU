import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Day20_Prestashop {
    WebDriver driver; //khai báo bến
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_Slider() {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
        List<WebElement> allSliders = driver.findElements(By.cssSelector("li.carousel-item img"));
        System.out.println("Trang chu co " +allSliders.size()+ " sliders");
    }
    @Test
     public void TC_02_Register() throws InterruptedException {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
        driver.findElement(By.cssSelector("div.user-info a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("header.page-header h1")).getText(), "Log in to your account");
        //chua co tai khoan
        //de trong

        jsExecutor.executeScript("arguments[0].setAttribute('required', '');", driver.findElement(By.cssSelector("input#field-email")));
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String error = (String) jsExecutor.executeScript("arguments[0].validationMessage;", driver.findElement(By.cssSelector("input#field-email")));
        Assert.assertEquals(error, "Please fill out this field");
    }

    @Test
    public void TC_03_Login() {

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
