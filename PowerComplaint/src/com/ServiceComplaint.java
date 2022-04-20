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

	
	

	
	
}
