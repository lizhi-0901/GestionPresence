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
import metier.Matiere;
import metier.Personnel;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author lizhiwang
 */
<<<<<<< HEAD
public class TestHibernate {
     public static void main (String[] args) throws ParseException{
         List<String> list = new ArrayList<>();
                    list.add("BD201910011400");
                    list.add("DC20191001140");
                    System.out.println(list.size());
                    for (int i=0;i<list.size();i++){
                        bd.Valide("NON", "21509151", list.get(i));
                        System.out.println(i);
                    }
//                    list.forEach((str) -> {
//                        bd.Valide("NON", "21509151", str);
//         });
     
}
}
=======
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
>>>>>>> 3af4b5158a28b8a1b93533b472ced4fcedfefef5
