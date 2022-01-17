package work5Diary;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class DiaryTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String DIARY_BASE_URL = "https://diary.ru/";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(DIARY_BASE_URL);
    }

    @Test
    void newEntryTest() throws InterruptedException {
        login();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='writeThisDiary']")));
        driver.findElement(By.xpath("//*[@id='writeThisDiary']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"postTitle\"]")));
        driver.findElement(By.xpath("//*[@id=\"postTitle\"]")).sendKeys("Test");
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"message_ifr\"]")));
        driver.findElement(By.xpath("//*[@id=\"tinymce\"]")).sendKeys("Test");
        driver.switchTo().defaultContent();
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rewrite\"]")));
//        actions.moveToElement(driver.findElement(By.xpath("//*[@id='rewrite']")))
//                .build()
//                .perform();
        driver.findElement(By.xpath("//*[@id='rewrite']")).click();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    void login() throws InterruptedException {
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbar_user-collapse\"]/ul[1]/li[1]/a\"")));
        driver.findElement(By.xpath("//*[@id=\"navbar_user-collapse\"]/ul[1]/li[1]/a")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"loginform-username\"]")));
        driver.findElement(By.xpath("//*[@id=\"loginform-username\"]")).sendKeys("Dihus");
        driver.findElement(By.xpath("//*[@id=\"loginform-password\"]")).sendKeys("qwerty123");
        Thread.sleep(20000);
        driver.findElement(By.xpath("//*[@id='login_btn']")).click();
    }
}
