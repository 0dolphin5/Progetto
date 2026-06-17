package Controller;

import Database.GestorePersistenza;
import Entity.*;

public class RegisterController {

    public static int register(String nome, String cognome, String via, String civico, String cap, String citta, String email, String ruolo){

        int esito=-1;
        GestoreUtenti gu = new GestoreUtenti();

        // Controllo se l'email è già in uso
        if (!gu.cercaUtentePerEmail(email).isEmpty()) {
            return -2; // Codice per Email già in uso
        }

        int capInt = Integer.parseInt(cap);
        if (ruolo.equalsIgnoreCase("Cliente")) {
            Cliente c = new Cliente(nome, cognome, email, ruolo, via, civico, capInt, citta);
            if (gu.registraUtente(c)) {
                esito = 1; // 1 indica successo
            }
        } else if (ruolo.equalsIgnoreCase("Ristoratore")) {
            Ristoratore r = new Ristoratore(nome, cognome, email, ruolo, via, civico, capInt, citta);
            if (gu.registraUtente(r)) {
                esito = 1;
            }
        } else if (ruolo.equalsIgnoreCase("Amministratore")) {
            // Il costruttore di Amministratore ha i parametri cap e citta invertiti rispetto a Cliente/Ristoratore
            Amministratore a = new Amministratore(nome, cognome, email, ruolo, via, civico, citta, capInt);
            if (gu.registraUtente(a)) {
                esito = 1;
            }
        }

        return esito;
    }


   /* #################QUESTO LO USAVO PRUMA QUANDO L'UTENTE IMMETTEVA L'INDIRIZZO IN UN UNICA STRINGA######################

    private static Indirizzo StringToIndirizzo(String indirizzoConsegna) {
        String[] parts = indirizzoConsegna.split(" ");
        String via = parts[0].trim();
        String civico = parts[1].trim();
        String cap = parts[2].trim();
        String citta = parts[3].trim();
        return new Indirizzo(via, civico, Integer.parseInt(cap),citta);
    }*/
}
