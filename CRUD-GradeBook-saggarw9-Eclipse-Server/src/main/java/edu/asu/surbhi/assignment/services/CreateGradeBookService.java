package edu.asu.surbhi.assignment.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.xml.sax.SAXException;

import edu.asu.surbhi.assignment.DAO.CreateGradeBookDAO;
import edu.asu.surbhi.assignment.models.GradingElement;

@Path("/CreateGradeBookService")
public class CreateGradeBookService {
	@Context
    private UriInfo context;
	
	@GET
	@Path("/createGradeBook/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response readGradeBook(@PathParam("id")String id)
	{
		CreateGradeBookDAO createGradeBookDAO = new CreateGradeBookDAO();
		if (id!=null) {
			GradingElement element;
			try {
				element = createGradeBookDAO.findGradeBookElement(id);
				if(element!=null)
					return Response.status(200).entity(element).build();

			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return Response.status(400).entity(null).build();
	}
	@POST
	@Path("/createGradeBook")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response createGradeBook(GradingElement gradingElement) {
		try {

			CreateGradeBookDAO createGradeBookDAO = new CreateGradeBookDAO();
			if (gradingElement!=null) {
				
				URI locationURI = URI.create(context.getAbsolutePath().toString()+"/"+gradingElement.getElement());
				boolean result = createGradeBookDAO.createGradeBook(gradingElement);
				
				if(result)
				{
					return Response.status(201).location(locationURI).entity(gradingElement).build();
				}
				else
				{
					return Response.status(400).entity(null).build();
				}

				
			} else {

				return Response.status(400).entity(null).build();

			}

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(400).entity(null).build();
		}

	}

}
