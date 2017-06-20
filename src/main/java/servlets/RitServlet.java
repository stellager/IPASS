package servlets;
import java.io.IOException;
import javax.servlet.ServletException;
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
String beginpunt = request.getParameter("beginpunt");
String eindpunt = request.getParameter("eindpunt");
String afstand = request.getParameter("afstand");
String duur = request.getParameter("duur");
String date = request.getParameter("date");
String email = request.getParameter("email");
String ritnaam = request.getParameter("ritnaam");
String tijd = request.getParameter("tijd");
ritDAO ritDAO = new ritDAO();
//The core Logic of the Registration application is present here. We are going to insert user data in to the database.
String ritOpgeslagen = ritDAO.saveRit(beginpunt,eindpunt, afstand, duur,email, date, ritnaam,tijd);
if(ritOpgeslagen.equals("SUCCESS"))   //On success, you can display a message to user on Home page
{
request.getRequestDispatcher("/sign_up_succesful.html").forward(request, response);
}
else   //On Failure, display a meaningful message to the User.
	request.getRequestDispatcher("/home.html").forward(request, response);
{

}
}
}