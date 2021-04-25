package com;


	//For REST Service
	import javax.ws.rs.*;
	import javax.ws.rs.core.MediaType;
	//For JSON
	import com.google.gson.*;

import model.Fund;

//For XML
	import org.jsoup.*;
	import org.jsoup.parser.*;
	import org.jsoup.nodes.Document;
	@Path("/Funds")
	public class FundService
	{
	Fund fundObj = new Fund();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds()
	{
		return fundObj.readFunds();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("date") String date,
	@FormParam("totalLend") String totalLend,
	@FormParam("remainingBalance") String remainingBalance,
	@FormParam("noOfInstallments") String noOfInstallments,
	@FormParam("amountPerInstallment") String amountPerInstallment)
	{
	String output = fundObj.insertFund(date, totalLend, remainingBalance, noOfInstallments,amountPerInstallment);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String fundData)
	{
	//Convert the input string to a JSON object
	JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
	//Read the values from the JSON object
	String fundID = fundObject.get("fundID").getAsString();
	String date = fundObject.get("date").getAsString();
	String totalLend = fundObject.get("totalLend").getAsString();
	String remainingBalance = fundObject.get("remainingBalance").getAsString();
	String noOfInstallments = fundObject.get("noOfInstallments").getAsString();
	String amountPerInstallment = fundObject.get("amountPerInstallment").getAsString();
	
	String output = fundObj.updateFund( fundID,date,  totalLend,  remainingBalance,  noOfInstallments, amountPerInstallment);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String fundData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());
	
	String fundID = doc.select("fundID").text();
	String output = fundObj.deleteFund(fundID);
	return output;
	}
	}


