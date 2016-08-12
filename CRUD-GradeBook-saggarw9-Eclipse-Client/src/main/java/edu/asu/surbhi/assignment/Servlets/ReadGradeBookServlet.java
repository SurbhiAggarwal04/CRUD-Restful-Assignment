package edu.asu.surbhi.assignment.Servlets;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import edu.asu.surbhi.assignment.models.Student;
import edu.asu.surbhi.assignment.services.DeleteGradeBookServiceClient;
import edu.asu.surbhi.assignment.services.ReadGradeBookServiceClient;

public class ReadGradeBookServlet extends HttpServlet{
	final String dir = System.getProperty("user.dir");
	String fullPath =dir+ "/gradebook.xml";
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("path", fullPath);
		ReadGradeBookServiceClient readService=new ReadGradeBookServiceClient();
		String xml=readService.readXML();
		if(xml!=null)
		request.getSession().setAttribute("xml",xml);
		else
			request.getSession().setAttribute("xml","File is not created yet or Some error occurred");
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("path", fullPath);
 
		String gradingElement=request.getParameter("gradingElementToRead");
		String studentId=request.getParameter("studentIdToRead");
		ReadGradeBookServiceClient readService=new ReadGradeBookServiceClient();
	    Student student=new Student();
	    String result="";
		if(gradingElement==null || gradingElement.equals(""))
		{
			student.setId(studentId);
			
		
		
			List<Student> studentList=readService.readByStudentId(student);
			try {
				if (studentList != null) {
					for (int i = 0; i < studentList.size(); i++) {
						StringWriter swt = new StringWriter();

						JAXBContext context = JAXBContext
								.newInstance(Student.class);
						Marshaller m = context.createMarshaller();
						m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
						m.marshal(studentList.get(i), swt);
						String xmlString = swt.toString();
						result = result + "\n" + xmlString;

					}
				}
				if (!result.equals(""))
					request.getSession().setAttribute("readStudentElements",
							result);
				else
					request.getSession().setAttribute("readStudentElements",
							"Student not present for the Grading eleemnts / File not yet create! Check by Read Complete XML file button!");

			} catch (JAXBException j) {
				request.setAttribute("readStudentElements", null);
			}
		}
		else if(studentId==null || studentId.equals(""))
		{
			System.out.println("gaya");
			student.setGradingElement(gradingElement);
			
			List<Student> studentList=readService.readByGradingElement(student);
				try {
					if (studentList != null) {
						for (int i = 0; i < studentList.size(); i++) {
							StringWriter swt = new StringWriter();

							JAXBContext context = JAXBContext
									.newInstance(Student.class);
							Marshaller m = context.createMarshaller();
							m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							m.marshal(studentList.get(i), swt);
							String xmlString = swt.toString();
							result = result + "\n" + xmlString;

						}
					}
					if (!result.equals(""))
						request.getSession().setAttribute("readStudentElements",
								result);
					else
						request.getSession().setAttribute("readStudentElements",
								"Grading element not present or there are no students graded for the grading element");

				} catch (JAXBException j) {
					request.setAttribute("readStudentElements", null);
				}
			
		}
		else
		{
			student.setId(studentId);
			student.setGradingElement(gradingElement);
			Student studentObj=readService.readByBoth(student);
			try {
				if (studentObj != null) {
					
						StringWriter swt = new StringWriter();

						JAXBContext context = JAXBContext
								.newInstance(Student.class);
						Marshaller m = context.createMarshaller();
						m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
						m.marshal(studentObj, swt);
						String xmlString = swt.toString();
						result = result + "\n" + xmlString;

					
				}
				if (!result.equals(""))
					request.getSession().setAttribute("readStudentElements",
							result);
				else
					request.getSession().setAttribute("readStudentElements",
							"Grading element not present or student not found for the grading element");

			} catch (JAXBException j) {
				request.setAttribute("readStudentElements", null);
			}
		}
	       request.setAttribute("location",ReadGradeBookServiceClient.location);

		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);

	}
	


}
