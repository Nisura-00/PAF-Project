package com;
import model.Notifications;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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


	//API for update items
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateItems(String itemData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		// Read the values from the JSON object
		String notificationId = itemObject.get("notificationId").getAsString();
		String notificationCode = itemObject.get("notificationCode").getAsString();
		String message = itemObject.get("message").getAsString();
		String date = itemObject.get("date").getAsString();
		String timePeriod = itemObject.get("timePeriod").getAsString();
		String area = itemObject.get("area").getAsString();
		String establishedBy = itemObject.get("establishedBy").getAsString();

		String output = itemObj.updateItem(notificationId, notificationCode, message, date, timePeriod, area, establishedBy);
		
		return output;
	}
}
