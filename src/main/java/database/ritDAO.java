package database;

import java.sql.*;
import java.util.UUID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class ritDAO extends BaseDAO{
public String saveRit(String beginpunt, String eindpunt, int afstand, int duur,String email, String date, String ritnaam, String tijd)
{
	String querydan = "empty";	
	 String query = "INSERT INTO ritten values (?,?,?,?,?,?,?,?,?)";
	 
	 try (Connection con = super.getConnection()) {
		 
	 PreparedStatement pstmt = con.prepareStatement(query);
	 pstmt.setString(1, beginpunt);
	 pstmt.setString(2, eindpunt);
	 pstmt.setInt(3, afstand);
	 pstmt.setInt(4, duur);
	 pstmt.setString(5, email);
	 pstmt.setString(6, date);
	 pstmt.setString(7, ritnaam);
	 pstmt.setString(8, tijd);
	 pstmt.setString(9, UUID.randomUUID().toString());
	 querydan=pstmt.toString();
	 PreparedStatement pstmt2 = con.prepareStatement(querydan);
	 int i= pstmt2.executeUpdate();
	 if (i!=0)  //Just to ensure data has been inserted into the database
	 return "SUCCESS"; 
	 }
	 catch(SQLException e)
	 {
	 e.printStackTrace();
	 }
	 return "Oops.. Something went wrong there..!"+querydan;  // On failure, send a message from here.
	 }
	 }
	
