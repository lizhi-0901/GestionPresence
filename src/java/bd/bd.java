package bd;


import java.sql.SQLException;
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
import org.hibernate.HibernateException;
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
    
//        static SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
    
     /**
      * c'est une function qui prends en entree comme identifiant 
      * et retourne mot de pass
      * @param identifiant
      * @return 
      */
     
    public static Personnel connection(String identifiant){

          if(transaction==null){
            transaction  = session.beginTransaction();
        }
          Personnel p=new Personnel();
          try{
              p = (Personnel) session.get(Personnel.class, identifiant);
              return p;
          }catch(HibernateException hibernateEx){
              try {
                transaction.rollback();
            } catch(RuntimeException runtimeEx){
                System.err.printf("Couldn’t Roll Back Transaction", runtimeEx);
                p= null;
            }
          }
//       
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
        List<Matiere> listMatieres=new ArrayList<>();
        try{
         listMatieres =session.createSQLQuery( "select  m.libelleMatiere,m.idFormation "+ 
                                                             "from Matiere m, Formation f,Enseigner e "+
                                                             "where f.idFormation =m.idFormation "+
                                                             "and e.libelleMatiere=m.libelleMatiere "+
                                                             "and e.idPersonne="+"'"+idEnseignant+"'"+
                                                             "and f.libelleFormation=" +"'"+libelleFormation+"'").list();
           
        }catch(HibernateException hibernateEx){
              try {
                transaction.rollback();
            } catch(RuntimeException runtimeEx){
                System.err.printf("Couldn’t Roll Back Transaction", runtimeEx);
                listMatieres= null;
            }
    }
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
            transaction  = session.beginTransaction();
        } 
        List<Personnel> listEtudiants =new ArrayList<>();
        try{
            Query query=session.createQuery("select new metier.Personnel(p.idPersonne,p.nom,p.prenom,p.photo) "+
                                        "from Personnel p inner join p.groupes pg, Groupe g "+
                                        "where g.idGroupe=:idGroupe "+
                                        "and pg.idGroupe=g.idGroupe ");
        
            query.setParameter("idGroupe", idGroupe);
            listEtudiants=query.list();
            
        }catch(HibernateException hibernateEx){
              try {
                transaction.rollback();
            } catch(RuntimeException runtimeEx){
                System.err.printf("Couldn’t Roll Back Transaction", runtimeEx);
                listEtudiants= null;
            }
    }

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
            transaction  = session.beginTransaction();
        }

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
     
     public static void creationCreneau(String idCreneau,Date date,int heureDeb,int duree,String nomCreneau,String idEnseignant,String typeCours) throws ParseException{
         session=null;
                session=HibernateUtil.getSessionFactory().openSession();
                transaction=session.beginTransaction();
        
        
        Creneau c=new Creneau();
        c.setIdCreneau(idCreneau);
        
        c.setDateDeb(date);
        c.setHeureDeb(heureDeb);
        c.setDuree(duree);
        c.setNomCreneau(nomCreneau);
        c.setTypeActivite(typeCours);
        c.setEnseignant(idEnseignant);
        
        session.save(c);
        transaction.commit();
     }
     
     public static void EnregistrerEtat(String idEtudiant, String idCreneau, String etat){
        session=null;
        session=HibernateUtil.getSessionFactory().openSession();
        transaction=session.beginTransaction();
            
            AffecterId id=new AffecterId();
            id.setIdCreneau(idCreneau);
            id.setIdPersonne(idEtudiant);
            Affecter affecter= new Affecter();
            affecter.setId(id);
            affecter.setEtatPresence(etat);
            session.merge(affecter);
            transaction.commit();
    }
     
    
    
