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

import edu.asu.surbhi.assignment.models.Student;

public class ReadGradeBookServiceClient {
	public static URI location;
	private WebResource webResource;
	private Client client;
	private static final String BASE_URI = "http://localhost:8080/CRUD-GradeBook-saggarw9-Eclipse-Server";

	public ReadGradeBookServiceClient()
	{
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
	}
	public String readXML()
	{
		webResource = client.resource(BASE_URI).path("ReadGradeBookService/readXML");
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
		if(response.getStatus()==200)
		{
		return response.getEntity(String.class);
		}
	else return null;
	}
	public List<Student> readByStudentId(Student student) {
		
		webResource = client.resource(BASE_URI).path("ReadGradeBookService/readByStudentId").path(student.getId());
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);
		if(response.getStatus()==200)
			{
			location=response.getLocation();
			return response.getEntity(new GenericType<List<Student>>(){});
			}
		else return null;
	}

	public List<Student> readByGradingElement(Student student) {
		webResource = client.resource(BASE_URI).path("ReadGradeBookService/readByGradingElement").path(student.getGradingElement());
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);
		if(response.getStatus()==200)
			{
			location=response.getLocation();
			return response.getEntity(new GenericType<List<Student>>(){});
			
			}
		else return null;
	}

	public Student readByBoth(Student student) {
		webResource = client.resource(BASE_URI).path("ReadGradeBookService/readByBoth").path(student.getId()).path(student.getGradingElement());
		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);
		if(response.getStatus()==200)
			{
			location=response.getLocation();
			return response.getEntity(Student.class);
			}
		else return null;
	}
}
