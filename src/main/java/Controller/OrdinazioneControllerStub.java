package Controller;

import Database.Session;
import Entity.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class OrdinazioneControllerStub {

    public static boolean salvaOrdinazione(ArrayList<String> piatti, ArrayList<Integer> quantita, String via,
            String civico, String cap, String citta, Long ristoranteId) {

        try {
            Cliente cliente = (Cliente) Session.getInstance().getUtenteLoggato();
            if (cliente == null) {
                System.out.println("Errore: nessun cliente loggato");
                return false;
            }

            Indirizzo indirizzoConsegna = new Indirizzo(via, civico, Integer.parseInt(cap), citta);
            GestoreOrdini go = new GestoreOrdini();

            // Costruisce il carrello
            List<RigaCarrelloVirtuale> carrello = new ArrayList<>();
            for (int i = 0; i < piatti.size(); i++) {
                if (quantita.get(i) > 0) {
                    Piatto piatto = go.cercaOCreaPiatto(piatti.get(i));
                    if (piatto == null) {
                        System.out.println("Errore: piatto '" + piatti.get(i) + "' non trovato");
                        return false;
                    }
                    RigaCarrelloVirtuale riga = new RigaCarrelloVirtuale(piatto, quantita.get(i));
                    carrello.add(riga);
                }
            }

            // Delega tutto al GestoreOrdini
            Ordine ordine = go.registraOrdine(cliente, indirizzoConsegna, carrello, ristoranteId);
            if (ordine != null) {
                System.out.println("Ordinazione salvata con successo");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Errore durante il salvataggio dell'ordinazione: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean controllaMeglioIndirizzo(String via, String civico, String cap, String citta) {

        if (via.length() > 40) {
            JOptionPane.showMessageDialog(null, "La via non può superare i 40 caratteri", "Errore",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValid(via)) {
            JOptionPane.showMessageDialog(null, "La via contiene caratteri non alfanumerici", "Errore",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (civico.length() > 5) {
            JOptionPane.showMessageDialog(null, "Il civico non può superare i 5 caratteri", "Errore",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValid(civico)) {
            JOptionPane.showMessageDialog(null, "Il civico contiene caratteri non numerici", "Errore",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (cap.length() != 5) {
            JOptionPane.showMessageDialog(null, "Il CAP deve essere di 5 caratteri", "Errore",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValid(cap)) {
            JOptionPane.showMessageDialog(null, "Il CAP contiene caratteri non numerici", "Errore",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (citta.length() > 20) {
            JOptionPane.showMessageDialog(null, "La città non può superare i 20 caratteri", "Errore",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isValid(String s) {
        return s.matches("[\\p{L}0-9/\\s.,'-]+");
    }

}
