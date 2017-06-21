package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

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
		    	
		    	job.add("title", resultset.getString(7));
		    	
		    	String datetime = resultset.getString(6)+"T"+resultset.getString(8);
		    	
		    	
		    	timefix timefix = new timefix();
			 	String tijd = timefix.addedTime(resultset.getString(8),resultset.getInt(4));
		    	String status = tijd.substring(tijd.indexOf("x"));
		    	String eindtijd = tijd.substring(0,tijd.indexOf("x"));
		    	if(status.equals("xsamedate")){
		    		job.add("end",datetime+"T"+ eindtijd);
		    		job.add("start", datetime);
		    		jab.add(job);
		    	}
		    	if(status.equals("xnextdate")){
		  
		    		String sourceDate =  resultset.getString(6);
		    		String newdatetime = LocalDate.parse(sourceDate).plusDays(1).toString();
		    		job.add("end", newdatetime+"T"+eindtijd);
		    		job.add("start", newdatetime);
		    	}
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
		 job.add("title", "Failure");
		 JsonArray array = jab.build();
		 return array; // On failure, send a message from here.
		 }
	}

