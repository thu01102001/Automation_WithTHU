import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Ex_Playwright {
    WebDriver driver; //khai báo bến
    WebDriverWait explicitWait;
    Actions actions;
    JavascriptExecutor javascriptExecutor;
    @BeforeClass
    public void InitialBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        javascriptExecutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }
    @Test(description = "Thuc hanh DOM bien mat nhanh")
    public void TC_01_() {
        driver.get("https://material.playwrightvn.com/15-auto-hide-notification.html");

        // khi ko nhap gi >> Nhan Add luon
        driver.findElement(By.cssSelector("button.btn-primary")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.notification")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.notification")).getText(), "Vui lòng nhập công việc.");

        //nhap >> Add
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notification")));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#todoInput"))).sendKeys("Study Automation FC");
        //driver.findElement(By.cssSelector("input#todoInput")).sendKeys("Study Automation FC");
        driver.findElement(By.cssSelector("button.btn-primary")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@id='todoList']/li[2]")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@id='todoList']/li[2]")).getText(), "Study Automation FC");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.notification")).getText(), "Thêm công việc thành công!");

        //nhap cv da ton tai >> Add
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.notification")));
        driver.findElement(By.cssSelector("input#todoInput")).sendKeys("Study Automation FC");
        driver.findElement(By.cssSelector("button.btn-primary")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.notification")).getText(), "Công việc đã tồn tại.");

    }
    @Test
    public void TC_02_Permission() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.geolocation", 2); // 1 = cho phép
        options.setExperimentalOption("prefs", prefs);

        driver.get("https://material.playwrightvn.com/017-detect-user-agent.html");

        Assert.assertEquals(driver.findElement(By.cssSelector("td#locationPermission")).getText(), "Đã cấp quyền");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String currentTime = LocalDateTime.now().format(formatter);
        Assert.assertEquals(driver.findElement(By.cssSelector("span#localTime")).getText().toLowerCase(), currentTime);

        // Sử dụng JavascriptExecutor để lấy User Agent
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String userAgent = (String) js.executeScript("return navigator.userAgent;");

        // In ra User Agent
        System.out.println("User Agent của trình duyệt là: " + userAgent);
        Assert.assertEquals(driver.findElement(By.cssSelector("td#userAgent")).getText(), userAgent);

        String platForm = (String) js.executeScript("return navigator.platform;");
        System.out.println("PlatForm của trình duyệt là: " + platForm);

        String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
        System.out.println("Browser của trình duyệt là: " + browser);

        String version = ((RemoteWebDriver) driver).getCapabilities().getBrowserVersion();
        System.out.println("Version của trình duyệt là: " + version);

        org.openqa.selenium.Dimension dimension = driver.manage().window().getSize();
        int width = dimension.width;
        int height = dimension.height;
        System.out.println("Screen Size của trình duyệt là: " + width+"x"+height);

        String locale = (String) js.executeScript("return navigator.language || navigator.userLanguage;");
        System.out.println("Locale của trình duyệt là: " + locale);

        //timeZone
        TimeZone timeZone = TimeZone.getDefault();
        String timeZoneID = timeZone.getID();
        String timeZoneName = timeZone.getDisplayName();
        System.out.println("mui gio: " +timeZoneID);
        System.out.println("ten hien thi: " +timeZoneName);
    }

    @Test
    public void TC_05_Mouse_Event() throws InterruptedException {
        driver.get("https://material.playwrightvn.com/018-mouse.html");
        actions.click(driver.findElement(By.cssSelector("div#clickArea")))
                .click(driver.findElement(By.cssSelector("div#clickArea")))
                .click(driver.findElement(By.cssSelector("div#clickArea")))
                .click(driver.findElement(By.cssSelector("div#clickArea")))
                .click(driver.findElement(By.cssSelector("div#clickArea"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#clickCount")).getText(), "Số lần nhấn: 5");
        Assert.assertEquals(driver.findElement(By.cssSelector("p#clickType")).getText(), "Loại nhấn: Đơn");

        Thread.sleep(2000);
        //dup
        actions.doubleClick(driver.findElement(By.cssSelector("div#clickArea"))).perform();
        actions.doubleClick(driver.findElement(By.cssSelector("div#clickArea"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#clickCount")).getText(), "Số lần nhấn: 9");
        Assert.assertEquals(driver.findElement(By.cssSelector("p#clickType")).getText(), "Loại nhấn: Đúp");

        Thread.sleep(2000);
        //phim
        actions.keyDown(Keys.CONTROL).click(driver.findElement(By.cssSelector("div#clickArea"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#clickCount")).getText(), "Số lần nhấn: 10");
        Assert.assertEquals(driver.findElement(By.cssSelector("p#clickType")).getText(), "Loại nhấn: Đơn");
        Assert.assertEquals(driver.findElement(By.cssSelector("p#modifierKeys")).getText(), "Phím kèm theo: Ctrl");

        Thread.sleep(2000);
        actions.keyUp(Keys.CONTROL).keyDown(Keys.META).doubleClick(driver.findElement(By.cssSelector("div#clickArea"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#clickCount")).getText(), "Số lần nhấn: 12");
        Assert.assertEquals(driver.findElement(By.cssSelector("p#clickType")).getText(), "Loại nhấn: Đúp");
        Assert.assertEquals(driver.findElement(By.cssSelector("p#modifierKeys")).getText(), "Phím kèm theo: Meta");

    }

    @Test
    public void TC_06_Enable_Random_Element() {
        driver.get("https://material.playwrightvn.com/019-enable-form.html");
        Assert.assertNotNull(driver.findElement(By.cssSelector("input#inputField")).getAttribute("disabled"));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#inputField"))).sendKeys("Le thu");
        Assert.assertNull(driver.findElement(By.cssSelector("input#inputField")).getAttribute("disabled"));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#selectField")));
        new Select(driver.findElement(By.cssSelector("select#selectField"))).selectByVisibleText("Tùy chọn 2");
        Assert.assertNull(driver.findElement(By.cssSelector("select#selectField")).getAttribute("disabled"));

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#buttonDelay"), "Đã kích hoạt"));
        Assert.assertNull(driver.findElement(By.cssSelector("button#submitButton")).getAttribute("disabled"));
    }

    @Test
    public void TC_07_Alert_Confirm_Prompt() throws InterruptedException {
        driver.get("https://material.playwrightvn.com/020-alert-confirm-prompt.html");
        driver.findElement(By.cssSelector("button#alertButton")).click();
        Alert alertAccept = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alertAccept.getText(), "Học automation test từ chưa biết gì.");
        alertAccept.accept();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button#confirmButton")).click();
        Alert alertOK = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alertOK.getText(), "Học automation test từ chưa biết gì. Bạn có đồng ý không?");
        alertAccept.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#resultDisplay")).getText(), "Confirm result: Đồng ý");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button#confirmButton")).click();
        Alert alertCancel = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alertCancel.getText(), "Học automation test từ chưa biết gì. Bạn có đồng ý không?");
        alertCancel.dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#resultDisplay")).getText(), "Confirm result: Không đồng ý");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button#promptButton")).click();
        Alert alertPromptOK = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alertPromptOK.getText(), "Học automation test từ chưa biết gì. Bạn nghĩ gì?");
        alertPromptOK.sendKeys("Le Thu");
        alertPromptOK.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#resultDisplay")).getText(), "Prompt result: Le Thu");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button#promptButton")).click();
        Alert alertPromptCancel = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alertPromptCancel.getText(), "Học automation test từ chưa biết gì. Bạn nghĩ gì?");
        alertPromptCancel.sendKeys("Le Thu");
        alertPromptCancel.dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#resultDisplay")).getText(), "Prompt result: Không có phản hồi");

    }

    @Test
    public void TC_08_Tab_Random() {
        driver.get("https://material.playwrightvn.com/021-page-random-open-new-page.html");
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToTabNew();
        Assert.assertTrue(driver.findElement(By.cssSelector("button#registerBtn")).isDisplayed());
    }
    public void switchToTabNew() {
        String title = driver.getTitle();
        Set<String> tabs= driver.getWindowHandles();
        for(String tab : tabs) {
            driver.switchTo().window(tab);
            if(!driver.getTitle().equals(title)) {
                break;
            }
        }
    }

    @Test
    public void TC_09_Popup_Random() {
        driver.get("https://material.playwrightvn.com/025-page-with-random-dialog.html");
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert);
        Assert.assertEquals(alert.getText(), "Xin chào các bạn, chúng mình là Playwright VN");
        alert.accept();
    }

    @Test
    public void TC_10_Import_Export() {
        driver.get("https://material.playwrightvn.com/021-import-export.html");

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
