import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day1 {
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://demo.seleniumeasy.com/basic-checkbox-demo.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
    }
//
    @Test
    public void TC_01_Checkbox() throws InterruptedException {
        driver.findElement(By.id("isAgeSelected")).click();
        Assert.assertTrue(driver.findElement(By.id("isAgeSelected")).isSelected());
        driver.findElement(By.xpath("//input[@id='isAgeSelected']//parent::label")).click();
        Thread.sleep(3000);
        Assert.assertFalse(driver.findElement(By.xpath("//input[@id='isAgeSelected']//parent::label")).isSelected());
    }
    @Test
    public void TC_02_Dropdown() throws InterruptedException {
        driver.get("https://www.globalsqa.com/demo-site/select-dropdown-menu/");
        Thread.sleep(3000);
        String select = "American Samoa";
        new Select(driver.findElement(By.xpath("//div[@class='information closable']//following-sibling::p/select"))).selectByVisibleText(select);
        Assert.assertFalse(new Select(driver.findElement(By.xpath("//div[@class='information closable']//following-sibling::p/select"))).isMultiple());
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//div[@class='information closable']//following-sibling::p/select"))).getFirstSelectedOption().getText(), select);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//div[@class='information closable']//following-sibling::p/select"))).getOptions().size(), 249);
        List<WebElement> el = driver.findElements(By.xpath("//div[@class='information closable']//following-sibling::p/select/option"));
        for (WebElement element:el) {
            System.out.println(element.getText());
        }
    }
    @Test
    public void TC_03_Dropdown1() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");
        Thread.sleep(3000);
        new Select(driver.findElement(By.id("oldSelectMenu"))).selectByVisibleText("White");
        Assert.assertFalse(new Select(driver.findElement(By.id("oldSelectMenu"))).isMultiple());
        Assert.assertEquals(new Select(driver.findElement(By.id("oldSelectMenu"))).getFirstSelectedOption().getText(), "White");
        Assert.assertEquals(new Select(driver.findElement(By.id("oldSelectMenu"))).getOptions().size(), 11);

        List<WebElement> color = driver.findElements(By.xpath("//select[@id='oldSelectMenu']/option"));
        for(WebElement element : color) {
            System.out.println(element.getText());
        }
    }
    @Test
    public void TC_04_Dropdown2() throws InterruptedException {
        driver.get("https://getbootstrap.com/docs/5.0/forms/select/");
        Thread.sleep(3000);
        new Select(driver.findElement(By.xpath("//h2[@id='default']//following-sibling::div[@class='bd-example'][1]/select"))).selectByVisibleText("Two");
        Assert.assertFalse(new Select(driver.findElement(By.xpath("//h2[@id='default']//following-sibling::div[@class='bd-example'][1]/select"))).isMultiple());
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//h2[@id='default']//following-sibling::div[@class='bd-example'][1]/select"))).getFirstSelectedOption().getText(), "Two");
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//h2[@id='default']//following-sibling::div[@class='bd-example'][1]/select"))).getOptions().size(), 4);
        List<WebElement> number = driver.findElements(By.xpath("//h2[@id='default']//following-sibling::div[@class='bd-example'][1]/select"));
        for (WebElement element : number) {
            System.out.println(element.getText());
        }
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
