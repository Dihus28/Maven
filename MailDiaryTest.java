package work5Diary;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MailDiaryTest {
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
    void sendMailTest() throws InterruptedException {
        login();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navbar_user-collapse\"]/ul[1]/li[6]/a")));
        driver.findElement(By.xpath("//*[@id='navbar_user-collapse']/ul[1]/li[6]/a")).click();
        driver.findElement(By.xpath("//*[@id='page_content']/div/p/a")).click();
        driver.findElement(By.xpath("//*[@id='touser']")).sendKeys("Dihus");
        driver.findElement(By.xpath("//*[@id=\"umailform-title\"]")).sendKeys("test");
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"message_ifr\"]")));
        driver.findElement(By.xpath("//*[@id=\"tinymce\"]")).sendKeys("test");
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//*[@id=\"page_content\"]/div[3]/button")).click();
        Thread.sleep(5000);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    void login() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"navbar_user-collapse\"]/ul[1]/li[1]/a")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"loginform-username\"]")));
        driver.findElement(By.xpath("//*[@id=\"loginform-username\"]")).sendKeys("Dihus");
        driver.findElement(By.xpath("//*[@id=\"loginform-password\"]")).sendKeys("qwerty123");
        Thread.sleep(20000);
        driver.findElement(By.xpath("//*[@id='login_btn']")).click();
    }
}