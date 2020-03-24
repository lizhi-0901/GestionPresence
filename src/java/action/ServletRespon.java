/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import bd.bd;
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
                        List<Creneau> clist =bd.getHeurePresent(idetudiant,anneemois);
                        Personnel p =bd.getEtudiantinfo(idetudiant);
                        String nom =p.getNom();
                        String prenom =p.getPrenom();
                        String photo =p.getPhoto();
                        List<String> listCreneau= new ArrayList<>();    
                        
                        out.println("<nom>" + nom + "</nom>");
                        out.println("<prenom>" + prenom + "</prenom>");
                        out.println("<photo>" + photo + "</photo>");        
                        for(String str :bd.output(clist,0)){
                            out.println("<date>" + str + "</date>");
                        }
                        for(String str :bd.output(clist,1)){
                            out.println("<heure>" + str + "</heure>");
                        }  
                        
                        
                        for(String str :bd.getHeurePresentidCreneau(idetudiant, anneemois)){
                            out.println("<idCreneau>" + str + "</idCreneau>");
                            listCreneau.add(str);
                        }  
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
