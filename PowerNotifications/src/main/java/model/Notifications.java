package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Notifications {
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powereg", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	public String insertItem(String notificationCode, String message, String date, String timePeriod, String area, String establishedBy)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into interruption (`notificationId`,`notificationCode`,`message`,`date`,`timePeriod`,`area`, `establishedBy`)"
	 + " values (?, ?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, notificationCode);
	 preparedStmt.setString(3, message);
	 preparedStmt.setString(4, date);
	 preparedStmt.setString(5, timePeriod);
	 preparedStmt.setString(6, area);
	 preparedStmt.setString(7, establishedBy);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	public String readItems()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Notification Code</th><th>Message</th>" +
	 "<th>Date</th>" +
	 "<th>Time Period</th>" + "<th>Area</th>" + "<th>Established By</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from interruption";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String notificationId = Integer.toString(rs.getInt("notificationId"));
	 String notificationCode = rs.getString("notificationCode");
	 String message = rs.getString("message");
	 String date = rs.getString("date");
	 String timePeriod = rs.getString("timePeriod");
	 String area = rs.getString("area");
	 String establishedBy = rs.getString("establishedBy");
	 
	 
	 // Add into the html table
	 output += "<tr><td>" + notificationCode + "</td>";
	 output += "<td>" + message + "</td>";
	 output += "<td>" + date + "</td>";
	 output += "<td>" + timePeriod + "</td>";
	 output += "<td>" + area + "</td>";
	 output += "<td>" + establishedBy + "</td>";
	 
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='notificationId' type='hidden' value='" + notificationId
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	public String updateItem(String notificationId, String notificationCode, String message, String date, String timePeriod, String area, String establishedBy)
	{
		String output = "";
		 try{
		 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE interruption SET notificationCode=?, message=?, date=?, timePeriod=?, area=?, establishedBy=? WHERE notificationId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, notificationCode);
			 preparedStmt.setString(2, message);
			 preparedStmt.setString(2, date);
			 preparedStmt.setString(4, timePeriod);
			 preparedStmt.setString(5, area);
			 preparedStmt.setString(6, establishedBy);
			 preparedStmt.setInt(7, Integer.parseInt(notificationId));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
		 }catch (Exception e){
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
		 	}
		 return output;
	}

}
