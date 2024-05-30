import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day13_Action {
    WebDriver driver; //khai báo bến
    Actions actions;
    JavascriptExecutor jsExecutor;
    String username = "thu", email = "thu@gmail.com", bio = "xin chào", country = "Australia", day = "01", month = "10", year= "2001";
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_Actions() throws InterruptedException {
        driver.get("http://webdriveruniversity.com/Actions/index.html#");

        //Kéo thả
        WebElement form = driver.findElement(By.cssSelector("div#draggable"));
        WebElement to = driver.findElement(By.cssSelector("div#droppable"));
        actions.dragAndDrop(form, to).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droppable']/p/b")).getText(), "Dropped!");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droppable']/p")).getCssValue("background-color"), "rgb(244, 89, 80)");
        System.out.println(driver.findElement(By.xpath("//div[@id='droppable']/p")).getCssValue("border-color"));

        //Double click
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("div#double-click")).getCssValue("background-color")).asHex(), "#fec42d");
        actions.doubleClick(driver.findElement(By.cssSelector("div#double-click"))).perform();
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("div#double-click")).getCssValue("background-color")).asHex(), "#93cb5a");

        //Hover btn 1
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me First!']")).getCssValue("background-color")).asHex().toUpperCase(), "#E6E6E6");
        actions.moveToElement(driver.findElement(By.xpath("//button[text()='Hover Over Me First!']"))).build().perform();
        Thread.sleep(3000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me First!']")).getCssValue("background-color")).asHex().toLowerCase(), "#4a4955");
        System.out.println(driver.findElement(By.xpath("//button[text()='Hover Over Me First!']")).getCssValue("background-color"));
        Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Hover Over Me First!']/following-sibling::div")).isDisplayed());
        actions.moveToElement(driver.findElement(By.xpath("//button[text()='Hover Over Me First!']/following-sibling::div"))).build().perform();
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me First!']/following-sibling::div/a")).getCssValue("color")).asHex().toLowerCase(), "#2aa2c4");

        //Hover btn 2
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Second!']")).getCssValue("background-color")).asHex().toUpperCase(), "#E6E6E6");
        actions.moveToElement(driver.findElement(By.xpath("//button[text()='Hover Over Me Second!']"))).build().perform();
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Second!']")).getCssValue("background-color")).asHex().toLowerCase(), "#4a4955");
        Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Hover Over Me Second!']/following-sibling::div")).isDisplayed());
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Second!']/following-sibling::div")).getCssValue("background-color")).asHex().toLowerCase(), "#f9f9f9");
        actions.moveToElement(driver.findElement(By.xpath("//button[text()='Hover Over Me Second!']/following-sibling::div"))).build().perform();
        Thread.sleep(3000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Second!']/following-sibling::div/a")).getCssValue("color")).asHex().toLowerCase(), "#2aa2c4");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Second!']/following-sibling::div/a")).getCssValue("background-color")).asHex().toUpperCase(), "#D6D6D6");

        //Hover btn 3
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']")).getCssValue("background-color")).asHex().toUpperCase(), "#E6E6E6");
        actions.moveToElement(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']"))).build().perform();
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']")).getCssValue("background-color")).asHex().toLowerCase(), "#4a4955");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']")).getCssValue("color")).asHex().toLowerCase(), "#ffffff");
        Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']/following-sibling::div")).isDisplayed());
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']/following-sibling::div")).getCssValue("background-color")).asHex().toLowerCase(), "#f9f9f9");
        actions.moveToElement(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']/following-sibling::div/a[1]"))).build().perform();
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']/following-sibling::div/a[1]")).getCssValue("color")).asHex().toLowerCase(), "#2aa2c4");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']/following-sibling::div/a[1]")).getCssValue("background-color")).asHex().toUpperCase(), "#D6D6D6");
        Thread.sleep(5000);
        actions.moveToElement(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']/following-sibling::div/a[2]"))).build().perform();
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']/following-sibling::div/a[2]")).getCssValue("color")).asHex().toLowerCase(), "#2aa2c4");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']/following-sibling::div/a[2]")).getCssValue("background-color")).asHex().toUpperCase(), "#D6D6D6");

        //Nhấn giữ ClickAndHold
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("div#click-box")).getCssValue("background-color")).asHex().toLowerCase(), "#000000");
        actions.clickAndHold(driver.findElement(By.cssSelector("div#click-box"))).build().perform();
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("div#click-box")).getCssValue("background-color")).asHex().toLowerCase(), "#00ff00");
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("div#click-box")).getCssValue("background-color")).asHex().toLowerCase(), "#00ff00");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#click-box")).getCssValue("font-size"), "30px");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#click-box")).getText(), "Well done! keep holding that click now.....");
        actions.release().perform();
        Thread.sleep(3000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("div#click-box")).getCssValue("background-color")).asHex().toLowerCase(), "#ff6347");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#click-box")).getText(), "Dont release me!!!");
        Thread.sleep(3000);
    }
    @Test
    public void TC_02_RegisterPage() {
        driver.get("https://material.playwrightvn.com/01-xpath-register-page.html");
        driver.findElement(By.cssSelector("input#username")).sendKeys(username);
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#female")).click();
        driver.findElement(By.cssSelector("input#traveling")).click();
        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText(country);
        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#country"))).isMultiple());
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#country"))).getFirstSelectedOption().getText(), country);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#country"))).getOptions().size(), 4);
//        driver.findElement(By.cssSelector("input#dob")).sendKeys(day+ "/" +month+ "/" +year);
        driver.findElement(By.cssSelector("textarea#bio")).sendKeys(bio);
        WebElement ratingInput = driver.findElement(By.cssSelector("input#rating"));
        jsExecutor.executeScript("arguments[0].value = arguments[1]", ratingInput, 8);
//        driver.findElement(By.cssSelector("input#newsletter")).click();
        WebElement favoriteColor = driver.findElement(By.cssSelector("input#favcolor"));
        jsExecutor.executeScript("arguments[0].value = arguments[1]", favoriteColor, "#19d238");
        List<WebElement> allRadio = driver.findElements(By.xpath("//input[@type='radio']"));
        String nameRadio = null, nameCheckbox = null, newsletter = null;
        for (WebElement radio : allRadio) {
            if(radio.isSelected()) {
                nameRadio = radio.getAttribute("value");
            }
        }
        List<WebElement> allCheckbox = driver.findElements(By.xpath("//input[@name='hobbies']"));
        for(WebElement checkbox : allCheckbox) {
            if(checkbox.isSelected()) {
                 nameCheckbox = checkbox.getAttribute("value");
            }
        }
        String rateus = driver.findElement(By.cssSelector("input#rating")).getAttribute("value");
        String favor = driver.findElement(By.cssSelector("input#favcolor")).getAttribute("value");
        if(driver.findElement(By.cssSelector("input#newsletter")).isSelected()) {
            newsletter = "Yes";
        } else {
            newsletter = "No";
        }
        driver.findElement(By.xpath("//button[text()='Register']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//tr//td[2]")).getText(), username);
        Assert.assertEquals(driver.findElement(By.xpath("//tr//td[3]")).getText(), email);
        System.out.println(driver.findElement(By.xpath("//tr//td[4]")).getText());
        Assert.assertEquals(driver.findElement(By.xpath("//tr//td[4]")).getText(),
                "" +
                        "Gender: " +nameRadio+ "\n" +
                        "Hobbies: " +nameCheckbox+ "\n" +
                        "Country: " +country.toLowerCase()+ "\n" +
                        "Date of Birth:\n" +
                        "Biography: " +bio+ "\n" +
                        "Rating: " +rateus+ "\n" +
                        "Favorite Color: " +favor+ "\n" +
                        "Newsletter: " +newsletter);

        driver.findElement(By.xpath("//button[text()='Edit']")).click();
        driver.findElement(By.cssSelector("input#username")).clear();
        username = "nguyenthilethu";
        driver.findElement(By.cssSelector("input#username")).sendKeys(username);
        driver.findElement(By.xpath("//button[text()='Register']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//tr//td[2]")).getText(), username);
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
