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
import metier.AffecterId;
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
     * @param idEnseignant
     * @return afficher la liste de formation qui est relie avec ce formation et enseignant.
     */
    public static List<Matiere> getMatieres(String libelleFormation,String idEnseignant){
        if(transaction==null){
            transaction=session.beginTransaction();
        } 
          List<Matiere> listMatieres =session.createSQLQuery("select  m.libelleMatiere,m.idFormation "+ 
                                                             "from Matiere m, Formation f,Enseigner e "+
                                                             "where f.idFormation =m.idFormation "+
                                                             "and e.libelleMatiere=m.libelleMatiere "+
                                                             "and e.idPersonne="+"'"+idEnseignant+"'"+
                                                             "and f.libelleFormation=" +"'"+libelleFormation+"'").list();
          return listMatieres;
    }
    
    public static void getEnseignant(String idPersonne){
        if(transaction==null){
            transaction=session.beginTransaction();
        }
        Personnel e=(Personnel)session.load(Personnel.class, idPersonne);
        System.out.println(e.getIdPersonne());
        
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
     
     public static String getInitialeMatiere(String nomMatiere){
          if(transaction==null){
            transaction=session.beginTransaction();
          }
        Matiere m=(Matiere)session.load(Matiere.class, nomMatiere);
        System.out.println(m.getInitiale());
        String str=m.getInitiale();
        
        return str;
     }
     
     public static String creationIdCreneau(String libelleMatiere,Date date,int heureDeb,int duree) throws ParseException{
            SimpleDateFormat df =new SimpleDateFormat("yyyy-mm-dd");
            String dateStr=df.format(date);
            String dateString=dateStr.substring(0, 4)+dateStr.substring(5, 7)+dateStr.substring(8);
            String heureString=String.valueOf(heureDeb);
            String dureeString=String.valueOf(duree);
            
            String initiale=bd.getInitialeMatiere(libelleMatiere);
            
            String idCreneau=initiale+dateString+heureString+dureeString;
         return idCreneau;
     }
     
     public static void creationCreneau(String idCreneau,Date date,int heureDeb,int duree) throws ParseException{
         session=null;
                session=HibernateUtil.getSessionFactory().openSession();
                transaction=session.beginTransaction();
        
        
        Creneau c=new Creneau(idCreneau,date,heureDeb,duree);
        
        session.save(c);
        transaction.commit();
     }
     
     public static void EnregistrerEtat(String idEtudiant, String idCreneau, String etat){
        session=null;
//        try{
            session=HibernateUtil.getSessionFactory().openSession();
            transaction=session.beginTransaction();
//        if(transaction==null){
//            transaction=session.beginTransaction();
//
//          }

            AffecterId id=new AffecterId();
            id.setIdCreneau(idCreneau);
            id.setIdPersonne(idEtudiant);
          
            Affecter affecter= new Affecter(id,etat);
            session.save(affecter);
            transaction.commit();
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
//                    
//                    String str="Developpement Applications Internet";
//                    int heure=570;
//                    int duree=180;
//                    
//                    String idCreneau=bd.creationIdCreneau(str, d, heure, duree);
//                    System.out.println(idCreneau);
                    
//                    
                    List<String> list=new ArrayList<>();
                    list.add("21613265");
                    list.add("21511000");
                    list.add("21511001");
                    list.add("21509151");
                    String etat="PPP";
                    for(String str:list){                         

                           bd.EnregistrerEtat(str, "MP201910010930", etat);
                    }
//
//                    String str="Developpement Applications Internet";
//                    String sigle=bd.getInitialeMatiere(str);
//                    System.out.println(sigle);

		}  
}
