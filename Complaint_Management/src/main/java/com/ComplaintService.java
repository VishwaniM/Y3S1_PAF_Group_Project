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

import model.Complaint;

@Path("/Complaint")

public class ComplaintService {
	
	Complaint complaintObj = new Complaint();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readComplaint() {
		return complaintObj.readComplaint();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insert(@FormParam("complainerName") String complainerName, 
			@FormParam("email") String email,
			@FormParam("phoneNumber") int phoneNumber,
			@FormParam("complaintCategory") String complaintCategory, 
			@FormParam("complaint") String complaint,
			@FormParam("issueArea") String issueArea,
			@FormParam("remarks") String remarks)

	{												
		String output = complaintObj.insertComplaint(complainerName, email, phoneNumber, complaintCategory, complaint, issueArea,remarks );
		
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateComplaint(String complaintData) 
	{
		// Convert the input string to a JSON object
		JsonObject complantObject = new JsonParser().parse(complaintData).getAsJsonObject();
		// Read the values from the JSON object
		
		int complaintID = complantObject.get("complaintID").getAsInt();
		String complainerName = complantObject.get("complainerName").getAsString();
		String email = complantObject.get("email").getAsString();
		int phoneNumber = complantObject.get("phoneNumber").getAsInt();
		String complaintCategory = complantObject.get("complaintCategory").getAsString();
		String complaint = complantObject.get("complaint").getAsString();
		String issueArea = complantObject.get("issueArea").getAsString();
		String remarks = complantObject.get("remarks").getAsString();
		
		String output = complaintObj.updateComplaint(complaintID, complainerName, email, phoneNumber, complaintCategory, complaint , issueArea, remarks);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteComplaint(String complaintData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(complaintData, "", Parser.xmlParser());
		//Read the value from the element 
		String complaintID = doc.select("complaintID").text();
		String output = complaintObj.deleteComplaint(complaintID);
		return output;
	}
}
	
