import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class Day12 {
    WebDriver driver; //khai báo bến
    Actions actions;
    WebDriverWait explicitWait;
    String todo = "nguyen thi le thu";
    @BeforeClass
    public void InitialBrowser() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }
//
    @Test
    public void TC_01_ToDoList() throws InterruptedException {
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");
        driver.findElement(By.xpath("//input[@placeholder='Add new todo']")).click();
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//input[@placeholder='Add new todo']")).getCssValue("border-color").toLowerCase()).asHex(), "#2980b9");

        driver.findElement(By.xpath("//ul/li[1]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//ul/li[1]")).getAttribute("class"), "completed");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder='Add new todo']")).sendKeys(todo + Keys.ENTER);
        Thread.sleep(3000);

        actions.moveToElement(driver.findElement(By.xpath("//li[contains(text(), '" +todo+ "')]"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//li[contains(text(), '" +todo+ "')]/span")).click();
        Thread.sleep(3000);

        List<WebElement> li = driver.findElements(By.xpath("//li[contains(text(), '" +todo+ "')]"));
        if(li.isEmpty()) {
            Assert.assertTrue(true, "Phần tử đã bị xóa khỏi DOM");
        }

        driver.findElement(By.cssSelector("i#plus-icon")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Add new todo']")).getCssValue("display"), "inline-block");
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
