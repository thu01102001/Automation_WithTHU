import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day7 {
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        driver.findElement(By.cssSelector("button.form-submit-button"));
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.form-submit-button")).getCssValue("background-color")).asHex().toLowerCase(), "#e9e9ed");
        //nhập thông tin
        //Patient Gender *
        new Select(driver.findElement(By.xpath("//label[contains(text(), 'Patient Gender')]/following-sibling::div/select"))).selectByVisibleText("Male");
        //Patient Name *
        driver.findElement(By.xpath("//label[text()=' First Name ']/preceding-sibling::input")).sendKeys("Nguyễn");
        driver.findElement(By.xpath("//label[text()=' Last Name ']/preceding-sibling::input")).sendKeys("Nguyễn");
        //Patient Birth Date *
        new Select(driver.findElement(By.xpath("//label[text()=' Month ']/preceding-sibling::select"))).selectByVisibleText("May");
        new Select(driver.findElement(By.xpath("//label[text()=' Day ']/preceding-sibling::select"))).selectByVisibleText("11");
        new Select(driver.findElement(By.xpath("//label[text()=' Year ']/preceding-sibling::select"))).selectByVisibleText("2001");
        //Patient Height (cm's)
        driver.findElement(By.xpath("//label[contains(text(), \"Patient Height (cm's)\")]/following-sibling::div/input")).sendKeys("150");
        driver.findElement(By.xpath("//label[contains(text(), \"Patient Weight (kg's)\")]/following-sibling::div/input")).sendKeys("48");
        //Patient Email
        driver.findElement(By.xpath("//label[contains(text(), 'Patient E-Mail')]/following-sibling::div/input")).sendKeys("thu@gmail.com");
        //Reason for seeing the doctor
        driver.findElement(By.xpath("//label[contains(text(), 'Reason for seeing the doctor:')]/following-sibling::div/input")).sendKeys("Thuuuuu");
        //Please list any drug allergies
        driver.findElement(By.xpath("//label[text() = ' Please list any drug allergies ']//following-sibling::div/textarea")).sendKeys("hihihi");
        //Checkbox
        driver.findElement(By.xpath("//input[@value='Anemia']")).click();
        // Other illnesses:
        driver.findElement(By.xpath("//label[text() = ' Other illnesses: ']//following-sibling::div/input")).sendKeys("hi");
        driver.findElement(By.xpath("//label[text() = ' Please list any Operations and Dates of Each ']//following-sibling::div/textarea")).sendKeys("k");
        driver.findElement(By.xpath("//label[text() = ' Please list your Current Medications ']//following-sibling::div/textarea")).sendKeys("h");
        //Radio
        driver.findElement(By.xpath("//input[@value='Never']")).click();
        driver.findElement(By.xpath("//input[@value='I have a loose diet']")).click();
        driver.findElement(By.xpath("//input[@value=\"I don't drink\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"I don't use caffeine\"]")).click();
        driver.findElement(By.xpath("//input[@value='No']")).click();
        //Include other comments regarding your Medical History
        driver.findElement(By.xpath("//label[text()=' Include other comments regarding your Medical History ']/following-sibling::div//textarea")).sendKeys("hihi");
        //Verify button sáng màu
    }
    @Test
    public void TC_02_() {

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
