/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import static bd.bd.session;
import java.text.ParseException;
import java.util.List;
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
//           List<Personnel> plist=bd.getEtudiants("MIAGEIPM2019TD2");
//           plist.forEach((p) -> {
//               System.out.println(p.getNom());
                    List<Matiere> mlist = bd.getMatieres("MIAGEIPM");
                    
     }
     
}
