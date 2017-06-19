package uservalidation;

import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;
import javax.sql.DataSource;  
import java.net.URI;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
  
public class RegisterServlet extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  

response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
          
String naam=request.getParameter("firstname");  
String email=request.getParameter("email");  
String wachtwoord=request.getParameter("wachtwoord");  

          
try{  
Class.forName("org.postgresql.Driver");  
Connection con=DriverManager.getConnection(  
		System.getenv("DATABASE_URL"));  
  
PreparedStatement ps=con.prepareStatement(  
"insert into users values(?,?,?,?)");  
  
ps.setString(1,email);  
ps.setString(2,naam);  
ps.setString(3,wachtwoord);  

          
int i=ps.executeUpdate();  
if(i>0)  
out.print("You are successfully registered...");  
      
          
}catch (Exception e2) {System.out.println(e2);}  
          
out.close();  
}  
  
}  