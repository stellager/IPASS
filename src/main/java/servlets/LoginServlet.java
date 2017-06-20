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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
    	
      
          
        String email=request.getParameter("email");  
        String wachtwoord=request.getParameter("wachtwoord");  
        loginDAO loginDAO = new loginDAO();
  
      String userLoggedIn = loginDAO.loginUser(email,wachtwoord);
        if(userLoggedIn.equals("SUCCESS"))   
        {
        	 PrintWriter writer = response.getWriter();
             
             // build HTML code
        	 String cookie = " document.cookie = 'cookiename=' "+email+";";
             String htmlResponse = "<html>";
        	htmlResponse+="<body onload='createCookie();'> </body>";
        	htmlResponse+="<script type='text/javascript'> function createCookie() {";
        	htmlResponse+=cookie;
        	htmlResponse+= "window.location.replace('home_login.html');";
        	htmlResponse+= " }</script></html";
        	     
        	writer.println(htmlResponse);
        }
        else   //On Failure, display a meaningful message to the User.
        {
        	 response.setContentType("text/plain");
             response.getWriter().println("FALSE"+email+wachtwoord+"-"+userLoggedIn);
        }
      
    }  
}  