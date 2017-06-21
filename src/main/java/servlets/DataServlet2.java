package servlets;
import java.io.IOException;
import java.text.ParseException;

import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dataDAO;

public class DataServlet2 extends HttpServlet {  
	private static final long serialVersionUID = 1L;
	  public DataServlet2() {
	    	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	
    
    	String email = request.getParameter("email");
    	
    	dataDAO dataDAO = new dataDAO();
    	  
    	JsonArray getData = null;
		getData = dataDAO.getData2(email);
        response.getWriter().write(getData.toString());
    	
    	
    }
    

}