package com;

import model.Payment; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON



@Path("/Payments")
public class PaymentService {

	Payment itemObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readPayment();
	}
	
	// insert items API
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)

		public String insertPayment(@FormParam("paymentCode") String paymentCode, @FormParam("cardHolder") String cardHolder,
				@FormParam("cardNo") String cardNo, @FormParam("cvv") String cvv, @FormParam("amount") String amount) {
			String output = itemObj.insertPayment(paymentCode, cardHolder, cardNo, cvv, amount);
			return output;
		}
}

