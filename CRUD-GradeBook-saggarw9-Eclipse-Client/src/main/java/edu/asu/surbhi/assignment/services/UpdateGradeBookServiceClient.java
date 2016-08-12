package edu.asu.surbhi.assignment.services;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import edu.asu.surbhi.assignment.models.GradingElement;
import edu.asu.surbhi.assignment.models.Student;

public class UpdateGradeBookServiceClient {
	public static URI location;
	private WebResource webResource;
	private Client client;
	private static final String BASE_URI = "http://localhost:8080/CRUD-GradeBook-saggarw9-Eclipse-Server";

	public UpdateGradeBookServiceClient()
	{
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
	}
public Student updateGradeBookClient(Student student)
{
	webResource = client.resource(BASE_URI).path("UpdateGradeBookService/updateGradeBook");

	ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
			.accept(MediaType.APPLICATION_XML)
			.put(ClientResponse.class,student);
	System.out.println(response.getStatus());
	if (response.getStatus() == 201) {
		location=response.getLocation();
		Student gradedElement=(Student) response.getEntity(Student.class);
		return gradedElement;
	}
	else
		return null;
}
	
}

