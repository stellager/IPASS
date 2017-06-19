package database;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class ritDAO extends BaseDAO{
public String saveRit(String email, String firstname, String wachtwoord)
{

	 String query = "INSERT INTO ritten values (?,?,?,?,?,?,?,?)";

	 try (Connection con = super.getConnection()) {

	 PreparedStatement pstmt = con.prepareStatement(query);
	 pstmt.setString(1, UUID.randomUUID().toString());
	 pstmt.setString(2, beginpunt);
	 pstmt.setString(3, eindpunt);
	 pstmt.setString(4, afstand);
	 pstmt.setString(5, duur);
	 pstmt.setString(6, interval);
	 pstmt.setString(7, email);
	 pstmt.setString(8, date);

	 
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
