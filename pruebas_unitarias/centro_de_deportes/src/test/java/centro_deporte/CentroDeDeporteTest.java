package centro_deporte;

import centro_deporte.manager.ICentroDeporte;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CentroDeDeporteTest {

    private ICentroDeporte centroDeporte;

    @BeforeAll
    static void iniciarPruebas() {
        System.out.println("Inicio de las pruebas");
    }

    @BeforeEach
    void prepararPrueba() {
        centroDeporte = ICentroDeporte.create();
    }

    @Test
    void crearCentroDeporte() {
        ICentroDeporte centro = ICentroDeporte.create();

        assertNotNull(centro);
    }

    @Test
    void obtenerDeportes() {
        assertFalse(centroDeporte.obtenerDeportes().isEmpty());
    }

    @Test
    void obtenerDeportesPorLetra() {
        assertFalse(centroDeporte.obtenerDeportes("B").isEmpty());
    }

    @Test
    void crearDeporte() {
        centroDeporte.crearDeporte("Handball");

        assertTrue(centroDeporte.obtenerDeportes().contains("Handball[DEPORTE]"));
    }

    @Test
    void modificarDeporte() {
        centroDeporte.modificarDeporte("Atletismo", "Atletismo Modificado");

        assertTrue(centroDeporte.obtenerDeportes().contains("Atletismo Modificado"));
    }

    @Test
    void eliminarDeporte() {
        centroDeporte.eliminarDeporte("Atletismo");

        assertFalse(centroDeporte.obtenerDeportes().contains("Atletismo"));
    }

    @AfterEach
    void finalizarPrueba() {
        centroDeporte = null;
    }

    @AfterAll
    static void finalizarPruebas() {
        System.out.println("Fin de las pruebas");
    }
}