package bd;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import metier.Groupe;
import metier.Matiere;
import metier.Personnel;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lizhiwang
 */
public class bd {
    
     static Session session = HibernateUtil.getSessionFactory().getCurrentSession();
     static Transaction t = session.beginTransaction();
     SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
    
     /**
      * c'est une function qui prends en entree comme identifiant 
      * et retourne mot de pass
      * @param identifiant
      * @return 
      */
     
    public static Personnel connection(String identifiant){
          Personnel p = (Personnel) session.get(Personnel.class, identifiant);
          return p;
    }
    
    /**
     * 
     * @param libelleFormation
     * @return afficher la liste de formation qui est relie avec ce formation
     */
    public Set<Matiere> getcours(String libelleFormation){
          Set<Matiere> setcours =new HashSet<>();
          return setcours;
    }
    
    /**
     * 
     * @param idgroupe
     * @return retourne la liste etudiant qui est dans ce group TD
     */
    public List<Personnel> getEtudiant(String  idGroupe){
           List<Personnel> listEtudiants=session.createSQLQuery("select p.nom,p.prenom "+
                                                            "from Personnel p, Appartenir a "+
                                                            "where p.idPersonne=a.idPersonne "+
                                                            "and a.idGroupe=" +"'"+idGroupe+"'").list();
        
        return listEtudiants;
    }
    
    
    /**
     * c'est une function qui retourne une groupe qui prend entree comme date heuredebut et matiere
     * @param date
     * @param heureDeb
     * @param nommatiere
     * @return 
     */
    /**
     * c'est une function qui retourne une groupe qui prend entree comme date heuredebut et matiere
     * @param date
     * @param heureDeb
     * @param nommatiere
     * @return 
     */
    public Groupe getGroupe(SimpleDateFormat date, int heureDeb, String nommatiere,String salle){
        Groupe g = new Groupe();
        
        String sql = "SELECT * FROM Creneau C, Groupe G, AffecterGroupe A "
                + "WHERE C.id_Creneau = A.id_Creneau and A. and C.idGroupe =  A.idGroupe and salle =:salle  "
                + "and heureDeb =:heure and libelleMatiere =:nommatiere ";
        
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Groupe.class);
        
        query.setParameter("heure", heureDeb);
        query.setParameter("nommatiere", nommatiere);
        query.setParameter("salle", salle);
        List results = query.list();  
        g =(Groupe)results.get(1);
        
        return g;
    }
    
    
   
}
