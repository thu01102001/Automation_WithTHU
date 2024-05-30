import org.openqa.selenium.*;
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

import java.time.Duration;
import java.util.List;

public class Day14_Alert {
    WebDriver driver; //khai báo bến
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_Accept_Alert() throws InterruptedException {
        driver.get("http://webdriveruniversity.com/Popup-Alerts/index.html");
        driver.findElement(By.xpath("//h2[text()='JavaScript Alert']/following-sibling::div/span")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(3000);
        Assert.assertEquals(alert.getText(), "I am an alert box!");
        alert.accept();
    }
    @Test
    public void TC_02_Confirm_Alert() throws InterruptedException {
        driver.get("http://webdriveruniversity.com/Popup-Alerts/index.html");
        driver.findElement(By.xpath("//h2[text()='JavaScript Confirm Box']/following-sibling::div/span")).click();
        Alert cfAlert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(3000);
        Assert.assertEquals(cfAlert.getText(), "Press a button!");
        cfAlert.dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#confirm-alert-text")).getText(), "You pressed Cancel!");
    }
    @Test
    public void TC_03_Demo_Alert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.cssSelector("button#alertButton")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(3000);
        Assert.assertEquals(alert.getText(), "You clicked a button");
        alert.accept();

        driver.findElement(By.cssSelector("button#timerAlertButton")).click();
        Alert alertTimeout = explicitWait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(3000);
        Assert.assertEquals(alertTimeout.getText(), "This alert appeared after 5 seconds");
        alert.accept();

        driver.findElement(By.cssSelector("button#confirmButton")).click();
        Alert alertCfY = explicitWait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(3000);
        Assert.assertEquals(alertCfY.getText(), "Do you confirm action?");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#confirmResult")).getText(), "You selected Ok");
        driver.findElement(By.cssSelector("button#confirmButton")).click();
        Alert alertCfN = explicitWait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(3000);
        Assert.assertEquals(alertCfN.getText(), "Do you confirm action?");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#confirmResult")).getText(), "You selected Cancel");

        driver.findElement(By.cssSelector("button#promtButton")).click();
        Alert alertPrompt = explicitWait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(3000);
        Assert.assertEquals(alertPrompt.getText(), "Please enter your name");
        String text = "nguyen thi le thu";
        alert.sendKeys(text);
        alertPrompt.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#promptResult")).getText(), "You entered " +text);
    }

    @Test
    public void TC_04_Alert_Automation() {
        driver.get("https://demo.automationtesting.in/Alerts.html");
        driver.findElement(By.cssSelector("div#OKTab button")).click();
        Alert alertAccept = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alertAccept.getText(), "I am an alert box!");
        alertAccept.accept();

        driver.findElement(By.xpath("//a[text()='Alert with OK & Cancel ']")).click();
        driver.findElement(By.cssSelector("div#CancelTab button")).click();
        Alert alertOK = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alertOK.getText(), "Press a Button !");
        alertOK.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "You pressed Ok");

        driver.findElement(By.cssSelector("div#CancelTab button")).click();
        Alert alertCancel = explicitWait.until(ExpectedConditions.alertIsPresent());
        alertCancel.dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "You Pressed Cancel");

        driver.findElement(By.xpath("//a[text()='Alert with Textbox ']")).click();
        driver.findElement(By.cssSelector("div#Textbox button")).click();
        Alert alertPrompt = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alertPrompt.getText(), "Please enter your name");
        String text = "thu";
        alertPrompt.sendKeys(text);
        alertPrompt.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo1")).getText(), "Hello " +text+ " How are you today");
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
