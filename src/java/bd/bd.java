package bd;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import metier.Matiere;
import metier.Personnel;
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
     
    public static String connection(String identifiant){
          Personnel p1 = (Personnel) session.get(Personnel.class, "21613265");
          System.out.println(p1);
          
          t.commit();
          return "mdp";
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
    public Set<Personnel> getEtudiant(String idgroupe){
           Set<Personnel> setetudiant =new HashSet<>();
           return setetudiant;
    }
    
   
    
   
}
