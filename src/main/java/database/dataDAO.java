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
		    	
		    	double raw= resultset.getInt(2)/3600;
		    	 
		    	    String[] arr=String.valueOf(raw).split("\\.");
		    	    int uren = (int)raw;
		    	    double minuten1 = (raw-uren)*100;
		    	    int minuten = (int)(minuten1);
		    	  
			    double rawkm = resultset.getInt(3)/1000;
			    double round = (int)rawkm*10 /10;
			 	job.add("ritnaam", resultset.getString(1));
			 	job.add("keergereden",Integer.toString(resultset.getInt(4)));
			 	job.add("totaalkm", String.valueOf(round));
			 	job.add("totaaltijd", "Totale tijd: "+Integer.toString(uren)+" uur en "+Integer.toString(minuten)+" minuten.");
			 	
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
