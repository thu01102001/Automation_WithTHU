import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day18_nopCommerce {
    WebDriver driver; //khai báo bến
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    Actions actions;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_Register() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("a.ico-register"))).perform();
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("a.ico-register")).getCssValue("color")).asHex().toLowerCase(), "#4ab2f1");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        //nhap thong tin
        driver.findElement(By.cssSelector("input#gender-female")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("nguyen");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("thu");
        new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).selectByVisibleText("1");
        new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).selectByVisibleText("October");
        new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).selectByVisibleText("2001");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("thu3@gmail.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
        driver.findElement(By.cssSelector("a.ico-logout")).click();
        Thread.sleep(3000);
    }
    @Test
    public void TC_02_Product() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-login")).click();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("thu3@gmail.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button.login-button")).click();
        Thread.sleep(3000);
        actions.moveToElement(driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']"))).perform();
        Thread.sleep(3000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']")).getCssValue("color")).asHex().toLowerCase(), "#4ab2f1");
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']/following-sibling::ul")).getCssValue("display"), "block");
        actions.moveToElement(driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']/following-sibling::ul/li[1]/a"))).perform();
        Thread.sleep(3000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']/following-sibling::ul/li[1]/a")).getCssValue("background-color")).asHex().toLowerCase(), "#f6f6f6");
        driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']/following-sibling::ul/li[1]/a")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title h1")).getText(), "Desktops");
        driver.findElement(By.xpath("//div[@class='details']//a[text()='Build your own computer']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='breadcrumb']//li[4]/strong")).getText(), "Build your own computer");
        new Select(driver.findElement(By.cssSelector("select#product_attribute_1"))).selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");
        new Select(driver.findElement(By.cssSelector("select#product_attribute_2"))).selectByVisibleText("4GB [+$20.00]");
        driver.findElement(By.cssSelector("input#product_attribute_3_7")).click();
        driver.findElement(By.cssSelector("input#product_attribute_4_9")).click();
        driver.findElement(By.cssSelector("input#product_attribute_5_12")).click();
        driver.findElement(By.cssSelector("button#add-to-cart-button-1")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='bar-notification success']")).getCssValue("display"), "block");
        driver.findElement(By.cssSelector("span.close")).click();
        driver.findElement(By.cssSelector("button#add-to-cart-button-1")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("span.cart-qty")).getText(), "(2)");
        driver.findElement(By.cssSelector("span.close")).click();
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("li#topcartlink")));
        actions.moveToElement(driver.findElement(By.cssSelector("li#topcartlink"))).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#flyout-cart")).getCssValue("display"), "block");
        driver.findElement(By.xpath("//button[text()='Go to cart']")).click();
        Thread.sleep(3000);
    }

    @Test
    public void TC_03_Search_Product() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/computers");
        String[] keywords = {"h", "ho", "hhh", "inch"};
        for (String keyword : keywords) {
            driver.findElement(By.cssSelector("input#small-searchterms")).clear();
            driver.findElement(By.cssSelector("input#small-searchterms")).sendKeys(keyword);
            actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
            Thread.sleep(3000);
            Assert.assertEquals(driver.findElement(By.cssSelector("input.search-text")).getAttribute("value"), keyword);
            List<WebElement> allResult = driver.findElements(By.cssSelector("div.item-box"));
            List<WebElement> allNameProduct = driver.findElements(By.cssSelector("h2.product-title a"));
            if(keyword.length() < 3) {
                Assert.assertEquals(driver.findElement(By.cssSelector("div.warning")).getText(), "Search term minimum length is 3 characters");
            }

            else {
                if(allResult.isEmpty()) {
                    System.out.println("ko");
                    Assert.assertEquals(driver.findElement(By.cssSelector("div.no-result")).getText(), "No products were found that matched your criteria.");
                } else {
                    System.out.println(allResult.size());
                    for (WebElement nameProduct : allNameProduct) {
                        System.out.println(nameProduct.getText());
                        Assert.assertTrue(nameProduct.getText().toLowerCase().contains(keyword));
                    }
                }
            }
        }

        List<WebElement> allBtnAddCart = driver.findElements(By.xpath("//button[text()='Add to cart']"));
        List<WebElement> btns = allBtnAddCart.subList(1, allBtnAddCart.size());
        for (WebElement btnAddCart : btns) {
            btnAddCart.click();
            driver.findElement(By.cssSelector("span.close")).click();
            Thread.sleep(3000);
        }

        //Add them sp2 4 lan (la sp2 co 5)
        driver.findElement(By.xpath("//div[@class='item-box'][3]//button[text()='Add to cart']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='item-box'][3]//button[text()='Add to cart']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='item-box'][3]//button[text()='Add to cart']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='item-box'][3]//button[text()='Add to cart']")).click();
        Thread.sleep(3000);

        //scroll len button cart
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("a.ico-cart")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.ico-cart")));
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/cart");

        //gia sp 1
        Double price1 = Double.parseDouble(driver.findElement(By.xpath("//tr[1]//span[@class='product-unit-price']")).getText().replaceAll("[^0-9.]", ""));
        //gia sp 2
        Double price2 = Double.parseDouble(driver.findElement(By.xpath("//tr[2]//span[@class='product-unit-price']")).getText().replaceAll("[^0-9.]", ""));
        System.out.println("Gia sp1: " +price1);
        System.out.println("Gia sp2: " +price2);

        //up so luong sp1 len 3
        driver.findElement(By.xpath("//tr[1]//div[@class='quantity up']")).click();
        driver.findElement(By.xpath("//tr[1]//div[@class='quantity up']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//tr[1]//div[@class='product-quantity']/input")).getAttribute("value"), "3");
        Double totalPrice1 = Double.parseDouble(driver.findElement(By.xpath("//tr[1]//td[6]/span")).getText().replaceAll("[^0-9.]", ""));
        Assert.assertEquals(totalPrice1, price1*3);
        System.out.println("Tong gia sp 1: " +totalPrice1);

        //tong sp2
        Assert.assertEquals(driver.findElement(By.xpath("//tr[2]//div[@class='product-quantity']/input")).getAttribute("value"), "5");
        Double totalPrice2 = Double.parseDouble(driver.findElement(By.xpath("//tr[2]//td[6]/span")).getText().replaceAll("[^0-9.]", ""));
        Assert.assertEquals(totalPrice2, price2*5);
        System.out.println("Tong gia sp 2: " +totalPrice2);
        //down so luong sp2 xuong con 2
        driver.findElement(By.xpath("//tr[2]//div[@class='quantity down']")).click();
        driver.findElement(By.xpath("//tr[2]//div[@class='quantity down']")).click();
        driver.findElement(By.xpath("//tr[2]//div[@class='quantity down']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//tr[2]//div[@class='product-quantity']/input")).getAttribute("value"), "2");
        Double totalPrice2Update = Double.parseDouble(driver.findElement(By.xpath("//tr[2]//td[6]/span")).getText().replaceAll("[^0-9.]", ""));
        Assert.assertEquals(totalPrice2Update, totalPrice2-(price2*3));
        System.out.println("Tong gia sp2 sau khi update: " +totalPrice2Update);

        //Tong sp 1 + 2
        Double total = Double.parseDouble(driver.findElement(By.xpath("//tr[1]//span[@class='value-summary']")).getText().replaceAll("[^0-9.]", ""));
        System.out.println("tong cuoi cung: " +total);
        Assert.assertEquals(total, totalPrice1+totalPrice2Update);

        //cong them tien goi qua
        new Select(driver.findElement(By.cssSelector("select#checkout_attribute_1"))).selectByVisibleText("Yes [+$10.00]");
        Thread.sleep(3000);
        Double giftPrice = Double.parseDouble(driver.findElement(By.cssSelector("div.selected-checkout-attributes")).getText().replaceAll("[^0-9.]", ""));
        Double totalUpdate = Double.parseDouble(driver.findElement(By.xpath("//tr[1]//span[@class='value-summary']")).getText().replaceAll("[^0-9.]", ""));
        System.out.println("Tong sau khi cong them tien goi qua: " +totalUpdate);
        Assert.assertEquals(totalUpdate, total+giftPrice);
    }

    @Test
    public void TC_04_Wishlist() throws InterruptedException {
        //wishlist khi chưa có item nào
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector("a.ico-wishlist")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.no-data")).getText(), "The wishlist is empty!");
        //sang trang book để add
        driver.findElement(By.xpath("//div[@class='header-menu']/ul[1]/li[5]/a")).click();
        List<WebElement> allBtn = driver.findElements(By.xpath("//button[text()='Add to wishlist']"));
        for (WebElement btn : allBtn) {
            btn.click();
//            driver.findElement(By.cssSelector("span.close")).click();
            Thread.sleep(3000);
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("span.wishlist-qty")).getText(), "(3)");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.ico-wishlist")));
        List<WebElement> allPrice = driver.findElements(By.cssSelector("td.unit-price span"));
        Double price1Number = null, price2Number = null, price3Number = null;
        if(allPrice.size() == 3) {
            WebElement price1 = allPrice.get(0);
            WebElement price2 = allPrice.get(1);
            WebElement price3 = allPrice.get(2);
            price1Number = Double.parseDouble(price1.getText().replaceAll("[^0-9.]", ""));
            price2Number = Double.parseDouble(price2.getText().replaceAll("[^0-9.]", ""));
            price3Number = Double.parseDouble(price3.getText().replaceAll("[^0-9.]", ""));
        }
        //update book2 len 4
        driver.findElement(By.xpath("//tbody/tr[2]/td[6]/input")).clear();
        driver.findElement(By.xpath("//tbody/tr[2]/td[6]/input")).sendKeys("4");
        driver.findElement(By.cssSelector("button#updatecart")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//tbody/tr[2]/td[6]/input")).getAttribute("value"), "4");
        Double updatePrice2 = Double.parseDouble(driver.findElement(By.xpath("//tbody/tr[2]/td[7]/span")).getText().replaceAll("[^0-9.]", ""));
        Assert.assertEquals(updatePrice2, price2Number*4);
        //remove book 3
        driver.findElement(By.xpath("//tbody/tr[3]/td[8]/button")).click();
        Thread.sleep(3000);
        List<WebElement> allTr = driver.findElements(By.xpath("//tbody/tr"));
        if(allTr.size() == 2) {
            System.out.println("hang 3 da dc xoa");
        } else {
            System.out.println("hang 3 chua dc xoa");
        }
        List<WebElement> allAdd =  driver.findElements(By.cssSelector("input[name='addtocart']"));
        for (WebElement checkboxAdd : allAdd) {
            checkboxAdd.click();
        }
        driver.findElement(By.cssSelector("button[name='addtocartbutton']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/cart");
        Assert.assertEquals(driver.findElement(By.cssSelector("span.cart-qty")).getText(), "(5)");
    }

    @Test
    public void TC_05_FilterByAttribute() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//div[@class='header-menu']/ul[1]/li[3]/a")).click();
        driver.findElement(By.xpath("//li[@class='active last']//a[text()='Shoes ']")).click();
        driver.findElement(By.cssSelector("input#attribute-option-15")).click();
        Thread.sleep(3000);
        List<WebElement> allItem = driver.findElements(By.cssSelector("div.item-box"));
        System.out.println(allItem.size());
        List<WebElement> allNameItem = driver.findElements(By.cssSelector("h2.product-title a"));
        for (WebElement nameItem : allNameItem) {
            System.out.println(nameItem.getText());
            Thread.sleep(3000);
            Assert.assertEquals(nameItem.getText(), "adidas Consortium Campus 80s Running Shoes");
        }
    }
    @Test void TC_06_SortBy() throws InterruptedException {
        //search nhung ko bam enter thi cai list result no hien duoi thanh search
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//div[@class='header-menu']/ul[1]/li[3]/a")).click();
        driver.findElement(By.xpath("//li[@class='active last']//a[text()='Clothing ']")).click();
        //lay ten sp truoc khi sap xep
        List<WebElement> allNameProduct = driver.findElements(By.cssSelector("h2.product-title a"));
        List<String> nameProductsBefore = new ArrayList<>();
        for (WebElement nameProduct : allNameProduct) {
            nameProductsBefore.add(nameProduct.getText());
        }
        System.out.println(nameProductsBefore);
        System.out.println("=====================");
        //chon sx tu a-z
        new Select(driver.findElement(By.cssSelector("select#products-orderby"))).selectByVisibleText("Name: Z to A");
        Thread.sleep(3000);
        List<WebElement> allNameProductAfter = driver.findElements(By.cssSelector("h2.product-title a"));
        List<String> nameProductsAfter = new ArrayList<>();
        for (WebElement nameProductAfter : allNameProductAfter) {
            nameProductsAfter.add(nameProductAfter.getText());
        }
        System.out.println(nameProductsAfter);
        Collections.sort(nameProductsBefore, Collections.reverseOrder());
        Assert.assertEquals(nameProductsAfter, nameProductsBefore);
        Thread.sleep(3000);
        System.out.println("Giá thấp - cao");
        List<WebElement> allPrice = driver.findElements(By.cssSelector("div.prices span.actual-price"));
        List<String> priceProduct = new ArrayList<>();
        for (WebElement price : allPrice) {
            priceProduct.add(price.getText().replaceAll("[^0-9.]", ""));
        }
        System.out.println(priceProduct);

        System.out.println("=====================");
        new Select(driver.findElement(By.cssSelector("select#products-orderby"))).selectByVisibleText("Price: Low to High");
        Thread.sleep(3000); // Đợi trang sắp xếp lại

        List<WebElement> allPriceAfter = driver.findElements(By.cssSelector("div.prices span.actual-price"));
        List<String> priceProductAfter = new ArrayList<>();
        for (WebElement priceAfter : allPriceAfter) {
            priceProductAfter.add(priceAfter.getText().replaceAll("[^0-9.]", ""));
        }
        System.out.println(priceProductAfter);

        List<String> sortedPriceProduct = new ArrayList<>(priceProduct);
        Collections.sort(sortedPriceProduct);
        System.out.println(sortedPriceProduct);

        Assert.assertEquals(priceProductAfter, sortedPriceProduct);

        // Sắp xếp giá từ cao đến thấp
        System.out.println("Giá cao - thấp");
        allPrice = driver.findElements(By.cssSelector("div.prices span.actual-price"));
        priceProduct = new ArrayList<>();
        for (WebElement price : allPrice) {
            priceProduct.add(price.getText().replaceAll("[^0-9.]", ""));
        }
        System.out.println(priceProduct);

        System.out.println("=====================");
        new Select(driver.findElement(By.cssSelector("select#products-orderby"))).selectByVisibleText("Price: High to Low");
        Thread.sleep(3000); // Đợi trang sắp xếp lại

        allPriceAfter = driver.findElements(By.cssSelector("div.prices span.actual-price"));
        priceProductAfter = new ArrayList<>();
        for (WebElement priceAfter : allPriceAfter) {
            priceProductAfter.add(priceAfter.getText().replaceAll("[^0-9.]", ""));
        }
        System.out.println(priceProductAfter);

        List<String> sortedPriceProductCT = new ArrayList<>(priceProduct);
        Collections.sort(sortedPriceProductCT, Collections.reverseOrder());
        System.out.println(sortedPriceProductCT);

        Assert.assertEquals(priceProductAfter, sortedPriceProductCT);
        
        //sap xep theo create on

    }

    @Test
    public void TC_08_Compare_List() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/compareproducts");
        //kiem tra khi chua co item
        Assert.assertEquals(driver.findElement(By.cssSelector("div.no-data")).getText(), "You have no items to compare.");
        driver.findElement(By.xpath("//div[@class='header-menu']/ul[1]/li[3]/a")).click();
        driver.findElement(By.xpath("//li[@class='active last']//a[text()='Clothing ']")).click();
        List<WebElement> allBtn = driver.findElements(By.xpath("//button[text()='Add to compare list']"));
        Thread.sleep(3000);
        for (WebElement btn : allBtn) {
            btn.click();
            Thread.sleep(3000);
        }
        driver.findElement(By.xpath("//a[text()='product comparison']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/compareproducts");

        List<WebElement> allBtnsRemove = driver.findElements(By.xpath("//tr[@class='remove-product']/td/button"));
        Thread.sleep(3000);
        System.out.println(allBtnsRemove.size());
        while (allBtnsRemove.size() > 0) {
            allBtnsRemove.get(0).click();
            allBtnsRemove = driver.findElements(By.xpath("//tr[@class='remove-product']/td/button"));
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("div.no-data")).getText(), "You have no items to compare.");
    }

    @Test
    public void TC_07_AddCartJean() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/levis-511-jeans");

        driver.findElement(By.xpath("//button[@class='button-1 add-to-cart-button']")).click();
        driver.findElement(By.cssSelector("span.close")).click();
        Thread.sleep(1000);

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.ico-cart")));
        Thread.sleep(3000);

        for (int i = 0; i < 10; i++) {
            driver.findElement(By.cssSelector("div.quantity.up")).click();
            WebElement quantityProductJean = driver.findElement(By.cssSelector("input.qty-input"));
            Integer quantity = Integer.parseInt(quantityProductJean.getAttribute("value"));
            Thread.sleep(3000);
            Double priceProductJean = null;
            Double totalPrice = Double.parseDouble(driver.findElement(By.cssSelector("span.product-subtotal")).getText().replaceAll("[^0-9.]", ""));
            if (quantity > 0 && quantity <= 2) {
                 priceProductJean = Double.parseDouble(driver.findElement(By.cssSelector("td.unit-price")).getText().replaceAll("[^0-9.]", ""));
            } else if (quantity >= 3 && quantity <= 5) {
                 priceProductJean = Double.parseDouble(driver.findElement(By.cssSelector("td.unit-price")).getText().replaceAll("[^0-9.]", ""));
            } else if (quantity >= 6 && quantity <= 9) {
                 priceProductJean = Double.parseDouble(driver.findElement(By.cssSelector("td.unit-price")).getText().replaceAll("[^0-9.]", ""));
            } else if (quantity >= 10) {
                 priceProductJean = Double.parseDouble(driver.findElement(By.cssSelector("td.unit-price")).getText().replaceAll("[^0-9.]", ""));
            }
            Assert.assertEquals(totalPrice, priceProductJean * quantity);
            System.out.println(totalPrice);
            Thread.sleep(3000);
        }
    }

    @Test
    public void TC_09_Search() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/computers");
        String[] keys = {"i", "in", "inc", "inch", "inch S"};
        WebElement searchInput = driver.findElement(By.cssSelector("input#small-searchterms"));
        for(String key : keys) {
            searchInput.clear();
            searchInput.sendKeys(key);
            Thread.sleep(3000);
            System.out.println("Key: " +key);
            List<WebElement> listItem = driver.findElements(By.cssSelector("ul#ui-id-1 li a span"));
            if(listItem.size() > 0) {
                for(WebElement item : listItem) {
                    System.out.println("Result: " +item.getText());
                }
            } else {
                System.out.println("Khong co result");
            }
            System.out.println("==========");
        }
    }

    @Test
    public void TC_10_AddNew() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//div[@class='news-item'][1]//a[@class='read-more']")).click();
        Thread.sleep(3000);

        //add khi chua nhap title, cmt
        driver.findElement(By.cssSelector("button[name='add-comment']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Title:']/following-sibling::span[@class='field-validation-error']")).getText(), "Enter comment title");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comment:']/following-sibling::span[@class='field-validation-error']")).getText(), "Enter comment text");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//label[text()='Title:']/following-sibling::span[@class='field-validation-error']")).getCssValue("color")).asHex().toLowerCase(), "#e4434b");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//label[text()='Comment:']/following-sibling::span[@class='field-validation-error']")).getCssValue("color")).asHex().toLowerCase(), "#e4434b");
        String title = "Nguyen Thi Le Thu", comment = "Xin chao cac ban";

        //add nhieu cmt
        for (int i = 1; i <= 10; i++) {
            driver.findElement(By.cssSelector("input#AddNewComment_CommentTitle")).sendKeys(title);
            driver.findElement(By.cssSelector("textarea#AddNewComment_CommentText")).sendKeys(comment);
            driver.findElement(By.cssSelector("button[name='add-comment']")).click();
            List<WebElement> cmt = driver.findElements(By.cssSelector("div.comment.news-comment"));
            Assert.assertEquals(cmt.size(), 1+i);
            System.out.println(cmt.size());
        }

        //add 1 cmt
        LocalDate date = LocalDate.now();
        LocalDateTime time = LocalDateTime.now();

        DateTimeFormatter ngay = DateTimeFormatter.ofPattern("M/d/yyyy");
        String ngaythang = date.format(ngay);

        DateTimeFormatter gio = DateTimeFormatter.ofPattern("h:mm a");
        String giophut = time.format(gio).toUpperCase();
        driver.findElement(By.cssSelector("input#AddNewComment_CommentTitle")).sendKeys(title);
        driver.findElement(By.cssSelector("textarea#AddNewComment_CommentText")).sendKeys(comment);
        driver.findElement(By.cssSelector("button[name='add-comment']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//strong[text()='" +title+ "']/ancestor::div[@class='comment-content']//span")).getText(), ngaythang+ " " +giophut);

    }

    @Test
    public void TC_11_Recently_View() {
        //nhan vao item phai sang man detail
        driver.get("https://demo.nopcommerce.com/jewelry");
        List<WebElement> allProduct = driver.findElements(By.cssSelector("h2.product-title a"));
        for (int i = 0; i < allProduct.size(); i++) {
            //lay lai sp de khi back lai nhung ko lay lai sp da click
            allProduct = driver.findElements(By.cssSelector("h2.product-title a"));
            WebElement product = allProduct.get(i);
            String nameProduct = product.getText();
            product.click();
            driver.navigate().back();
            allProduct = driver.findElements(By.cssSelector("h2.product-title a"));

            //kiem tra recently
            List<WebElement> allRecentlyProduct = driver.findElements(By.xpath("//div[@class='listbox']//a[@class='product-name']"));
            WebElement recentlyProductFirst = allRecentlyProduct.get(0);
            System.out.println(recentlyProductFirst.getText());
            System.out.println(nameProduct);
            Assert.assertEquals(recentlyProductFirst.getText(), nameProduct);
        }
    }

    @Test
    public void TC_12_Display_Page() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/clothing");
        Select select = new Select(driver.findElement(By.cssSelector("select#products-pagesize")));
        String[] optionDisplay = {"3", "6", "9"};

        //CACH 1
//        for (String option : optionDisplay) {
//            select.selectByVisibleText(option);
//            System.out.println(select.getFirstSelectedOption().getText());
//            Thread.sleep(3000);
//            // Lấy lại danh sách tất cả các sản phẩm
//            List<WebElement> allItem = driver.findElements(By.cssSelector("div.item-box"));
//            System.out.println("display: " + allItem.size());
//            System.out.println("========");
//        }

        //CACH 2
        for(String option : optionDisplay) {
            select.selectByVisibleText(option);
            System.out.println(select.getFirstSelectedOption().getText());
            Thread.sleep(3000);
            List<WebElement> allItem = driver.findElements(By.cssSelector("div.item-box"));
            int selectOption = Integer.parseInt(option);
            Assert.assertTrue(allItem.size() >= 0 && allItem.size() <= selectOption);
            System.out.println(allItem.size());
        }
    }
    @Test
    public void TC_12_Download_Sample() {
        //check xem download thanh cong chua
    }
    @Test
    public void TC_11_Change_Money() {

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
