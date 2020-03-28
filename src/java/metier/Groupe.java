package metier;
// Generated Mar 18, 2020 6:08:12 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Groupe generated by hbm2java
 */
public class Groupe  implements java.io.Serializable {


     private String idGroupe;
     private String nomGroupe;
     private String typeFormation;
     private String typeGroupe;
     private String dateDeb;
     private String dateFin;
     private Set creneaus = new HashSet(0);
     private Set personnels = new HashSet(0);

    public Groupe() {
    }

	

  
    public Groupe(String idGroupe, String typeFormation, String typeGroupe, String dateDeb, String dateFin) {
        this.idGroupe = idGroupe;
        this.typeFormation = typeFormation;

        this.typeGroupe=typeGroupe;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }

  
    public Groupe(String idGroupe, String typeFormation, String typeGroupe, String dateDeb, String dateFin, Set creneaus, Set personnels) {
       this.idGroupe = idGroupe;
       this.typeFormation = typeFormation;

       this.typeGroupe=typeGroupe;
       this.dateDeb = dateDeb;
       this.dateFin = dateFin;
       this.creneaus = creneaus;
       this.personnels = personnels;
    }
    


    public Groupe(String idGroupe,String typeFormation,String typeGroupe){
        this.idGroupe = idGroupe;
        this.typeFormation = typeFormation;

        this.typeGroupe=typeGroupe;
    }
   
    public String getIdGroupe() {
        return this.idGroupe;
    }
    
    public void setIdGroupe(String idGroupe) {
        this.idGroupe = idGroupe;
    }
    public String getNomGroupe() {
        return this.typeFormation;
    }
    
    public void setNomGroupe(String typeFormation) {
        this.typeFormation = typeFormation;
    }
    public String getDateDeb() {
        return this.dateDeb;
    }
    
    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }
    public String getDateFin() {
        return this.dateFin;
    }
    
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
    public Set getCreneaus() {
        return this.creneaus;
    }
    
    public void setCreneaus(Set creneaus) {
        this.creneaus = creneaus;
    }
    public Set getPersonnels() {
        return this.personnels;
    }
    
    public void setPersonnels(Set personnels) {
        this.personnels = personnels;
    }

    public void setTypeGroupe(String typeGroupe) {
        this.typeGroupe = typeGroupe;
    }


    public String getTypeGroupe() {
        return typeGroupe;
    }







}