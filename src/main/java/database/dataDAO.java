package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

public class dataDAO extends BaseDAO{
	JsonArrayBuilder jab = Json.createArrayBuilder();
	public JsonArray getData(String email)
	{
		String query = "SELECT ritnaam , SUM(duur) AS som , SUM(afstand), count(ritnaam) as count FROM ritten where email = ? AND date >= ? AND date <? GROUP BY ritnaam ORDER BY count DESC";
		 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
		LocalDate localDate = LocalDate.now();
		LocalDate volgendeDate = localDate.plusMonths(1);
		
		
		 String querycurrmonth = dtf.format(localDate)+"-01";
		 String querynextmonth= dtf.format(volgendeDate)"-01";
		 
		 try (Connection con = super.getConnection()) {

		 PreparedStatement pstmt = con.prepareStatement(query);
		 pstmt.setString(1, email);
		 pstmt.setString(2, querycurrmonth);
		 pstmt.setString(3, querynextmonth);
		
		 
		 ResultSet resultset= pstmt.executeQuery();
		 
		 int count=0;
		 while (resultset.next() ) {
			 count+=1;
		    	JsonObjectBuilder job = Json.createObjectBuilder();
		    	
		    	double raw= resultset.getInt(2);
		    	double raw1 = raw/3600;
		    	    String[] arr=String.valueOf(raw).split("\\.");
		    	    int uren = (int)raw1;
		    	    double minuten1 = raw1-uren;
		    	    double minuten2 = minuten1*100;
		    	    int minuten = (int)minuten2;
		    	  
			    double rawkm = resultset.getInt(3)/1000;
			    double round = (int)rawkm*10 /10;
			 	job.add("ritnaam", resultset.getString(1));
			 	job.add("keergereden",Integer.toString(resultset.getInt(4)));
			 	job.add("totaalkm", String.valueOf(round));
			 	job.add("totaaltijd", uren+" uur en "+minuten+" minuten.");
			 	
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
