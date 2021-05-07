package com.gadget_badget.fundingbodies.service;

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

import com.gadget_badget.fundingbodies.model.FundingBodiesServlet;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/FundingBodies") 
public class fundingbodyService 
{	
	FundingBodiesServlet FundingBodiesObj = new FundingBodiesServlet(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFundingBodies() 
	{ 
		return FundingBodiesObj.readFundingBodies(); 
	} 	

	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertFundingBodies(
		 @FormParam("FundingBodiesCode") String FundingBodiesCode, 
		 @FormParam("customerID") String customerID, 
		 @FormParam("customerEmail") String customerEmail, 
		 @FormParam("customerName") String customerName,
		 @FormParam("totalAmount") String totalAmount,
		 @FormParam("cardNo") String cardNo,
		 @FormParam("cvvNo") String cvvNo)
		 
	{ 
		String output = FundingBodiesObj.insertFundingBodies(FundingBodiesCode, customerID, customerEmail, customerName, totalAmount, cardNo, cvvNo); 
		return output; 
	}	
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFundingBodies(String FundingBodiesData) 
	{ 
		//Convert the input string to a JSON object 
		 JsonObject FundingBodiesObject = new JsonParser().parse(FundingBodiesData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String FundingBodiesID = FundingBodiesObject.get("FundingBodiesID").getAsString(); 
		 String FundingBodiesCode = FundingBodiesObject.get("FundingBodiesCode").getAsString(); 
		 String customerID = FundingBodiesObject.get("customerID").getAsString(); 
		 String customerEmail = FundingBodiesObject.get("customerEmail").getAsString(); 
		 String customerName = FundingBodiesObject.get("customerName").getAsString(); 
		 String totalAmount = FundingBodiesObject.get("totalAmount").getAsString(); 
		 String cardNo = FundingBodiesObject.get("cardNo").getAsString(); 
		 String cvvNo = FundingBodiesObject.get("cvvNo").getAsString(); 
		 
		 String output = FundingBodiesObj.updateFundingBodies(FundingBodiesID, FundingBodiesCode, customerID, customerEmail, customerName, totalAmount, cardNo, cvvNo); 
		 return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteFundingBodies(String FundingBodiesData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(FundingBodiesData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String FundingBodiesID = doc.select("FundingBodiesID").text(); 
		 String output = FundingBodiesObj.deleteFundingBodies(FundingBodiesID); 
		 return output; 
	}
}