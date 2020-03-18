package metier;
// Generated Mar 18, 2020 6:08:12 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Formation generated by hbm2java
 */
public class Formation  implements java.io.Serializable {


     private String idFormation;
     private String libelleFormation;
     private String diplomeFormation;
     private String rythme;
     private Set matieres = new HashSet(0);
     private Set personnels = new HashSet(0);

    public Formation() {
    }

	
    public Formation(String idFormation, String libelleFormation, String diplomeFormation, String rythme) {
        this.idFormation = idFormation;
        this.libelleFormation = libelleFormation;
        this.diplomeFormation = diplomeFormation;
        this.rythme = rythme;
    }
    public Formation(String idFormation, String libelleFormation, String diplomeFormation, String rythme, Set matieres, Set personnels) {
       this.idFormation = idFormation;
       this.libelleFormation = libelleFormation;
       this.diplomeFormation = diplomeFormation;
       this.rythme = rythme;
       this.matieres = matieres;
       this.personnels = personnels;
    }
   
    public String getIdFormation() {
        return this.idFormation;
    }
    
    public void setIdFormation(String idFormation) {
        this.idFormation = idFormation;
    }
    public String getLibelleFormation() {
        return this.libelleFormation;
    }
    
    public void setLibelleFormation(String libelleFormation) {
        this.libelleFormation = libelleFormation;
    }
    public String getDiplomeFormation() {
        return this.diplomeFormation;
    }
    
    public void setDiplomeFormation(String diplomeFormation) {
        this.diplomeFormation = diplomeFormation;
    }
    public String getRythme() {
        return this.rythme;
    }
    
    public void setRythme(String rythme) {
        this.rythme = rythme;
    }
    public Set getMatieres() {
        return this.matieres;
    }
    
    public void setMatieres(Set matieres) {
        this.matieres = matieres;
    }
    public Set getPersonnels() {
        return this.personnels;
    }
    
    public void setPersonnels(Set personnels) {
        this.personnels = personnels;
    }




}


