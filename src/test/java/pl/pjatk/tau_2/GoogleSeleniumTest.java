package pl.pjatk.tau_2;

import java.time.Duration;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GoogleSeleniumTest {

    private WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }

    /**
     *      1 asercja - tytuł strony to "Google"
     */
    @Test
    @Order(1)
    public void siteTitleTest() {

        String siteTitle = driver.getTitle();
        assertEquals("Google", siteTitle);
        System.out.println(siteTitle);

    }

    /**
     *      2 asercja - wartość tekstowa rejectButton to "Odrzuć wszystko"
     */
    @Test
    @Order(2)
    public void rejectButtonTextTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement rejectButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Odrzuć wszystko']")));
        assertEquals("Odrzuć wszystko", rejectButton.getText());
        System.out.println(rejectButton.getText());
    }

    /**
     *      3 asercja - nie prawda że rejectButton jest widoczny
     */
    @Test
    @Order(3)
    public void rejectButtonNotVisibleTest() {
        WebElement rejectButton = driver.findElement(By.xpath("//div[text()='Odrzuć wszystko']"));
        rejectButton.click();
        assertFalse(rejectButton.isDisplayed());
        System.out.println(rejectButton.isDisplayed());
    }

    /**
     *      4 asercja - Searchbox jest widoczny
     */
    @Test
    @Order(4)
    public void searchBoxVisibilityTest() {
        WebElement searchBox = driver.findElement(By.name("q"));
        assertTrue(searchBox.isDisplayed());
        System.out.println(searchBox.isDisplayed());
    }

    /**
     *      5 asercja - wartość wpisanu w polu searchBox = "askew"
     */
    @Test
    @Order(5)
    public void searchBoxValueTest() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("askew");
        assertEquals("askew", searchBox.getAttribute("value"));
        System.out.println(searchBox.getAttribute("value"));
    }

    /**
     *      6 asercja - adres strony różni się od początkowego (www.google.pl) po wykonaniu submit na wartości pola wyszukiwania
     */
    @Test
    @Order(6)
    public void differentUrlAddressTest() {
        WebElement searchBox = driver.findElement(By.name("q"));
        String currentUrl = driver.getCurrentUrl();
        searchBox.submit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotEquals(driver.getCurrentUrl(), currentUrl);
        System.out.println(driver.getCurrentUrl());
    }


    /**
     *      7 asercja - wywołanie przycisku odtwarzania zmienia jego stan/klasę
     */
    @Test
    @Order(7)
    public void triggerClassChangeClassTest(){
        WebElement triggerButton = driver.findElement(By.cssSelector("[aria-label='Odsłuchaj tekst źródłowy']"));
        String classBeforeTrigger = triggerButton.getAttribute("class");
        triggerButton.click();
        assertNotEquals(classBeforeTrigger, triggerButton.getAttribute("class"));
    }

    /**
     *      8 asercja - zawartość searchbox jest wyczyszczona po wcisnięciu przycisku
     */
    @Test
    @Order(8)
    public void screenKeyboardTest() {
        WebElement clearButton = driver.findElement(By.cssSelector("[aria-label='Wyczyść']"));
        WebElement searchBox = driver.findElement(By.name("q"));
        clearButton.click();
        assertEquals("", searchBox.getAttribute("value"));
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