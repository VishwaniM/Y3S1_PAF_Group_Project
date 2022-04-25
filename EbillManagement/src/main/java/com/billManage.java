package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
import model.bill;

@Path("/BillDetails")
public class billManage {
	bill BillDetailsObj = new bill();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String BillDetails() {
		return BillDetailsObj.readBillDetails();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBillDetails(@FormParam("billID") String billID,			
	 @FormParam("cusID") String cusID,
	 @FormParam("unit") String unit,
	 @FormParam("amount") String amount
	 )
	{
	 String output = BillDetailsObj.insertBillDetails(billID, cusID, unit, amount);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBillDetails(String billData)
	{
	//Convert the input string to a JSON object
	 JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
	//Read the values from the JSON object
	 String billID = billObject.get("billID").getAsString();
	 String cusID = billObject.get("cusID").getAsString();
	 String unit = billObject.get("unit").getAsString();
	 String amount = billObject.get("amount").getAsString();
	
	 String output = BillDetailsObj.updateBillDetails(billID, cusID, unit, amount);
	return output;
	} 
	
	@DELETE 
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBillDetails(String billData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String billID = doc.select("billID").text();
	 String output = BillDetailsObj.deleteBillDetails(billID);
	return output;
	}
}
