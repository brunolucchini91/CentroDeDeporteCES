import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BusquedaWikipedia {

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

        sitioWeb = "https://es.wikipedia.org/";
        busqueda = "Hola mundo";
    }

    @Test
    void buscarHolaMundo() {
        driver.get(sitioWeb);

        driver.findElement(By.cssSelector("input[accesskey='f']")).sendKeys(busqueda);

        String valorBusqueda = driver.findElement(By.cssSelector("input[accesskey='f']")).getAttribute("value");

        Assertions.assertThat(valorBusqueda)
                .as("El texto ingresado no es el esperado")
                .isEqualTo(busqueda);

        driver.findElement(By.cssSelector("input[accesskey='f']")).sendKeys(Keys.ENTER);

        String tituloArticulo = driver.findElement(By.cssSelector("h1 .mw-page-title-main")).getText();

        Assertions.assertThat(tituloArticulo)
                .as("El artículo encontrado no es el esperado")
                .isEqualTo(busqueda);
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
}