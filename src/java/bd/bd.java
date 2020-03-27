package bd;


import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
     
     public static String creationIdCreneau(String libelleMatiere,String date,int heureDeb,int duree) throws ParseException{
            
            String dateString=date.substring(0, 4)+date.substring(5, 7)+date.substring(8);
            String heureString=String.valueOf(heureDeb);
            String dureeString=String.valueOf(duree);
            
            String initiale=bd.getInitialeMatiere(libelleMatiere);
            
            String idCreneau=initiale+dateString+heureString+dureeString;
         return idCreneau;
     }
     

     public static void creationCreneau(String idCreneau,String date,int heureDeb,int duree,String nomCreneau,String idEnseignant,String typeCours) throws ParseException{

         session=null;
//          if(transaction==null){
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
    
    public static List<Creneau> getHeurePresent(String idetudiant,String date,String etatPresence){
         if(transaction==null){
            transaction  = session.beginTransaction();
        }
         Query query=session.createSQLQuery("select  c.dateDeb,sum(duree),a.idCreneau "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and a.etatPresence=:etat "+
                                                          "and a.idPersonne=:id "+
                                                          "and c.dateDeb like :date "+        
                                                          "group by c.dateDeb");
        query.setParameter("id", idetudiant);
        query.setParameter("date", "%"+date+"%");
        query.setParameter("etat", etatPresence);
        List<Creneau> listcreneau =query.list();
        return listcreneau;
    }
    
    public static List<Creneau> getHeurePresentapres(String idetudiant,String date,String etatPresence){
         if(transaction==null){
            transaction  = session.beginTransaction();
        }
         Query query=session.createSQLQuery("select  c.dateDeb,sum(duree),a.idCreneau "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and a.etatPresence=:etat "+
                                                          "and a.idPersonne=:id "+
                                                          "and c.dateDeb like :date "+
                                                          "and c.heureDeb> 720 "+
                                                          "group by c.dateDeb");
        query.setParameter("id", idetudiant);
        query.setParameter("date", "%"+date+"%");
        query.setParameter("etat", etatPresence);
        List<Creneau> listcreneau =query.list();
        return listcreneau;
    }
    
    public static List<Creneau> getHeurePresentmatin(String idetudiant,String date,String etatPresence){
         if(transaction==null){
            transaction  = session.beginTransaction();
        }
         Query query=session.createSQLQuery("select  c.dateDeb,sum(duree),a.idCreneau "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and a.etatPresence=:etat "+
                                                          "and a.idPersonne=:id "+
                                                          "and c.dateDeb like :date "+
                                                          "and c.heureDeb< 630 "+
                                                          "group by c.dateDeb");
        query.setParameter("id", idetudiant);
        query.setParameter("date", "%"+date+"%");
        query.setParameter("etat", etatPresence);
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
                if(transaction==null){
                transaction  = session.beginTransaction();
                    }
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
                
                 if(transaction==null){
                        transaction  = session.beginTransaction();
                                     }
                
                    try {
                      //session = HibernateUtil.getSessionFactory().getCurrentSession();
                      //transaction  = session.beginTransaction();
                      Query query =session.createSQLQuery("INSERT INTO Affecter(idPersonne,idCreneau,etatPresence) VALUES(:idEtudiant,:idCreneau,:etatPresence) ");
                      query.setParameter("idEtudiant", idEtudiant);
                       query.setParameter("idCreneau", idCreneau);
                        query.setParameter("etatPresence", etatPresence);
                        query.executeUpdate();
                        transaction.commit();
                    }
                    catch (Exception e) {
                        System.out.println("erruer"+e);
                        throw e;
                    }
                  } 
         
          public static List<Creneau> getHeureE(String idetudiant,String date){
         if(transaction==null){
            transaction  = session.beginTransaction();
        }
         Query query=session.createSQLQuery("select  c.dateDeb,sum(duree),a.idCreneau "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and c.typeActivite in('Cours','TD','Exam') "+
                                                          "and a.idPersonne=:id "+
                                                          "and c.dateDeb like :date "+
                                                          "group by c.dateDeb");
        query.setParameter("id", idetudiant);
        query.setParameter("date", "%"+date+"%");
       
        List<Creneau> listcreneau =query.list();
        return listcreneau;
        }
          
         public static List<Creneau> getHeureEmatin(String idetudiant,String date){
         if(transaction==null){
            transaction  = session.beginTransaction();
        }
         Query query=session.createSQLQuery("select  c.dateDeb,sum(duree),a.idCreneau "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and c.typeActivite in('Cours','TD','Exam') "+
                                                          "and a.idPersonne=:id "+
                                                          "and c.dateDeb like :date "+
                                                          "and c.heureDeb< 630 "+
                                                          "group by c.dateDeb");
        query.setParameter("id", idetudiant);
        query.setParameter("date", "%"+date+"%");
       
        List<Creneau> listcreneau =query.list();
        return listcreneau;
        } 
          
         public static List<Creneau> getHeureEApre(String idetudiant,String date){
         if(transaction==null){
            transaction  = session.beginTransaction();
        }
         Query query=session.createSQLQuery("select  c.dateDeb,sum(duree),a.idCreneau "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and c.typeActivite in('Cours','TD','Exam') "+
                                                          "and a.idPersonne=:id "+
                                                          "and c.dateDeb like :date "+
                                                          "and c.heureDeb> 720 "+
                                                          "group by c.dateDeb");
        query.setParameter("id", idetudiant);
        query.setParameter("date", "%"+date+"%");
       
        List<Creneau> listcreneau =query.list();
        return listcreneau;
        }
         public static List<Creneau> getHeureD(String idetudiant,String date){
         if(transaction==null){
            transaction  = session.beginTransaction();
        }
         Query query=session.createSQLQuery("select  c.dateDeb,sum(duree),a.idCreneau "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and c.typeActivite in('projet','conference') "+
                                                          "and a.idPersonne=:id "+
                                                          "and c.dateDeb like :date "+        
                                                          "group by c.dateDeb");
        query.setParameter("id", idetudiant);
        query.setParameter("date", "%"+date+"%");
       
        List<Creneau> listcreneau =query.list();
        return listcreneau;
        }
        
         public static List<Creneau> getHeureDmatin(String idetudiant,String date){
         if(transaction==null){
            transaction  = session.beginTransaction();
        }
         Query query=session.createSQLQuery("select  c.dateDeb,sum(duree),a.idCreneau "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and c.typeActivite in('projet','conference') "+
                                                          "and a.idPersonne=:id "+
                                                          "and c.dateDeb like :date "+
                                                          "and c.heureDeb< 630 "+
                                                          "group by c.dateDeb");
        query.setParameter("id", idetudiant);
        query.setParameter("date", "%"+date+"%");
       
        List<Creneau> listcreneau =query.list();
        return listcreneau;
        }
         
         public static List<Creneau> getHeureDApres(String idetudiant,String date){
         if(transaction==null){
            transaction  = session.beginTransaction();
        }
         Query query=session.createSQLQuery("select  c.dateDeb,sum(duree),a.idCreneau "+
                                                          "from Creneau c,Affecter a "+
                                                          "where c.idCreneau=a.idCreneau "+
                                                          "and c.typeActivite in('projet','conference') "+
                                                          "and a.idPersonne=:id "+
                                                          "and c.dateDeb like :date "+
                                                          "and c.heureDeb> 720 "+
                                                          "group by c.dateDeb");
        query.setParameter("id", idetudiant);
        query.setParameter("date", "%"+date+"%");
       
        List<Creneau> listcreneau =query.list();
        return listcreneau;
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
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            
//                    Date date = df.parse("2020-03-20");
                    //String idCreneau=bd.creationIdCreneau("Big Data", "2020-04-20", 570, 60);
                   // System.out.println(idCreneau);
                    //bd.creationCreneau(idCreneau, "2020-04-20", 570, 60, "Big Data", "alainberro@gmail.com", "TD");
                    //bd.EnregistrerEtat("", idCreneau, idCreneau);
                    String idetudiant="21509151";
                    String anneemois="2020-02";
                    //heureE
                        List<Creneau> listem =bd.getHeureEmatin(idetudiant,anneemois);
//                        List<Creneau> listea =bd.getHeureEApre(idetudiant,anneemois);
                        List<Creneau> liste =bd.getHeureE(idetudiant,anneemois);
                        //absent
                        List<Creneau> abslistm =bd.getHeurePresentmatin(idetudiant,anneemois,"absent");
                        List<Creneau> abslista =bd.getHeurePresentapres(idetudiant,anneemois,"absent");
                        List<Creneau> abslist =bd.getHeurePresent(idetudiant,anneemois,"absent");
                        //heureD
                        List<Creneau> listdm =bd.getHeureDmatin(idetudiant,anneemois);
//                        List<Creneau> listda =bd.getHeureDApres(idetudiant,anneemois);
                        List<Creneau> listd =bd.getHeureD(idetudiant,anneemois);
                        
                        List<String> list=new ArrayList<>();
                        List<String> listm=new ArrayList<>();
                        //ajouter tous les elements de trois tables dans list
//                        util.addlist(list, bd.output(listem, 0));
//                        util.addlist(list, bd.output(abslistm,0));
//                        util.addlist(list, bd.output(listea,0));
//                        util.addlist(list, bd.output(listdm, 0));
//                        util.addlist(list, bd.output(abslista,0));
//                        util.addlist(list, bd.output(listda,0));
                        util.addlist(list, bd.output(listd,0));
                        util.addlist(list, bd.output(liste,0));
                        util.addlist(list, bd.output(abslist,0));
                        
                        util.addlist(listm, bd.output(listem, 0));
                        util.addlist(listm, bd.output(abslistm,0));
                        util.addlist(listm, bd.output(listdm, 0));
                        //eliminer les duplicate
                        list=util.removeDuplicate(list);
                        listm=util.removeDuplicate(list);

                        //date
                        System.out.println("size"+list.size());
                        for(int i=0;i<list.size();i++){
                            System.out.println("<date>" + list.get(i) + "</date>");
                        }
                        
                        HashMap<String, String> mape =new HashMap<>();
                        mape=util.addMap(liste);
                        HashMap<String, String> mapd =new HashMap<>();
                        mapd=util.addMap(listd);
                        HashMap<String, String> mapabs =new HashMap<>();
                        mapabs=util.addMap(abslist);
                        
                         HashMap<String, String> mapem =new HashMap<>();
                        mapem=util.addMap(listem);
                        HashMap<String, String> mapdm =new HashMap<>();
                        mapdm=util.addMap(listdm);
                        HashMap<String, String> mapabsm =new HashMap<>();
                        mapabsm=util.addMap(abslistm);
                        
//                        for (Map.Entry<String, String> entry : mape.entrySet()) {
// 
//                        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
// 
//}
//  
//                        System.out.println(mape.get("2020-02-01"));
                        // heuretotal
                        	    //设置保留位数
 
	                DecimalFormat df=new DecimalFormat("0.00");
                        int sizee=liste.size();
                        for(int i=0;i<list.size();i++){
                               if(i<sizee){
                                    String str=list.get(i);
                                    System.out.println(str);
                                    if((bd.output(liste,0)).contains(str)){
                                        
                                        String st=mape.get(str);
                                            int heure=Integer.parseInt(st);
                                            System.out.println("<Eheuretotal>" + df.format((float)heure/60) + "</Eheuretotal>");
                                        
                                    }
                                    else{
                                        
                                        System.out.println("<Eheuretotal>" + 0 + "</Eheuretotal>");
                                        sizee++;
                                    }
                                }
                                else{
                                    System.out.println("<Eheuretotal>" + 0 + "</Eheuretotal>");
                                }
                                
                            }
                        // heureabs
                        int sizeabs=abslist.size();
                        for(int i=0;i<list.size();i++){
                               if(i<sizee){
                                    String str=list.get(i);
                                    if((bd.output(abslist,0)).contains(str)){
                                        String st=mapabs.get(str);
                                        int heure=Integer.parseInt(st);
                                        System.out.println("<absheuretotal>" + df.format((float)heure/60) + "</absheuretotal>");
                                        
                                    }
                                    else{
                                        System.out.println("<absheuretotal>" + 0 + "</absheuretotal>");
                                        sizeabs++;
                                    }
                                }
                                else{
                                    System.out.println("<absheuretotal>" + 0 + "</absheuretotal>");
                                }
                                
                            }
                        
                        
                        // heuredtotal
                        int sized=listd.size();
                        for(int i=0;i<list.size();i++){
                               if(i<sizee){
                                    String str=list.get(i);
                                    if((bd.output(listd,0)).contains(str)){
                                            String st=mapd.get(str);
                                            int heure=Integer.parseInt(st);
                                            System.out.println("<Dheuretotal>" + df.format((float)heure/60) + "</Dheuretotal>");
                                    }
                                    else{
                                        System.out.println("<Dheuretotal>" + 0 + "</Dheuretotal>");
                                        sizeabs++;
                                    }
                                }
                                else{
                                    System.out.println("<Dheuretotal>" + 0 + "</Dheuretotal>");
                                }
                                
                            }
                        int sizeem=listem.size();
                        for(int i=0;i<listm.size();i++){
                               if(i<sizeem){
                                    String str=listm.get(i);
                                    if((bd.output(listem,0)).contains(str)){
                                            String st=mapem.get(str);
                                            int heure=Integer.parseInt(st);
                                            System.out.println("<Eheurematin>" +df.format((float)heure/60) + "</Eheurematin>");
                                        
                                    }
                                    else{
                                        System.out.println("<Eheurematin>" + 0 + "</Eheurematin>");
                                        sizeem++;
                                    }
                                }
                                else{
                                    System.out.println("<Eheurematin>" + 0 + "</Eheurematin>");
                                }
                                
                            }
                        // heuredmatin
                        int sizedm=listdm.size();
                        for(int i=0;i<listm.size();i++){
                              if(i<sizedm){
                                    String str=listm.get(i);
                                    if((bd.output(listdm,0)).contains(str)){
                                            String st=mapdm.get(str);
                                            int heure=Integer.parseInt(st);
                                            System.out.println("<heuredmatin>" + df.format((float)heure/60) + "</heuredmatin>");
                                     }
                                    else{
                                        System.out.println("<heuredmatin>" + 0 + "</heuredmatin>");
                                        sizedm++;
                                    }
                                }
                                else{
                                    System.out.println("<heuredmatin>" + 0 + "</heuredmatin>");
                                }
                                
                            }
                        
                        
                        // heureabsmatin
                        int sizeabsm=abslistm.size();
                        for(int i=0;i<listm.size();i++){
                              if(i<sizeabsm){
                                    String str=listm.get(i);
                                    if((bd.output(abslistm,0)).contains(str)){
                                        String st=mapabsm.get(str);
                                            int heure=Integer.parseInt(st);
                                            System.out.println("<absheurem>" + df.format((float)heure/60)+ "</absheurem>");
                                        
                                    }
                                    else{
                                        System.out.println("<absheurem>" + 0 + "</absheurem>");
                                        sizeabsm++;
                                    }
                                }
                                else{
                                    
                                    System.out.println("<absheurem>" + 0 + "</absheurem>");
                                }
                                
                            }
                        
//                    System.out.println("date"+bd.output(listem, 0));
//                    System.out.println("heure"+bd.output(listem, 1));
//                    System.out.println("date"+bd.output(listea, 0));
//                    System.out.println("heure"+bd.output(listea, 1));
//                    System.out.println("date"+bd.output(liste, 0));
//                    System.out.println("heure"+bd.output(liste, 1));
//                    System.out.println("date"+bd.output(abslistm, 0));
//                    System.out.println("heure"+bd.output(abslistm, 1));
//                    System.out.println("date"+bd.output(abslista, 0));
//                    System.out.println("heure"+bd.output(abslista, 1));
//                    System.out.println("date"+bd.output(abslist, 0));
//                    System.out.println("heure"+bd.output(abslist, 1));
//                    System.out.println("date"+bd.output(listdm, 0));
//                    System.out.println("heure"+bd.output(listdm, 1));
//                     System.out.println("date"+bd.output(listda, 0));
//                    System.out.println("heure"+bd.output(listda, 1));
//                    System.out.println("date"+bd.output(listd, 0));
//                    System.out.println("heure"+bd.output(listd, 1));
//                  
                    
                    }        
 
}
