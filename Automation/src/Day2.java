import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;

public class Day2 {
    WebDriver driver; //khai báo biến
    //Wait tường minh: trạng thái cụ thể cho element
    // Visible / invisible / Presence/ number/ clickable
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30)); //chơ cho element thỏa mãn điều kiện nào đó
        //wait ngầm định : ko rõ ràng cho 1 trạng thái cụ thể nào hết
        //cho việc tìm element - findelements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // chờ element được tìm thấy
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
    }
//
    @Test
    public void TC_01_() throws InterruptedException {
        // 1 - Click vào 1 thẻ để cho nó xổ các item trong dropdown ra
        driver.findElement(By.xpath("//span[@id='number-button']")).click();
        Thread.sleep(3000);
        // 2.1 - Nó sẽ xổ ra chứa hết các item
        // 2.2 - Nó sẽ xổ ra nhưng chỉ chứa 1 phần và đang load thêm
        // Chờ cho nó xổ hết ra các item trong dropdown

        //Xuất hiện đầy đủ trong html
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']//div")));
        List<WebElement> allItems = driver.findElements(By.xpath("//ul[@id='number-menu']//div"));
        //allItems đang lưu trữ 19 item bên trong
        for (WebElement element : allItems) {
            String textItem = element.getText();
            if(textItem.equals("8")) {
                element.click();
                break;
            }
        }

        // 3.1 - Nếu như item cần chọn nó hiển thị thì click vào
        // 3.2 - Nếu như item cần chọn nằm bên dưới 1 số trường hợp cần scroll xuống để hiển thị lên rồi mới chon
        // 4 - Trước khi click cần kiểm tra nếu như text của item bằng với item cần chọn thì click vào
    }
    @Test
    public void TC_02_() {

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
