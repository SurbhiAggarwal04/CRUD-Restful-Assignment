package edu.asu.surbhi.assignment.services;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import edu.asu.surbhi.assignment.models.GradingElement;


public class CreateGradeBookServiceClient {
	public static URI location;
	private WebResource webResource;
	private Client client;
	private static final String BASE_URI = "http://localhost:8080/CRUD-GradeBook-saggarw9-Eclipse-Server";

	public CreateGradeBookServiceClient()
	{
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
	}
	public GradingElement createGradeBookClient(GradingElement gradingElement) throws JAXBException
	{
		webResource = client.resource(BASE_URI).path("CreateGradeBookService/createGradeBook");
		//webResource=client.resource("http://localhost:8080/CRUD-GradeBook-saggarw9-Eclipse-Server/CreateGradeBookService/createGradeBook");

		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class,gradingElement);
		if (response.getStatus() == 201) {
			location=response.getLocation();
			GradingElement gradedElement=(GradingElement) response.getEntity(GradingElement.class);
			return gradedElement;
		}
		else
			return null;
	}
}
