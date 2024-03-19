package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import org.junit.Test;


import aeroporto.*;

public class TestCodice {


	@Test
	public void testUtilizzo() {
		
		//Inizializzazione variabili
		DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
		PrintStream standardOut = System.out;
		ByteArrayOutputStream outputStreamCaptor=new ByteArrayOutputStream();
		Sistema ilSistema=Sistema.getSistema();//Creo ilSistema
		
		//ATTERRAGGIO AEREO
		ilSistema.atterraggio("1", 0, 1);//Faccio atterrare un aereo di linea
		ilSistema.decolloAereo("L-0");
		assertEquals(true, ilSistema.thereIsAereo(""));//L'aereo non è decollato
		Aereo a=ilSistema.getListaAerei().iterator().next();
		
		//RIFORNIMENTO AD AEREO
		assertEquals(true, a.isNeedCarburante());//Prima del rifornimento il flag needCarburante è a true
		ilSistema.addCarburante("L-0");
		assertEquals(false, a.isNeedCarburante());//Dopo è a false
		
		//CREAZIONE VOLO
		try {
			ilSistema.nuovoVolo("IDNonValido", "", formatoData.parse("1/1/2050"), formatoData.parse("1/1/2050"),"","","","");
		} catch (ParseException e) {
			e.printStackTrace();
		}//Creo un volo con ID aereo inesistente
		assertEquals(false, ilSistema.thereIsVolo());//Non è stato creato nessun volo 
		try {
			ilSistema.nuovoVolo("L-0", "Milano", formatoData.parse("1/1/2050"), formatoData.parse("1/1/2050"), "", "", "", "");
		} catch (ParseException e) {
			e.printStackTrace();
		}//Creo un volo per milano il 1/1/2050
		//Controllo che i parametri di volo siano quelli inseriti nel test
		Volo v=ilSistema.getListaVoli().iterator().next();
		assertEquals("V-0",v.getID());
		assertEquals("Milano", v.getDestinazione());
		try {
			assertEquals(formatoData.parse("1/1/2050"), v.getDataPartenza());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			assertEquals(formatoData.parse("1/1/2050"), v.getDataArrivo());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//MODIFICA DESTINAZIONE
		ilSistema.modificaVolo("V-0", "Roma");		
		assertEquals("Roma", v.getDestinazione());		
		
		//RICERCA VOLI
		System.setOut(new PrintStream(outputStreamCaptor));
		ilSistema.ricercaVoli("Roma");//Metodo ricerca voli su Roma
		ilSistema.ricercaVoli("Milano");//Metodo ricerca voli su Milano
		//Controllo che l'output sia quello desiderato prima si stampa il volo per Roma e poi il messaggio non ci sono
		//voli per questa destinazione (per il casp di ricerca per Milano)
		assertEquals("I voli diretti a Roma sono:\r\nV-0                 01/01/50\r\nNon ci sono voli per questa destinazione", outputStreamCaptor.toString().trim());
		System.setOut(standardOut);
		
		//NUOVA PRENOTAZIONE
		try {
			ilSistema.prenotaVolo("V-0", "Mario", "Rossi", "MR00", 2, true, formatoData.parse("1/1/2000"));
		} catch (ParseException e) {
			e.printStackTrace();
		}//Creo una prenotazione
		Prenotazione p=ilSistema.getListaPrenotazioni().iterator().next();
		try {
			ilSistema.prenotaVolo("V-0", "", "", "", 2, true, formatoData.parse("1/1/2000"));
		} catch (ParseException e) {
			e.printStackTrace();
		}//Il massimo numero di passeggeri sull'aereo è 1 quindi non sarà possibile creare un nuovo passeggero
		assertEquals(1,v.getNPasseggeri());
		//Controllo che i parametri siano inseriti correttamente nell'oggetto Prenotazione
		assertEquals("Mario", p.getNome());
		assertEquals("Rossi", p.getCognome());
		assertEquals("MR00", p.getNCartaIdentita());
		assertEquals(2, p.getNBagagli());
		assertEquals(true, p.isPriorita());
		assertEquals(false, p.isCertificazioneCov19());
		try {
			assertEquals(formatoData.parse("1/1/2000"), p.getDataNascita());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertEquals(v, p.getVolo());
		
		//INSERIMENTO CERTIFICAZIONE
		ilSistema.modificaPrenotazione("P-0", "MR00");
		assertEquals(true, p.isCertificazioneCov19());
		
		//CANCELLAZIONE PRENOTAZIONE
		assertEquals(true, ilSistema.thereIsPrenotazione());
		ilSistema.cancellaPrenotazione("P-0");
		assertEquals(false,ilSistema.thereIsPrenotazione());
		
		//DECOLLO AEREO
		ilSistema.decolloAereo("L-0");
		assertEquals(false, ilSistema.thereIsAereo(""));
		assertEquals(false, ilSistema.thereIsPrenotazione());
		assertEquals(false, ilSistema.thereIsVolo());
		
		//TEST MASSIMO ATTERRAGGI
		ilSistema.atterraggio("1", 0, 0);
		ilSistema.atterraggio("2", 0, 0);
		ilSistema.atterraggio("1", 0, 0);
		ilSistema.atterraggio("1", 0, 0);
		ilSistema.atterraggio("1", 0, 0);
		ilSistema.atterraggio("1", 0, 0);
		ilSistema.atterraggio("1", 0, 0);
		}
	
	
}
