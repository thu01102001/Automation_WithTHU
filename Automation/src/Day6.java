import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day6 {
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_Checkbox() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        WebElement checkbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input"));
        if(!checkbox.isSelected()) {
            checkbox.click();
            Thread.sleep(3000);
            Assert.assertTrue(checkbox.isSelected());
        }
        checkbox.click();
        Thread.sleep(3000);
        Assert.assertFalse(checkbox.isSelected());
     }
    @Test
    public void TC_02_Radio() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        WebElement radio = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input"));
        if(!radio.isSelected()) {
            radio.click();
            Thread.sleep(3000);
            Assert.assertTrue(radio.isSelected());
        }
    }
    @Test
    public void TC_03_CheckboxRadio() throws InterruptedException {
        driver.get("https://material.angular.io/components/radio/examples");
        WebElement summer = driver.findElement(By.xpath("//label[text()='Summer']//preceding-sibling::div/input"));
        if(!summer.isSelected()) {
            summer.click();
            Thread.sleep(3000);
            Assert.assertTrue(summer.isSelected());
        }
        driver.get("https://material.angular.io/components/checkbox/examples");
        WebElement checked = driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input"));
        WebElement indeterminate = driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input"));
        if(!checked.isSelected() && !indeterminate.isSelected()) {
            checked.click();
            indeterminate.click();
            Thread.sleep(3000);
            Assert.assertTrue(checked.isSelected() && indeterminate.isSelected());
        }
        else {
            checked.click();
            indeterminate.click();
            Thread.sleep(3000);
            Assert.assertFalse(checked.isSelected() && indeterminate.isSelected());
        }
    }

    @Test
    public void TC_04_selectAll() throws InterruptedException {
        driver.get("https://automationfc.github.io/multiple-fields/");
        Select patientGender = new Select(driver.findElement(By.xpath("//label[contains(text(), 'Patient Gender')]/following-sibling::div/select")));
        patientGender.selectByVisibleText("Male");
        Assert.assertFalse(patientGender.isMultiple());
        Assert.assertEquals(patientGender.getFirstSelectedOption().getText(), "Male");
        Assert.assertEquals(patientGender.getOptions().size(), 3);

        driver.findElement(By.xpath("//label[text()=' First Name ']/preceding-sibling::input")).sendKeys("Thu");
        driver.findElement(By.xpath("//label[text()=' Last Name ']/preceding-sibling::input")).sendKeys("Nguyễn");

        Select month = new Select(driver.findElement(By.xpath("//label[text()=' Month ']/preceding-sibling::select")));
        month.selectByVisibleText("June");
        Assert.assertFalse(month.isMultiple());
        Assert.assertEquals(month.getFirstSelectedOption().getText(), "June");
        Assert.assertEquals(month.getOptions().size(), 13);

        Select day = new Select(driver.findElement(By.xpath("//label[text()=' Day ']/preceding-sibling::select")));
        day.selectByVisibleText("3");
        Assert.assertFalse(day.isMultiple());
        Assert.assertEquals(day.getFirstSelectedOption().getText(), "3");
        Assert.assertEquals(day.getOptions().size(), 32);

        Select year = new Select(driver.findElement(By.xpath("//label[text()=' Year ']/preceding-sibling::select")));
        year.selectByVisibleText("2015");
        Assert.assertFalse(year.isMultiple());
        Assert.assertEquals(year.getFirstSelectedOption().getText(), "2015");
        Assert.assertEquals(year.getOptions().size(),100);

        driver.findElement(By.xpath("//label[contains(text(), 'Patient Height')]/following-sibling::div/input")).sendKeys("160");
        driver.findElement(By.xpath("//label[contains(text(), 'Patient Weight')]/following-sibling::div/input")).sendKeys("48");
        driver.findElement(By.xpath("//label[contains(text(), 'Patient E-Mail')]/following-sibling::div/input")).sendKeys("thu@gmail.com");
        driver.findElement(By.xpath("//label[contains(text(), 'Reason for seeing the doctor:')]/following-sibling::div/input")).sendKeys("Nguyễn Thị Lệ Thu");
        driver.findElement(By.xpath("//label[contains(text(), 'Please list any drug allergies')]/following-sibling::div/textarea")).sendKeys("Thu\n" +
                "Nguyen");

        Thread.sleep(3000);
        //check all
        List<WebElement> allCheckboxs = driver.findElements(By.cssSelector("div.form-input-wide input[type='checkbox']"));
        for(WebElement checkbox : allCheckboxs) {
            if(!checkbox.isSelected()) {
                checkbox.click();
                Thread.sleep(1);
            }
        }
        //verify hết all
        for (WebElement checkbox: allCheckboxs) {
            Assert.assertTrue(checkbox.isSelected());
        }
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        allCheckboxs = driver.findElements(By.cssSelector("div.form-input-wide input[type='checkbox']"));

        //chọn 1 ô checkbox trong all
        for (WebElement checkbox : allCheckboxs) {
            if(checkbox.getAttribute("value").equals("Gout") && !checkbox.isSelected()) {
                checkbox.click();
                Thread.sleep(3000);
            }
        }

        //verify hét all
        for (WebElement checkbox: allCheckboxs) {
            if(checkbox.getAttribute("value").equals("Gout")) {
                Assert.assertTrue(checkbox.isSelected());
            }
            else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }

        driver.findElement(By.xpath("//label[text()=' Other illnesses: ']//following-sibling::div/input")).sendKeys("Thu");
        driver.findElement(By.xpath("//label[text()=' Please list any Operations and Dates of Each ']/following-sibling::div/textarea")).sendKeys("thu");
        driver.findElement(By.xpath("//label[text()=' Please list your Current Medications ']/following-sibling::div/textarea")).sendKeys("nguyen");

        if(!driver.findElement(By.xpath("//label[text()=' 1-2 days ']")).isSelected()) {
            driver.findElement(By.xpath("//label[text()=' 1-2 days ']")).click();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(By.xpath("//label[text()=' 1-2 days ']/preceding-sibling::input")).isSelected());
        }

        if(!driver.findElement(By.xpath("//label[text()=' I have a strict diet ']")).isSelected()) {
            driver.findElement(By.xpath("//label[text()=' I have a strict diet ']")).click();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(By.xpath("//label[text()=' I have a strict diet ']/preceding-sibling::input")).isSelected());
        }

        if(!driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]")).isSelected()) {
            driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]")).click();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")]/preceding-sibling::input")).isSelected());
        }

        if(!driver.findElement(By.xpath("//label[text()=\" I don't use caffeine \"]")).isSelected()) {
            driver.findElement(By.xpath("//label[text()=\" I don't use caffeine \"]")).click();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(By.xpath("//label[text()=\" I don't use caffeine \"]/preceding-sibling::input")).isSelected());
        }

        if(!driver.findElement(By.xpath("//label[text()=' 2+ packs/day ']")).isSelected()) {
            driver.findElement(By.xpath("//label[text()=' 2+ packs/day ']")).click();
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(By.xpath("//label[text()=' 2+ packs/day ']/preceding-sibling::input")).isSelected());
        }

        driver.findElement(By.xpath("//label[text()=' Include other comments regarding your Medical History ']/following-sibling::div/textarea")).sendKeys("HIHIHIHIHI");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
