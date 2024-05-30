import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.List;

public class Day15_Puzzle {
    WebDriver driver; //khai báo bến
    Actions actions;
    WebDriverWait explicit;
    JavascriptExecutor jsExcutor;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        jsExcutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        explicit = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).build().perform();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@role='tooltip']")).isDisplayed());
    }
    @Test
    public void TC_02_() throws InterruptedException {
        driver.get("https://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).build().perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@data-reactid='467']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Wear Online Store");
    }
    @Test
    public void TC_03_() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='fhs_center_right fhs_mouse_point fhs_dropdown_hover fhs_dropdown_click']"))).build().perform();
        Thread.sleep(3000);
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='VPP - Dụng Cụ Học Sinh']"))).build().perform();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//a[@title='VPP - Dụng Cụ Học Sinh']/ancestor::div[@class='fhs_stretch_stretch']/div[3]//a[text()='Gọt Bút Chì']")).isDisplayed());
        driver.findElement(By.xpath("//a[@title='VPP - Dụng Cụ Học Sinh']/ancestor::div[@class='fhs_stretch_stretch']/div[3]//a[text()='Gọt Bút Chì']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='category269']/strong")).getText(), "GỌT BÚT CHÌ");

    }

    @Test
    public void TC_04() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        WebElement o1 = driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee'][1]"));
        WebElement o2 = driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee'][2]"));
        WebElement o3 = driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee'][3]"));
        WebElement o4 = driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee'][4]"));
        actions.clickAndHold(o1).build().perform();
        actions.clickAndHold(o2).build().perform();
        actions.clickAndHold(o3).build().perform();
        actions.clickAndHold(o4).build().perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//li")).getAttribute("class"), "ui-state-default ui-selectee ui-selecting");
        actions.release().build().perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//li")).getAttribute("class"), "ui-state-default ui-selectee ui-selected");
    }
    @Test
    public void TC_05() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        WebElement o1 = driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee'][1]"));
        WebElement o3 = driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee'][3]"));
        WebElement o6 = driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee'][6]"));
        WebElement o11 = driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee'][11]"));
        actions.keyDown(Keys.CONTROL).click(o1).build().perform();
        actions.keyDown(Keys.CONTROL).click(o3).build().perform();
        actions.keyDown(Keys.CONTROL).click(o6).build().perform();
        actions.keyDown(Keys.CONTROL).click(o11).build().perform();
        Thread.sleep(3000);
    }
    @Test
    public void TC_06() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double click me']"));
        jsExcutor.executeScript("arguments[0].scrollIntoView(true);", doubleClick);
        actions.doubleClick(doubleClick).perform();
    }

    @Test
    public void TC_07() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        driver.findElement(By.xpath("//a[text()='Simple Context Menu']")).click();
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).build().perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("ul.context-menu-list.context-menu-root")).isDisplayed());
        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']/parent::li"))).build().perform();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Quit']/parent::li")).getAttribute("class").contains("context-menu-hover"));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Quit']/parent::li")).click();
        Thread.sleep(3000);
        Alert alert = explicit.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("ul.context-menu-list.context-menu-root")).getCssValue("display"), "none");
    }

    @Test
    public void TC_08() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement small = driver.findElement(By.cssSelector("div#draggable"));
        WebElement big = driver.findElement(By.cssSelector("div#droptarget"));
        actions.dragAndDrop(small, big).build().perform();
        Thread.sleep(3000);
        Assert.assertEquals(big.getText(), "You did great!");
    }

    @Test
    public void TC_09() throws InterruptedException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        WebElement a = driver.findElement(By.cssSelector("div#column-a"));
        WebElement b = driver.findElement(By.cssSelector("div#column-b"));
        actions.dragAndDrop(a, b).build().perform();
        actions.release();
        Thread.sleep(3000);

        actions.dragAndDrop(b, a).build().perform();
        actions.release();
        Thread.sleep(3000);
    }

    @Test
    public void TC_10_Puzzle() throws InterruptedException {
        driver.get("https://material.playwrightvn.com/05-xpath-drag-and-drop.html");
        List<WebElement> drag = driver.findElements(By.cssSelector("div.puzzle-piece"));
        List<WebElement> drop = driver.findElements(By.cssSelector("div.dropzone"));
        if(drag.size() == drop.size()) {
            for (int i = 0; i < drag.size(); i++) {
                actions.dragAndDrop(drag.get(i), drop.get(i)).build().perform();
                Thread.sleep(3000);
                Assert.assertEquals(Color.fromString(drop.get(i).getCssValue("background-color")).asHex().toLowerCase(), "#c8e6c9");
            }
        }
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
