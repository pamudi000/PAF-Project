package model;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	//A common method to connect to the DB
		private Connection connect()
		{
		Connection con = null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
		}
		public String insertPayment(String userID, String amount, String description, String contact)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		// create a prepared statement
		String query = " INSERT INTO `payment`(`paymentID`, `userID`, `amount`, `description`, `contact`)"
		+ " values (?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, userID);
		preparedStmt.setString(3, amount);
		preparedStmt.setString(4, description);
		preparedStmt.setString(5, contact);
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
		}
		catch (Exception e)
		{
		output = "Error while inserting the payment.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String readPayment()
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }
		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>User ID</th>" +
		"<th>Amount</th>" +
		"<th>Description</th>"+
		"<th>Contact</th>";
		
		String query = "SELECT * FROM payment";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
		String paymentID = Integer.toString(rs.getInt("paymentID"));
		String userID = rs.getString("userID");
		String amount = rs.getString("amount");
		String description = rs.getString("description");
		String contact = rs.getString("contact");
		// Add into the html table
		output += "<tr><td>" + userID + "</td>";
		output += "<td>" + amount + "</td>";
		output += "<td>" + description + "</td>";
		output += "<td>" + contact + "</td>";
		
		}
		con.close();
		// Complete the html table
		output += "</table>";
		}
		catch (Exception e)
		{
		output = "Error while reading the payment.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String updatePayment(String paymentID,String userID, String amount, String description, String contact)
		{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE payment SET userID=?,amount=?,description=?,contact=? WHERE paymentID=? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, userID);
			preparedStmt.setString(2, amount);
			preparedStmt.setString(3, description);
			preparedStmt.setString(4, contact);
			preparedStmt.setInt(5, Integer.parseInt(paymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			}
			catch (Exception e)
			{
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
			}
			return output;
			}
		
		
			public String deletePayment(String paymentID)
			{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from payment where paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			}
			catch (Exception e)
			{
			output = "Error while deleting the payment.";
			System.err.println(e.getMessage());
			}
			return output;
			}

}
