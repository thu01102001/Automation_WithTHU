import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day11 {
    WebDriver driver; //khai báo bến
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_DemoSite() {
        driver.get("https://demo.automationtesting.in/Register.html");
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("nguyen");
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("nguyen");
        driver.findElement(By.xpath("//label[text()='Address']/following-sibling::div/textarea")).sendKeys("nguyenthilethu");
        driver.findElement(By.xpath("//label[text()='Email address*']/following-sibling::div/input")).sendKeys("thu@gmail.com");
        driver.findElement(By.xpath("//label[text()='Phone*']/following-sibling::div/input")).sendKeys("0364697180");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='Male']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='Cricket']")));
//        driver.findElement(By.xpath("//div[@id='msdd']")).click();
//        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='msdd']/following-sibling::div/ul/li/a")));
//        List<WebElement> allLanguage = driver.findElements(By.xpath("//div[@id='msdd']/following-sibling::div/ul/li/a"));
//        for (WebElement language : allLanguage) {
//            if(language.getText().equals("English") && language.getText().equals("French")) {
//                language.click();
//                break;
//            }
//        }

//        new Select(driver.findElement(By.xpath("//select[@id='Skills']"))).selectByVisibleText("C++");
//        Assert.assertFalse(new Select(driver.findElement(By.xpath("//select[@id='Skills']"))).isMultiple());
//        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@id='Skills']"))).getOptions().size(), 78);

//        driver.findElement(By.xpath("//span[@class='selection']")).click();
//        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='select2-results']/ul/li")));
//        List<WebElement> allCountry = driver.findElements(By.xpath("//span[@class='select2-results']/ul/li"));
//        for(WebElement country : allCountry) {
//            if(country.getText().equals("India")) {
//                country.click();
//                break;
//            }
//        }

