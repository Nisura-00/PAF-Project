package com;

import model.Consumption;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Consumption")
public class ServiceConsumption {
	Consumption ConsumptionObj = new Consumption();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readConsumption() {
		return ConsumptionObj.readConsumption();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConsumption(@FormParam("Cname") String Cname, 
			@FormParam("Caddress") String Caddress,
			@FormParam("AccNo") String AccNo,
			@FormParam("Cdate") String Cdate,
			@FormParam("UnitNo") String UnitNo,
			@FormParam("PriceUnit") String PriceUnit,
			@FormParam("TotalAmount") String TotalAmount) {
		String output = ConsumptionObj.insertConsumption(Cname, Caddress, AccNo, Cdate, UnitNo, PriceUnit,TotalAmount);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateConsumption(String consumpationData) {
		// Convert the input string to a JSON object
		JsonObject ProObject = new JsonParser().parse(consumpationData).getAsJsonObject();

		// Read the values from the JSON object
		String Cid = ProObject.get("Cid").getAsString();
		String Cname = ProObject.get("Cname").getAsString();
		String Caddress = ProObject.get("Caddress").getAsString();
		String AccNo = ProObject.get("AccNo").getAsString();
		String Cdate = ProObject.get("Cdate").getAsString();
		String UnitNo = ProObject.get("UnitNo").getAsString();
		String PriceUnit = ProObject.get("PriceUnit").getAsString();
		String TotalAmount = ProObject.get("TotalAmount").getAsString();

		String output = ConsumptionObj.updateConsumption(Cid, Cname, Caddress, AccNo, Cdate, UnitNo, PriceUnit, TotalAmount);
		return output;
	}

	
}
