package edu.asu.surbhi.assignment.Servlets;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlNs;

import edu.asu.surbhi.assignment.models.GradingElement;
import edu.asu.surbhi.assignment.services.CreateGradeBookServiceClient;
import edu.asu.surbhi.assignment.services.ReadGradeBookServiceClient;


public class CreateGradeBookServlet extends HttpServlet{

	final String dir = System.getProperty("user.dir");
	String fullPath =dir+ "/gradebook.xml";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			String noOfElements=request.getParameter("noOfElements");
			List<GradingElement> gradingElements=new ArrayList<GradingElement>();
			List<GradingElement> gradingElementsNotAdded=new ArrayList<GradingElement>();

			int i=1;
			CreateGradeBookServiceClient createService=new CreateGradeBookServiceClient();

			while(i<=Integer.parseInt(noOfElements))
			{
				String gradingElement=request.getParameter("gradingElement"+i);
				String percentageAllocation=request.getParameter("percentageAllocation"+i);
				GradingElement gradingObj=new GradingElement();
				gradingObj.setAllocation(percentageAllocation);
				gradingObj.setElement(gradingElement);
				try {
					GradingElement createdGrade=createService.createGradeBookClient(gradingObj);
					if(createdGrade!=null)
					gradingElements.add(createdGrade);
					else
						gradingElementsNotAdded.add(gradingObj);
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				
			}

			String result="";
			try
			{
			for(i=0;i<gradingElements.size();i++)
			{
				
				StringWriter swt=new StringWriter();
				JAXBContext context = JAXBContext.newInstance(GradingElement.class);

				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				m.marshal(gradingElements.get(i), swt);	
				String xmlString=swt.toString();
				result=result+"\n"+xmlString;
				}
			
			String notAddedResult="";
			for(i=0;i<gradingElementsNotAdded.size();i++)
			{
				notAddedResult+=gradingElementsNotAdded.get(i).getElement()+"\n";
			}
			request.getSession().setAttribute("createdElements", result);
			request.getSession().setAttribute("notCreatedElements", notAddedResult);
		       request.setAttribute("locationCreate",CreateGradeBookServiceClient.location);

			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
				
			}
			catch(JAXBException e)
			{
				request.setAttribute("createdElements", null);
				request.setAttribute("path", fullPath);

				request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			}

			
			
			
	}

}
