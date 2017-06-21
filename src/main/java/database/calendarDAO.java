package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import java.sql.*;


public class calendarDAO extends BaseDAO{
	JsonArrayBuilder jab = Json.createArrayBuilder();
	public JsonArray getCalendar(String email) throws ParseException
	{
		String query = "SELECT * FROM ritten WHERE email = ?";

		 try (Connection con = super.getConnection()) {

		 PreparedStatement pstmt = con.prepareStatement(query);
		 pstmt.setString(1, email);
		 
		 
		 ResultSet resultset= pstmt.executeQuery();
		 
	    	
		
		 while (resultset.next() ) {
			 
		    	JsonObjectBuilder job = Json.createObjectBuilder();
		    	
		    	
		    	String datetime = resultset.getString(6)+"T"+resultset.getString(8);
		    	String simpledate = resultset.getString(6)+"-"+resultset.getString(8);
		    	job.add("start", datetime);
		    	double uren = resultset.getInt(4)/60;
			    int minuten = (int) uren;
			 
			   
			    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
		    	java.util.Date d = df.parse(simpledate);
		    	Calendar date = Calendar.getInstance();
		    	date.setTime(d);
		    	long t= date.getTimeInMillis();
		    	Date afterAddingTenMins=new Date(t + (minuten * 60000));
		    	date.setTime(afterAddingTenMins);
		    	
		    	
			 	job.add("title", date.toString());
		    	
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

