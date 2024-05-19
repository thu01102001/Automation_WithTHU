import org.openqa.selenium.By;
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

public class Day5 {
    WebDriver driver; //khai báo bến
    WebDriverWait wait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_DefaultTelerik() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::span/input")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::span/input")).isSelected());
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
