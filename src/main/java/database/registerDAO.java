package database;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class registerDAO extends BaseDAO{
public String registerUser(String firstname, String email, String wachtwoord)
{

	 String query = "INSERT INTO users values (?,?,?)";

	 try (Connection con = super.getConnection()) {

	 PreparedStatement pstmt = con.prepareStatement(query);
	 pstmt.setString(1, firstname);
	 pstmt.setString(2, email);
	 pstmt.setString(3, wachtwoord);
	 int i= pstmt.executeUpdate();
	 if (i!=0)  //Just to ensure data has been inserted into the database
	 return "SUCCESS"; 
	 }
	 catch(SQLException e)
	 {
	 e.printStackTrace();
	 }
	 return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
	 }
	 }
