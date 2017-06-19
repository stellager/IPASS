package servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.registerDAO;

public class RegisterServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
public RegisterServlet() {
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//Copying all the input parameters in to local variables
String name = request.getParameter("firstname");
String email = request.getParameter("email");
String wachtwoord = request.getParameter("wachtwoord");
 
registerDAO registerDao = new registerDAO();
//The core Logic of the Registration application is present here. We are going to insert user data in to the database.
String userRegistered = registerDao.registerUser(email,name,wachtwoord);
if(userRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
{
request.getRequestDispatcher("home.html").forward(request, response);
}
else   //On Failure, display a meaningful message to the User.
{
request.setAttribute("errMessage", userRegistered);
request.getRequestDispatcher("/Register.jsp").forward(request, response);
}
}
}