/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package aeroporto;

import java.util.*;

import eccezioni.GateNonDisponibileException;

// ----------- << imports@AAAAAAF+T15Y5g0da/c= >>
// ----------- >>

// ----------- << class.annotations@AAAAAAF+T15Y5g0da/c= >>
// ----------- >>
public class Linea extends Aereo {
	//-----------------------------------INIZIALIZZAZIONE VARIABILI---------------------------------------------------------------
    
	// ----------- << attribute.annotations@AAAAAAF+T15Y5g0f68s= >>
    // ----------- >
    private int nPasseggeriMax;
    
    private static Integer numero=0;
    
    //-----------------------------------COSTRUTTORE------------------------------------------------------------------
	public Linea(int nPass) throws GateNonDisponibileException{
    	 
    	boolean gateDisp=false;//Bool che controlla se ci sono gate disponibili
    	Iterator<Gate> gates =Gate.getListaGate().iterator();//Iterator sulla lista dei gate
    	
    	while (gates.hasNext()) {//Controllo se ci sono gate disponibili
    		Gate a=gates.next();
    		if(!a.isIsOccupied()) {
    			gateDisp=true;
    		}		
    	}
    	
    	if(gateDisp){//Se ci sono gate disponibili l'aereo pu� atterrare quindi inserisco i suoi valori
    		gates =Gate.getListaGate().iterator();//Ricomincio a scorrere la lista dall'inizio
    		this.setID("L-" + numero.toString());
        	numero++;
        	setNeedCarburante(true);
        	setAssegnato(false);
    		while(gates.hasNext()) {//Assegna l'aereo al primo gate disponibile
        		Gate a=gates.next();
        		if(!a.isIsOccupied()) {
        			a.setIsOccupied(true);
        			System.out.println("Aereo assegnato al gate "+ a.getID());
        			break;
        		}
        	}
    		setNPasseggeriMax(nPass);
    	} else {//Se non ci sono gate disponibili lancio un eccezione
			throw new GateNonDisponibileException();
		}
    	
	}     
    //-----------------------------------METODI SET/GET------------------------------------------------------------------
	@Override
	protected int getNKg() {
		return 0;
	}
	
    protected int getNPasseggeriMax() {
        return nPasseggeriMax;
    }

    protected void setNPasseggeriMax(int nPasseggeriMax) {
        this.nPasseggeriMax = nPasseggeriMax;
    }
	

// ----------- << class.extras@AAAAAAF+T15Y5g0da/c= >>
// ----------- >>
}