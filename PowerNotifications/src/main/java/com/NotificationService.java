package com;
import model.Notifications;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Notifications")
public class NotificationService {
	Notifications itemObj = new Notifications();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readItems();
	}

	// insert items API
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String insertItems(@FormParam("notificationCode") String notificationCode, @FormParam("message") String message,
			@FormParam("date") String date, @FormParam("timePeriod") String timePeriod , @FormParam("area") String area, @FormParam("establishedBy") String establishedBy) {
		String output = itemObj.insertItem(notificationCode, message, date, timePeriod, area, establishedBy);
		return output;
	}

}
