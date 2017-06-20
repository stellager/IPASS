package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;


public class loginDAO extends BaseDAO{
	public String loginUser(String email,String wachtwoord)
	{
		String query = "SELECT COUNT(*) FROM users WHERE email = ? AND wachtwoord = ?";

		 try (Connection con = super.getConnection()) {

		 PreparedStatement pstmt = con.prepareStatement(query);
		 pstmt.setString(1, email);
		 pstmt.setString(3, wachtwoord);
		 
		 final ResultSet i= pstmt.executeQuery();
		 final int count = i.getInt(1);
		
		 if (count!=0)  //Just to ensure data has been inserted into the database
		 return "SUCCESS"; 
		 }
		 catch(SQLException e)
		 {
		 e.printStackTrace();
		 }
		 return "Failure";  // On failure, send a message from here.
		 }
	}

