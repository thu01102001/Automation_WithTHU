import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Day24_UploadFile {
    WebDriver driver; //khai báo bến
    String folderUpload = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;
    String anh = "hinh-thien-nhien-3d-002.jpg";
    String anhPath = folderUpload + anh;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
//
    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://material.playwrightvn.com/01-xpath-register-page.html");
        By uploadFile = By.xpath("//input[@type='file']");
        driver.findElement(uploadFile).sendKeys(anhPath);
        Thread.sleep(2000);

    }
    @Test
    public void TC_02_() {

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
