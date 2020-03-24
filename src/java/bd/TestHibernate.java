/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import static bd.bd.session;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import metier.Matiere;
import metier.Personnel;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author lizhiwang
 */
//public class TestHibernate {
//     public static void main (String[] args) throws ParseException{
//         SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//            Date date =df.parse("2019-10-01");
//                        String libelleMatiere ="Donnee,integration";
//                        int heureDeb =570;
//                        System.out.println(heureDeb);
//                        System.out.println(libelleMatiere);
//                        System.out.println(date);
//                        
//                        ArrayList<String> glist =bd.output(bd.getGroupe(date, heureDeb, libelleMatiere));
//                        
//                        System.out.println(glist.size());
//                        for(String g: glist){
//                          
//                        
//                            System.out.println("groupe"+g); 
//                            // retourne idgroupe
//			
//			}
//                    
//     }
//     
//}
