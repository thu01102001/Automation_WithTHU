import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class Day19_PracticeAutomation {
    WebDriver driver; //khai báo bến
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_OnlyThreeSliders() {
        driver.get("https://practice.automationtesting.in/");
        List<WebElement> sliders = driver.findElements(By.cssSelector("div.n2-ss-slide-background"));
        if (sliders.size() == 3) {
            System.out.println("Trang chu co 3 thanh truot");
        }
        else {
            System.out.println("trang chu co " +sliders.size()+ " thanh truot");
        }
    }
    @Test
    public void TC_02_OnlyThreeArrivals() {
        driver.get("https://practice.automationtesting.in/");
        List<WebElement> arrivals = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link"));
        if (arrivals.size() == 3) {
            System.out.println("Trang chu co 3 arrivals");
        }
        else {
            System.out.println("Trang chu co " +arrivals.size()+ " arrvials");
        }
    }

    @Test
    public void TC_03_Navigate() throws InterruptedException {
        driver.get("https://practice.automationtesting.in/");
        List<WebElement> imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
        for (int i = 0; i < imgs.size(); i ++) {
            imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
            imgs.get(i).click();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(By.cssSelector("button.single_add_to_cart_button")).isDisplayed());
            driver.navigate().back();
            imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
        }
    }

    @Test
    public void TC_04_Description() throws InterruptedException {
        driver.get("https://practice.automationtesting.in/");
        List<WebElement> imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
        for (int i = 0; i < imgs.size(); i ++) {
            imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
            imgs.get(i).click();
            Thread.sleep(3000);
            System.out.println("Arrial: " +i);
            System.out.println(driver.findElement(By.cssSelector("div#tab-description p")).getText());
            System.out.println("=========");
            driver.navigate().back();
            imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
        }
    }

    @Test
    public void TC_05_Review() throws InterruptedException {
        driver.get("https://practice.automationtesting.in/");
        List<WebElement> imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
        for (int i = 0; i < imgs.size(); i ++) {
            imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
            imgs.get(i).click();
            Thread.sleep(3000);
            System.out.println("Arrial: " +i);
//            explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Reviews (0)']")));
            driver.findElement(By.xpath("//a[text()='Reviews (0)']")).click();
            List<WebElement> listCmt = driver.findElements(By.cssSelector("ol.commentlist"));
            System.out.println(listCmt.size());
            System.out.println("=========");
            driver.navigate().back();
            imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
        }
    }

    @Test
    public void TC_06_Add_Basket() throws InterruptedException {
        driver.get("https://practice.automationtesting.in/");
        List<WebElement> imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
        for (int i = 0; i < imgs.size(); i ++) {
            imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
            imgs.get(i).click();
            Thread.sleep(3000);
            System.out.println("Arrial: " +i);
//            explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Reviews (0)']")));
            driver.findElement(By.cssSelector("button.single_add_to_cart_button")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//a[text()='View Basket']")).isDisplayed());
            driver.navigate().back();
            driver.navigate().back();
            imgs = driver.findElements(By.cssSelector("a.woocommerce-LoopProduct-link img"));
        }
    }

    @Test
    public void TC_07_AddBasketMoreBook() {

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
