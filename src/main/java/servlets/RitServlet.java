package servlets;
import java.io.IOException;
import database.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ritDAO;

public class RitServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
public RitServlet() {
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//Copying all the input parameters in to local variables
String beginpunt = request.getParameter("begin");
String eindpunt = request.getParameter("eind");
String afstand1 = request.getParameter("afstandMetric");
int afstand = Integer.parseInt(afstand1);
String duur1 = request.getParameter("duurMetric");
int duur= Integer.parseInt(duur1);
String date = request.getParameter("datum");

String ritnaam = request.getParameter("naam");
timefix timefix = new timefix();
String tijd = timefix.fixedTime(request.getParameter("tijd"));
Cookie[] cookies = request.getCookies();

String email=null;
for (Cookie cookie : cookies) {
	   if (cookie.getName().equals("login")) {
		  email = cookie.getValue();
	    }}


ritDAO ritDAO = new ritDAO();

//The core Logic of the Registration application is present here. We are going to insert user data in to the database.

String ritOpgeslagen = ritDAO.saveRit(beginpunt,eindpunt, afstand, duur,email, date, ritnaam,tijd);
if(ritOpgeslagen.equals("SUCCESS"))   //On success, you can display a message to user on Home page
{
	 Cookie RitCookie = new Cookie("ritopgeslagen",ritnaam);
	 response.addCookie(RitCookie);
request.getRequestDispatcher("/planner.html").forward(request, response);
}
else   //On Failure, display a meaningful message to the User.
	
	response.setContentType("text/plain");
	response.getWriter().println(beginpunt+eindpunt+ afstand+ duur+email+ date+ ritnaam+tijd+ ritOpgeslagen);
{

}
}
}