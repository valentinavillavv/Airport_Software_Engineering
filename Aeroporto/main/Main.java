package main;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;

import aeroporto.Sistema;
import eccezioni.DataException;


@SuppressWarnings("resource")
public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//------------------------------------------------------------------------------------------INIZIALIZZAZIONE VARIABILI---
		
		//Creazione del Sistema
		Sistema ilSistema = Sistema.getSistema();
		Scanner in = new Scanner(System.in);
		
		String sel;
		//---------------------------------------------------------------------------------------------MENU' SELEZIONE ACCESSO---
		do {
			System.out.println("Accedi:");
			System.out.println("1 -> Accesso cliente \n"
					+ "2 -> Accesso dipendente \n" + "0 -> Spegni Programma");
			sel=in.nextLine();
			switch (sel) {
			case "1":
			{ AccessoClienti(); break;}
			case "2": 
			{ AccessoDipendenti(); break;}
			case "0": 
			{ System.out.println("Arrivederci!"); break;}
			default: 
			{ System.out.println("Valore non valido"); break;}
			}
		}while(!sel.equals("0"));
	}
	
	//------------------------------------------------------------------------------------------------------ACCESSO DIPENDENTI---
	private static void AccessoDipendenti() {
		Scanner in = new Scanner(System.in);
		String password;
		System.out.println("Accesso dipendenti \n" + "Password: ");
		System.out.println("Digita 'chiudi' per tornare al menu' precedente");
		for(int i=0;i<3;i++) { //Viene richiesta la password per un massimo di 3 volte
			password=in.nextLine();
			if (password.equals("Man")|password.equals("Amm")|password.equals("TDC"))
			{
				switch (password) {
				case "Man": //Accesso dipendente manutenzione
				{ FunzionalitaMan(); break;}
				case "Amm": //Accesso dipendente amministrazione
				{ FunzionalitaAmm(); break;}
				case "TDC": //Accesso dipendente torre di controllo
				{ FunzionalitaTDC(); break;}
				default:
				{ System.out.println("Errore di sistema"); break;}
				} 
				break;
			} else if (password.equals("chiudi")) { 
				break;
			} else { 
				System.out.println("Password non valida");
			}
		}
	}

	//---------------------------------------------------------------------------------------------------------ACCESSO CLIENTI---
	private static void AccessoClienti() {
		Scanner s = new Scanner(System.in);
		String sel;
		String IDVolo;
		Sistema ilSistema = Sistema.getSistema();
		
		do{//Mostra le funzionalità del cliente fino al logout 
			System.out.println("Menu' Clienti");
			System.out.println("Seleziona: \n->1 Ricerca voli \n" + "->2 Fai una prenotazione \n"  
				+ "->3 Inserisci la certificazione Cov-19 \n"  + "->4 Cancella una prenotazione \n"
				+ "->0 Logout");
		sel = s.nextLine();
		switch (sel) {
		case "1"://Ricerca voli data una destinazione
		{
			System.out.println("Inserisci una destinazione:");
			Scanner in2 = new Scanner(System.in);
			String dest=in2.nextLine();
			ilSistema.ricercaVoli(dest);
			break;
		}
		case "2"://Fare una nuova prenotazione
		{	
			if(ilSistema.thereIsVolo()) {
				Scanner in2 = new Scanner(System.in);
				Scanner in = new Scanner(System.in);
		    	System.out.println("Inserisci nome: ");
		    	String nome=in.nextLine();
		    	System.out.println("Inserisci cognome: ");
		    	String cognome=in.nextLine();
		    	System.out.println("Inserisci numero Carta d'identità: ");
		    	String nCartaIdentita=in.nextLine();
		    	System.out.println("Quanti bagagli vuoi imbarcare?");
		    	int nBagagli=in.nextInt();
		    	System.out.println("Acquisto biglietto con priorità? (si/no)");
		    	boolean rispInserita=false;
		    	String risp;
		    	boolean priorita = false;
		    	do {
		    		risp=in.nextLine();
		    		if(risp.equals("si")) {
		    			priorita=true;
		    			rispInserita=true;
		    		} else if(risp.equals("no")) {
		    			priorita=false;
		    			rispInserita=true;
		    		} else {
						System.out.println("Risposta non valida, digitare si o no");
					}
				} while(!rispInserita);
		    	
		    	boolean dataValida;
		    	String nascita;
		    	Date d = null;
		    	do {//Inserimento data nascita con controllo
		    		dataValida = true;
		    		System.out.println("Inserisci la data di nascita nel formato gg/mm/aaaa: ");
		        	nascita = in.nextLine();
		        	try {
		            	//converte la stringa della data in un oggetto di classe Date
		            	DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
		            	// imposta che i calcoli di conversione della data siano rigorosi
		            	formatoData.setLenient(false);
		            	d = formatoData.parse(nascita);
		            		
		            	if (d.compareTo(Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY).getTime()) > 0) {
		            		dataValida = false;
		            		throw new DataException("La data di nascita non può essere dopo la data odierna");
		            	}
		        	} catch (java.text.ParseException e) {
		        		dataValida = false;
		        		System.out.println("Formato data non valido!");
		        	} catch (DataException e) {
		        		e.printStackTrace();
		        	}
		    	} while(!dataValida);
		    	
				System.out.println("Inserisci ID del volo che vuoi prenotare: ");
				ilSistema.stampaVoli("Linea");
				IDVolo=in2.nextLine();
				ilSistema.prenotaVolo(IDVolo,nome,cognome,nCartaIdentita,nBagagli,priorita, d);
			} else {
				System.out.println("Al momento non ci sono voli\n");
			}
			break;
		}
		case "3"://Inserimento della certificazione
		{	
			if(!ilSistema.thereIsPrenotazione()) {
				System.out.println("Non ci sono ancora prenotazioni nel sistema\n");
			}
			else {
	    		System.out.println("Inserisci ID del biglietto a cui vuoi aggiungere la certificazione: ");
				Scanner in = new Scanner(System.in);
				String biglietto=in.nextLine();
				System.out.println("Inserisci numero carta d'identità a cui è intestato il biglietto: ");
				String nCarta=in.nextLine();
				ilSistema.modificaPrenotazione(biglietto, nCarta);
	    	}
	    	break;
		}
		case "4"://Cancellazione prenotazione
		{	
	    	if(!ilSistema.thereIsPrenotazione()) {
				System.out.println("Non ci sono ancora prenotazioni nel sistema\n");
			}
	    	else {
	    		System.out.println("Inserisci ID del biglietto per cancellare la tua prenotazione: ");
				Scanner in = new Scanner(System.in);
				String biglietto=in.nextLine();
				ilSistema.cancellaPrenotazione(biglietto);
	    	}
			break;
		}
		case "0": //Logout
		{
			break;
		}
		default:
		{
			System.out.println("Valore non valido");
			break;
		}	
		}
	} while (!sel.equals("0"));
		
	}
	
	//---------------------------------------------------------------------------------------------------MENU' AMMINISTRAZIONE---
	private static void FunzionalitaAmm() {
		
		Scanner s = new Scanner(System.in);
		String sel;
		Sistema ilSistema = Sistema.getSistema();
		
		do{//Mostra le funzionalità da amministratore fino al logout 
			System.out.println("Menu' Amministrazione");
			System.out.println("Seleziona: \n->1 Crea un nuovo volo \n" + "->2 Modifica destinazione di un volo esistente \n"
					+ "->0 Logout");
			sel = s.nextLine();
			switch (sel) {
			case "1"://Crea un nuovo volo
			{
				if (ilSistema.thereIsAereo("nonAssegnato")) {
					Scanner in = new Scanner(System.in);
			    	System.out.println("Inserisci destinazione: ");
			    	String destinazione = in.nextLine();
			    	boolean dataValida;
			    	String partenza;
			    	String arrivo;
			    	Date ds = null, da = null;
			    	do {//Controllo validità data di partenza
			    		dataValida = true;
			    		System.out.println("Inserisci la data di partenza nel formato gg/mm/aaaa: ");
			        	partenza = in.nextLine();
			        	try {
			            	//converte la stringa della data in un oggetto di classe Date
			            	DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
			            	// imposta che i calcoli di conversione della data siano rigorosi
			            	formatoData.setLenient(false);
			            	ds = formatoData.parse(partenza);
			            		
			            	if (ds.compareTo(Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY).getTime()) < 0) {
			            		dataValida = false;
			            		throw new DataException("Non si può inserire una data che precede la data di oggi!");
			            	}
			            } catch (java.text.ParseException e) {
			        		dataValida = false;
			        		System.out.println("Formato data non valido!");
			        	} catch (DataException e) {
			        		e.printStackTrace();
			        	}
			    	} while(!dataValida);
			    	
			    	do {//Controllo validità data di arrivo
			    		System.out.println("Inserisci la data di arrivo nel formato gg/mm/aaaa: ");
			    		arrivo = in.nextLine();
			           	dataValida = true;
			    		try {
			            	//converte la stringa della data in un oggetto di classe Date
			            	DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
			            	formatoData.setLenient(false);
			           		da = formatoData.parse(arrivo);
			           		if (ds.compareTo(da) > 0) {
			           			dataValida = false;
			           			throw new DataException("La partenza non può avvenire dopo l'arrivo!");
			           		}
			        	} catch (java.text.ParseException e) {
			        		dataValida = false;
			        		System.out.println("Formato data non valido!");
			        	} catch (DataException e) {
			        		e.printStackTrace();
			        	}
			    	} while(!dataValida);
			    	
			    	//Inserimento nome e cognome dei piloti
			    	System.out.println("Inserisci il nome del comandante");
			    	String nCom=in.nextLine();
			    	System.out.println("Inserisci il cognome del comandante");
			    	String cCom=in.nextLine();
			    	System.out.println("Inserisci il nome del copilota");
			    	String nCop=in.nextLine();
			    	System.out.println("Inserisci il cognome del copilota");
			    	String cCop=in.nextLine();
			    	//Inserimento idAereo
					System.out.println("Inserisci l'ID dell'aereo da utilizzare: ");
					ilSistema.stampaAerei("nonAssegnato");
					String idAereo=s.nextLine();
			    
					ilSistema.nuovoVolo(idAereo, destinazione, ds,da,nCom,cCom,nCop,cCop);
				}
				else {
					System.out.println("Non ci sono aerei disponibili\n");
				}
				break;
				
			}
			case "2"://Modifica destinazione di un volo esistente
			{
		    	if(!ilSistema.thereIsVolo()) {
		    		System.out.println("Al momento non ci sono voli\n");
		    	} else {
					Scanner in2 = new Scanner(System.in);
					System.out.println("Inserisci nuova destinazione: ");
					String dest=in2.nextLine();
					System.out.println("Inserisci ID del volo di cui cambiare la destinazione: ");
					ilSistema.stampaVoli("all");
					String IDVolo=in2.nextLine();
					ilSistema.modificaVolo(IDVolo,dest);
		    	}
		    	break;
			}
			case "0": 
			{
				break;
			}
			default:
			{
				System.out.println("Valore non valido");
				break;
			}	
			}
		} while (!sel.equals("0"));
	}

	//---------------------------------------------------------------------------------------------------------------MENU' TDC---
	private static void FunzionalitaTDC() {
		System.out.println("Menu' Torre di Controllo");
		Sistema ilSistema = Sistema.getSistema();
		Scanner in = new Scanner(System.in);
		String sel;
		String IDAereo;
		int kgMax,nPass;
		
		do {//Mostra le funzionalità TDC fino al logout
			System.out.println("Seleziona: \n" + "->1 Fai atterrare un aereo \n" + "->2 Fai decollare un aereo\n"
					+ "->0 Logout");
			sel = in.nextLine();
			switch (sel) {
			case "1"://Si fa atterrare un aereo
			{
				System.out.println("Seleziona che tipo di aereo sta arrivando:");
		    	System.out.println("Linea ->1 \nCargo ->2 ");
		    	String lOrC=in.nextLine();//Variabile che ci dice se sta atterrando un aeeo di linea o cargo
		    	if(lOrC.equals("1")) {
		    		System.out.println("Inserisci il numero massimo di passeggeri");
		    		nPass=in.nextInt();
		    		ilSistema.atterraggio(lOrC,0,nPass);
		    	}
		    	else if(lOrC.equals("2")) {
		    		kgMax=in.nextInt();
		    		ilSistema.atterraggio(lOrC,kgMax,0);
		    	}
		    	else {
		    		System.out.println("Selezione invalida! \n");
		    	}
				break;
			}
			case "2"://Si fa decollare un aereo
			{
		    	if(!ilSistema.thereIsAereo("")) { //Controllo che ci siano aerei da far decollare
		    		System.out.println("Nell'aeroporto non ci sono aerei da far decollare\n");
		    	} else {
		    		
		    		ilSistema.stampaAerei("Tutti");
		    		System.out.println("\nInserisci l'ID dell' aereo da far decollare");
					Scanner in2 = new Scanner(System.in);
					IDAereo=in2.nextLine();
					ilSistema.decolloAereo(IDAereo);
		    	}
		    	break;
			}
			case "0"://logout
			{
				break;
			}
			default:
				System.out.println("Valore non valido");
				break;
			}
		} while (!sel.equals("0"));
	
		
	}
	
	//------------------------------------------------------------------------------------------------------MENU' MANUTENZIONE---
	private static void FunzionalitaMan() {
		System.out.println("Menu' Manutenzione");
		Sistema ilSistema = Sistema.getSistema();
		Scanner in = new Scanner(System.in);
		String sel;
		String IDAereo;
		do {//Mostra le funzionalità di manutenzione fino al logout
			System.out.println("Inserisci: \n" + "->1 Fai rifornimento ad un aereo\n" +"->0 Logout");
			sel = in.nextLine();
			switch (sel) {
			case "1"://Fai rifornimento ad un aereo dato il suo ID
			{
				Scanner in2 = new Scanner(System.in);		
				if (ilSistema.thereIsAereo("needCarburante")) {
					ilSistema.stampaAerei("needCarburante");
					System.out.println("Inserisci ID dell'aereo a cui si vuole fare carburante: ");
					IDAereo=in2.nextLine();
					ilSistema.addCarburante(IDAereo);
					
				} else {
					System.out.println("Non ci sono aerei che necessitano di carburante\n");
				}
				break;
			}
			case "0"://logout
			{
				break;
			}
			default:
				System.out.println("Valore non valido");
				break;
			}
		} while (!sel.equals("0"));
	
	}
}