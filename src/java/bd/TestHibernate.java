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
           Session session = HibernateUtil.getSessionFactory().getCurrentSession();
           Transaction t = session.beginTransaction();
           Personnel p1 = (Personnel) session.get(Personnel.class, "21613265");
           System.out.println(p1);
          
            t.commit();
       //  int i=bd.connection("lizhi");
       //  System.out.println(i);
     }
}
