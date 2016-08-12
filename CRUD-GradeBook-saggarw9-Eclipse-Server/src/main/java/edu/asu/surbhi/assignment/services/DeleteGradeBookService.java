package edu.asu.surbhi.assignment.services;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.xml.sax.SAXException;

import edu.asu.surbhi.assignment.DAO.DeleteGradeBookDAO;
import edu.asu.surbhi.assignment.models.GradingElement;
import edu.asu.surbhi.assignment.models.Student;

@Path("/DeleteGradeBookService")
public class DeleteGradeBookService {
	@Context
    private UriInfo context;
DeleteGradeBookDAO deleteDAO=new DeleteGradeBookDAO();	
	@DELETE
	@Path("/deleteByStudentId/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteByStudentId(@PathParam("id") String id) {
		if(id!=null && !id.equals(""))
		{
			List<Student> deletedList=null;
			boolean result = false;
			URI locationURI = URI.create(context.getAbsolutePath().toString());

			try {
				Student student=new Student();
				student.setId(id);
				deletedList= deleteDAO.deleteByStudentId(student);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(deletedList!=null && deletedList.size()>0)
				return Response.status(200).location(locationURI).entity(new GenericEntity<List<Student>>(deletedList) {}).build();
			else
				return Response.status(400).entity(null).build();
		}
		return Response.status(400).entity(null).build();
		
	}
	@DELETE
	@Path("/deleteByGradingElement/{element}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteByGradingElement(@PathParam("element") String element) {
		if(element!=null && !element.equals(""))
		{
			URI locationURI = URI.create(context.getAbsolutePath().toString());

			GradingElement deletedGradingElement=null;
			boolean result = false;
			try {
				Student student=new Student();
				student.setGradingElement(element);
				deletedGradingElement = deleteDAO.deleteByGradingElement(student);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(deletedGradingElement!=null)
				return Response.status(200).location(locationURI).entity(deletedGradingElement).build();
			else
				return Response.status(400).entity(null).build();
		}
		return Response.status(400).entity(null).build();
		
	}
	@DELETE
	@Path("/deleteByBoth/{id}/{element}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteByBoth(@PathParam("id") String id,@PathParam("element") String element) {
		if(id!=null && !id.equals("") && element!=null && !element.equals(""))
		{
			URI locationURI = URI.create(context.getAbsolutePath().toString());	
			Student deletedStudent=null;
			boolean result = false;
			try {
				Student student=new Student();
				student.setId(id);
				student.setGradingElement(element);
				deletedStudent = deleteDAO.deleteByBoth(student);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(deletedStudent!=null)
				return Response.status(200).location(locationURI).entity(deletedStudent).build();
			else
				return Response.status(400).entity(null).build();
		}
		return Response.status(400).entity(null).build();
		
	}
}
