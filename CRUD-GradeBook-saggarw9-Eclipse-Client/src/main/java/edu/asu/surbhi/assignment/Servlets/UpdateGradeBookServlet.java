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

import edu.asu.surbhi.assignment.models.GradingElement;
import edu.asu.surbhi.assignment.models.Student;
import edu.asu.surbhi.assignment.services.CreateGradeBookServiceClient;
import edu.asu.surbhi.assignment.services.UpdateGradeBookServiceClient;

public class UpdateGradeBookServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String dir = System.getProperty("user.dir");
	String fullPath =dir+ "/gradebook.xml";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("path", fullPath);

			String noOfElements=request.getParameter("noOfElementsUpdate");
			ArrayList<Student> studentList=new ArrayList<Student>();
			UpdateGradeBookServiceClient updateService=new UpdateGradeBookServiceClient();
            int i=1;
			while(i<=Integer.parseInt(noOfElements))
			{
				String gradingElement=request.getParameter("updateGradingElement"+i);
				String studentId=request.getParameter("studentId"+i);
				String studentName=request.getParameter("studentName"+i);
				String studentGrade=request.getParameter("studentGrade"+i);
				String studentFeedback=request.getParameter("studentFeedback"+i);

				Student student=new Student();
				student.setFeedback(studentFeedback);
				student.setGrade(studentGrade);
				student.setId(studentId);
				student.setName(studentName);
				student.setGradingElement(gradingElement);
				student=updateService.updateGradeBookClient(student);
				if(student!=null)
				studentList.add(student);
				i++;
				
			}
			String result="";
			try
			{
			for(i=0;i<studentList.size();i++)
			{
				StringWriter swt=new StringWriter();
				JAXBContext context = JAXBContext.newInstance(Student.class);

				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				m.marshal(studentList.get(i), swt);	
				String xmlString=swt.toString();
				result=result+"\n"+xmlString;
				}
			request.getSession().setAttribute("createdStudentElements", result);
				
			}
			catch(JAXBException j)		
			{
				request.setAttribute("createdStudentElements", "Could not add, Some error occurred");
			}
		       request.setAttribute("locationUpdate",UpdateGradeBookServiceClient.location);

			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

			
	}

}
