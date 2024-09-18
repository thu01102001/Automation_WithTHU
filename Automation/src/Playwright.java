import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Playwright {
    WebDriver driver; //khai báo bến
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }
//
    @Test
    public void TC_01_Tab() throws InterruptedException {
        driver.get("https://material.playwrightvn.com/06-new-tab.html");
        driver.findElement(By.xpath("//a[text()='Truy cập Example 1']")).click();
        Set<String> newWindows = driver.getWindowHandles();
        for(String newWindow : newWindows) {
            if(!newWindow.equals(driver.getWindowHandle())) {
                driver.switchTo().window(newWindow);
            }
        }
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("h1"), "Example Domain")));
        driver.close();
        Set<String> titleWindows = driver.getWindowHandles();
        for(String titleWindow : titleWindows) {
            driver.switchTo().window(titleWindow);
            if(!titleWindow.equals("Trang Thực Hành Mở Tab Mới")) {
                break;
            }
        }
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("h1"), "Khám Phá Tab Mới!")));


    }
    @Test
    public void TC_02_Dropdown() throws InterruptedException {

    }
    @Test
    public void TC_03_Dropdown1() throws InterruptedException {
    }
    @Test
    public void TC_04_Dropdown2() throws InterruptedException {
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
