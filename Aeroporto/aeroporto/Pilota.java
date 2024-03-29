/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package aeroporto;

// ----------- << imports@AAAAAAF+T15Y5AzrBtQ= >>
// ----------- >>

// ----------- << class.annotations@AAAAAAF+T15Y5AzrBtQ= >>
// ----------- >>
public class Pilota {
	//-----------------------------------INIZIALIZZAZIONE VARIABILI---------------------------------------------------------
	
    // ----------- << attribute.annotations@AAAAAAF+T15Y5Azs6xw= >>
    // ----------- >>
    private boolean comandante;
    
    static Integer num = 0;
    
    // ----------- << attribute.annotations@AAAAAAF+T15Y5AztMOQ= >>
    // ----------- >>
    private String ID;
    
    // ----------- << attribute.annotations@AAAAAAF+T15Y5AztMOQ= >>
    // ----------- >>
    private String nome;
    
    // ----------- << attribute.annotations@AAAAAAF+T15Y5AztMOQ= >>
    // ----------- >>
    private String cognome;

    // ----------- << attribute.annotations@AAAAAAF+T15Y4wzZ8kE= >>
    // ----------- >>
    private Volo Volo;
    
    //-----------------------------------COSTRUTTORE-------------------------------------------------------------------
	public Pilota(boolean valore,String nome,String cognome) {
		setIsPilota(valore);
    	this.nome = nome;
    	this.cognome = cognome;
    	setID(this.nome + "-" + this.cognome + "-" + num.toString());
    	num++;
    }
    
	//-----------------------------------METODI SET/GET----------------------------------------------------------------
    public boolean isComandante() {
        return comandante;
    }

    public String getID() {
        return ID;
    }

    Volo getVolo() {
        return Volo;
    }

    void setIsPilota(boolean comandante) {
        this.comandante = comandante;
    }

    void setID(String ID) {
        this.ID = ID;
    }

    void setVolo(Volo Volo) {
        this.Volo = Volo;
    }

// ----------- << class.extras@AAAAAAF+T15Y5AzrBtQ= >>
// ----------- >>
}