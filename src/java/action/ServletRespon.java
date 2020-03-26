/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import bd.bd;
import bd.util;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.Creneau;
import metier.Matiere;
import metier.Personnel;

/**
 *
 * @author lizhiwang
 */
public class ServletRespon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("application/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try (PrintWriter out = response.getWriter())
			{
			/*----- Ecriture de la page XML -----*/
			out.println("<?xml version=\"1.0\"?>");
                        
                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR);
                        String idetudiant = request.getParameter("idetudiant");
                        String mois = request.getParameter("mois");
                        String annee =request.getParameter("annee");
                        String anneemois =""; 
                        if(annee.equals(null)){
                            System.out.println("not select year");
                             anneemois = Integer.toString(year)+"-"+mois;
                        }else{
                             anneemois =annee+"-"+mois;
                        }
                        out.println("<liste_etatpresence>");
//                        System.out.println(idetudiant);
                        //present
                        List<Creneau> clist =bd.getHeureE(idetudiant,anneemois);
                        //absent
                        List<Creneau> abslist =bd.getHeurePresent(idetudiant,anneemois,"absent");
                        //retard
                        List<Creneau> dlist =bd.getHeureD(idetudiant,anneemois);
                        Personnel p =bd.getEtudiantinfo(idetudiant);
                        String nom =p.getNom();
                        String prenom =p.getPrenom();
                        String photo =p.getPhoto();
                        List<String> listCreneau= new ArrayList<>();    
                        
                        out.println("<nom>" + nom + "</nom>");
                        out.println("<prenom>" + prenom + "</prenom>");
                        out.println("<photo>" + photo + "</photo>"); 
                        
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
                        util.addlist(list, bd.output(clist, 0));
                        util.addlist(list, bd.output(abslist,0));
                        util.addlist(list, bd.output(dlist,0));
                        //eliminer les duplicate
                        list=util.removeDuplicate(list);
                        //date
                        
                        for(int i=0;i<list.size();i++){
                            out.println("<date>" + list.get(i) + "</date>");
                        }
                        // heure prsence 
                        int s=clist.size();
                        for(int i=0;i<list.size();i++){
                               if(i<s){
                                    String str=list.get(i);
                                    if((bd.output(clist,0)).contains(str)){
                                        for(String st:((bd.output(clist,1)))){
                                            int heure=Integer.parseInt(st);
                                            out.println("<Eheure>" + (heure/60) + "</Eheure>");
                                        }
                                    }
                                    else{
                                        out.println("<Eheure>" + 0 + "</Eheure>");
                                        s++;
                                    }
                                }
                                else{
                                    out.println("<Eheure>" + 0 + "</Eheure>");
                                }
                                
                            }
                        int sizere=clist.size();
                        for(int i=0;i<list.size();i++){
                                if(i<sizere){
                                    String str=list.get(i);
                                    if((bd.output(dlist,0)).contains(str)){
                                        for(String st:((bd.output(dlist,1)))){
                                            int heure=Integer.parseInt(st);
                                            out.println("<Dheure>" + (heure/60) + "</Dheure>");
                                        }
                                    }
                                    else{
                                        out.println("<Dheure>" + 0 + "</Dheure>");
                                        sizere++;
                                        }
                                }
                                else{
                                    out.println("<Dheure>" + 0 + "</Dheure>");
                                }
                                
                            }
                        int sizeabs=clist.size();
                        for(int i=0;i<list.size();i++){
                              if(i<sizeabs){
                                    String str=list.get(i);
                                    if((bd.output(abslist,0)).contains(str)){
                                        for(String st:((bd.output(abslist,1)))){
                                            int heure=Integer.parseInt(st);
                                            out.println("<absheure>" + (heure/60) + "</absheure>");
                                        }
                                    }
                                    else{
                                        out.println("<absheure>" + 0 + "</absheure>");
                                        sizeabs++;
                                    }
                                }
                                else{
                                    
                                    out.println("<absheure>" + 0 + "</absheure>");
                                }
                                
                            }
                        
                        
                        
                        
                        //heure presence
//                        for(int i=0;i<max;i++){
//                            int ecart=max-clist.size();
//                            
//                            for(int j=i;j<ecart;j++){
//                                System.out.println("heure"+i+" "+bd.output(clist, 1).get(j));
//                                String str=bd.output(clist, 1).get(j);
//                                out.println("<heure>" + (Integer.parseInt(str)/60) + "</heure>");
//                            }
//                            for(int j=ecart;j<max;j++){
//                                out.println("<heure>" + 0 + "</heure>");
//                            }
//                            
//                           }  
                        
//                        for(int i=0;i<max;i++){
//                            String str=bd.output(retalist, 1).get(i);
//                            if(str==null){
//                                str="0";
//                                out.println("<reheure>" + (Integer.parseInt(str)/60) + "</reheure>");
//                            }else{
//                                out.println("<reheure>" + (Integer.parseInt(str)/60) + "</reheure>");
//                            }
//                            
//                        }  
//                        for(int i=0;i<max;i++){
//                            String str=bd.output(abslist, 1).get(i);
//                            if(str==null){
//                                str="0";
//                                out.println("<absheure>" + (Integer.parseInt(str)/60) + "</absheure>");
//                            }else{
//                                out.println("<absheure>" + (Integer.parseInt(str)/60) + "</absheure>");
//                            }
//                            
//                        }  
//                        for(String str :bd.getHeurePresentidCreneau(idetudiant, anneemois)){
//                            out.println("<idCreneau>" + str + "</idCreneau>");
//                            listCreneau.add(str);
//                        }  
                        HttpSession session = request.getSession(true);
                        session.setAttribute("id", listCreneau);
                        out.println("</liste_etatpresence>");
    }    }
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doGet(request, response); }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */



    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>// </editor-fold>

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
