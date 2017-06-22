package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class removeDAO extends BaseDAO {
	public String removeRit(String id)
	{
		String query = "DELETE FROM ritten WHERE ritid = ?";

		 try (Connection con = super.getConnection()) {

		 PreparedStatement pstmt = con.prepareStatement(query);
		 pstmt.setString(1, id);
		
		 
		 ResultSet resultset= pstmt.executeQuery();
		 
		 if (resultset.next() ) {
			 
			 return "SUCCESS"+resultset.getString(2);
		 }else{
			 
		 return "NOUSER"; 
		 
		 }}
		 
		 catch(SQLException e)
		 {
		 e.printStackTrace();
		 }
		 
		 return "Failure";  // On failure, send a message from here.
		 }
	

}
