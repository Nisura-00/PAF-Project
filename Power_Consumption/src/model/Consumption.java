package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Consumption {

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

	public String insertConsumption(String Cname, String Caddress, String AccNo, String Cdate, String UnitNo, String PriceUnit, String TotalAmount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into consumpation(`Cid`, `Cname`, `Caddress`, `AccNo`, `Cdate`, `UnitNo`, `PriceUnit`, `TotalAmount`)" + " values ( ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Cname);
			preparedStmt.setString(3, Caddress);
			preparedStmt.setString(4, AccNo);
			preparedStmt.setString(5, Cdate);
			preparedStmt.setString(6, UnitNo);
			preparedStmt.setString(7, PriceUnit);
			preparedStmt.setString(8, TotalAmount);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the consumpation.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String readConsumption() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Consumption ID</th><th>Consumer Name</th><th>Consumer Address</th><th>Account No</th><th>Date</th><th>No of Units</th><th>Price per Unit</th><th>Total Amount</th></tr>";
			String query = "select * from consumpation";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Cid = Integer.toString(rs.getInt("Cid"));
				String Cname = rs.getString("Cname");
				String Caddress = rs.getString("Caddress");
				String AccNo = rs.getString("AccNo");
				String Cdate = rs.getString("Cdate");
				String UnitNo = rs.getString("UnitNo");
				String PriceUnit = rs.getString("PriceUnit");
				String TotalAmount = rs.getString("TotalAmount");

				output += "<tr><td>" + Cid + "</td>";
				output += "<td>" + Cname + "</td>";
				output += "<td>" + Caddress + "</td>";
				output += "<td>" + AccNo + "</td>";
				output += "<td>" + Cdate + "</td>";
				output += "<td>" + UnitNo + "</td>";
				output += "<td>" + PriceUnit + "</td>";
				output += "<td>" + TotalAmount + "</td>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the consumpation.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateConsumption(String Cid, String Cname, String Caddress, String AccNo, String Cdate, String UnitNo, String PriceUnit, String TotalAmount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE consumpation SET Cname=?,Caddress=?,AccNo=?,Cdate=?,UnitNo=?,PriceUnit=?,TotalAmount=? WHERE Cid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, Cname);
			preparedStmt.setString(2, Caddress);
			preparedStmt.setString(3, AccNo);
			preparedStmt.setString(4, Cdate);
			preparedStmt.setString(5, UnitNo);
			preparedStmt.setString(6, PriceUnit);
			preparedStmt.setString(7, TotalAmount);
			preparedStmt.setInt(8, Integer.parseInt(Cid));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the consumpation.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	

}
