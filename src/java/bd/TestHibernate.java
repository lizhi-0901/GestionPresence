/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import static bd.bd.session;
import java.text.ParseException;
import metier.Personnel;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author lizhiwang
 */
public class TestHibernate {
     public static void main (String[] args) throws ParseException{
           String mdp =  bd.connection("21613265").getMotDePasse();
           String type = bd.connection("21613265").getType();
           System.out.println(mdp);
           System.out.println(type);
     }
}
