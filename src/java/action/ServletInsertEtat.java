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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Creneau;
import metier.Groupe;
import metier.Matiere;

/**
 *
 * @author Arslan
 */
public class ServletInsertEtat extends HttpServlet
{
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException
        {
             /*----- Récupération des paramètres -----*/
            String dateString=request.getParameter("date");
            String libelleMatiere = request.getParameter("cours");
            String heure=request.getParameter("heure");
            SimpleDateFormat df = new SimpleDateFormat("yy-mm-dd");
            Date date = df.parse(dateString);
            int heureDeb= Integer.parseInt(heure);
            String idCreneau="";
            System.out.println();

            List<Creneau> listCreneau=bd.getIdCreneau(date, heureDeb, libelleMatiere);
            for(Creneau c:listCreneau){
                idCreneau=c.getIdCreneau();
            }
            
            String idEtudiant=request.getParameter("id");
            String etat=request.getParameter("etat");
            System.out.println(listCreneau);
            System.out.println(idEtudiant);
            System.out.println(etat);
            bd.EnregistrerEtat(idEtudiant, idCreneau, etat);
        }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletInsertEtat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletInsertEtat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

} /*----- Fin de la classe Enregistrer -----*/
