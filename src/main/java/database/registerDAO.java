package database;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class registerDAO extends BaseDAO{
public String registerUser(String email, String firstname, String wachtwoord)
{

	 String query = "INSERT INTO users values (?,?,?)";

	 try (Connection con = super.getConnection()) {

	 PreparedStatement pstmt = con.prepareStatement(query);
	 pstmt.setString(1, email);
	 pstmt.setString(2, firstname);
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
public String saveRit(String beginpunt, String eindpunt, int afstand, int duur,String email, String date, String ritnaam, String tijd)
{
	String querydan = "empty";	
	 String query = "INSERT INTO ritten values (?,?,?,?,?,?,?,?)";

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
	 querydan=pstmt.toString();
	 int i= pstmt.executeUpdate();
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
