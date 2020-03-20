package bd;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
     static Transaction t = session.beginTransaction();
     SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
    
     /**
      * c'est une function qui prends en entree comme identifiant 
      * et retourne mot de pass
      * @param identifiant
      * @return 
      */
     
    public static Personnel connection(String identifiant){
//          try{
          Personnel p = (Personnel) session.get(Personnel.class, identifiant);
          return p;
//          }
//          catch(SQLexception ){
//              
//          }
    }
    
     /**
     * 
     * @param libelleFormation
     * @return afficher la liste de formation qui est relie avec ce formation
     */
    public static List<Matiere> getMatieres(String libelleFormation){
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
    public static List<Groupe> getGroupe(Date date, int heureDeb, String libelleMatiere){
        Query query=session.createQuery("select new metier.Groupe(*) " +
                                        "from Creneau c, Groupe g, AffecterGroupe a "+
                                        "where c.idCreneau =a.idCreneau "+
                                        "and a.idGroupe=g.idGroupe "+ 
                                        "and c.dateDeb=:date "+
                                        "and c.heureDeb=:heure "+
                                        "and c.libelleMatiere=:libelle");
        query.setParameter("date", date);
        query.setParameter("heure", heureDeb);
        query.setParameter("libelle", libelleMatiere);
        List<Groupe> listGroupe=query.list();
        
        return listGroupe;
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
                
                
                
//	public static void main (String[] s) throws ParseException
//		{
//                    
////                    List<Personnel> l = bd.getEtudiants("MIAGEIPM2019TD1");
////                    List<Matiere> l = bd.getMatieres("MIAGE IPM");
////                    SimpleDateFormat df =new SimpleDateFormat("yyyy-mm-dd");
////                    Date d=df.parse("2019-10-01");
////                    List<Groupe> l=bd.getGroupe(d, 570, "Management de projet");
////                    bd.affichage(l);
//                    
////                     List<Personnel> plist=bd.getEtudiants("MIAGEIPM2019TD2");
////                    bd.affichage(plist);
////           
//           List<Matiere> m = bd.getMatieres("MIAGEIPM");
//           ArrayList<String> ps = bd.output(m);
//           for(String p : ps){
//               System.out.println(p);
//           }
//		}
    
    
   
}
