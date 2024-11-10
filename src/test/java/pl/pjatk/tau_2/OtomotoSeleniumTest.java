package pl.pjatk.tau_2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OtomotoSeleniumTest {

    private WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().window().maximize();
        driver.get("https://www.otomoto.pl");
    }


    /**
     * 1 asercja - strona załadowała się - zgadza się title
     */
    @Test
    @Order(1)
    public void siteTitleTest() {
        String title = driver.getTitle();
        System.out.println(title);
        assertTrue(title.equals("OTOMOTO - nowe i używane samochody i motocykle oraz części samochodowe. Ogłoszenia motoryzacyjne."));
    }

    /**
     * 2 asercja - akceptuje ciasteczka, powiadomienie o nich znika
     */
    @Test
    @Order(2)
    public void cookiesTest() {
        WebElement cookieBanner = driver.findElement(By.cssSelector("[id='onetrust-banner-sdk']"));
        WebElement acceptCookies = driver.findElement(By.cssSelector(("[id='onetrust-accept-btn-handler']")));
        acceptCookies.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(!cookieBanner.isDisplayed());
    }

    /**
     * 3 asercja - po kliknięciu zaloguj otwiera się formularz logowania
     */
    @Test
    @Order(3)
    public void loginSiteTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-touch-point='login_page']")));
        loginButton.click();

        assertTrue(!driver.getCurrentUrl().equals("https://www.otomoto.pl"));
    }

    /**
     * 4 asercja - w polu Email podanie niepoprawnego adresu wyświetli komunikat o niezgodności
     */
    @Test
    @Order(4)
    public void wrongEmailFormatTest() {
        WebElement usernameForm = driver.findElement(By.cssSelector("[id='username']"));
        usernameForm.sendKeys("test@test");
        assertTrue(driver.findElement(By.xpath("//div[text()='To nie wygląda jak adres mailowy...']")).isDisplayed());
    }

    /**
     * 5 asercja - w polu Hasło podanie zbyt krótkiej wartości wyświetli komunikat o niezgodności
     */
    @Test
    @Order(5)
    public void wrongPasswordTest() {
        WebElement passwordForm = driver.findElement(By.cssSelector("[id='password']"));
        passwordForm.sendKeys("test");
        assertTrue(driver.findElement(By.xpath("//div[text()='Masz pewność co do hasła? Jest zbyt krótkie']")).isDisplayed());

    }

    /**
     * 6 asercja - podanie nieistniejących danych logowania wyświetli banner informujący o braku konta dla wprowadzonych danych
     */
    @Test
    @Order(6)
    public void wrongCredentialsTest() {
        WebElement usernameForm = driver.findElement(By.cssSelector("[id='username']"));

        WebElement passwordForm = driver.findElement(By.cssSelector("[id='password']"));
        WebElement signInButton = driver.findElement(By.cssSelector("[data-testid='login-submit-button']"));

        usernameForm.sendKeys(".test");
        passwordForm.sendKeys("te");
        signInButton.click();

        WebElement errorBanner = driver.findElement(By.cssSelector("[data-testid='error-banner']"));
        assertTrue(errorBanner.isDisplayed());
    }

    /**
     *      7 asercja - niemożliwe jest wybranie modelu bez wybrania marki
     */
    @Test
    @Order(7)
    public void cantFindOnlyByCarModelTest(){
        driver.get("https://www.otomoto.pl");
        WebElement carModelSelector = driver.findElement(By.cssSelector("[aria-label='Model pojazdu']"));
        WebElement expandedMenuBar = driver.findElement(By.cssSelector("[aria-expanded='false']"));
        carModelSelector.click();

        assertFalse( expandedMenuBar.equals("true"));
    }


    /**
     *      8 asercja - podanie istniejących marki i modelu oraz wciśnięcie submit zmieni adres Url
     */
    @Test
    @Order(8)
    public void findCarByBrandAndModelTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement carBrandSelector = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-label='Marka pojazdu']")));
        carBrandSelector.click();

        WebElement menuBar = driver.findElement(By.cssSelector("[role='menubar']"));
        menuBar.sendKeys("toyota");
        WebElement carBrand = driver.findElement(By.cssSelector("[name='Toyota']"));
        carBrand.click();

        WebElement carModelSelector = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-label='Model pojazdu']")));
        carModelSelector.click();

        WebElement carModelInput = driver.findElement(By.cssSelector("div[aria-label='Model pojazdu'] input"));
        carModelInput.sendKeys("supra");
        WebElement carModel = driver.findElement(By.cssSelector("[name='Supra']"));
        carModel.click();

        WebElement submit = driver.findElement(By.cssSelector("[type='submit']"));
        submit.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(driver.getCurrentUrl().equals("https://www.otomoto.pl"));
    }

    @AfterEach
    public void wait1sec() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
