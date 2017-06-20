package servlets;
import database.loginDAO;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import database.registerDAO;  

public class LoginServlet extends HttpServlet {  
	private static final long serialVersionUID = 1L;
	  public LoginServlet() {
	    	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
    	
    	
          
        String email=request.getParameter("email");  
        String wachtwoord=request.getParameter("wachtwoord");  
        loginDAO loginDAO = new loginDAO();
  
      String userLoggedIn = loginDAO.loginUser(email,wachtwoord);
        if(userLoggedIn.contains("SUCCESS"))   
        {
        	String name = userLoggedIn.substring(7);
        	 Cookie LoginCookie = new Cookie("login",email);
        	 Cookie NameCookie = new Cookie("name",name);
        	 response.addCookie(LoginCookie);
        	 response.addCookie(NameCookie);
        	 request.getRequestDispatcher("home_login.html").forward(request, response);
        	 
  
             
        	
        	     
        	
        }
        else   //On Failure, display a meaningful message to the User.
        {
        	 response.setContentType("text/plain");
             response.getWriter().println("FALSE"+email+wachtwoord+"-"+userLoggedIn);
        }
      
    }  
}  