//    public static List<String> getAffecter(String idetudiant){
//        List<String> listaffecter =session.createSQLQuery("select a.idCreneau "+
//                                                          "from Affecter a "+
//                                                          "where a.etatPresence= 'present' ").list();
//       
//        return listaffecter;
//    }
    
    public static List<Creneau> getHeurePresent(String idetudiant,String date){
         if(transaction==null){
            transaction  = session.beginTransaction();
        }
         Query query=session.createSQLQuery("select  c.dateDeb,sum(duree),a.idCreneau "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and a.etatPresence= 'present' "+
                                                          "and a.idPersonne=:id "+
                                                          "and c.dateDeb like :date "+        
                                                          "group by c.dateDeb");
        query.setParameter("id", idetudiant);
        query.setParameter("date", "%"+date+"%");
        List<Creneau> listcreneau =query.list();
        return listcreneau;
    }
    /**
     * 
     * @param idetudiant
     * @param date
     * @return 
     */
    public static List<String> getHeurePresentidCreneau(String idetudiant,String date){
        if(transaction==null){
            transaction  = session.beginTransaction();
        }
         Query query=session.createSQLQuery("select a.idCreneau "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and a.etatPresence= 'present' "+
                                                          "and a.idPersonne=:id "+
                                                          "and c.dateDeb like :date "+        
                                                          "group by c.dateDeb,a.idCreneau");
        query.setParameter("id", idetudiant);
        query.setParameter("date", "%"+date+"%");
        List<String> listcreneau =query.list();
        return listcreneau;
       
    }
    
    
    
    public static Personnel getEtudiantinfo(String idetudiant){
        if(transaction==null){
            transaction  = session.beginTransaction();
        }
        
        Personnel etu =(Personnel)session.get(Personnel.class, idetudiant);
        return etu;
    }
    

            
            public static void Valide(String etatValide, String idetudiant, String idCreneau){
                session=null;
               // transaction=null;
                    try {
                      session = HibernateUtil.getSessionFactory().getCurrentSession();
                      transaction  = session.beginTransaction();
////                    
//                    Query query =session.createSQLQuery("update Affecter set etatValide=:etat "+
//                                                     "where idPersonne=:idP "+
//                                                     "and idCreneau=:idC ");
//                    query.setParameter("etat", etatValide);
//                    query.setParameter("idP", idetudiant);
//                    query.setParameter("idC", idCreneau);
//                    query.executeUpdate();
//                    session.getTransaction().commit();
                      AffecterId id=new AffecterId();
                      id.setIdCreneau(idCreneau);
                      id.setIdPersonne(idetudiant);
                      Affecter affecter =(Affecter) session.get(Affecter.class, id);
                      affecter.setEtatValide(etatValide);
                      transaction.commit();
                    }
                    catch (RuntimeException e) {
                        transaction.rollback();
                        throw e;
                    }
                  } 
            
            public static Creneau getCreneau(String date,String activ, int heureDeb){
                if(transaction==null){
                  transaction  = session.beginTransaction();
                        }
                         
                    Query query=session.createQuery("select new metier.Creneau(c.idCreneau, c.matiere, c.nomCreneau, c.salle, c.enseignant, c.dateDeb, c.heureDeb, c.duree, c.typeActivite, c.commantaire) " +
                                                    "from Creneau c "+
                                                    "where c.dateDeb=:date "+
                                                    "and c.heureDeb=:heureDeb "+
                                                    "and c.typeActivite=:type");
                    query.setParameter("date", date);
                    query.setParameter("heureDeb", heureDeb);
                    query.setParameter("type", activ);
                    query.setMaxResults(1);
		    Creneau cre = (Creneau) query.uniqueResult();
                    return cre;
            }
            
            
            public static void EnregistereSaisirheure(String idCreneau,String idEtudiant,String etatPresence){
                session=null;
               // transaction=null;
                    try {
                      session = HibernateUtil.getSessionFactory().getCurrentSession();
                      transaction  = session.beginTransaction();
                      Query query =session.createSQLQuery("INSERT INTO Affecter(idPersonne,idCreneau,etatPresence) VALUES(:idEtudiant,:idCreneau,:etatPresence) ");
                      query.setParameter("idEtudiant", idEtudiant);
                       query.setParameter("idCreneau", idCreneau);
                        query.setParameter("etatPresence", etatPresence);
                        query.executeUpdate();
                        transaction.commit();
                    }
                    catch (RuntimeException e) {
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
//                      SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//                      Date date=df.parse("2020-10-01");
//                      String idEnseignant="alainberro@gamil.com";
//                      String typeCours="typeCours";
//                      bd.creationCreneau("BD2020000148090",date,570,90,"Big Data",idEnseignant,typeCours);
                     List<String> list=new ArrayList<>();
                     list.add("21613265");
                     list.add("21509151");
                     list.add("21511000");
                     list.add("21511001");
                     String etat="Retard";
                     String idCreneau="BD2020000148090";
                     for(String str:list){
                         bd.EnregistrerEtat(str, idCreneau, etat);
                     }
                     
                    
                    }        
 
}
