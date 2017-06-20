package servlets;
import database.loginDAO;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import database.registerDAO;  

public class LoginServlet extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
    	
      
          
        String email=request.getParameter("email");  
        String wachtwoord=request.getParameter("wachtwoord");  
        loginDAO loginDAO = new loginDAO();
  
      String userLoggedIn = loginDAO.loginUser(email,wachtwoord);
        if(userLoggedIn.equals("SUCCESS"))   
        {
        	
        	 response.setContentType("text/plain");
             response.getWriter().println("TRUE");
        }
        else   //On Failure, display a meaningful message to the User.
        {
        	 response.setContentType("text/plain");
             response.getWriter().println("FALSE"+email+wachtwoord);
        }
      
    }  
}  