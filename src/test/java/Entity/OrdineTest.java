package Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrdineTest {

    private Ordine ordine;

    // Viene eseguito in automatico prima di ogni @Test
    @BeforeEach
    void setUp() {
        Cliente clienteFittizio = new Cliente();
        Indirizzo indirizzoFittizio = new Indirizzo();
        ordine = new Ordine("Creato", indirizzoFittizio, clienteFittizio);
    }

    @Test
    void testCalcoloTotaleCarrello() {
        // ARRANGE: Predisposizione degli oggetti e degli input
        Piatto pizza = new Piatto();
        pizza.setId(1L);
        pizza.setNomePiatto("Pizza Margherita");
        pizza.setPrezzo(6.50f);

        Piatto bibita = new Piatto();
        bibita.setId(2L);
        bibita.setNomePiatto("Coca Cola");
        bibita.setPrezzo(2.50f);

        ordine.addPiattoAlCarrello(pizza, 2);  // 13.00
        ordine.addPiattoAlCarrello(bibita, 1); // 2.50

        // ACT: Invocazione del System Under Test
        float totaleEffettivo = ordine.getTotale();

        // ASSERT: Verifica del risultato (Oracolo: 15.50)
        assertEquals(15.50f, totaleEffettivo, 0.01, "Il totale non corrisponde alla somma dei prezzi");
    }

    @Test
    void shouldReturnZero_WhenCartIsEmpty() {
        // ARRANGE: L'ordine è già stato inizializzato vuoto dal metodo setUp()

        // ACT
        float totaleEffettivo = ordine.getTotale();

        // ASSERT
        assertEquals(0.0f, totaleEffettivo, 0.01, "Un carrello vuoto deve avere totale 0");
    }
}