package metier;
// Generated Mar 18, 2020 6:08:12 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Fonction generated by hbm2java
 */
public class Fonction  implements java.io.Serializable {


     private String fonction;
     private Set personnels = new HashSet(0);

    public Fonction() {
    }

	
    public Fonction(String fonction) {
        this.fonction = fonction;
    }
    public Fonction(String fonction, Set personnels) {
       this.fonction = fonction;
       this.personnels = personnels;
    }
   
    public String getFonction() {
        return this.fonction;
    }
    
    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    public Set getPersonnels() {
        return this.personnels;
    }
    
    public void setPersonnels(Set personnels) {
        this.personnels = personnels;
    }




}


