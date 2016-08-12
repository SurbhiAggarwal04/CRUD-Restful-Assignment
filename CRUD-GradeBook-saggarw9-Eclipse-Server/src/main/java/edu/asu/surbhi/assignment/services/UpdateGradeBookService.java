package edu.asu.surbhi.assignment.services;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import edu.asu.surbhi.assignment.DAO.CreateGradeBookDAO;
import edu.asu.surbhi.assignment.DAO.UpdateGradeBookDAO;
import edu.asu.surbhi.assignment.models.GradingElement;
import edu.asu.surbhi.assignment.models.Student;

@Path("/UpdateGradeBookService")
public class UpdateGradeBookService {
	@Context
    private UriInfo context;
	@PUT
	@Path("/updateGradeBook")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response updateGradeBook(Student student) {
		try {

			UpdateGradeBookDAO updateGradeBookDAO = new UpdateGradeBookDAO();
			if (student!=null) {
				
				
				boolean result = updateGradeBookDAO.updateGradeBook(student);
				if(result)
				{
					String path="http://localhost:8080/CRUD-GradeBook-saggarw9-Eclipse-Server/ReadGradeBookService/readByBoth/"+student.getId()+"/"+student.getGradingElement();
					return Response.status(201).location(URI.create(path)).entity(student).build();
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
