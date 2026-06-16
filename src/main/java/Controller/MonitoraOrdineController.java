package Controller;

import Database.Session;
import Entity.Cliente;
import Entity.GestoreOrdini;
import Entity.Ordine;
import Entity.Utente;

import java.util.List;

public class MonitoraOrdineController {

    public static List<Ordine> richiediListaOrdiniClienteLoggato() {

        Utente utente = Session.getInstance().getUtenteLoggato();

        if (!(utente instanceof Cliente cliente)) {
            return null;
        }

        Long idCliente = cliente.getId();

        GestoreOrdini gestoreOrdini = new GestoreOrdini();

        return gestoreOrdini.cercaOrdiniDelCliente(idCliente);
    }

    public static String richiediStatoOrdine(Long idOrdine) {

        Utente utente = Session.getInstance().getUtenteLoggato();

        if (!(utente instanceof Cliente cliente)) {
            return "Nessun cliente autenticato";
        }

        if (idOrdine == null) {
            return "Ordine non valido";
        }

        Long idCliente = cliente.getId();

        GestoreOrdini gestoreOrdini = new GestoreOrdini();

        String stato = gestoreOrdini.cercaStatoOrdine(idCliente, idOrdine);

        if (stato == null) {
            return "Stato non riconosciuto";
        }

        return stato;
    }
}