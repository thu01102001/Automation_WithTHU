import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day10 {
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_() {
        driver.get("https://toolsqa.com/selenium-training?q=banner#enroll-form");
        driver.findElement(By.cssSelector("input#first-name")).sendKeys("Thu");
        driver.findElement(By.cssSelector("input#last-name")).sendKeys("Nguyen");
        driver.findElement(By.cssSelector("input#email")).sendKeys("thu@gmail.com");
        driver.findElement(By.cssSelector("input#mobile")).sendKeys("thu@gmail.com");

        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Lesotho");
        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#country"))).isMultiple());
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#country"))).getOptions().size(), 224);

        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#country"))).getFirstSelectedOption().getText(), "Lesotho");
        driver.findElement(By.cssSelector("input#city")).sendKeys("Nam Dinh");
        driver.findElement(By.cssSelector("textarea#message")).sendKeys("nguyen thi le thu");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Send']")).getCssValue("background-color").toLowerCase()).asHex(), "#27579e");
    }
    @Test
    public void TC_02_Saucedemo() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input#login-button")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
