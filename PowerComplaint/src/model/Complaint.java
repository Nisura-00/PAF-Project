package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Complaint {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electro?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	

	
	public String readComplaint() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Complaint ID</th><th>Person Name</th><th>Complaint NIC</th><th>Area</th><th>Account No</th><th>Address</th><th>Email</th><th>Complaint</th></tr>";
			String query = "select * from complaint";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String cID = Integer.toString(rs.getInt("cID"));
				String PerName = rs.getString("PerName");
				String PerNIC = rs.getString("PerNIC");
				String cArea = rs.getString("cArea");
				String cAccNo = rs.getString("cAccNo");
				String cAddress = rs.getString("cAddress");
				String cEmal = rs.getString("cEmal");
				String Comp = rs.getString("Comp");

				output += "<tr><td>" + cID + "</td>";
				output += "<td>" + PerName + "</td>";
				output += "<td>" + PerNIC + "</td>";
				output += "<td>" + cArea + "</td>";
				output += "<td>" + cAccNo + "</td>";
				output += "<td>" + cAddress + "</td>";
				output += "<td>" + cEmal + "</td>";
				output += "<td>" + Comp + "</td>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the complaint.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	

}
