package edu.asu.surbhi.assignment.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import edu.asu.surbhi.assignment.DAO.DeleteGradeBookDAO;
import edu.asu.surbhi.assignment.DAO.ReadGradeBookDAO;
import edu.asu.surbhi.assignment.models.Student;

@Path("/ReadGradeBookService")
public class ReadGradeBookService {
	@Context
    private UriInfo context;
	ReadGradeBookDAO readDAO = new ReadGradeBookDAO();

	@GET
	@Path("/readXML")
	@Produces(MediaType.APPLICATION_XML)
	public Response readXML() throws FileNotFoundException, UnsupportedEncodingException, ParserConfigurationException
	{
		String xml=null;
		try {
			xml = readDAO.readXML();
		} catch (IOException e) {
			return Response.status(400).entity(null).build();
			// TODO Auto-generated catch block
		
		}
		if(xml!=null)
		{
			return Response.status(200)
					.entity(xml).build();
		}
		return Response.status(400).entity(null).build();
	}
	@GET
	@Path("/readByStudentId/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response readByStudentId(@PathParam("id") String id) {
		System.out.println("service");
		
			System.out.println(id);
			boolean result = false;
			List<Student> studentList = null;
			URI locationURI = URI.create(context.getAbsolutePath().toString());
			try {
				Student student=new Student();
				student.setId(id);
				studentList = readDAO.readByStudentId(student);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (studentList != null && studentList.size() > 0)
				return Response.status(200).location(locationURI)
						.entity(new GenericEntity<List<Student>>(studentList) {
						}).build();
			else
				return Response.status(400).entity(null).build();
		
		
		

	}

	@GET
	@Path("/readByGradingElement/{element}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response readByGradingElement(@PathParam("element") String element) {
	
		if (element != null && !element.equals("")) {
			List<Student> studentList = null;
			boolean result = false;
			URI locationURI = URI.create(context.getAbsolutePath().toString());

			try {
				Student student =new Student();
				student.setGradingElement(element);
				studentList = readDAO.readByGradingElement(student);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (studentList != null && studentList.size() > 0)
				return Response.status(200).location(locationURI)
						.entity(new GenericEntity<List<Student>>(studentList) {
						}).build();
			else
				return Response.status(400).entity(null).build();
		}
		return Response.status(400).entity(null).build();

	}

	@GET
	@Path("/readByBoth/{id}/{element}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response readByBoth(@PathParam("id") String id,@PathParam("element") String element) {
		if (element != null && !element.equals("") && id!=null && !id.equals("")) {
			boolean result = false;
			Student studentObj = null;
			URI locationURI = URI.create(context.getAbsolutePath().toString());

			try {
				Student student=new Student();
				student.setId(id);
				student.setGradingElement(element);
				studentObj = readDAO.readByBoth(student);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (studentObj != null)
				return Response.status(200).location(locationURI).entity(studentObj).build();
			else
				return Response.status(400).entity(null).build();
		}
		return Response.status(400).entity(null).build();

	}
}