//        new Select(driver.findElement(By.xpath("//select[@id='yearbox']"))).selectByVisibleText("2001");
//        Assert.assertFalse(new Select(driver.findElement(By.xpath("//select[@id='yearbox']"))).isMultiple());
//        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@id='yearbox']"))).getFirstSelectedOption(), "2001");
//        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@id='yearbox']/option"))).getOptions().size(), 101);
//
//        new Select(driver.findElement(By.xpath("//select[@placeholder='Month']"))).selectByVisibleText("10");
//        Assert.assertFalse(new Select(driver.findElement(By.xpath("//select[@placeholder='Month']"))).isMultiple());
//        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@placeholder='Month']"))).getFirstSelectedOption(), "10");
//        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@placeholder='Month']"))).getOptions().size(), 13);
//
//        new Select(driver.findElement(By.xpath("//select[@id='daybox']"))).selectByVisibleText("01");
//        Assert.assertFalse(new Select(driver.findElement(By.xpath("//select[@id='daybox']"))).isMultiple());
//        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@id='daybox']"))).getFirstSelectedOption(), "01");
//        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@id='daybox']"))).getOptions().size(), 32);

        driver.findElement(By.xpath("//input[@id='firstpassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='secondpassword']")).sendKeys("123456");

        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[@id='submitbtn']")).getCssValue("background-color").toLowerCase()).asHex(), "#337ab7");
    }
    @Test
    public void TC_02_ContactUs() throws InterruptedException {
        driver.get("http://webdriveruniversity.com/Contact-Us/contactus.html");

        System.out.println(driver.findElement(By.xpath("//input[@name='first_name']")).getAttribute("placeholder"));
        System.out.println(driver.findElement(By.xpath("//input[@name='last_name']")).getAttribute("placeholder"));
        System.out.println(driver.findElement(By.xpath("//input[@name='email']")).getAttribute("placeholder"));
        System.out.println(driver.findElement(By.xpath("//textarea[@name='message']")).getAttribute("placeholder"));

        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("nguyen");
        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("thu");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("thu@gmail.com");
        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("nguyen thi le thu");

        driver.findElement(By.xpath("//input[@value='SUBMIT']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://webdriveruniversity.com/Contact-Us/contact-form-thank-you.html");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#contact_reply h1")).getText(), "Thank You for your Message!");

    }

    @Test
    public void TC_03_Login() {
        driver.get("http://webdriveruniversity.com/Login-Portal/index.html");
        System.out.println(driver.findElement(By.cssSelector("input#text")).getAttribute("placeholder"));
        System.out.println(driver.findElement(By.cssSelector("input#pass")).getAttribute("placeholder"));
        driver.findElement(By.cssSelector("input#text")).sendKeys("thu");
        driver.findElement(By.cssSelector("input#password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#login-button")).click();
    }

    @Test
    public void TC_04_Item() throws InterruptedException {
        driver.get("http://webdriveruniversity.com/Accordion/index.html");
        driver.findElement(By.cssSelector("button#manual-testing-accordion")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("button#manual-testing-accordion")).getAttribute("class") , "accordion active" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div#manual-testing-description")).getCssValue("max-height"), "70px");

        driver.findElement(By.cssSelector("button#cucumber-accordion")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("button#cucumber-accordion")).getAttribute("class") , "accordion active" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div#cucumber-testing-description")).getCssValue("max-height"), "50px");

        driver.findElement(By.cssSelector("button#automation-accordion")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("button#cucumber-accordion")).getAttribute("class") , "accordion active" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div#automation-testing-description")).getCssValue("max-height"), "90px");
// Đợi cho đến khi button chuyển sang "Loading complete" thì mới click
        explicitWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("hidden-text")), "LOADING COMPLETE."));

        driver.findElement(By.cssSelector("button#click-accordion")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("button#click-accordion")).getAttribute("class") , "accordion active" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div#timeout")).getCssValue("max-height"), "20px");
    }

    @Test
    public void TC_04_Checkbox_Radio_Dropdown() {
        driver.get("https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-1"))).selectByVisibleText("Python");
        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-1"))).isMultiple());
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-1"))).getFirstSelectedOption().getText(), "Python");
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-1"))).getOptions().size(), 4);

        new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-2"))).selectByVisibleText("TestNG");
        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-2"))).isMultiple());
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-2"))).getFirstSelectedOption().getText(), "TestNG");
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-2"))).getOptions().size(), 4);

        new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-3"))).selectByVisibleText("CSS");
        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-3"))).isMultiple());
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-3"))).getFirstSelectedOption().getText(), "CSS");
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-3"))).getOptions().size(), 4);

        List<WebElement> AllCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for(WebElement checkbox : AllCheckbox) {
            if(!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        List<WebElement> AllRadio = driver.findElements(By.xpath("//h2[text()='Radio Button(s)']//following-sibling::div/form/input"));
        for(WebElement radio : AllRadio) {
            if(!radio.isSelected()) {
                radio.click();
            }
        }

        Assert.assertFalse(driver.findElement(By.xpath("//input[@value='cabbage']")).isEnabled());
        driver.findElement(By.xpath("//input[@value='lettuce']")).click();

//        new Select(driver.findElement(By.cssSelector("select#fruit-selects"))).selectByVisibleText("Pear");
//        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#fruit-selects"))).isMultiple());
//        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#fruit-selects"))).getFirstSelectedOption().getText(), "Pear");
//        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#fruit-selects"))).getOptions().size(), 4);

        List<WebElement> allCheckbox = driver.findElements(By.cssSelector("select#fruit-selects option"));
        for(WebElement checkboxx : allCheckbox) {
            checkboxx.click();
        }
    }

    @Test
    public void TC_05_ToDoList() {
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");
        driver.findElement(By.cssSelector("i#plus-icon")).click();
        System.out.println(driver.findElement(By.xpath("//input[@placeholder='Add new todo']")).getCssValue("display"));
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Add new todo']")).getCssValue("display"), "none");

    }
    @Test
    public void TC_05() throws InterruptedException {
        driver.get("http://webdriveruniversity.com/Contact-Us/contactus.html");
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("thuuuu");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@name='first_name']")).clear();


    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
