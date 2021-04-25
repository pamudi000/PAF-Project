package model;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	//A common method to connect to the DB
		private Connection connect()
		{
		Connection con = null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/funds", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
		}
		public String insertFund(String date, String totalLend, String remainingBalance, String noOfInstallments,String amountPerInstallment)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		// create a prepared statement
		String query = " INSERT INTO `fund`(`fundID`, `date`, `totalLend`, `remainingBalance`, `noOfInstallments`, `amountPerInstallment`)"
		+ " values (?, ?, ?, ?, ?,?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, date);
		preparedStmt.setString(3, totalLend);
		preparedStmt.setString(4, remainingBalance);
		preparedStmt.setString(5, noOfInstallments);
		preparedStmt.setString(6, amountPerInstallment);
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
		}
		catch (Exception e)
		{
		output = "Error while inserting the fund.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String readFunds()
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }
		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>Date</th><th>Total Lend</th>" +
		"<th>Remaining Balance</th>" +
		"<th>No Of Installments</th>"+
		"<th>Amount Per Installment</th>";
		
		String query = "SELECT * FROM `fund`";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
		String fundID = Integer.toString(rs.getInt("fundID"));
		String date = rs.getString("date");
		String totalLend = rs.getString("totalLend");
		String remainingBalance = Double.toString(rs.getDouble("remainingBalance"));
		String noOfInstallments = rs.getString("noOfInstallments");
		String amountPerInstallment = rs.getString("amountPerInstallment");
		// Add into the html table
		output += "<tr><td>" + date + "</td>";
		output += "<td>" + totalLend + "</td>";
		output += "<td>" + remainingBalance + "</td>";
		output += "<td>" + noOfInstallments + "</td>";
		output += "<td>" + amountPerInstallment + "</td>";
		
		
		}
		con.close();
		// Complete the html table
		output += "</table>";
		}
		catch (Exception e)
		{
		output = "Error while reading the funds.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String updateFund(String fundID,String date, String totalLend, String remainingBalance, String noOfInstallments,String amountPerInstallment)
		{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE fund SET date=?,totalLend=?,remainingBalance=?,noOfInstallments=?,amountPerInstallment=? WHERE fundID=? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, date);
			preparedStmt.setString(2, totalLend);
			preparedStmt.setString(3, remainingBalance);
			preparedStmt.setString(4, noOfInstallments);
			preparedStmt.setString(5, amountPerInstallment);
			preparedStmt.setInt(6, Integer.parseInt(fundID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			}
			catch (Exception e)
			{
			output = "Error while updating the fund.";
			System.err.println(e.getMessage());
			}
			return output;
			}
		
		
			public String deleteFund(String fundID)
			{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from fund where fundID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fundID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			}
			catch (Exception e)
			{
			output = "Error while deleting the fund.";
			System.err.println(e.getMessage());
			}
			return output;
			}

}
