import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class Day16_TodoPage {
    WebDriver driver; //khai báo bến
    Actions actions;
    WebDriverWait explicitWait;
    String task = "thu", newTask = "xin chào";
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://material.playwrightvn.com/03-xpath-todo-list.html");
        By add =  By.cssSelector("button#add-task");
        Assert.assertEquals(Color.fromString(driver.findElement(add).getCssValue("background")).asHex().toLowerCase(), "#333333");
        actions.moveToElement(driver.findElement(add)).build().perform();
        Thread.sleep(3000);
        Assert.assertEquals(Color.fromString(driver.findElement(add).getCssValue("background")).asHex().toLowerCase(), "#555555");
        driver.findElement(By.cssSelector("input#new-task")).sendKeys(task);
        driver.findElement(add).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("ul#task-list span")).getText(), task);
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
        Thread.sleep(3000);
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Edit Task");
        alert.sendKeys(newTask);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("ul#task-list span")).getText(), newTask);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(alert.getText(), "Are you sure you want to delete this task?");
        alert.accept();
        List<WebElement> span = driver.findElements(By.xpath("//span[text() = '" +newTask+ "']"));
        if(span.isEmpty()) {
            Assert.assertTrue(true, "đã xóa");
        }
    }

    @Test
    public void TC_02_() throws InterruptedException {
        driver.get("https://material.playwrightvn.com/04-xpath-personal-notes.html");
        String noteTitle1 = "Hello", noteTitle2 = "Hí";
        String noteContent1 = "Xin chào các bạn", noteContent2 = "What is your name";
        driver.findElement(By.cssSelector("input#note-title")).sendKeys(noteTitle1);
        driver.findElement(By.cssSelector("textarea#note-content")).sendKeys(noteContent1);
        driver.findElement(By.cssSelector("button#add-note")).click();
        driver.findElement(By.cssSelector("input#note-title")).sendKeys(noteTitle2);
        driver.findElement(By.cssSelector("textarea#note-content")).sendKeys(noteContent2);
        driver.findElement(By.cssSelector("button#add-note")).click();
        driver.findElement(By.cssSelector("input#note-title")).sendKeys("noteTitle2");
        driver.findElement(By.cssSelector("textarea#note-content")).sendKeys("noteContent2");
        driver.findElement(By.cssSelector("button#add-note")).click();
        driver.findElement(By.cssSelector("input#note-title")).sendKeys("nuteTitle3");
        driver.findElement(By.cssSelector("textarea#note-content")).sendKeys("nuteContent3");
        driver.findElement(By.cssSelector("button#add-note")).click();
        List<WebElement> list = driver.findElements(By.cssSelector("ul#notes-list li"));
        int listNote = list.size();
        WebElement counts = driver.findElement(By.cssSelector("div#note-count"));
        String count = counts.getText().replaceAll("[^0-9]", "");
        int totalCount = Integer.parseInt(count);
        Assert.assertEquals(totalCount, listNote);
        System.out.println("total: " +totalCount);
        System.out.println("size: " +listNote);
        driver.findElement(By.xpath("//strong[text()='" +noteTitle2+ "']/parent::div/following-sibling::div/button[text()='Edit']")).click();
        driver.findElement(By.cssSelector("input#note-title")).clear();
        driver.findElement(By.cssSelector("input#note-title")).sendKeys("nguyen thi");
        driver.findElement(By.cssSelector("textarea#note-content")).clear();
        driver.findElement(By.cssSelector("textarea#note-content")).sendKeys("le thu");
        driver.findElement(By.cssSelector("button#add-note")).click();
        driver.findElement(By.xpath("//strong[text()='" +noteTitle1+ "']/parent::div/following-sibling::div/button[text()='Delete']")).click();
        Thread.sleep(3000);
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Are you sure you want to delete this note?");
        alert.accept();
        //update lại số lượng
        list = driver.findElements(By.cssSelector("ul#notes-list li"));
        listNote = list.size();
        counts = driver.findElement(By.cssSelector("div#note-count"));
        count = counts.getText().replaceAll("[^0-9]", "");
        totalCount = Integer.parseInt(count);
        Assert.assertEquals(totalCount, listNote);
        System.out.println("total: " +totalCount);
        System.out.println("size: " +listNote);
        System.out.println("---------------------------");

        //search
        list = driver.findElements(By.cssSelector("ul#notes-list li"));
        String[] keywords = {"n", "o", "t"};
        WebElement inputSearch = driver.findElement(By.cssSelector("input#search"));
        for(String keyword : keywords) {
            inputSearch.sendKeys(keyword);
            Thread.sleep(3000);
            list = driver.findElements(By.cssSelector("ul#notes-list li"));
            listNote = list.size();
            counts = driver.findElement(By.cssSelector("div#note-count"));
            //Loại bỏ text không phải là số s
            count = counts.getText().replaceAll("[^0-9]", "");
            totalCount = Integer.parseInt(count);
            Assert.assertEquals(totalCount, listNote);
            System.out.println("total: " +totalCount);
            System.out.println("size: " +listNote);
            System.out.println("---------------------------");
        }
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
