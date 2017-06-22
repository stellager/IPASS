package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.removeDAO;

public class DeleteServlet {
	private static final long serialVersionUID = 1L;
	  public DeleteServlet() {
	    	}
  protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                  throws ServletException, IOException {  
  	
  	
        
      String id=request.getParameter("id");  
    
      removeDAO  removeDAO  = new  removeDAO();

    String userLoggedIn = removeDAO.removeRit(id);
      if(userLoggedIn.contains("SUCCESS"))   
      {
      	

           
      	
      	     
      	
      }
      else   //On Failure, display a meaningful message to the User.
      {
      	
      }
    
  }  
}
