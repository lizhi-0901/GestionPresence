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
                        String idetudiant="21509151";
                        String anneemois="2020-02";
                        List<Creneau> clist =bd.getHeurePresent(idetudiant,anneemois,"present");
                        //absent
                        List<Creneau> abslist =bd.getHeurePresent(idetudiant,anneemois,"absent");
                        //retard
                        List<Creneau> retalist =bd.getHeurePresent(idetudiant,anneemois,"retard");
                        List<String> list=new ArrayList<>();
                        //ajouter tous les elements de trois tables dans list
                        util.addlist(list, bd.output(clist, 0));
                        util.addlist(list, bd.output(abslist,0));
                        util.addlist(list, bd.output(retalist,0));
                        //eliminer les duplicate
                        list=util.removeDuplicate(list);
                        //date
                        
                        for(int i=0;i<list.size();i++){
                            System.out.println("<date>" + list.get(i) + "</date>");
                        }
                        // heure prsence 
                        int s=clist.size();
                        for(int i=0;i<list.size();i++){
                               if(i<s){
                                    String str=list.get(i);
                                    if((bd.output(clist,0)).contains(str)){
                                        for(String st:((bd.output(clist,1)))){
                                            int heure=Integer.parseInt(st);
                                            System.out.println("<heure>" + (heure/60) + "</heure>");
                                        }
                                    }
                                    else{
                                        System.out.println("<heure>" + 0 + "</heure>");
                                        s++;
                                    }
                                }
                                else{
                                    
                                    System.out.println("<heure>" + 0 + "</heure>");
                                }
                                
                            }
                        int sizeabs=clist.size();
                        for(int i=0;i<list.size();i++){
                              if(i<sizeabs){
                                    String str=list.get(i);
                                    if((bd.output(abslist,0)).contains(str)){
                                        for(String st:((bd.output(abslist,1)))){
                                            int heure=Integer.parseInt(st);
                                            System.out.println("<absheure>" + (heure/60) + "</absheure>");
                                        }
                                    }
                                    else{
                                        System.out.println("<absheure>" + 0 + "</absheure>");
                                        sizeabs++;
                                    }
                                }
                                else{
                                    
                                    System.out.println("<absheure>" + 0 + "</absheure>");
                                }
                                
                            }
                        
                        int sizere=clist.size();
                        for(int i=0;i<list.size();i++){
                                if(i<sizere){
                                    String str=list.get(i);
                                    if((bd.output(retalist,0)).contains(str)){
                                        for(String st:((bd.output(retalist,1)))){
                                            
                                            int heure=Integer.parseInt(st);
                                            System.out.println("<reheure>" + (heure/60) + "</reheure>");
                                        }
                                    }
                                    else{
                                        System.out.println("<reheure>" + 0 + "</reheure>");
                                        sizere++;
                                    }
                                }
                                else{
                                    
                                    System.out.println("<reheure>" + 0 + "</reheure>");
                                }
                                
                            }
                        
//                        //heure absent
//                       for(int i=0;i<list.size();i++){
//                           
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

