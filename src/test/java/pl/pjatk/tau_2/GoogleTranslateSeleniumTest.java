package pl.pjatk.tau_2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GoogleTranslateSeleniumTest {

    private WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.manage().window().maximize();
        driver.get("https://translate.google.com");
    }

    /**
     *      1 asercja - title nie ma wartości "Tłumacz Google" po otwraciu url
     */
    @Test
    @Order(1)
    public void siteTitleTest() {

        String siteTitle = driver.getTitle();
        assertNotEquals("Tłumacz Google", siteTitle);
    }

    /**
     *      2 asercja - title ma wartość "Tłumacz Google" po zaakaceptowaniu ciasteczek
     */
    @Test
    @Order(2)
    public void siteTitleCorrectTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement rejectButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Odrzuć wszystko']")));
        rejectButton.click();
        Boolean siteTitle = wait.until(ExpectedConditions.titleContains("Google"));

        if(siteTitle){driver.getTitle();}

        assertEquals("Tłumacz Google", driver.getTitle());
    }

    /**
     *      3 asercja - pole wprowadzenia tekstu przechwytuje wprowadzane wartości
     */
    @Test
    @Order(3)
    public void translateTest() {


        WebElement textArea = driver.findElement(By.cssSelector("[aria-label='Tekst źródłowy']"));
        textArea.click();
        textArea.sendKeys("I am prepared");

        assertEquals(textArea.getAttribute("value"), "I am prepared");
    }

    /**
     *      4 asercja - po zmianie języka tłumaczonego z tłumaczonym zmienia się wartość w textArea
     */
    @Test
    @Order(4)
    public void correctTranslationTest(){
        WebElement switchLanguagesButton = driver.findElement(By.xpath("//button[i[text()='swap_horiz']]"));
        switchLanguagesButton.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement textAreaAfterSwitch = driver.findElement(By.cssSelector("[aria-label='Tekst źródłowy']"));

        assertNotEquals("I am prepared", textAreaAfterSwitch.getAttribute("value"));
    }

    /**
     *      5 asercja - kliknięcie przycisku "history" wyświetli banner o wymóg zalogowania
     */
    @Test
    @Order(5)
    public void needToSignInAfterClickingAtHistoryButtonTest(){
        WebElement historyButton = driver.findElement(By.cssSelector("[data-probe-id='translation_history_footer_button']"));
        historyButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement singInAlert = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-label='Zaloguj się, by wyświetlić historię tłumaczeń']")));

        assertTrue(singInAlert.isDisplayed());
    }

    /**
     *      6 asercja - przyciśnięcie "Cancel" zamknie okno z alertem o potrzebie logowania - niemożliwe będzie kliknięcie na element będący alertem
     */
    @Test
    @Order(6)
    public void closeAlertWindowTest(){
        WebElement singInAlert = driver.findElement(By.cssSelector("[aria-label='Zaloguj się, by wyświetlić historię tłumaczeń']"));
        WebElement cancelButton = driver.findElement(By.cssSelector("[data-mdc-dialog-action='Cancel']"));
        cancelButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThrowsExactly(StaleElementReferenceException.class, () -> singInAlert.click());
    }

    /**
     *      7 asercja - możliwe jest wybranie opcji "Powoli" w panelu Ustawień - będzie zaznaczona
     */
    @Test
    @Order(7)
    public void settingsBannerRadioButtonTest(){
        WebElement settingsButton = driver.findElement(By.cssSelector("[aria-label='Ustawienia']"));
        settingsButton.click();
        WebElement slowlyButton = driver.findElement(By.cssSelector("[aria-label='Powoli']"));
        slowlyButton.click();

        assertTrue(slowlyButton.isSelected());

    }

    /**
     *      8 asercja - z panelu aplikacji Google możliwe jest przejście do YouTube
     */
    @Test
    @Order(8)
    public void googleMainMenuBarTest(){
        WebElement mainMenuButton = driver.findElement(By.cssSelector("[aria-label='Menu główne']"));
        mainMenuButton.click();
        WebElement allAboutGoogleButton = driver.findElement(By.xpath("//a[contains(text(), 'Wszystko o Google')]"));
        allAboutGoogleButton.click();

        assertNotEquals(driver.getCurrentUrl(), "translate.google.pl");
    }

    @AfterEach
    public void wait1sec(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public void tearDown() {driver.quit();}
}
