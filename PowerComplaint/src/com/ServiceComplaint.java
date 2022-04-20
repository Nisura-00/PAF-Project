package com;

import model.Complaint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Complaint")
public class ServiceComplaint {
	Complaint ComplaintObj = new Complaint();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readComplaint() {
		return ComplaintObj.readComplaint();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertComplaint(@FormParam("PerName") String PerName, 
			@FormParam("PerNIC") String PerNIC,
			@FormParam("cArea") String cArea,
			@FormParam("cAccNo") String cAccNo,
			@FormParam("cAddress") String cAddress,
			@FormParam("cEmal") String cEmal,
			@FormParam("Comp") String Comp) {
		String output = ComplaintObj.insertComplaint(PerName, PerNIC, cArea, cAccNo, cAddress, cEmal,Comp);
		return output;

	}

	
	

	
	
}
