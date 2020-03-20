package bd;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import metier.Affecter;
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
    public static List<Groupe> getGroupe(Date date,int heureDeb, String libelleMatiere) throws ParseException{
        SimpleDateFormat df =new SimpleDateFormat("yyyy-mm-dd");
        String d=df.format(date);
        Query query=session.createQuery("select new metier.Groupe(gs.idGroupe,gs.nomGroupe,gs.typeGroupe) " +
                                        "from Creneau c join c.groupes gs,Creneau c "+
                                        "where c.dateDeb=:date "+
                                        "and c.heureDeb=:heure "+
                                        "and c.matiere.libelleMatiere=:libelle");
        query.setParameter("date", d);
        query.setParameter("heure", heureDeb);
        query.setParameter("libelle", libelleMatiere);
        
        List<Groupe> listGroupe=query.list();
        
        return listGroupe;
    }
    
    public static List<String> getAffecter(String idetudiant){
        List<String> listaffecter =session.createSQLQuery("select a.idCreneau "+
                                                          "from Affecter a "+
                                                          "where a.etatPresence= 'present' ").list();
       
        return listaffecter;
    }
    
    public static List<Creneau> getHeurePresent(String idetudiant){
        List<Creneau> listcreneau =session.createSQLQuery("select  c.dateDeb,sum(duree) "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and a.etatPresence= 'present' "+
                                                          "and a.idPersonne =" +"'"+idetudiant+"' "+
                                                          "group by c.dateDeb").list();
       
        return listcreneau;
    }
    
    public static List<Personnel> getEtudiantinfo(String idetudiant){
        List<Personnel> listetu =session.createSQLQuery("select a.idCreneau "+
                                                          "from Affecter a "+
                                                          "where a.etatPresence= 'present' ").list();
       
        return listetu;
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
        /**
         * 
         * @param l
         * @return 
         */
        public  static ArrayList<String> output (List l,int i)
		{
		Iterator e = l.iterator();
                Object[] tab_obj = null;
                ArrayList<String> str= new ArrayList<>(); 
		while (e.hasNext())
			{
			tab_obj = ((Object[]) e.next());
                        str.add(String.valueOf(tab_obj[i])); 
                        }
                        return str;

		}
                
                
                
	public static void main (String[] s) throws ParseException
		{
                    
//                   
                    List<Creneau> lista= bd.getHeurePresent("21509151") ;
                    
                    for(String str :bd.output(lista,0)){
                        System.out.println(str);
                    }
                    for(String str :bd.output(lista,1)){
                        System.out.println(str);
                    }
		}
    
    
   
}
