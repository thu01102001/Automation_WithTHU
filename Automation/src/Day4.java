import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class Day4 {
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_Egov_Button() throws InterruptedException {
        driver.get("https://egov.danang.gov.vn/reg");
        //Verify button disable
        Assert.assertFalse(driver.findElement(By.cssSelector("input.egov-button")).isEnabled());
        driver.findElement(By.cssSelector("input#chinhSach")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input.egov-button")).isEnabled());
        Thread.sleep(3000);

        //Verify màu
        String registerBackgroundRGB = driver.findElement(By.cssSelector("input.egov-button")).getCssValue("background-color");
        Color registerBackgroundColor = Color.fromString(registerBackgroundRGB);
        String registerBackgroundHexa = registerBackgroundColor.asHex();
        Assert.assertEquals(registerBackgroundHexa, "#ef5a00");
    }
    @Test
    public void TC_02_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-item.popup-login-tab-login")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color")).asHex().toLowerCase(), "#000000");
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("thu@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color")).asHex().toLowerCase(), "#c92127");

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
