package model;


import java.sql.*;
public class Payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");     

			
			// Provide the correct details: DBServer/DBName, username, password
			
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/powereg","root", ""); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertPayment(String paymentCode, String cardHolder, String cardNo, String cvv, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			
			// create a prepared statement
			String query = " insert into payment( paymentID, paymentCode, cardHolder, cardNo, cvv, amount)"
					+ " values( ?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, paymentCode);
			preparedStmt.setString(3, cardHolder);
			preparedStmt.setInt(4, Integer.parseInt(cardNo));
			preparedStmt.setInt(5, Integer.parseInt(cvv));
			preparedStmt.setDouble(6, Double.parseDouble(amount));
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while Inserting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	
	
	//reading items
	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Payment Code</th><th>Card Holder's Name</th>" + "<th>Card No</th>"
					+ "<th>CVV</th>" + "<th>Amount</th>"+"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String paymentCode = rs.getString("paymentCode");
				String cardHolder = rs.getString("cardHolder");
				String cardNo = rs.getString("cardNo");
				String cvv = rs.getString("cvv");
				String amount = Double.toString(rs.getDouble("amount"));
				
				// Add into the html table
				output += "<tr><td>" + paymentCode + "</td>";
				output += "<td>" + cardHolder + "</td>";
				output += "<td>" + cardNo + "</td>";
				output += "<td>" + cvv + "</td>";
				output += "<td>" + amount + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='paymentID' type='hidden' value='" + paymentID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}