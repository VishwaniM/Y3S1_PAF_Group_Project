package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import model.user;

@Path("/userDetails")
public class userManage {
	user UserDetailsObj = new user();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String UserDetails() {
		return UserDetailsObj.readUserDetails();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUserDetails(@FormParam("userID") String userID,			
	 @FormParam("uName") String uName,
	 @FormParam("email") String email,
	 @FormParam("pnumber") String pnumber
	 )
	{
	 String output = UserDetailsObj.insertUserDetails(userID, uName, email, pnumber);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUserDetails(String UserData)
	{
	//Convert the input string to a JSON object
	 JsonObject billObject = new JsonParser().parse(UserData).getAsJsonObject();
	//Read the values from the JSON object
	 String userID = billObject.get("userID").getAsString();
	 String uName = billObject.get("uName").getAsString();
	 String email = billObject.get("email").getAsString();
	 String pnumber = billObject.get("pnumber").getAsString();
	
	 String output = UserDetailsObj.updateUserDetails(userID, uName, email, pnumber);
	return output;
	} 
	
	@DELETE 
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUserDetails(String UserData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(UserData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String userID = doc.select("userID").text();
	 String output = UserDetailsObj.deleteUserDetails(userID);
	return output;
	}
}

