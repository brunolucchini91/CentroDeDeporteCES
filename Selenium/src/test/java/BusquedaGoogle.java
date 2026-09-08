import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.time.Duration;

public class BusquedaGoogle {

    private static WebDriver driver;
    private static String sitioWeb;
    private static String busqueda;

    @BeforeAll
    static void beforeAll() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        sitioWeb = "https://www.google.com/";
        busqueda = "Manzana verde";

    }

        @Test
        void buscarEnGoogle() {
            driver.get(sitioWeb);
            driver.findElement(By.name("q")).sendKeys(busqueda);
            String valorBusqueda = driver.findElement(By.name("q")).getAttribute("value");

            Assertions.assertThat(valorBusqueda)
                    .as("El texto ingresado no es el esperado")
                    .isEqualTo(busqueda);
            driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        }
    @AfterAll
    static void afterAll() {
        sleep(3);
        driver.quit();
    }
    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    String valorBusqueda = driver.findElement(By.name("q")).getAttribute("value");

}