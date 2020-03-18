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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lizhiwang
 */
public class ServletConnection extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		try (PrintWriter out = response.getWriter())
			{
			/*----- Ecriture de la page XML -----*/
                        // out.println("connecter"); // Attention au "out" utilise "System.out" si tu veux voir un affichage en console Netbeans
			
			out.println("<?xml version=\"1.0\"?>");
			out.println("<liste_auteur>");

			
			String identifiant = request.getParameter("identifiant");
                       // String mdp =request.getParameter("mdp");
                        System.out.println(identifiant);
                          //  System.out.println(mdp);
                        String ret = bd.connection(identifiant);
                       // System.out.println("le resultat:::::::::::::"+ret);
                        out.println("<ret>" + ret + "</ret>"); 
                        
			// La balise <liste_auteur> n'est pas fermée !
			out.println("</liste_auteur>");
			}
        
        
        /*
        coonection 
        */
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
