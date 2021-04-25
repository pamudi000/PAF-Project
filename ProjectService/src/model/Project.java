package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {
	

	private Connection connect()
	{
	Connection con = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	
	con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useTimezone=true&serverTimezone=UTC", "root", "");
	}
	catch (Exception e)
	{e.printStackTrace();}
	return con;
	}
	public String insertProject(String projectCode, String projectName, String projectDetails, String projectDesc, String projectAuthor)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for inserting."; }
	// create a prepared statement
	String query = " INSERT INTO `project`(`projectID`, `projectCode`, `projectName`, `projectDetails`, `projectDesc`, `projectAuthor`)"
	+ " values (?, ?, ?, ?, ?, ?)";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, projectCode);
	preparedStmt.setString(3, projectName);
	preparedStmt.setString(4, projectDetails);
	preparedStmt.setString(5, projectDesc);
	preparedStmt.setString(6, projectAuthor);
	
	// execute the statement
	preparedStmt.execute();
	con.close();
	
	output = "Inserted successfully";
	}
	catch (Exception e)
	{
	output = "Error while inserting the project.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	public String readProjects()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for reading."; }
	
	output = "<table border='1'><tr><th>Project Code</th><th>Project Name</th>" +
	"<th>Project Details</th>" +
	"<th>Project Description</th>" +
	"<th>Project Author</th></tr>";
	
	
	String query = "select * from project";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	// iterate through the rows in the result set
	while (rs.next())
	{
	String projectID = Integer.toString(rs.getInt("projectID"));
	String projectCode = rs.getString("projectCode");
	String projectName = rs.getString("projectName");
	String projectDetails = rs.getString("projectDetails");
	String projectDesc = rs.getString("projectDesc");
	String projectAuthor = rs.getString("projectAuthor");

	output += "<tr><td>" + projectCode + "</td>";
	output += "<td>" + projectName + "</td>";
	output += "<td>" + projectDetails + "</td>";
	output += "<td>" + projectDesc + "</td>";
	output += "<td>" + projectAuthor + "</td>";
	
	
	}
	con.close();
	
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the project.";
	System.err.println(e.getMessage());
	}
	return output;
	}

	
	public String updateProject(String projectID, String projectCode, String projectName, String projectDetails, String projectDesc,String projectAuthor)
	{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for updating."; }
		
		// create a prepared statement
		String query = "UPDATE project SET projectCode=?,projectName=?,projectDetails=?,projectDesc=?,projectAuthor=? WHERE projectID=? ";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setString(1, projectCode);
		preparedStmt.setString(2, projectName);
		preparedStmt.setString(3, projectDetails);
		preparedStmt.setString(4, projectDesc);
		preparedStmt.setString(5, projectAuthor);
		preparedStmt.setInt(6, Integer.parseInt(projectID));
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Updated successfully";
		}
		catch (Exception e)
		{
		output = "Error while updating the project.";
		System.err.println(e.getMessage());
		}
		return output;
		}
	
		public String deleteProject(String projectID)

		{
		String output = "";
		try
		{
		Connection con = connect();
		
		if (con == null)
		{
			return "Error while connecting to the database for deleting."; 
		
		}
		
		// create a prepared statement
		String query = "delete from project where projectID=?";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(projectID));
		
		// execute the statement
		preparedStmt.execute();
		con.close();
		
		output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the project.";
			System.err.println(e.getMessage());
		}
			return output;
		}
		}




