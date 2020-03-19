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
     protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		try (PrintWriter out = response.getWriter())
			{
			/*----- Ecriture de la page XML -----*/
                        // out.println("connecter"); // Attention au "out" utilise "System.out" si tu veux voir un affichage en console Netbeans
			
			out.println("<?xml version=\"1.0\"?>");
			

			
			String identifiant = request.getParameter("identifiant");
                        String mdp_p = request.getParameter("mdp");
                       // String mdp =request.getParameter("mdp");
//                        System.out.println("identifiant_param"+identifiant);
//                        System.out.println("mdp_param"+mdp_p);
                          //  System.out.println(mdp);
                        String mdp =  bd.connection(identifiant).getMotDePasse();
                        String type = bd.connection(identifiant).getType();
//                            System.out.println("type::::::"+type);
//                            System.out.println("mdp::::::"+mdp);
                        if(! mdp_p.equals(mdp)){
                            System.out.println("mdp wrong");
                            String msg="wrong mot de pass";
                            out.println("<msg>" + msg + "</msg>");
                        }    
                        else{
                        switch(type) {
                            case "Etudiant": 
                                request.getRequestDispatcher("etudiantPage").forward(request, response);
                                break;
                            case "":
                                break;
                            default:
                        }
                            
                        }
                        }
        
     }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
