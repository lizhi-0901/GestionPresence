package metier;
// Generated Mar 27, 2020 12:45:14 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Personnel generated by hbm2java
 */
public class Personnel  implements java.io.Serializable {


     private String idPersonne;
     private String motDePasse;
     private String nom;
     private String prenom;
     private String dateNaissance;
     private String type;
     private String photo;
     private String adresseMail;
     private String numTel;
     private String prenomUsage;
     private String adresse;
     private String entreprise;
     private String contactTel;
     private String contactMail;
     private String typeformation;
     private Set fonctions = new HashSet(0);
     private Set groupes = new HashSet(0);
     private Set matieres = new HashSet(0);
     private Set justifiers = new HashSet(0);
     private Set affecters = new HashSet(0);
     private Set formations = new HashSet(0);

    public Personnel() {
    }

	
    public Personnel(String idPersonne, String motDePasse, String nom, String prenom, String type, String adresseMail) {
        this.idPersonne = idPersonne;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.adresseMail = adresseMail;
    }
    public Personnel(String idPersonne, String motDePasse, String nom, String prenom, String dateNaissance, String type, String photo, String adresseMail, String numTel, String prenomUsage, String adresse, String entreprise, String contactTel, String contactMail, String typeformation, Set fonctions, Set groupes, Set matieres, Set justifiers, Set affecters, Set formations) {
       this.idPersonne = idPersonne;
       this.motDePasse = motDePasse;
       this.nom = nom;
       this.prenom = prenom;
       this.dateNaissance = dateNaissance;
       this.type = type;
       this.photo = photo;
       this.adresseMail = adresseMail;
       this.numTel = numTel;
       this.prenomUsage = prenomUsage;
       this.adresse = adresse;
       this.entreprise = entreprise;
       this.contactTel = contactTel;
       this.contactMail = contactMail;
       this.typeformation = typeformation;
       this.fonctions = fonctions;
       this.groupes = groupes;
       this.matieres = matieres;
       this.justifiers = justifiers;
       this.affecters = affecters;
       this.formations = formations;
    }
   
    public String getIdPersonne() {
        return this.idPersonne;
    }
    
    public void setIdPersonne(String idPersonne) {
        this.idPersonne = idPersonne;
    }
    public String getMotDePasse() {
        return this.motDePasse;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getDateNaissance() {
        return this.dateNaissance;
    }
    
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getPhoto() {
        return this.photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getAdresseMail() {
        return this.adresseMail;
    }
    
    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }
    public String getNumTel() {
        return this.numTel;
    }
    
    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
    public String getPrenomUsage() {
        return this.prenomUsage;
    }
    
    public void setPrenomUsage(String prenomUsage) {
        this.prenomUsage = prenomUsage;
    }
    public String getAdresse() {
        return this.adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getEntreprise() {
        return this.entreprise;
    }
    
    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }
    public String getContactTel() {
        return this.contactTel;
    }
    
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }
    public String getContactMail() {
        return this.contactMail;
    }
    
    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }
    public String getTypeformation() {
        return this.typeformation;
    }
    
    public void setTypeformation(String typeformation) {
        this.typeformation = typeformation;
    }
    public Set getFonctions() {
        return this.fonctions;
    }
    
    public void setFonctions(Set fonctions) {
        this.fonctions = fonctions;
    }
    public Set getGroupes() {
        return this.groupes;
    }
    
    public void setGroupes(Set groupes) {
        this.groupes = groupes;
    }
    public Set getMatieres() {
        return this.matieres;
    }
    
    public void setMatieres(Set matieres) {
        this.matieres = matieres;
    }
    public Set getJustifiers() {
        return this.justifiers;
    }
    
    public void setJustifiers(Set justifiers) {
        this.justifiers = justifiers;
    }
    public Set getAffecters() {
        return this.affecters;
    }
    
    public void setAffecters(Set affecters) {
        this.affecters = affecters;
    }
    public Set getFormations() {
        return this.formations;
    }
    
    public void setFormations(Set formations) {
        this.formations = formations;
    }




}


