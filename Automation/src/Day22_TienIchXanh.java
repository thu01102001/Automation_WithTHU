import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.Collator;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Day22_TienIchXanh {
    WebDriver driver; //khai báo bến
    Actions actions;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    String firstName = "nguyen", email = "thu3@gmail.com", phone = "0364697171", pass = "123456";

    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_Hover_Contact() throws InterruptedException {
        driver.get("https://tienichxanh.com.vn/");
        List<WebElement> allContact = driver.findElements(By.cssSelector("a.hai01"));
        hoverItem(allContact);
    }

    private void hoverItem(List<WebElement> allItem) throws InterruptedException {
        for (WebElement item : allItem) {
            actions.moveToElement(item).perform();
            Thread.sleep(3000);
            Assert.assertEquals(Color.fromString(item.getCssValue("color")).asHex().toLowerCase(), "#f6470e");
        }
    }
    @Test
    public void TC_02_Hover_Account() throws InterruptedException {
        driver.get("https://tienichxanh.com.vn/");
        actions.moveToElement(driver.findElement(By.cssSelector("li.login_content"))).perform();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.cssSelector("ul.ul_account")).isDisplayed());
        actions.moveToElement(driver.findElement(By.xpath("//ul[@class='ul_account']/li[1]"))).perform();
        Thread.sleep(2000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//ul[@class='ul_account']/li[1]")).getCssValue("background")).asHex().toLowerCase(), "#ffffff");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//ul[@class='ul_account']/li[1]/a")).getCssValue("color")).asHex().toLowerCase(), "#f6470e");
        Thread.sleep(2000);
        actions.moveToElement(driver.findElement(By.xpath("//ul[@class='ul_account']/li[2]"))).perform();
        Thread.sleep(2000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//ul[@class='ul_account']/li[2]")).getCssValue("background")).asHex().toLowerCase(), "#ffffff");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//ul[@class='ul_account']/li[2]/a")).getCssValue("color")).asHex().toLowerCase(), "#7b8395");
    }

    @Test
    public void TC_03_Register() throws InterruptedException {
        driver.get("https://tienichxanh.com.vn/");
        actions.moveToElement(driver.findElement(By.cssSelector("li.login_content"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//ul[@class='ul_account']/li[2]")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("p.title_page")).getText(), "Đăng ký tài khoản");
        driver.findElement(By.cssSelector("input#firstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(pass);
        driver.findElement(By.cssSelector("button[value='Đăng ký']")).click();
        Thread.sleep(3000);
        actions.moveToElement(driver.findElement(By.cssSelector("li.login_content"))).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='ul_account']/li[1]/a")).getText(), "Tài khoản");
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='ul_account']/li[2]/a")).getText(), "Đăng xuất");
        actions.moveToElement(driver.findElement(By.cssSelector("li.login_content"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//ul[@class='ul_account']/li[2]")).click();
        Thread.sleep(2000);
        actions.moveToElement(driver.findElement(By.cssSelector("li.login_content"))).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='ul_account']/li[1]/a")).getText(), "Đăng nhập");
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='ul_account']/li[2]/a")).getText(), "Đăng ký");
        //them case dki email , sdt da ton tai
        //sdt ko hop le
        // firstname ....
    }

    @Test
    public void TC_04_Login() throws InterruptedException {
        driver.get("https://tienichxanh.com.vn/");
        actions.moveToElement(driver.findElement(By.cssSelector("li.login_content"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//ul[@class='ul_account']/li[1]")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("p.title_page")).getText(), "Đăng nhập tài khoản");
        driver.findElement(By.cssSelector("input#customer_email")).sendKeys("a@gmail.com");
        driver.findElement(By.cssSelector("input#customer_password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[value='Đăng nhập']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text_warning")).getText(), "Thông tin đăng nhập không chính xác.");
        driver.findElement(By.cssSelector("input#customer_email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#customer_password")).sendKeys(pass);
        driver.findElement(By.cssSelector("button[value='Đăng nhập']")).click();
        Thread.sleep(2000);
        actions.moveToElement(driver.findElement(By.cssSelector("li.login_content"))).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='ul_account']/li[1]/a")).getText(), "Tài khoản");
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='ul_account']/li[2]/a")).getText(), "Đăng xuất");
        Assert.assertEquals(driver.findElement(By.cssSelector("p.title_page")).getText(), "Trang khách hàng");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.form-signup strong")).getText(), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.block-content.form-signup p")).getText(), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.input-group input")).getAttribute("value"), email);
        if(driver.findElement(By.cssSelector("span.count_item")).getText().equals("0")) {
            Assert.assertEquals(driver.findElement(By.cssSelector("tbody p")).getText(), "Không có đơn hàng nào.");
        }
        actions.moveToElement(driver.findElement(By.cssSelector("li.login_content"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//ul[@class='ul_account']/li[2]")).click();
        Thread.sleep(2000);
        actions.moveToElement(driver.findElement(By.cssSelector("li.login_content"))).perform();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='ul_account']/li[1]/a")).getText(), "Đăng nhập");
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='ul_account']/li[2]/a")).getText(), "Đăng ký");

    }

    @Test
    public void TC_05_Scroll() throws InterruptedException {
        driver.get("https://tienichxanh.com.vn/");
        jsExecutor.executeScript("window.scroll(0, document.body.scrollHeight);");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("i.fa-arrow-up")).click();
        Thread.sleep(3000);
        Assert.assertEquals(jsExecutor.executeScript("return window.pageYOffset;"), Long.valueOf(0));
    }

    @Test
    public void TC_06_Popup_Hover_Item() throws InterruptedException {
        driver.get("https://tienichxanh.com.vn/");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='product-info product-info-bases a-center']//a[text()='Máy làm mát đồ uống tiện lợi']")));
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='product-info product-info-bases a-center']//a[text()='Máy làm mát đồ uống tiện lợi']"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='owl-item active'][1]//a[@data-handle='may-lam-mat-do-uong-tien-loi']//ion-icon[@name='eye']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.quick-view-product")).isDisplayed());
        actions.moveToElement(driver.findElement(By.xpath("//form[@id='product-actions-26642572']//button[@type='submit']"))).perform();
        Thread.sleep(3000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//form[@id='product-actions-26642572']//button[@type='submit']")).getCssValue("background")).asHex().toLowerCase(), "#ffffff");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//form[@id='product-actions-26642572']//button[@type='submit']")).getCssValue("color")).asHex().toLowerCase(), "#f6470e");
        driver.findElement(By.xpath("//div[@class='quick-view-product']//i[@class='fas fa-times']")).click();
        Thread.sleep(3000);
        Assert.assertFalse(driver.findElement(By.cssSelector("div.quick-view-product")).isDisplayed());
    }

    @Test
    public void TC_08_AllItem() throws InterruptedException {
        driver.get("https://tienichxanh.com.vn/");
        actions.moveToElement(driver.findElement(By.xpath("//nav[@class='header-nav']//span[text()='Sản phẩm']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='item_small hidden-sm hidden-xs']")).isDisplayed());
        Thread.sleep(3000);
        //all category
        Assert.assertEquals(driver.findElements(By.xpath("//ul[@class='item_small hidden-sm hidden-xs']//a")).size(), 16);
        driver.findElement(By.xpath("//a[text()='Đồ dùng sinh hoạt ']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("p.title_page")).getText(), "Đồ dùng sinh hoạt");
        //all product
        List<WebElement> allPage = driver.findElements(By.xpath("//a[@class='page-link']"));
        WebElement pageFinal = allPage.get(allPage.size()-2);
        int page = Integer.parseInt(pageFinal.getText());
        List<WebElement> allProduct = new ArrayList<>();
        System.out.println("====");
        System.out.println(page);
        for(int i = 1; i <= page; i++) {
            List<WebElement> listProduct = driver.findElements(By.cssSelector("div.product-base"));
            allProduct.addAll(listProduct);
            if(i < page) {
                driver.findElement(By.xpath("//a[@class='page-link'][text()='"+ (i + 1) +"']")).click();
                Thread.sleep(2000);
            }
        }
        System.out.println(allProduct.size());
    }
    @Test
    public void TC_09_Sapxep() throws InterruptedException {
        driver.get("https://tienichxanh.com.vn/");
        actions.moveToElement(driver.findElement(By.xpath("//nav[@class='header-nav']//span[text()='Sản phẩm']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='item_small hidden-sm hidden-xs']")).isDisplayed());
        Thread.sleep(3000);
        //all category
        Assert.assertEquals(driver.findElements(By.xpath("//ul[@class='item_small hidden-sm hidden-xs']//a")).size(), 16);
        driver.findElement(By.xpath("//a[text()='Đồ dùng sinh hoạt ']")).click();
        Thread.sleep(2000);
        //lay name product truoc khi sx
        List<WebElement> allPage = driver.findElements(By.xpath("//a[@class='page-link']"));
        WebElement pageFinal = allPage.get(allPage.size() - 2);
        int page = Integer.parseInt(pageFinal.getText().trim());
        System.out.println("Total pages: " + page);

        // Collect product names before sorting
        List<String> nameNature = new ArrayList<>();
        for (int i = 1; i <= page; i++) {
            List<WebElement> allProductNameBefore = driver.findElements(By.cssSelector("h3.product-name a"));
            for (WebElement nameBefore : allProductNameBefore) {
                nameNature.add(nameBefore.getText().trim());
            }
            if (i < page) {
                driver.findElement(By.xpath("//a[@class='page-link'][text()='" + (i + 1) + "']")).click();
                Thread.sleep(2000);
            }
        }

        // Clean up product names (remove unnecessary characters)
        List<String> cleanedNames = new ArrayList<>();
        for (String name : nameNature) {
            String cleanedName = name.replaceAll("[,]", "").trim();
            cleanedNames.add(cleanedName);
        }

        // Sort the list alphabetically
        Collator collator = Collator.getInstance(new Locale("vi", "VN"));
        collator.setStrength(Collator.PRIMARY);
        Collections.sort(cleanedNames, collator);

        // Output sorted product names
        System.out.println("--------- Sorted Product Names A-Z ---------");
        for (String sortedName : cleanedNames) {
            System.out.println(sortedName);
        }

        // Navigate to sort products A-Z
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div#sort-by li span")));
        actions.moveToElement(driver.findElement(By.cssSelector("div#sort-by li span"))).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//ul[@class='ul_2']/li[2]")).click();
        Thread.sleep(3000);

        // Collect product names after sorting A-Z
        List<String> nameAZ = new ArrayList<>();
        for (int i = 1; i <= page; i++) {
            List<WebElement> allProductNameAfter = driver.findElements(By.cssSelector("h3.product-name a"));
            for (WebElement nameAfter : allProductNameAfter) {
                nameAZ.add(nameAfter.getText().trim());
            }
            if (i < page) {
                driver.findElement(By.xpath("//a[@class='page-link'][text()='" + (i + 1) + "']")).click();
                Thread.sleep(2000);
            }
        }
        List<String> cleanedNamesAfter = new ArrayList<>();
        for (String name : nameAZ) {
            String cleanedNameAfter = name.replaceAll("[,]", "").trim();
            cleanedNamesAfter.add(cleanedNameAfter);
        }

        // Assert that the number of products and the product list itself are the same before and after sorting
        Assert.assertEquals(nameAZ.size(), nameNature.size());
        Assert.assertEquals(cleanedNamesAfter, cleanedNames);
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
