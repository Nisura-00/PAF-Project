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

	
	
}
