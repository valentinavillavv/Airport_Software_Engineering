/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package aeroporto;

// ----------- << imports@AAAAAAF+T15Y5g0gipM= >>
// ----------- >>

// ----------- << class.annotations@AAAAAAF+T15Y5g0gipM= >>
// ----------- >>
public abstract class Aereo{
	//-----------------------------------INIZIALIZZAZIONE VARIABILI---------------------------------------------------
	
    // ----------- << attribute.annotations@AAAAAAF+T15Y5g0h2o4= >>
    // ----------- >>
    private String ID;

    // ----------- << attribute.annotations@AAAAAAF+T15Y5g0iX1I= >>
    // ----------- >>
    private boolean needCarburante;
    
    // ----------- << attribute.annotations@AAAAAAF+T15Y5g0iX1I= >>
    // ----------- >>
    private boolean assegnato;

    // ----------- << attribute.annotations@AAAAAAF+T15Y5AzcEjM= >>
    // ----------- >>
    private Volo Volo;

    // ----------- << attribute.annotations@AAAAAAF+T15Y5g0oZ5w= >>
    // ----------- >>
    private Gate Gate;

    //-----------------------------------METODI SET/GET---------------------------------------------------
    
    public String getID() {
        return ID;
    }

    public boolean isNeedCarburante() {
        return needCarburante;
    }
    
    public boolean isAssegnato() {
        return assegnato;
    }

    public Volo getVolo() {
        return Volo;
    }

    public Gate getGate() {
        return Gate;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNeedCarburante(boolean needCarburante) {
        this.needCarburante = needCarburante;
    }
    
    public void setAssegnato(boolean assegnato) {
        this.assegnato = assegnato;
    }


    public void setVolo(Volo Volo) {
        this.Volo = Volo;
    }

    public void setGate(Gate Gate) {
        this.Gate = Gate;
    }

    // ----------- << method.annotations@AAAAAAF+T15Y5g0lJc8= >>
    // ----------- >>
    public void modificaCarburante() {
    // ----------- << method.body@AAAAAAF+T15Y5g0lJc8= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAF+T15Y5g0lJc8= >>
    // ----------- >>
    public void modificaAssegnato() {
    // ----------- << method.body@AAAAAAF+T15Y5g0lJc8= >>
    // ----------- >>
    }
// ----------- << class.extras@AAAAAAF+T15Y5g0gipM= >>
// ----------- >>

	protected abstract int getNPasseggeriMax();

	protected abstract int getNKg();
}