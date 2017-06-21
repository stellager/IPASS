package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

public class dataDAO extends BaseDAO{
	JsonArrayBuilder jab = Json.createArrayBuilder();
	public JsonArray getData(String email)
	{
		String query = "SELECT ritnaam , SUM(duur) AS som , SUM(afstand), count(ritnaam) FROM ritten where email = ? GROUP BY ritnaam ORDER BY som DESC";

		 try (Connection con = super.getConnection()) {

		 PreparedStatement pstmt = con.prepareStatement(query);
		 pstmt.setString(1, email);
		
		 
		 ResultSet resultset= pstmt.executeQuery();
		 
		 int count=0;
		 while (resultset.next() ) {
			 count+=1;
		    	JsonObjectBuilder job = Json.createObjectBuilder();
		    	
		    	job.add("test", resultset.getString(1));
		    	jab.add(job);
			 
		 } 
			 
		 JsonArray array = jab.build();
		 return array;
		 }
		 
		 catch(SQLException e)
		 {
		 e.printStackTrace();
		 }
		 JsonObjectBuilder job = Json.createObjectBuilder();
		 job.add("0", "Failure");
		 JsonArray array = jab.build();
		 return array;// On failure, send a message from here.
		 }
}
