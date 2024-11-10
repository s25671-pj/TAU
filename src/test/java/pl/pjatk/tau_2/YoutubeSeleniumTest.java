package pl.pjatk.tau_2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class YoutubeSeleniumTest {

    private WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().window().maximize();
        driver.get("https://www.youtube.com");
    }

    /**
     *      1 asercja - wartość tekstowa rejectButton to "Odrzuć wszystko"
     */
    @Test
    @Order(1)
    public void rejectButtonTextTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement rejectButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Odrzuć wszystko']")));
        assertEquals("Odrzuć wszystko", rejectButton.getText());
    }

    /**
     *      2 asercja - nie prawda że rejectButton jest widoczny
     */
    @Test
    @Order(2)
    public void rejectButtonNotVisibleTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement rejectButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-label='Nie wyrażaj zgody na wykorzystywanie plików cookie i innych danych do opisanych celów']")));
        rejectButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(rejectButton.isDisplayed());
    }

    /**
     *      3 asercja - wartość wpisanu w polu searchBox = "never gonna give you up"
     */
    @Test
    @Order(3)
    public void searchBoxValueTest() {
        WebElement searchBox = driver.findElement(By.name("search_query"));
        searchBox.sendKeys("never gonna give you up no ads");
        assertEquals("never gonna give you up no ads", searchBox.getAttribute("value"));
    }

    /**
     *      4 asercja - adres strony różni się od początkowej (www.google.pl) po wykonaniu submit na wartości pola wyszukiwania
     */
    @Test
    @Order(4)
    public void differentUrlAddressTest() {
        WebElement searchBox = driver.findElement(By.name("search_query"));
        String currentUrl = driver.getCurrentUrl();
        searchBox.submit();
        assertNotEquals(driver.getCurrentUrl(), currentUrl);
    }

    /**
     *      5 asercja - odtwarzacz wideo jest widoczny
     */
    @Test
    @Order(5)
    public void mediaSiteTest(){
        WebElement vidTitle = driver.findElement(By.cssSelector("[title='Rick Roll (Different link + no ads)']"));
        vidTitle.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(driver.findElement(By.cssSelector("[id='player']")).isDisplayed());
    }

    /**
     *      6 asercja - po kliknięciu pouzy zmienia się wartość title dla button o aria-keyshortcuts='k'
     */
    @Test
    @Order(6)
    public void screenKeyboardTest() {
        WebElement pauseButton = driver.findElement(By.cssSelector("[aria-keyshortcuts='k']"));
        String pauseTitle= pauseButton.getAttribute("title");
        pauseButton.click();
        WebElement pauseButton2 = driver.findElement(By.cssSelector("[aria-keyshortcuts='k']"));
        String playTitle= pauseButton2.getAttribute("title");
        assertNotEquals(playTitle , pauseTitle);

    }

    /**
     *      7 asercja - pole wyszukiwania jest wyczyszczone, pojawia się klawiatura ekranowa
     */
    @Test
    @Order(7)
    public void clearAndKeyboardTest() {
        WebElement clearButton = driver.findElement(By.cssSelector("[aria-label='Wyczyść szukane hasło']"));
        WebElement searchBox = driver.findElement(By.name("search_query"));
        clearButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement screenKeyboard = driver.findElement(By.cssSelector("[id='gs_ok50']"));
        screenKeyboard.click();
        assertTrue(searchBox.getAttribute("value").isEmpty());
    }

    /**
     *      8 asercja - z klawiatury ekranowej do searchquery wypisuje się hasło "rickrolled" :) - klawiatura ekranowa działa
     */
    @Test
    @Order(8)
    public void secretCodeValueInSearchBoxTest(){
        WebElement searchBox = driver.findElement(By.name("search_query"));
        String[] secretCode = {"82", "73", "67", "75", "82", "79", "76", "69", "68"};
        Map<String, WebElement> codeMap = new HashMap<>();
        for (String code : secretCode) {
            String codePrefix = "K" + code;
            WebElement codeElement = driver.findElement(By.id(codePrefix));
            codeMap.put(codePrefix, codeElement);
            if (codePrefix.equals("K76")) {
                codeElement.click();
            }
            codeElement.click();
        }
        WebElement playButton = driver.findElement(By.cssSelector("[aria-keyshortcuts='k']"));
        playButton.click();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("rickrolled", searchBox.getAttribute("value"));
    }


    @AfterEach
    public void wait1sec(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public void tearDown() {driver.quit();}
}
