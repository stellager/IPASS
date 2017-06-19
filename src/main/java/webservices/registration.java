package webservices;

import javax.ws.rs.*;
import javax.xml.ws.*;

import database.*;

@Path("/registration")
public class registration {
	
@POST
public String RegisterUser(@FormParam("firstname") String username, @FormParam("email") String email, @FormParam("wachtwoord") String wachtwoord){
	System.out.println("er is wat gebeurd");
	try{
		registerDAO dao = new registerDAO();

		return "Registration Succesful";

	
	}catch (Exception e) {
		return "Registration Unsuccesful";
	}
}
	
}
