package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import java.sql.*;


public class calendarDAO extends BaseDAO{
	JsonArrayBuilder jab = Json.createArrayBuilder();
	public JsonArray getCalendar(String email)
	{
		String query = "SELECT * FROM ritten WHERE email = ?";

		 try (Connection con = super.getConnection()) {

		 PreparedStatement pstmt = con.prepareStatement(query);
		 pstmt.setString(1, email);
		 
		 
		 ResultSet resultset= pstmt.executeQuery();
		 
	    	
		
		 while (resultset.next() ) {
			 
		    	JsonObjectBuilder job = Json.createObjectBuilder();
		    	String checkTime =	resultset.getString(8);
		    	if(checkTime.contains("PM")){
		    		String checked =checkTime.replaceAll("PM", "");
		    		int i =Integer.parseInt(checked);
		    		i+=12;
		    		String Timefinal = Integer.toString(i);
		    		
		    		
		    		String datetime = resultset.getString(6)+"T"+Timefinal;
			    	job.add("title", resultset.getString(7));
			    	job.add("start", datetime);
			    	
			    	job.add("end", datetime);
			    	jab.add(job);
		    	}
		    	else{
		    		String Timefinal =checkTime.replaceAll("AM", "");
		    		String datetime = resultset.getString(6)+"T"+Timefinal;
		    		
		    		
			    	job.add("title", resultset.getString(7));
			    	job.add("start", datetime);
			    	job.add("end", datetime);
			    	jab.add(job);
		    	
		    	}
		    	
		    
			 
		 } 
			 
		 JsonArray array = jab.build();
		 return array;
		 }
		 
		 catch(SQLException e)
		 {
		 e.printStackTrace();
		 }
		 JsonObjectBuilder job = Json.createObjectBuilder();
		 job.add("title", "Failure");
		 JsonArray array = jab.build();
		 return array; // On failure, send a message from here.
		 }
	}

