import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day9 {
    WebDriver driver; //khai báo bến
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }
//
    @Test
    public void TC_01_PracticeForm() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input#firstName")).sendKeys("Nguyen");
        driver.findElement(By.cssSelector("input#lastName")).sendKeys("Thu");
        driver.findElement(By.cssSelector("input#userEmail")).sendKeys("thu@gmail.com");
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Female']/preceding-sibling::input")));

        driver.findElement(By.cssSelector("input#userNumber")).sendKeys("0364607180");
        driver.findElement(By.cssSelector("input#dateOfBirthInput")).sendKeys("07 May 2001");
        driver.findElement(By.xpath("//div[@class='subjects-auto-complete__input']//input")).sendKeys("Toan");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Reading']/preceding-sibling::input")));
        driver.findElement(By.cssSelector(" textarea#currentAddress")).sendKeys("nguyenthilethu");

        //Bị fail do đề frame quảng cáo
        driver.findElement(By.xpath("//div[@id='state']/div")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='state']//following-sibling::div[@class=' css-26l3qy-menu']/div/div")));
        List<WebElement> allItem = driver.findElements(By.xpath("//div[@id='state']//following-sibling::div[@class=' css-26l3qy-menu']/div/div"));
        for(WebElement item : allItem) {
            if(item.getText().equals("Haryana")) {
                item.click();
                break;
            }
        }

        driver.findElement(By.cssSelector("div#city")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='city']//following-sibling::div[@class=' css-26l3qy-menu']/div/div")));
        List<WebElement> allCity = driver.findElements(By.xpath("//div[@id='city']//following-sibling::div[@class=' css-26l3qy-menu']/div/div"));
        for(WebElement itemCity : allCity) {
            if(itemCity.getText().equals("Lucknow")) {
                itemCity.click();
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
