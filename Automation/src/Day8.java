import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Day8 {
    WebDriver driver; //khai báo bến
    By result = By.cssSelector("p#result");
    WebDriverWait explicitWWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        explicitWWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
//
    @Test
    public void TC_01_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
//        driver.findElement(By.xpath("//button[text() = 'Click for JS Alert']")).click();
//        Thread.sleep(3000);

        //chờ cho alert present
        //nếu trong thời gian chờ mà xuất hiện thì switch
        //nếu ko xuất hiện thì fail
        Alert alert = explicitWWait.until(ExpectedConditions.alertIsPresent());
        //switch qua
        //Alert alert = driver.switchTo().alert();
        //cancel alert (void dimiss)
        //accept alert (void accept)
        //set text trong alert (String getText())
        //nhập text (void sendKeys)
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        //khi mình accept / cancel thì alert sẽ mất đi
        alert.accept();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(result).getText(), "You clicked an alert successfully");
    }
    @Test
    public void TC_02_Confirm_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text() = 'Click for JS Confirm']")).click();
        Alert alert = explicitWWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        Thread.sleep(3000);
        alert.dismiss(); //cancel
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(result).getText(), "You clicked: Cancel");

    }
    @Test
    public void TC_03_Prompt_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text() = 'Click for JS Prompt']")).click();
        Alert alert = explicitWWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        String text = "Selenium WebDriver";
        alert.sendKeys(text);
        Thread.sleep(3000);
        alert.accept();
        Assert.assertEquals(driver.findElement(result).getText(), "You entered: " +text);
    }
    @Test
    public void TC_04_Authentication_Alert() {
        //thư viện alert ko sử dụng cho authentication
        //sử dụng Chrome devtool Protocol (CDP)
        //Cách 1: truyền hẳn user / password vào hẳn link
        //Trick - By pass
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
        //Cách 2: Chạy trên windows (AutoIT)

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
