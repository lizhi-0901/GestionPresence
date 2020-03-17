package bd;


import java.text.ParseException;
import org.hibernate.Transaction;
import org.hibernate.Session;
import metier.Utilisateur;
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
    
     /**
      * 
      * @param nom
      * @param mdp
      * @return 
      */
     
    public static int connection(String nom,String mdp){
          Utilisateur u1= (Utilisateur)session.get(Utilisateur.class, 1);
          System.out.println(u1);
          
          t.commit();
          return 0;
    }
    
    
    
   
}
