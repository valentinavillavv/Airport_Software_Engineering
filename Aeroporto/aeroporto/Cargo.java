/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package aeroporto;

import java.util.*;

import eccezioni.GateNonDisponibileException;

// ----------- << imports@AAAAAAF+T15Y5g0at/4= >>
// ----------- >>

// ----------- << class.annotations@AAAAAAF+T15Y5g0at/4= >>
// ----------- >>
public class Cargo extends Aereo {
	//-----------------------------------INIZIALIZZAZIONE VARIABILI---------------------------------------------------
	
    // ----------- << attribute.annotations@AAAAAAF+T15Y5g0c4NU= >>
    // ----------- >>
    private int nKgMax;
    
    private static Integer numero=0;
    
    //-----------------------------------COSTRUTTORE------------------------------------------------------------------
	public Cargo(int kgMax) throws GateNonDisponibileException{
    	boolean gateDisp=false;//Bool che controlla se ci sono gate disponibili
    	Iterator<Gate> gates =Gate.getListaGate().iterator();//Iterator sulla lista dei gate
    	while (gates.hasNext()) {//Controllo se ci sono gate disponibili
    		Gate a=gates.next();
    		if(!a.isIsOccupied()) {
    			gateDisp=true;
    		}
    	}
    	
    	if(gateDisp) {//Se ci sono gate disponibili l'aereo pu� atterrare quindi inserisco i suoi valori 
    		gates=Gate.getListaGate().iterator();//Ricomincio a scorrere la lista dall'inizio
    		this.setID("C-" + numero.toString());
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
    		this.nKgMax=kgMax;
    	} else {//Se non ci sono gate disponibili lancio un eccezione
			throw new GateNonDisponibileException();
		}
    	
    }
    
    //-----------------------------------METODI SET/GET------------------------------------------------------------------
    
    protected int getNKgMax() {
        return nKgMax;
    }
   

    protected void setNKgMax(int nKgMax) {
        this.nKgMax = nKgMax;
    }
    
	@Override
    protected int getNKg() {
		return this.nKgMax;
	}

	@Override
	protected int getNPasseggeriMax() {
		return 0;
	}

// ----------- << class.extras@AAAAAAF+T15Y5g0at/4= >>
// ----------- >>
}