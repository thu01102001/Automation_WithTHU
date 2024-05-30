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
import java.util.Random;

public class Day10 {
    WebDriver driver; //khai báo bến
    String lastName = "thu", firstName = "nguyen", password = "1234567", birthDay = "2001-10-01", phone = "0364697180", street1 = "Nam Định", street2 = "Hà Nội", city = "TP.HCM", state = "t", code = "113", country = "Việt Nam";
    Random rand;
    String email = "thu" + new Random().nextInt() + "@gmail.com";
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_() {
        driver.get("https://toolsqa.com/selenium-training?q=banner#enroll-form");
        driver.findElement(By.cssSelector("input#first-name")).sendKeys("Thu");
        driver.findElement(By.cssSelector("input#last-name")).sendKeys("Nguyen");
        driver.findElement(By.cssSelector("input#email")).sendKeys("thu@gmail.com");
        driver.findElement(By.cssSelector("input#mobile")).sendKeys("thu@gmail.com");

        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Lesotho");
        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#country"))).isMultiple());
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#country"))).getOptions().size(), 224);

        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#country"))).getFirstSelectedOption().getText(), "Lesotho");
        driver.findElement(By.cssSelector("input#city")).sendKeys("Nam Dinh");
        driver.findElement(By.cssSelector("textarea#message")).sendKeys("nguyen thi le thu");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Send']")).getCssValue("background-color").toLowerCase()).asHex(), "#27579e");
    }
    @Test
    public void TC_02_Saucedemo() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input#login-button")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void TC_03_herokuapp() throws InterruptedException {
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
        driver.findElement(By.cssSelector("button#signup")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input#firstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastName")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button#submit")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://thinking-tester-contact-list.herokuapp.com/contactList");
        driver.findElement(By.cssSelector("button#add-contact")).click();
        driver.findElement(By.cssSelector("input#firstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastName")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#birthdate")).sendKeys(birthDay);
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
        driver.findElement(By.cssSelector("input#street1")).sendKeys(street1);
        driver.findElement(By.cssSelector("input#street2")).sendKeys(street2);
        driver.findElement(By.cssSelector("input#city")).sendKeys(city);
        driver.findElement(By.cssSelector("input#stateProvince")).sendKeys(state);
        driver.findElement(By.cssSelector("input#postalCode")).sendKeys(code);
        driver.findElement(By.cssSelector("input#country")).sendKeys(country);
        driver.findElement(By.cssSelector("button#submit")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://thinking-tester-contact-list.herokuapp.com/contactList");
        Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='contactTableBodyRow'][1]/td[2]")).getText(), firstName+ " " +lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='contactTableBodyRow'][1]/td[3]")).getText(), birthDay);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='contactTableBodyRow'][1]/td[4]")).getText(), email);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='contactTableBodyRow'][1]/td[5]")).getText(), phone);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='contactTableBodyRow'][1]/td[6]")).getText(), street1+ " " +street2);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='contactTableBodyRow'][1]/td[7]")).getText(), city+ " " +state+ " " +code);
        Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='contactTableBodyRow'][1]/td[8]")).getText(), country);
        driver.findElement(By.cssSelector("button#logout")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://thinking-tester-contact-list.herokuapp.com/");
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button#submit")).click();

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
