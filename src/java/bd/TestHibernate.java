/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;
import bd.bd;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import metier.Affecter;
import metier.AffecterId;
import metier.Creneau;
import metier.Matiere;
import metier.Personnel;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author lizhiwang
 */

public class TestHibernate {
     public static void main (String[] args) throws ParseException{
         String idetudiant ="21509151";
         String anneemois="2020-02";
                        //heureE
                        List<Creneau> listem =bd.getHeureEmatin(idetudiant,anneemois);
                        List<Creneau> listea =bd.getHeureEApre(idetudiant,anneemois);
                        List<Creneau> liste =bd.getHeureE(idetudiant,anneemois);
                        //absent
                        List<Creneau> abslistm =bd.getHeurePresentmatin(idetudiant,anneemois,"absent");
                        List<Creneau> abslista =bd.getHeurePresentapres(idetudiant,anneemois,"absent");
                        List<Creneau> abslist =bd.getHeurePresent(idetudiant,anneemois,"absent");
                        //heureD
                        List<Creneau> listdm =bd.getHeureDmatin(idetudiant,anneemois);
                        List<Creneau> listda =bd.getHeureDApres(idetudiant,anneemois);
                        List<Creneau> listd =bd.getHeureD(idetudiant,anneemois);
                        
                        Personnel p =bd.getEtudiantinfo(idetudiant);
                        String nom =p.getNom();
                        String prenom =p.getPrenom();
                        String photo =p.getPhoto();
                        List<String> listCreneau= new ArrayList<>();    
                        
//                        out.println("<nom>" + nom + "</nom>");
//                        out.println("<prenom>" + prenom + "</prenom>");
//                        out.println("<photo>" + photo + "</photo>"); 
//                        
//                        int max=0;
//                        
//                        max=clist.size();
//                        list=clist;
//                        if(max<abslist.size()){
//                            max=abslist.size();
//                            list=abslist;
//                            if(max<retalist.size()){
//                                max=retalist.size();
//                                list=retalist;
//                            }
//                        }else{
//                            if(max<retalist.size()){
//                                max=retalist.size();
//                                list=retalist;
//                            }
//                        }
                        List<String> list=new ArrayList<>();
                        //ajouter tous les elements de trois tables dans list
                        util.addlist(list, bd.output(listem, 0));
                        util.addlist(list, bd.output(abslistm,0));
                        util.addlist(list, bd.output(listea,0));
                        util.addlist(list, bd.output(listdm, 0));
                        util.addlist(list, bd.output(abslista,0));
                        util.addlist(list, bd.output(listda,0));
                        util.addlist(list, bd.output(listd,0));
                        util.addlist(list, bd.output(liste,0));
                        util.addlist(list, bd.output(abslist,0));
                        //eliminer les duplicate
                        list=util.removeDuplicate(list);
                        //date
                        
                        for(int i=0;i<list.size();i++){
                            System.out.println("<date>" + list.get(i) + "</date>");
                        }
//                                if(i<abslist.size()){
//                            String str=list.get(i);
//                            if(abslist.contains(str)){
//                                int heure=Integer.parseInt((bd.output(abslist,1).get(i)));
//                                System.out.println("<absheure>" + (heure/60) + "</absheure>");
//                            }
//                            else{
//                                System.out.println("<absheure>" + 0 + "</absheure>");
//                                i=i-1;
//                            }}
//                                else{
//                                    System.out.println("<absheure>" + 0 + "</absheure>");
//                                }
//                                
//                        }
//     
//                        for(int i=0;i<list.size();i++){
//                            
//                                if(i<retalist.size()){
//                            String str=list.get(i);
//                            if(retalist.contains(str)){
//                                int heure=Integer.parseInt((bd.output(retalist,1).get(i)));
//                                System.out.println("<reheure>" + (heure/60) + "</reheure>");
//                            }
//                            else{
//                                System.out.println("<reheure>" + 0 + "</reheure>");
//                                i=i-1;
//                            }}
//                            else{
//                                    System.out.println("<reheure>" + 0 + "</reheure>");
//                                }
//                        }
}
}

