package com;

import model.Project;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Project")
public class ProjectService
{
	Project projectObj = new Project();


	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProjects()
	{
			return projectObj.readProjects();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("projectCode") String projectCode,
			@FormParam("projectName") String projectName,
			@FormParam("projectDetails") String projectDetails,
			@FormParam("projectDesc") String projectDesc,
			@FormParam("projectAuthor") String projectAuthor)
	{
		String output = projectObj.insertProject(projectCode, projectName, projectDetails, projectDesc,projectAuthor);
			return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(String projectData)
	{
		//Convert the input string to a JSON object
		JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();
		//Read the values from the JSON object
		String projectID = projectObject.get("projectID").getAsString();
		String projectCode = projectObject.get("projectCode").getAsString();
		String projectName = projectObject.get("projectName").getAsString();
		String projectDetails = projectObject.get("projectDetails").getAsString();
		String projectDesc = projectObject.get("projectDesc").getAsString();
		String projectAuthor = projectObject.get("projectAuthor").getAsString();
	
		String output = projectObj.updateProject(projectID, projectCode, projectName, projectDetails, projectDesc, projectAuthor);
		return output;
}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String projectData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(projectData, "", Parser.xmlParser());
		
		String projectID = doc.select("projectID").text();
		String output = projectObj.deleteProject(projectID);
		return output;
	}
}