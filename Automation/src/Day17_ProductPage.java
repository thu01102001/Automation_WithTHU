import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Day17_ProductPage {
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://material.playwrightvn.com/02-xpath-product-page.html");
        List<WebElement> listBtn = driver.findElements(By.cssSelector("button.add-to-cart"));
        for (WebElement btn : listBtn) {
            btn.click();
        }
        WebElement price1 = driver.findElement(By.xpath("//tbody/tr[1]/td[4]"));
        String price11 = price1.getText().replaceAll("[^0-9.]", ""); // Giữ lại cả số và dấu chấm thập phân
        double totalPrice1 = Double.parseDouble(price11);

        WebElement price2 = driver.findElement(By.xpath("//tbody/tr[2]/td[4]"));
        String price22 = price2.getText().replaceAll("[^0-9.]", ""); // Giữ lại cả số và dấu chấm thập phân
        double totalPrice2 = Double.parseDouble(price22);

        WebElement price = driver.findElement(By.xpath("//tbody/tr[3]/td[4]"));
        String price3 = price.getText().replaceAll("[^0-9.]", ""); // Giữ lại cả số và dấu chấm thập phân
        double totalPrice3 = Double.parseDouble(price3);

        driver.findElement(By.xpath("//button[@data-product-id='3']")).click();
        Thread.sleep(3000);
        List<WebElement> listQuantity = driver.findElements(By.xpath("//tbody/tr/td[3]"));
        for (WebElement quantity : listQuantity) {
            quantity.getText();
        }
        Assert.assertEquals(driver.findElement(By.xpath("//tbody/tr[3]/td[3]")).getText(), "2");
        WebElement priceUpdate = driver.findElement(By.xpath("//tbody/tr[3]/td[4]"));
        String update = priceUpdate.getText().replaceAll("[^0-9.]", ""); // Giữ lại cả số và dấu chấm thập phân
        Double updatePrice3 = Double.parseDouble(update);
        Assert.assertEquals(updatePrice3, totalPrice3*2);
        System.out.println(updatePrice3);

        WebElement ttPrice = driver.findElement(By.cssSelector("td.total-price"));
        String tongPrice = ttPrice.getText().replaceAll("[^0-9.]", ""); // Giữ lại cả số và dấu chấm thập phân
        double tPrice = Double.parseDouble(tongPrice);
        System.out.println(tPrice);
        Assert.assertEquals(tPrice, totalPrice1+totalPrice2+updatePrice3);

        driver.findElement(By.xpath("//tr[2]/td[5]/button")).click();
        WebElement ttPriceUpdate = driver.findElement(By.cssSelector("td.total-price"));
        String tongPriceUpdate = ttPriceUpdate.getText().replaceAll("[^0-9.]", ""); // Giữ lại cả số và dấu chấm thập phân
        double tPriceUpdate = Double.parseDouble(tongPriceUpdate);
        Assert.assertEquals(tPriceUpdate, tPrice-totalPrice2);
        System.out.println("---------------");
        System.out.println(tPriceUpdate);
    }

    @Test
    public void TC_02_() {

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
