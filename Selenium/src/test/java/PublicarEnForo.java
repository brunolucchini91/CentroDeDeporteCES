import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class PublicarEnForo {

    private static WebDriver driver;
    private static String sitioWeb;
    private static String usuario;
    private static String contrasenia;
    private static String busquedaForo;

    @BeforeAll
    static void beforeAll() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        sitioWeb = "https://capacitacion.ces.com.uy/";
        usuario = System.getenv("USUARIO_CES");
        contrasenia = System.getenv("CONTRASEÑA_CES");
        busquedaForo = "Bienvenida";
    }

    @Test
    void buscarForoBienvenida() {
        driver.get(sitioWeb);

        // Acceder
        driver.findElement(By.cssSelector("span.login a")).click();

        // Iniciar sesión
        driver.findElement(By.id("username")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(contrasenia);
        driver.findElement(By.id("loginbtn")).click();

        // Ingresar al curso
        driver.findElement(By.cssSelector(
                "a[href='https://capacitacion.ces.com.uy/course/view.php?id=1128']"
        )).click();

        // Ingresar a Foros desde Actividades
        driver.findElement(By.cssSelector(
                "a[href='https://capacitacion.ces.com.uy/mod/forum/index.php?id=1128']"
        )).click();

        // Buscar "Bienvenida"
        driver.findElement(By.cssSelector("input[name='search']")).sendKeys(busquedaForo);
        driver.findElement(By.cssSelector("input[name='search']")).sendKeys(Keys.ENTER);

        // Obtener los títulos encontrados
        List<WebElement> titulos = driver.findElements(
                By.cssSelector("h4.font-weight-bold")
        );

        // Verificar que exista un título relacionado con "Bienvenida"
        boolean existeBienvenida = false;

        for (WebElement titulo : titulos) {
            if (titulo.getText().contains("Bienvenid")) {
                existeBienvenida = true;
            }
        }

        Assertions.assertThat(existeBienvenida)
                .as("No se encontró un foro con título Bienvenida")
                .isTrue();
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