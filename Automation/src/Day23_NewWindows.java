import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class Day23_NewWindows {
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://material.playwrightvn.com/index.html");
        driver.findElement(By.xpath("//a[text()='Bài học 1: Mở trang ở tab mới']")).click();
        Thread.sleep(3000);
        switchToWindowByTitle("Trang Thực Hành Mở Tab Mới");
        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(), "Trang Thực Hành Mở Tab Mới");
        driver.findElement(By.xpath("//a[text()='Truy cập Example 1']")).click();
        switchToWindowByTitle("Example Domain");
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "Example Domain");
        Thread.sleep(3000);
        driver.close();
        switchToWindowByTitle("Trang Thực Hành Mở Tab Mới");
        Thread.sleep(3000);
//        driver.findElement(By.xpath("//a[text()='Trở về trang chủ']")).click();
//        Thread.sleep(3000);
        driver.close();
        switchToWindowByTitle("Tài liệu học automation test - Playwright Việt Nam");
        Thread.sleep(3000);

    }

    private void switchToWindowByTitle(String title) {
        Set<String> example1 = driver.getWindowHandles();
        for (String example : example1) {
            driver.switchTo().window(example);
            if(example.equals(title)) {
                break;
            }
        }
    }

    @Test
    public void TC_02_() {

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
