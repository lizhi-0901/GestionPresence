package bd;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import metier.Creneau;
import metier.Groupe;
import metier.Matiere;
import metier.Personnel;
import org.hibernate.Query;
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
     static Transaction transaction = null;
     SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
    
     /**
      * c'est une function qui prends en entree comme identifiant 
      * et retourne mot de pass
      * @param identifiant
      * @return 
      */
     
    public static Personnel connection(String identifiant){
        if(transaction==null){
            transaction=session.beginTransaction();
        } 
        Personnel p = (Personnel) session.get(Personnel.class, identifiant);
        return p;
    }
    
     /**
     * 
     * @param libelleFormation
     * @return afficher la liste de formation qui est relie avec ce formation
     */
    public static List<Matiere> getMatieres(String libelleFormation){
        if(transaction==null){
            transaction=session.beginTransaction();
        } 
          List<Matiere> listMatieres =session.createSQLQuery("select  m.libelleMatiere,m.idFormation "+ 
                                                             "from Matiere m, Formation f "+
                                                             "where f.idFormation =m.idFormation "+ 
                                                             "and f.libelleFormation=" +"'"+libelleFormation+"'").list();
          return listMatieres;
    }
    
    /**
     * @param idGroupe
     * @return retourne la liste etudiant qui est dans ce group TD
     */
    public static List<Personnel> getEtudiants(String idGroupe){
        if(transaction==null){
            transaction=session.beginTransaction();
        } 

        Query query=session.createQuery("select new metier.Personnel(p.idPersonne,p.nom,p.prenom,p.photo) "+
                                        "from Personnel p inner join p.groupes pg, Groupe g "+
                                        "where g.idGroupe=:idGroupe "+
                                        "and pg.idGroupe=g.idGroupe ");
        query.setParameter("idGroupe", idGroupe);
        List<Personnel> listEtudiants=query.list();
        
        return listEtudiants;
    }
    
    
    /**
     * c'est une function qui retourne une groupe qui prend entree comme date heuredebut et matiere
     * @param date
     * @param heureDeb
     * @param libelleMatiere
     * @return 
     */
     public static List<Groupe> getGroupe(Date date,int heureDeb, String libelleMatiere) throws ParseException{
         
        if(transaction==null){
            transaction=session.beginTransaction();
        }
        
        SimpleDateFormat df =new SimpleDateFormat("yyyy-mm-dd");
        String d=df.format(date);
        Query query=session.createQuery("select new metier.Groupe(gs.idGroupe,gs.nomGroupe,gs.typeGroupe) " +
                                        "from Creneau c join c.groupes gs,Creneau c "+
                                        "where c.dateDeb=:date "+
                                        "and c.heureDeb=:heure "+
                                        "and c.matiere.libelleMatiere=:libelle "); 
        query.setParameter("date", d);
        query.setParameter("heure", heureDeb);
        query.setParameter("libelle", libelleMatiere);
        
        
        
        List<Groupe> listGroupe=query.list();
        
        return listGroupe;
    }
     
     public static List<Creneau> getIdCreneau(Date date,int heureDeb, String libelleMatiere)throws ParseException{
        if(transaction==null){
            transaction=session.beginTransaction();
        } 
        SimpleDateFormat df =new SimpleDateFormat("yyyy-mm-dd");
        String d=df.format(date);
        
        Query query=session.createQuery("select new metier.Creneau(c.idCreneau) "+
                                        "from Creneau c "+
                                        "where c.dateDeb=:date "+
                                        "and c.heureDeb=:heure "+
                                        "and c.matiere.libelleMatiere=:libelle ");
        
        query.setParameter("date", d);
        query.setParameter("heure", heureDeb);
        query.setParameter("libelle", libelleMatiere);
        
        List<Creneau> idCreneau=query.list();
        return idCreneau;
     };
     
     public static void EnregistrerEtat(String idEtudiant, String idCreneau, String etat){
        session=null;
        try{
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            transaction=session.beginTransaction();
            Query query =session.createSQLQuery("update Affecter set etatPresence=:etat "+
                                         "where idPersonne=:idP "+
                                         "and idCreneau=:idC ");
            query.setParameter("etat", etat);
            query.setParameter("idP", idEtudiant);
            query.setParameter("idC", idCreneau);
            query.executeUpdate();
            transaction.commit();
            }
            catch(RuntimeException e){
                transaction.rollback();
                throw e;
            }
        
        
    }
     
    
    
    
	/*----------------------------*/
	/* Programme principal (test) */
	/*----------------------------*/
    
        private static void affichage (List l)
		{
		Iterator e = l.iterator();
		while (e.hasNext())
			{
			Object[] tab_obj = ((Object[]) e.next());

			for (Object obj : tab_obj)
				System.out.print(obj + " ");

			System.out.println("");
			}
		}
        
        public  static ArrayList<String> output (List l)
		{
		Iterator e = l.iterator();
                Object[] tab_obj = null;
                ArrayList<String> str= new ArrayList<>(); 
		while (e.hasNext())
			{
			tab_obj = ((Object[]) e.next());
                        str.add(String.valueOf(tab_obj[0])); 
                        }
                        return str;

		}
                
                
                
	public static void main (String[] s) throws ParseException
		{                  
//                    for(Groupe g:l){
//                        System.out.println(g.getNomGroupe());
//                    }
//                    SimpleDateFormat df =new SimpleDateFormat("yyyy-mm-dd");
//                    Date d=df.parse("2019-10-01");
//                    List<Creneau> id=bd.getIdCreneau(d, 570, "Management de projet");
//                    for(Creneau c:id){
//                        System.out.println(c.getIdCreneau());
//                    }
                    String idEtudiant="21613265";
                    String idCreneau="MP201910010930";
                    String etat="Retard";
                    bd.EnregistrerEtat(idEtudiant, idCreneau, etat);

		}  
}
