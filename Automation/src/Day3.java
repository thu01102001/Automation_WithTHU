import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day3 {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_Jquery() throws InterruptedException {
        driver.findElement(By.cssSelector("span#speed-xbutton")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div")));
        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#speed-menu div"));
        for(WebElement item : allItems) {
            String nameItem = item.getText();
            if(nameItem.equals("Medium")) {
                item.click();
                break;
            }
            Thread.sleep(3000);
        }
        EditSelectInDropdown("Slower");
        Thread.sleep(3000);
        driver.navigate().refresh();
        EditSelectInDropdown("Faster");
        Thread.sleep(3000);

    }
    public void EditSelectInDropdown(String nameitem) {
        driver.findElement(By.cssSelector("span#speed-button")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div")));
        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#speed-menu div"));
        for(WebElement item : allItems) {
            String nameItem = item.getText();
            if(nameItem.equals(nameItem)) {
                item.click();
                break;
            }
        }
    }
    @Test
    public void TC_02_React() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        driver.findElement(By.cssSelector("div.ui.fluid.selection.dropdown")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ui.fluid.selection.dropdown span")));
        List<WebElement> allItems = driver.findElements(By.cssSelector("div.ui.fluid.selection.dropdown span"));
        for (WebElement item : allItems) {
            String nameItem = item.getText();
            if(nameItem.equals("Jenny Hess")) {
                item.click();
                break;
            }
        }
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("div.ui.fluid.selection.dropdown")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ui.fluid.selection.dropdown span")));
        for (WebElement item : allItems) {
            String nameItem = item.getText();
            if(nameItem.equals("Elliot Fu")) {
                item.click();
                break;
            }
        }
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("div.ui.fluid.selection.dropdown")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ui.fluid.selection.dropdown span")));
        for (WebElement item : allItems) {
            String nameItem = item.getText();
            if(nameItem.equals("Stevie Feliciano")) {
                item.click();
                break;
            }
        }
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("div.ui.fluid.selection.dropdown")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ui.fluid.selection.dropdown span")));
        for (WebElement item : allItems) {
            String nameItem = item.getText();
            if(nameItem.equals("Christian")) {
                item.click();
                break;
            }
        }
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("div.ui.fluid.selection.dropdown")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ui.fluid.selection.dropdown span")));
        for (WebElement item : allItems) {
            String nameItem = item.getText();
            if(nameItem.equals("Matt")) {
                item.click();
                break;
            }
        }
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("div.ui.fluid.selection.dropdown")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ui.fluid.selection.dropdown span")));
        for (WebElement item : allItems) {
            String nameItem = item.getText();
            if(nameItem.equals("Justen Kitsune")) {
                item.click();
                break;
            }
        }
        Thread.sleep(3000);
    }
    @Test
    public void TC_03_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        driver.findElement(By.cssSelector("li.dropdown-toggle")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.dropdown-menu a")));
        List<WebElement> allItems = driver.findElements(By.cssSelector("ul.dropdown-menu a"));
        for (WebElement item : allItems) {
            String nameItems = item.getText();
            if(nameItems.equals("First Option")) {
                item.click();
            }
        }
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("li.dropdown-toggle")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.dropdown-menu a")));
        for (WebElement item : allItems) {
            String nameItems = item.getText();
            if(nameItems.equals("Second Option")) {
                item.click();
            }
        }
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("li.dropdown-toggle")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.dropdown-menu a")));
        for (WebElement item : allItems) {
            String nameItems = item.getText();
            if(nameItems.equals("Third Option")) {
                item.click();
            }
        }
        Thread.sleep(3000);
    }

    @Test
    public void TC_04_Editable() throws InterruptedException {
        driver.get("https://indrimuska.github.io/jquery-editable-select/");
        driver.findElement(By.cssSelector("div#default-place")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[text()='Default']//parent::div[@class='row form-group']//li")));
        List<WebElement> allItems = driver.findElements(By.xpath("//div[text()='Default']//parent::div[@class='row form-group']//li"));
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Default']//parent::div[@class='row form-group']//li")).size(), 20);

        for(WebElement item : allItems) {
            String nameItem = item.getText();
            if(nameItem.equals("Ford")) {
                item.click();
            }
        }
        Thread.sleep(3000);

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
