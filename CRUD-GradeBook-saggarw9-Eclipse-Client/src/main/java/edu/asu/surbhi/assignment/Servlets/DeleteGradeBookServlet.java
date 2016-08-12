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

import edu.asu.surbhi.assignment.models.GradingElement;
import edu.asu.surbhi.assignment.models.Student;
import edu.asu.surbhi.assignment.services.DeleteGradeBookServiceClient;
import edu.asu.surbhi.assignment.services.ReadGradeBookServiceClient;

public class DeleteGradeBookServlet extends HttpServlet {
	final String dir = System.getProperty("user.dir");
	String fullPath =dir+ "/gradebook.xml";
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String gradingElement = request.getParameter("gradingElementToDelete");
		String studentId = request.getParameter("studentIdToDelete");
		DeleteGradeBookServiceClient deleteService = new DeleteGradeBookServiceClient();
		Student student = new Student();
		String result = "";
		request.setAttribute("path", fullPath);

		if (gradingElement == null || gradingElement.equals("")) {
			student.setId(studentId);
			List<Student> studentList = deleteService
					.deleteByStudentId(student);
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
					request.getSession().setAttribute("deletedStudentElements",
							result);
				else
					request.getSession().setAttribute("deletedStudentElements",
							"Item not present");
			

			} catch (JAXBException j) {
				request.setAttribute("deletedStudentElements", null);
			}

		} else if (studentId == null || studentId.equals("")) {
			student.setGradingElement(gradingElement);
			GradingElement gradingDeleted = deleteService
					.deleteByGradingElement(student);
			try {
				if (gradingDeleted != null) {
					StringWriter swt = new StringWriter();

					JAXBContext context = JAXBContext
							.newInstance(GradingElement.class);
					Marshaller m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					m.marshal(gradingDeleted, swt);
					String xmlString = swt.toString();
					result = result + "\n" + xmlString;

					request.getSession().setAttribute("deletedStudentElements",
							result);
				} else {
					request.getSession().setAttribute("deletedStudentElements",
							"Grading element not present");
				}
			} catch (JAXBException j) {
				request.setAttribute("deletedStudentElements", null);
			}
		} else {
			student.setId(studentId);
			student.setGradingElement(gradingElement);
			student = deleteService.deleteByBoth(student);
			try {
				if (student != null) {
					StringWriter swt = new StringWriter();

					JAXBContext context = JAXBContext
							.newInstance(Student.class);
					Marshaller m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					m.marshal(student, swt);
					String xmlString = swt.toString();
					result = result + "\n" + xmlString;

					request.getSession().setAttribute("deletedStudentElements",
							result);
				} else
					request.getSession()
							.setAttribute(
									"deletedStudentElements",
									"No entry for Student under the Grading element or the grading element does not exist in gradebook.xml");

			} catch (JAXBException j) {
				request.setAttribute("deletedStudentElements", null);
			}
		}
        request.setAttribute("locationDelete",DeleteGradeBookServiceClient.location);

		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,
				response);

	}

}
