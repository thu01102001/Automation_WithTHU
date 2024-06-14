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

public class Day21_Popup {
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_Fixed_Popup_In_Dom() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_.icon-before")).click();
        Thread.sleep(2000);
        By loginPage = By.cssSelector("div#modal-login-v1 div.modal-dialog");
        //popup dang hien thi
        Assert.assertTrue(driver.findElement(loginPage).isDisplayed());
        driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationFC");
        driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationFC");
        driver.findElement(By.cssSelector("button.btn-login-v1")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading']/preceding-sibling::div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("div#modal-login-v1 div.modal-dialog button.close")).click();
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(loginPage).isDisplayed());

    }
    @Test
   public void TC_02_() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        Thread.sleep(2000);
//        Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login div.modal-content")).isDisplayed());
        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationFC");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("automationFC");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Tiki() throws InterruptedException {
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Thread.sleep(2000);

        //khong nhap du lieu
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[2]")).getText(), "Mật khẩu không được để trống");

        //dong popup
        driver.findElement(By.cssSelector("img.close-img")).click();
        Thread.sleep(2000);

        //khi popup dong lai thi HTML cua popup ko con trong DOM nua
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);

    }

    @Test
    public void TC_04_Fb() throws InterruptedException {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("div._8ien")).isDisplayed());
        driver.findElement(By.cssSelector("img._8idr.img")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElements(By.cssSelector("div._8ien")).size(), 0);
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
