package Controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmministratoreControllerTest {

    @Test
    void testDateInvertiteLanciaEccezione() {
        // ARRANGE: Setup dell'input cronologicamente invertito
        String dataInizio = "31/12/2024";
        String dataFine = "01/01/2024";

        // ACT & ASSERT: Si verifica che l'eccezione venga lanciata e si intercetta il messaggio
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            AmministratoreController.monitoraSistema(dataInizio, dataFine);
        });

        // ASSERT: Verifica finale del messaggio d'errore
        assertEquals("Intervallo date non valido", exception.getMessage());
    }

    @Test
    void testDataFormatoErratoLanciaEccezione() {
        // ARRANGE: Input con caratteri alfabetici non consentiti
        String dataInizio = "15/ab/2024";
        String dataFine = "31/12/2024";

        // ACT & ASSERT
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            AmministratoreController.monitoraSistema(dataInizio, dataFine);
        });

        // ASSERT: Asserzione per verificare che il sistema notifichi il formato errato
        assertTrue(exception.getMessage().contains("Formato data non valido"));
    }
}