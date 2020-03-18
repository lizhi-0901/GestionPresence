package metier;
// Generated Mar 18, 2020 11:52:06 AM by Hibernate Tools 4.3.1



/**
 * Affecter generated by hbm2java
 */
public class Affecter  implements java.io.Serializable {


     private AffecterId id;
     private Creaneau creaneau;
     private Personnel personnel;
     private String etatPresence;
     
     private byte[] signatureEnseignant;

    public Affecter() {
    }

    public Affecter(AffecterId id, Creaneau creaneau, Personnel personnel, String etatPresence, byte[] signatureEnseignant) {
       this.id = id;
       this.creaneau = creaneau;
       this.personnel = personnel;
       this.etatPresence = etatPresence;
       this.signatureEnseignant = signatureEnseignant;
    }
   
    public AffecterId getId() {
        return this.id;
    }
    
    public void setId(AffecterId id) {
        this.id = id;
    }
    public Creaneau getCreaneau() {
        return this.creaneau;
    }
    
    public void setCreaneau(Creaneau creaneau) {
        this.creaneau = creaneau;
    }
    public Personnel getPersonnel() {
        return this.personnel;
    }
    
    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
    public String getEtatPresence() {
        return this.etatPresence;
    }
    
    public void setEtatPresence(String etatPresence) {
        this.etatPresence = etatPresence;
    }
    public byte[] getSignatureEnseignant() {
        return this.signatureEnseignant;
    }
    
    public void setSignatureEnseignant(byte[] signatureEnseignant) {
        this.signatureEnseignant = signatureEnseignant;
    }




}


