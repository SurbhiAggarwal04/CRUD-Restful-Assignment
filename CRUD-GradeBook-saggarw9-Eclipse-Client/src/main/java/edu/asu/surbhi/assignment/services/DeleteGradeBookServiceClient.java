package edu.asu.surbhi.assignment.services;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import edu.asu.surbhi.assignment.models.GradingElement;
import edu.asu.surbhi.assignment.models.Student;

public class DeleteGradeBookServiceClient {
	public static URI location;
	private WebResource webResource;
	private Client client;
	private static final String BASE_URI = "http://localhost:8080/CRUD-GradeBook-saggarw9-Eclipse-Server";

	public DeleteGradeBookServiceClient()
	{
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
	}
	public List<Student> deleteByStudentId(Student student) {
		webResource = client.resource(BASE_URI).path("DeleteGradeBookService/deleteByStudentId").path(student.getId());
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.delete(ClientResponse.class);
		
		if(response.getStatus()==200)
			{
			List<Student> deletedList=(List<Student>) response.getEntity(new GenericType<List<Student>>(){});
			location=response.getLocation();
			System.out.println(location);
			return deletedList;
			}
		else return null;
	}

	public GradingElement deleteByGradingElement(Student student) {
		webResource = client.resource(BASE_URI).path("DeleteGradeBookService/deleteByGradingElement").path(student.getGradingElement());
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.delete(ClientResponse.class);
		if(response.getStatus()==200)
			{
			location=response.getLocation();
			System.out.println(location);

			GradingElement grading=response.getEntity(GradingElement.class);
			return grading;
			}
		else return null;
	}

	public Student deleteByBoth(Student student) {
		webResource = client.resource(BASE_URI).path("DeleteGradeBookService/deleteByBoth").path(student.getId()).path(student.getGradingElement());
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.delete(ClientResponse.class);
		if(response.getStatus()==200)
			{
			location=response.getLocation();
			System.out.println(location);

			return response.getEntity(Student.class);
			}
		else return null;
	}
}
