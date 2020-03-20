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
     protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
	    String identifiant = request.getParameter("lg_username");
            String mdp_p = request.getParameter("lg_password");
            System.out.println("identifiant"+identifiant);						  
        String mdp =  bd.connection(identifiant).getMotDePasse();
	  System.out.println("mdp "+mdp);		 
        String type = bd.connection(identifiant).getType();
	  System.out.println("type "+type);		 
//            String mdp="123456";
//            String type ="Enseignat";
			if(! mdp_p.equals(mdp))
					{
					request.setAttribute("msg_erreur", "Mot de passe erroné !");
					request.getRequestDispatcher("login").forward(request, response);
					}
               else
					{
					switch(type)
						{
						case "Etudiant": 
							request.getRequestDispatcher("etudiantPage").forward(request, response);
							break;
						case "Enseigant":
                                                        request.getRequestDispatcher("faireappelPage").forward(request, response);
							break;
						default:
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
