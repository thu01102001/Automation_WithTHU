import com.sun.jdi.ThreadReference;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
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

public class Day20_Prestashop {
    WebDriver driver; //khai báo bến
    JavascriptExecutor jsExecutor;
    Actions actions;
    WebDriverWait explicitWait;
    String firstName = "nguyen", lastName = "thu",  password = "123456", birthday = "01/10/2001";
    String email = "automation" + new Random().nextInt(999) + "@gmail.com";
    String email1 = "automationn123456@gmail.com";
    @BeforeClass
    public void InitialBrowser() {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Hover() throws InterruptedException {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
        verifyColor("//a[text()='Contact us']", "#24b9d7");
        verifyColor("//span[@class='expand-more']", "#24b9d7");
        verifyColor("//div[@class='user-info']/a[@title='Log in to your customer account']", "#24b9d7");
        verifyColor("//li[@id='category-3']/a", "#24b9d7");
        verifyColor("//li[@id='category-6']/a", "#24b9d7");
        verifyColor("//li[@id='category-9']/a", "#24b9d7");
        actions.moveToElement(driver.findElement(By.xpath("//li[@id='category-3']/a"))).perform();
        Thread.sleep(3000);
        verifyColor("//a[contains(text(), 'Men')]", "#24b9d7");
        verifyColor("//a[contains(text(), 'Women')]", "#24b9d7");
        actions.moveToElement(driver.findElement(By.xpath("//li[@id='category-6']/a"))).perform();
        verifyColor("//a[contains(text(), 'Stationery')]", "#24b9d7");
        verifyColor("//a[contains(text(), 'Home Accessories')]", "#24b9d7");
    }

    private void verifyColor(String xpath, String color) throws InterruptedException {
        actions.moveToElement(driver.findElement(By.xpath(xpath))).perform();
        Thread.sleep(3000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath(xpath)).getCssValue("color")).asHex().toLowerCase(), color);
        Thread.sleep(2000);
    }

    @Test
     public void TC_02_Click() throws InterruptedException {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
        driver.findElement(By.xpath("//a[text()='Contact us']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(), "CONTACT US");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='user-info']/a[@title='Log in to your customer account']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("Log in to your account"));
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div#_desktop_logo a")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'Popular Products')]")).isDisplayed());

        actions.moveToElement(driver.findElement(By.xpath("//li[@id='category-3']/a"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(), 'Men')]")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.h1")).getText(), "MEN");

        actions.moveToElement(driver.findElement(By.xpath("//li[@id='category-3']/a"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(), 'Women')]")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.h1")).getText(), "WOMEN");

        actions.moveToElement(driver.findElement(By.xpath("//li[@id='category-6']/a"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(), 'Stationery')]")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.h1")).getText(), "STATIONERY");

        actions.moveToElement(driver.findElement(By.xpath("//li[@id='category-6']/a"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(), 'Home Accessories')]")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.h1")).getText(), "HOME ACCESSORIES");

        //Cart khi ko co product
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("div.cart-preview")).getCssValue("background-color")).asHex().toLowerCase(), "#f6f6f6");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//span[text()='Cart']")).getCssValue("color")).asHex().toLowerCase(), "#7a7a7a");
        WebElement qualityCarts = driver.findElement(By.cssSelector("span.cart-products-count"));
        String qualityCart = qualityCarts.getText().replaceAll("[^0-9.]", "");
        Assert.assertEquals(qualityCart, "0");

        //Search
        Assert.assertEquals(driver.findElement(By.cssSelector("input.ui-autocomplete-input")).getAttribute("placeholder"), "Search our catalog");
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("input.ui-autocomplete-input")).getCssValue("background-color")).asHex().toLowerCase(), "#f1f1f1");
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("input.ui-autocomplete-input")).getCssValue("color")).asHex().toLowerCase(), "#9b9b9b");
        Thread.sleep(2000);

        //quay lai trang chu
        driver.findElement(By.cssSelector("div#_desktop_logo a")).click();
        Thread.sleep(2000);

        //slider
        List<WebElement> listSliders = driver.findElements(By.cssSelector("li.carousel-item"));
        Assert.assertEquals(listSliders.size(), 3);

        WebElement sliderActive = driver.findElement(By.cssSelector("li.carousel-item.active img"));
        String imgActive1 = sliderActive.getAttribute("src");
        System.out.println("Anh 1: " +imgActive1);

        driver.findElement(By.cssSelector("div.direction span.icon-next i")).click();
        Thread.sleep(2000);
        sliderActive = driver.findElement(By.cssSelector("li.carousel-item.active img"));
        String imgActive2 = sliderActive.getAttribute("src");
        System.out.println("Anh 2: " +imgActive2);
        Assert.assertNotEquals(imgActive1, imgActive2);

        driver.findElement(By.cssSelector("div.direction span.icon-next i")).click();
        Thread.sleep(2000);
        sliderActive = driver.findElement(By.cssSelector("li.carousel-item.active img"));
        String imgActive3 = sliderActive.getAttribute("src");
        System.out.println("Anh 3: " +imgActive3);
        Assert.assertNotEquals(imgActive2, imgActive3);
        Thread.sleep(3000);


        //language
        driver.findElement(By.xpath("//span[@class='expand-more']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@aria-labelledby='language-selector-label']//a[@class='dropdown-item']")));
        List<WebElement> allLanguages = driver.findElements(By.xpath("//ul[@aria-labelledby='language-selector-label']//a[@class='dropdown-item']"));
        Assert.assertEquals(allLanguages.size(), 46);
        for (WebElement hoverLanguage : allLanguages) {
            actions.moveToElement(hoverLanguage).perform();
            Thread.sleep(3000);
            Assert.assertEquals(Color.fromString(hoverLanguage.getCssValue("color")).asHex().toLowerCase(), "#24b9d7");
            Thread.sleep(2000);
        }

        for (WebElement clickLanguage : allLanguages) {
            if (clickLanguage.getText().equals("Eesti keel")) {
                clickLanguage.click();
                break;
            }
        }
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("span.expand-more")).getText(), "Eesti keel");
        Thread.sleep(3000);
    }

    @Test
    public void TC_03_PopularProduct() throws InterruptedException {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
        Thread.sleep(3000);

        //tc16
        List<WebElement> listPopularProductHome = driver.findElements(By.xpath("//h2[contains(text(), 'Popular Products')]/following-sibling::div//article"));
        Assert.assertEquals(listPopularProductHome.size(), 8);

        //tc17
        System.out.println("So luong product sale: " +driver.findElements(By.xpath("//h2[contains(text(), 'Popular Products')]/following-sibling::div//article//li")).size());

        Thread.sleep(3000);
        //tc18
        List<WebElement> hoverProducts = driver.findElements(By.xpath("//h2[contains(text(), 'Popular Products')]/following-sibling::div//article//img"));
        for (WebElement hoverProduct : hoverProducts) {
            actions.moveToElement(hoverProduct).perform();
            Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'Popular Products')]/following-sibling::div//article//div[@class='thumbnail-top']/div")).isDisplayed());
            Thread.sleep(2000);
        }

        //tc19
        List<WebElement> hoverFavoriteProducts = driver.findElements(By.cssSelector("button.wishlist-button-add"));
        for (WebElement hoverFavoriteProduct : hoverFavoriteProducts) {
            actions.moveToElement(hoverFavoriteProduct).perform();
            String opacity = hoverFavoriteProduct.getCssValue("opacity");
            float opacityValue = Float.parseFloat(opacity);
            float opacityNumber = (float) (Math.floor(opacityValue * 10) / 10.0);
            Assert.assertEquals(opacityNumber, 0.7);
            Thread.sleep(2000);
        }

        //tc20
        driver.findElement(By.xpath("//div[@class='products row']//div[1]/article//button")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='wishlist-modal modal fade show']/div")).isDisplayed());

        //tc21
        driver.findElement(By.xpath("//div[@class='wishlist-modal modal fade show']//button[contains(text(), 'Cancel')]")).click();
        Assert.assertEquals(driver.findElements(By.xpath("//div[@class='wishlist-modal modal fade show']/div")).size(), 0);

        //tc22
        driver.findElement(By.xpath("//div[@class='products row']//div[1]/article//button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='wishlist-modal modal fade show']/div//a")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("header.page-header h1")).getText(), "Log in to your account");
        driver.navigate().back();
        Thread.sleep(2000);
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'Popular Products')]")).isDisplayed());

        //tc23
        driver.findElement(By.xpath("//div[@class='products row']//div[1]/article//button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='wishlist-modal modal fade show']/div//span")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElements(By.xpath("//div[@class='wishlist-modal modal fade show']/div")).size(), 0);

        driver.manage().deleteAllCookies();
        //tc25
        String nameProduct = driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//h3/a")).getText().toLowerCase();
        String priceProduct = driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//span[@class='regular-price']")).getText();
        System.out.println(priceProduct);
        String priceProductSale = driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//span[@class='price']")).getText();
        System.out.println(priceProductSale);

        WebElement element = driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//span[@class='discount-percentage discount-product']"));
        String textContent = (String) jsExecutor.executeScript("return arguments[0].textContent;", element);
        String tagSale = textContent.replaceAll("[^0-9.]", "");
        driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//img")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//section[@id='wrapper']//div[@class='container']//li[4]/span")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//section[@id='wrapper']//div[@class='container']//h1")).getText().toLowerCase(), nameProduct);
        Assert.assertEquals(driver.findElement(By.xpath("//section[@id='wrapper']//div[@class='container']//span[@class='regular-price']")).getText(), priceProduct);
        Assert.assertEquals(driver.findElement(By.xpath("//section[@id='wrapper']//div[@class='container']//span[@class='current-price-value']")).getText(), priceProductSale);
        Assert.assertEquals(driver.findElement(By.xpath("//section[@id='wrapper']//div[@class='container']//span[@class='discount discount-percentage']")).getText().replaceAll("[^0-9.]", ""), tagSale);
        driver.navigate().back();
        driver.manage().deleteAllCookies();
        Thread.sleep(3000);
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'Popular Products')]")).isDisplayed());

        //tc26
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//img"))).perform();
        Thread.sleep(3000);
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//a[@class='quick-view js-quick-view']"))).perform();
        Thread.sleep(2000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//a[@class='quick-view js-quick-view']")).getCssValue("color")).asHex().toLowerCase(), "#24b9d7");

        //tc31
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//img"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//a[@class='quick-view js-quick-view']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='quickview-modal-2-9']/div[@class='modal-dialog modal-dialog-centered']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='quickview-modal-2-9']/div[@class='modal-dialog modal-dialog-centered']//h1")).getText().toLowerCase(), nameProduct);
        List<WebElement> listOption = driver.findElements(By.xpath("//div[@id='quickview-modal-2-9']/div[@class='modal-dialog modal-dialog-centered']//option"));

        // Lấy đối tượng Select từ phần tử <select>
        WebElement selectElement = driver.findElement(By.xpath("//div[@id='quickview-modal-2-9']/div[@class='modal-dialog modal-dialog-centered']//select"));
        Select select = new Select(selectElement);

        for (WebElement option : listOption) {
            if(option.getText().equals("M")) {
                option.click();
                break;
            }
        }

        driver.findElement(By.xpath("//div[@id='quickview-modal-2-9']/div[@class='modal-dialog modal-dialog-centered']//input[@id='quantity_wanted']")).clear();
        driver.findElement(By.xpath("//div[@id='quickview-modal-2-9']/div[@class='modal-dialog modal-dialog-centered']//input[@id='quantity_wanted']")).sendKeys("5");
        driver.findElement(By.xpath("//div[@id='quickview-modal-2-9']/div[@class='modal-dialog modal-dialog-centered']//button[@type='submit']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='blockcart-modal']/div")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='blockcart-modal']/div//h4")).getText().contains("Product successfully added to your shopping cart"));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='blockcart-modal']/div//h6")).getText().toLowerCase(), nameProduct);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='blockcart-modal']/div//span[@class='product-quantity']/strong")).getText(), "5");
        driver.findElement(By.xpath("//div[@id='blockcart-modal']/div//button[text()='Continue shopping']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='blockcart-modal']")).getCssValue("display"), "none");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//div[@id='_desktop_cart']/div")).getCssValue("background-color")).asHex().toLowerCase(), "#24b9d7");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='_desktop_cart']/div//span[@class='cart-products-count']")).getText(), "(5)");


        actions.moveToElement(driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//img"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='products row']//div[2]/article//a[@class='quick-view js-quick-view']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='quickview-modal-2-9']/div[@class='modal-dialog modal-dialog-centered']//button[@type='submit']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='blockcart-modal']/div//a")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//section[@id='wrapper']//h1")).getText().toLowerCase(), "shopping cart");
        driver.findElement(By.cssSelector("div#_desktop_logo a")).click();
        Thread.sleep(2000);
//        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(), 'All products')]"))));
        driver.findElement(By.xpath("//a[contains(text(), 'All products')]//following-sibling::i")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//section[@id='wrapper']//h1")).getText().toLowerCase(), "home");
        driver.navigate().back();
        Thread.sleep(3000);
    }

    @Test
    public void TC_04_Search() throws InterruptedException {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input.ui-autocomplete-input")).click();
        actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("h1#js-product-list-header")).getText().toLowerCase(), "search results");
        Assert.assertEquals(driver.findElement(By.cssSelector("h4#product-search-no-matches")).getText().toLowerCase(), "no matches were found for your search");
        String[] listKeyNoResult = {"h", "hu", "hcuascnascsa"};
        for (String keyNoResult : listKeyNoResult) {
            driver.findElement(By.cssSelector("input.ui-autocomplete-input")).sendKeys(keyNoResult);
            actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
            Assert.assertEquals(driver.findElement(By.cssSelector("h1#js-product-list-header")).getText().toLowerCase(), "search results");
            Assert.assertEquals(driver.findElement(By.cssSelector("h4#product-search-no-matches")).getText().toLowerCase(), "no matches were found for your search");
            Assert.assertEquals(driver.findElement(By.cssSelector("section#content input[placeholder]")).getAttribute("value"), keyNoResult);
            driver.findElement(By.cssSelector("input.ui-autocomplete-input")).clear();
            Thread.sleep(2000);
        }
        String[] listKeyResult = {"hum", "Hummingbird cushion"};
        for (String keyResult : listKeyResult) {
            driver.findElement(By.cssSelector("input.ui-autocomplete-input")).sendKeys(keyResult);
            actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
            System.out.println("Ket qua search cua " +keyResult+ " la: " +driver.findElement(By.cssSelector("section#products p")).getText());
            driver.findElement(By.cssSelector("input.ui-autocomplete-input")).clear();
            Thread.sleep(2000);
        }
        driver.findElement(By.cssSelector("input.ui-autocomplete-input")).clear();
        driver.findElement(By.cssSelector("input.ui-autocomplete-input")).sendKeys("hum");
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(By.cssSelector("ul.ui-autocomplete")).getCssValue("display"), "block");
        String nameCheck = driver.findElement(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all searchbar-autocomplete']/li[1]//span")).getText().toLowerCase();
        driver.findElement(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all searchbar-autocomplete']/li[1]")).click();
        Assert.assertEquals(nameCheck, driver.findElement(By.cssSelector("section#main h1")).getText().toLowerCase());

    }

    @Test
    public void TC_05_SortBy() throws InterruptedException {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input.ui-autocomplete-input")).sendKeys("hum");
        actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();

        //lay ten truoc khi sap xep
        List<WebElement> nameAllBefore = driver.findElements(By.cssSelector("h2.h3.product-title a"));
        List<String> nameBefore = new ArrayList<>();
        for (WebElement name : nameAllBefore) {
            nameBefore.add(name.getText());
        }

        driver.findElement(By.xpath("//button[@aria-label='Sort by selection']")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.dropdown-menu a")));
        List<WebElement> listOption = driver.findElements(By.cssSelector("div.dropdown-menu a"));
        for (WebElement option : listOption) {
            if(option.getText().equals("Name, A to Z")) {
                option.click();
                break;
            }
        }
        Thread.sleep(3000);
        List<WebElement> nameAllAfter = driver.findElements(By.cssSelector("h2.h3.product-title a"));
        List<String> nameAfter = new ArrayList<>();
        for (WebElement name : nameAllAfter) {
            nameAfter.add(name.getText());
        }

        Collections.sort(nameBefore);
        Assert.assertEquals(nameBefore, nameAfter);
        Thread.sleep(2000);

        //z-a
        nameAllBefore = driver.findElements(By.cssSelector("h2.h3.product-title a"));
        nameBefore = new ArrayList<>();
        for (WebElement name : nameAllBefore) {
            nameBefore.add(name.getText());
        }

        driver.findElement(By.xpath("//button[@aria-label='Sort by selection']")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.dropdown-menu a")));
        listOption = driver.findElements(By.cssSelector("div.dropdown-menu a"));
        for (WebElement option : listOption) {
            if(option.getText().equals("Name, Z to A")) {
                option.click();
                break;
            }
        }
        Thread.sleep(3000);
        nameAllAfter = driver.findElements(By.cssSelector("h2.h3.product-title a"));
        nameAfter = new ArrayList<>();
        for (WebElement name : nameAllAfter) {
            nameAfter.add(name.getText());
        }
        Collections.sort(nameBefore, Collections.reverseOrder());
        Assert.assertEquals(nameAfter, nameBefore);

        //gia thap - cao
        //lay ten gia
        List<WebElement> priceAllBefore = driver.findElements(By.cssSelector("span.price"));
        List<String> priceBefore = new ArrayList<>();
        for (WebElement price : priceAllBefore) {
            priceBefore.add(price.getText().replaceAll("[^0-9.]", ""));
        }
        driver.findElement(By.xpath("//button[@aria-label='Sort by selection']")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.dropdown-menu a")));
        listOption = driver.findElements(By.cssSelector("div.dropdown-menu a"));
        for (WebElement option : listOption) {
            if(option.getText().equals("Price, low to high")) {
                option.click();
                break;
            }
        }
        Thread.sleep(3000);
        List<WebElement> priceAllAfter = driver.findElements(By.cssSelector("span.price"));
        List<String> priceAfter = new ArrayList<>();
        for (WebElement price : priceAllAfter) {
            priceAfter.add(price.getText().replaceAll("[^0-9.]", ""));
        }

        Collections.sort(priceBefore);
        Assert.assertEquals(priceAfter, priceBefore);
        Thread.sleep(2000);


        //gia cao den thap
        priceAllBefore = driver.findElements(By.cssSelector("span.price"));
        priceBefore = new ArrayList<>();
        for (WebElement price : priceAllBefore) {
            priceBefore.add(price.getText().replaceAll("[^0-9.]", ""));
        }

        driver.findElement(By.xpath("//button[@aria-label='Sort by selection']")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.dropdown-menu a")));
        listOption = driver.findElements(By.cssSelector("div.dropdown-menu a"));
        for (WebElement option : listOption) {
            if(option.getText().equals("Price, high to low")) {
                option.click();
                break;
            }
        }
        Thread.sleep(3000);
        priceAllAfter = driver.findElements(By.cssSelector("span.price"));
        priceAfter = new ArrayList<>();
        for (WebElement price : priceAllAfter) {
            priceAfter.add(price.getText().replaceAll("[^0-9.]", ""));
        }
        Collections.sort(priceBefore, Collections.reverseOrder());
        Assert.assertEquals(priceAfter, priceBefore);
        Thread.sleep(2000);
    }

    @Test
    public void TC_06_Signup() throws InterruptedException {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("div.user-info a")).click();
        driver.findElement(By.cssSelector("div.no-account a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("section#wrapper h1")).getText(), "Create an account");
        //Kiem tra khi ko nhap cac truong bat buoc
        //ko nhap first name
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String invalidFirstName = getElementValidationMessage("//input[@id='field-firstname']");
        Assert.assertEquals(invalidFirstName, "Please fill out this field.");
        Thread.sleep(3000);

        //nhap firstname
        //ko nhap last name
        driver.findElement(By.xpath("//input[@id='field-firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String invalidLastName = getElementValidationMessage("//input[@id='field-lastname']");
        Assert.assertEquals(invalidLastName, "Please fill out this field.");
        Thread.sleep(3000);

        //nhap last name
        //ko nhap email
        driver.findElement(By.xpath("//input[@id='field-lastname']")).sendKeys(lastName);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String invalidEmail = getElementValidationMessage("//input[@id='field-email']");
        Assert.assertEquals(invalidEmail, "Please fill out this field.");
        Thread.sleep(3000);

        //nhap email = a
        String emailEx = "a";
        driver.findElement(By.xpath("//input[@id='field-email']")).sendKeys(emailEx);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        invalidEmail = getElementValidationMessage("//input[@id='field-email']");
        Assert.assertEquals(invalidEmail, "Please include an '@' in the email address. '" +emailEx+ "' is missing an '@'.");
        Thread.sleep(3000);

        //nhap email = aa@
        emailEx = "aa@";
        driver.findElement(By.xpath("//input[@id='field-email']")).clear();
        driver.findElement(By.xpath("//input[@id='field-email']")).sendKeys(emailEx);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        invalidEmail = getElementValidationMessage("//input[@id='field-email']");
        Assert.assertEquals(invalidEmail, "Please enter a part following '@'. '" +emailEx+ "' is incomplete.");
        Thread.sleep(3000);

        //nhap email = aa@aa
        //ko nhap password
        emailEx = "aa@aa";
        driver.findElement(By.xpath("//input[@id='field-email']")).clear();
        driver.findElement(By.xpath("//input[@id='field-email']")).sendKeys(emailEx);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String invalidPass = getElementValidationMessage("//input[@id='field-password']");
        Assert.assertEquals(invalidPass, "Please fill out this field.");
        Thread.sleep(3000);

        //nhap password
        String passwordEx = "1";
        driver.findElement(By.xpath("//input[@id='field-password']")).sendKeys(passwordEx);
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.password-strength-feedback")).getCssValue("display"), "block");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//div[@class='progress-bar bg-danger']")).getCssValue("background-color")).asHex().toLowerCase(), "#ff4c4c");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        invalidPass = getElementValidationMessage("//input[@id='field-password']");
        Assert.assertEquals(invalidPass, "Please match the requested format.");
        Thread.sleep(3000);

        //nhap password
        passwordEx = "123456";
        driver.findElement(By.xpath("//input[@id='field-password']")).clear();
        driver.findElement(By.xpath("//input[@id='field-password']")).sendKeys(passwordEx);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.password-strength-feedback")).getCssValue("display"), "block");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String termsAndConditions = getElementValidationMessage("//div[@class='form-group row '][7]//input");
        Assert.assertEquals(termsAndConditions, "Please check this box if you want to proceed.");
        Thread.sleep(3000);

        //click terms
        driver.findElement(By.xpath("//div[@class='form-group row '][7]//input")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String customerDataPrivacy = getElementValidationMessage("//div[@class='form-group row '][9]//input");
        Assert.assertEquals(customerDataPrivacy, "Please check this box if you want to proceed.");
        Thread.sleep(3000);

        //click customer privacy
        driver.findElement(By.xpath("//div[@class='form-group row '][9]//input")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(3000);

        //dang ky fail
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='field-email']/following-sibling::div//li")).getText(), "Invalid format.");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='field-password']/parent::div/following-sibling::div//li[1]")).getText(), "Password must be between 8 and 72 characters long");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='field-password']/parent::div/following-sibling::div//li[2]")).getText(), "The minimum score must be: Strong");

        passwordEx = "1234567890abcde1";
        driver.findElement(By.xpath("//input[@id='field-password']")).clear();
        driver.findElement(By.xpath("//input[@id='field-password']")).sendKeys(passwordEx);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//div[@class='progress-bar bg-warning']")).getCssValue("background-color")).asHex().toLowerCase(), "#ff9a52");

        //nhap password
        passwordEx = "asdfghjklzxcvbnmas";
        driver.findElement(By.xpath("//input[@id='field-password']")).clear();
        driver.findElement(By.xpath("//input[@id='field-password']")).sendKeys(passwordEx);
        Thread.sleep(2000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//div[@class='progress-bar bg-success']")).getCssValue("background-color")).asHex().toLowerCase(), "#4cbb6c");

        //show password
        driver.findElement(By.xpath("//button[@data-text-hide='Hide']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("input#field-password")).getAttribute("type"), "text");

        //nhap email hop le
        driver.findElement(By.xpath("//input[@id='field-email']")).clear();
        driver.findElement(By.xpath("//input[@id='field-email']")).sendKeys(email1);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(3000);

        //dki thanh cong
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.prestashop.com/#/en/front");
        Assert.assertEquals(driver.findElement(By.cssSelector("a.account span")).getText(), firstName+ " " +lastName);

        //check lai thong tin account
        driver.findElement(By.cssSelector("a.account")).click();
        driver.findElement(By.xpath("//div[@class='links']/a[1]")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("input#field-firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#field-lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#field-email")).getAttribute("value"), email1);

        driver.findElement(By.xpath("//a[@class='logout hidden-sm-down']")).click();
        driver.findElement(By.cssSelector("input#field-email")).sendKeys(email1);
        driver.findElement(By.cssSelector("input#field-password")).sendKeys(passwordEx);
        driver.findElement(By.cssSelector("button#submit-login")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.prestashop.com/#/en/front");
        Assert.assertEquals(driver.findElement(By.cssSelector("a.account span")).getText(), firstName+ " " +lastName);
        driver.findElement(By.xpath("//a[@class='logout hidden-sm-down']")).click();
        driver.findElement(By.cssSelector("div#_desktop_logo a")).click();
//
//        //kiem tra login
//        //kiem tra ko nhap email, password
//        driver.findElement(By.xpath("//a[@class='logout hidden-sm-down']")).click();
//        driver.findElement(By.cssSelector("button#submit-login")).click();
//        invalidEmail = getElementValidationMessage("//input[@id='field-email']");
//        Assert.assertEquals(invalidEmail, "Please fill out this field.");
//
//        //nhap email = a
//        emailEx = "a";
//        driver.findElement(By.xpath("//input[@id='field-email']")).sendKeys(emailEx);
//        driver.findElement(By.cssSelector("button#submit-login")).click();
//        invalidEmail = getElementValidationMessage("//input[@id='field-email']");
//        Assert.assertEquals(invalidEmail, "Please include an '@' in the email address. '" +emailEx+ "' is missing an '@'.");
//        Thread.sleep(3000);
//
//        //nhap email = aa@
//        emailEx = "aa@";
//        driver.findElement(By.xpath("//input[@id='field-email']")).clear();
//        driver.findElement(By.xpath("//input[@id='field-email']")).sendKeys(emailEx);
//        driver.findElement(By.cssSelector("button#submit-login")).click();
//        invalidEmail = getElementValidationMessage("//input[@id='field-email']");
//        Assert.assertEquals(invalidEmail, "Please enter a part following '@'. '" +emailEx+ "' is incomplete.");
//        Thread.sleep(3000);
//
//        //nhap email = aa@aa
//        //ko nhap password
//        emailEx = "aa@aa";
//        driver.findElement(By.xpath("//input[@id='field-email']")).clear();
//        driver.findElement(By.xpath("//input[@id='field-email']")).sendKeys(emailEx);
//        driver.findElement(By.cssSelector("button#submit-login")).click();
//        invalidPass = getElementValidationMessage("//input[@id='field-password']");
//        Assert.assertEquals(invalidPass, "Please fill out this field.");
//        Thread.sleep(3000);
//
//        //nhap dung password & email ko hop le
//        //nhap password
//        passwordEx = "1";
//        driver.findElement(By.xpath("//input[@id='field-password']")).sendKeys(passwordEx);
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("button#submit-login")).click();
//        invalidPass = getElementValidationMessage("//input[@id='field-password']");
//        Assert.assertEquals(invalidPass, "Please match the requested format.");
//        Thread.sleep(3000);
//
//        passwordEx = "1234567890";
//        driver.findElement(By.xpath("//input[@id='field-password']")).clear();
//        driver.findElement(By.xpath("//input[@id='field-password']")).sendKeys(passwordEx);
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("button#submit-login")).click();
//        //nhap email ko hop le, password hop le
//        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='field-email']/following-sibling::div//li")).getText(), "Invalid format.");
//
//        //nhap email hop le, password sai
//        emailEx = "aa@gmail.com";
//        driver.findElement(By.xpath("//input[@id='field-email']")).clear();
//        driver.findElement(By.xpath("//input[@id='field-email']")).sendKeys(emailEx);
//        passwordEx = "1234567899999321";
//        driver.findElement(By.xpath("//input[@id='field-password']")).clear();
//        driver.findElement(By.xpath("//input[@id='field-password']")).sendKeys(passwordEx);
//        driver.findElement(By.cssSelector("button#submit-login")).click();
//        Assert.assertEquals(driver.findElement(By.cssSelector("div.help-block li")).getText(), "Authentication failed.");
//        //nhap dung email sai pass
//        driver.findElement(By.xpath("//input[@id='field-email']")).clear();
//        driver.findElement(By.xpath("//input[@id='field-email']")).sendKeys(email1);
//        passwordEx = "1234567899999321";
//        driver.findElement(By.xpath("//input[@id='field-password']")).clear();
//        driver.findElement(By.xpath("//input[@id='field-password']")).sendKeys(passwordEx);
//        driver.findElement(By.cssSelector("button#submit-login")).click();
//        Assert.assertEquals(driver.findElement(By.cssSelector("div.help-block li")).getText(), "Authentication failed.");
//        //nhap dung email, dung password
//        passwordEx = "asdfghjklzxcvbnmas";
//        driver.findElement(By.xpath("//input[@id='field-password']")).clear();
//        driver.findElement(By.xpath("//input[@id='field-password']")).sendKeys(passwordEx);
//        driver.findElement(By.cssSelector("button#submit-login")).click();
//
//        //login thanh cong
//        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.prestashop.com/#/en/front");
//        Assert.assertEquals(driver.findElement(By.cssSelector("a.account span")).getText(), firstName+ " " +lastName);
    }

    @Test
    public void TC_07_Cart() throws InterruptedException {

//        driver.get("https://demo.prestashop.com/#/en/front");
//        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
//        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Mug The best is yet to come']")).click();
        actions.click(driver.findElement(By.cssSelector("input#quantity_wanted"))).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).keyDown(Keys.DELETE).keyUp(Keys.DELETE).perform();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input#quantity_wanted")).sendKeys("5");
        driver.findElement(By.xpath("//button[@data-button-action='add-to-cart']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.cssSelector("div#blockcart-modal div.modal-dialog")).isDisplayed());
        String price = driver.findElement(By.cssSelector("p.product-price")).getText();
        double priceProduct = Double.parseDouble((price.replaceAll("[^0-9.]", "")));
        String totalPrice = driver.findElement(By.cssSelector("span.subtotal.value")).getText();
        double totalPriceProduct = Double.parseDouble((totalPrice.replaceAll("[^0-9.]", "")));
        //neu priceProduct & totalPriceProduct lech nhau khong qua 0.01 thi test case van pass
        double delta = 0.01;
        Assert.assertEquals(totalPriceProduct, priceProduct*5, delta);
        //chuyen toi cart
        driver.findElement(By.xpath("//a[text()='Proceed to checkout']")).click();
        Thread.sleep(2000);
        //cart khi ko co item
        driver.findElement(By.xpath("//a[@data-link-action='delete-from-cart']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("span.no-items")).getText(), "There are no more items in your cart");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()='Proceed to checkout']")).getCssValue("background-color")).asHex().toLowerCase(), "#f6f6f6");
        //tiep tuc mua hang
        driver.findElement(By.cssSelector("a.label")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Mug The adventure begins']")).click();
        driver.findElement(By.xpath("//button[@data-button-action='add-to-cart']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//i[text()='close']")).click();
        driver.findElement(By.cssSelector("div.blockcart.cart-preview.active")).click();
        Thread.sleep(2000);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//a[text()='Proceed to checkout']")).getCssValue("background-color")).asHex().toLowerCase(), "#24b9d7");
        driver.findElement(By.xpath("//a[text()='Proceed to checkout']")).click();
        Thread.sleep(2000);
        //khi chua co acc
        Assert.assertTrue(driver.findElement(By.cssSelector("input#field-firstname")).isDisplayed());
        //login
        driver.findElement(By.xpath("//a[contains(text(), 'Sign in')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("form#login-form input#field-email")).sendKeys(email1);
        String passwordEx = "asdfghjklzxcvbnmas";
        driver.findElement(By.cssSelector("form#login-form input#field-password")).sendKeys(passwordEx);
        driver.findElement(By.cssSelector("form#login-form button[name='continue']")).click();
        Assert.assertNotEquals(driver.findElement(By.xpath("//section[@id='checkout-addresses-step']//div[@class='content']")).getCssValue("display"), "none");
        //address
        String invalidAdress = getElementValidationMessage("//input[@id='field-address1']");
        driver.findElement(By.cssSelector("div#delivery-address button[type='submit']")).click();
        Assert.assertEquals(invalidAdress, "Please fill out this field.");
        //nhap address
        driver.findElement(By.xpath("//input[@id='field-address1']")).sendKeys("nam dinh");
        driver.findElement(By.cssSelector("div#delivery-address button[type='submit']")).click();
        String invalidZip = getElementValidationMessage("//input[@id='field-postcode']");
        Assert.assertEquals(invalidZip, "Please fill out this field.");
        //nhap zip
        driver.findElement(By.xpath("//input[@id='field-postcode']")).sendKeys("111");
        driver.findElement(By.cssSelector("div#delivery-address button[type='submit']")).click();
        String invalidCity = getElementValidationMessage("//input[@id='field-city']");
        Assert.assertEquals(invalidCity, "Please fill out this field.");
        //nhap city
        driver.findElement(By.xpath("//input[@id='field-city']")).sendKeys("viet nam");
        driver.findElement(By.cssSelector("div#delivery-address button[type='submit']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.alert.alert-danger")).getText(), "Invalid postcode - should look like \"NNNNN\"");
        //nhap lai zip
        driver.findElement(By.xpath("//input[@id='field-postcode']")).clear();
        driver.findElement(By.xpath("//input[@id='field-postcode']")).sendKeys("11111");
        driver.findElement(By.cssSelector("div#delivery-address button[type='submit']")).click();
        Assert.assertNotEquals(driver.findElement(By.xpath("//section[@id='checkout-delivery-step']//div[@class='content']")).getCssValue("display"), "none");
        //shipping
        driver.findElement(By.cssSelector("div.delivery-options-list button")).click();
        //payment
        Assert.assertNotEquals(driver.findElement(By.cssSelector("section#checkout-payment-step div.content")).getCssValue("display"), "none");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[contains(text(), 'Place order')]")).getCssValue("background-color")).asHex().toLowerCase(), "#f6f6f6");
        driver.findElement(By.cssSelector("input#payment-option-2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#payment-option-2-additional-information")).getCssValue("display"), "block");
        driver.findElement(By.xpath("//input[@id='conditions_to_approve[terms-and-conditions]']")).click();
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[contains(text(), 'Place order')]")).getCssValue("background-color")).asHex().toLowerCase(), "#24b9d7");
        driver.findElement(By.xpath("//button[contains(text(), 'Place order')]")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//h3[@class='h1 card-title']")).getText().toLowerCase(), "your order is confirmed");
        Assert.assertEquals(driver.findElement(By.xpath("//h3[@class='h1 card-title']/following-sibling::p")).getText().toLowerCase(), "an email has been sent to the " +email1+ " address.");
        double total = Double.parseDouble(driver.findElement(By.xpath("//tbody/tr[3]/td[2]")).getText().replaceAll("[^0-9.]", ""));
        Assert.assertEquals(total, totalPriceProduct);

    }
    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(By.xpath(locator)));
    }

    @Test
    public void TC_08_Filter_Detail() throws InterruptedException {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#framelive")));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(), 'All products')]")).click();
        //Categories

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
