package servlets;
import database.calendarDAO;
import java.io.IOException;  
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import database.registerDAO;  

public class CalendarServlet extends HttpServlet {  
	private static final long serialVersionUID = 1L;
	  public CalendarServlet() {
	    	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	
    	String start = request.getParameter("start");
    	String eind = request.getParameter("end");
    	String email = request.getParameter("email");
    	
    	calendarDAO calendarDAO = new calendarDAO();
    	  
    	JsonArray getCalendar = calendarDAO.getCalendar(email);
        response.getWriter().write(getCalendar.toString());
    	
    	
    }
    

}