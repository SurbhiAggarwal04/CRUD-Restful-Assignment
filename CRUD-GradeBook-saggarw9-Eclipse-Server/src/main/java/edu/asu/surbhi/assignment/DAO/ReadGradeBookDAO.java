package edu.asu.surbhi.assignment.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import edu.asu.surbhi.assignment.models.Student;

public class ReadGradeBookDAO {
	final String dir = System.getProperty("user.dir");
	String fullPath = dir + "/gradebook.xml";
	File file = new File(fullPath);

	public String readXML() throws ParserConfigurationException, IOException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		InputStream inputStream;
        if(file.exists())
        {
		inputStream = new FileInputStream(file);

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		return sb.toString();
        }
        return null;
	}

	public ArrayList<Student> readByStudentId(Student student)
			throws SAXException, IOException {
		final String dir = System.getProperty("user.dir");
		String fullPath = dir + "/gradebook.xml";
		File file = new File(fullPath);
		ArrayList<Student> studentList = new ArrayList<Student>();
		ClassLoader loader = this.getClass().getClassLoader();
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = null;
			Element rootElement = null;
			if (file.exists()) {

				InputStream inputStream;

				inputStream = new FileInputStream(file);

				Reader reader = new InputStreamReader(inputStream, "UTF-8");
				InputSource is = new InputSource(reader);
				is.setEncoding("UTF-8");
				doc = docBuilder.parse(is);
				NodeList nList = doc.getElementsByTagName("Student");
				int i = 0;
				for (; i < nList.getLength(); i++) {
					Node nNode = nList.item(i);
					rootElement = (Element) nNode;
					String id = rootElement.getElementsByTagName("id").item(0)
							.getTextContent();
					if (id.equals(student.getId())) {
						Student studentObj = new Student();
						studentObj.setFeedback(rootElement
								.getElementsByTagName("Feedback").item(0)
								.getTextContent());
						studentObj.setId(id);
						studentObj.setGrade(rootElement
								.getElementsByTagName("Grade").item(0)
								.getTextContent());
						studentObj.setName(rootElement
								.getElementsByTagName("Name").item(0)
								.getTextContent());

						studentObj.setGradingElement(((Element) (rootElement
								.getParentNode())).getAttribute("name"));
						studentList.add(studentObj);
					}

				}
				return studentList;

			}

			else {
				return null;
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<Student> readByGradingElement(Student student)
			throws SAXException, IOException {
		ArrayList<Student> studentList = new ArrayList<Student>();

		ClassLoader loader = this.getClass().getClassLoader();
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = null;
			Element rootElement = null;
			if (file.exists()) {

				InputStream inputStream;

				inputStream = new FileInputStream(file);

				Reader reader = new InputStreamReader(inputStream, "UTF-8");
				InputSource is = new InputSource(reader);
				is.setEncoding("UTF-8");
				doc = docBuilder.parse(is);
				NodeList nList = doc.getElementsByTagName("GradingElement");
				int i = 0;
				for (; i < nList.getLength(); i++) {
					Node nNode = nList.item(i);
					rootElement = (Element) nNode;
					String gradingElement = rootElement.getAttribute("name");
					if (gradingElement.equalsIgnoreCase(student
							.getGradingElement())) {
						NodeList stList = rootElement
								.getElementsByTagName("Student");
						int j = 0;
						for (; j < stList.getLength(); j++) {
							Element stEle = (Element) stList.item(j);
							Student studentObj = new Student();
							studentObj.setFeedback(stEle
									.getElementsByTagName("Feedback").item(0)
									.getTextContent());
							studentObj.setId(stEle.getElementsByTagName("id")
									.item(0).getTextContent());
							studentObj.setGrade(stEle
									.getElementsByTagName("Grade").item(0)
									.getTextContent());
							studentObj.setName(stEle
									.getElementsByTagName("Name").item(0)
									.getTextContent());

							studentObj.setGradingElement(((Element) (stEle
									.getParentNode())).getAttribute("name"));
							studentList.add(studentObj);
						}
						break;

					}
				}
				return studentList;

			}

			else {
				return null;
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public Student readByBoth(Student student) throws SAXException, IOException {

		ClassLoader loader = this.getClass().getClassLoader();
		ArrayList<Student> list = new ArrayList<Student>();
		// File file = new File( loader.getResource( "/gradebook.xml"
		// ).getFile());
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = null;
			Element rootElement = null;
			if (file.exists()) {

				InputStream inputStream;

				inputStream = new FileInputStream(file);

				Reader reader = new InputStreamReader(inputStream, "UTF-8");
				InputSource is = new InputSource(reader);
				is.setEncoding("UTF-8");
				doc = docBuilder.parse(is);
				NodeList nList = doc.getElementsByTagName("GradingElement");
				int i = 0;
				for (; i < nList.getLength(); i++) {
					Node nNode = nList.item(i);
					rootElement = (Element) nNode;
					String gradingElement = rootElement.getAttribute("name");
					if (gradingElement.equalsIgnoreCase(student
							.getGradingElement())) {
						NodeList studentList = rootElement
								.getElementsByTagName("Student");
						int j = 0;
						for (; j < studentList.getLength(); j++) {
							Element studentEle = (Element) studentList.item(j);
							String studentId = studentEle
									.getElementsByTagName("id").item(0)
									.getTextContent();
							if (studentId.equals(student.getId())) {
								Student studentObj = new Student();
								studentObj.setFeedback(studentEle
										.getElementsByTagName("Feedback")
										.item(0).getTextContent());
								studentObj.setId(studentEle
										.getElementsByTagName("id").item(0)
										.getTextContent());
								studentObj.setGrade(studentEle
										.getElementsByTagName("Grade").item(0)
										.getTextContent());
								studentObj.setName(studentEle
										.getElementsByTagName("Name").item(0)
										.getTextContent());

								studentObj
										.setGradingElement(((Element) (studentEle
												.getParentNode()))
												.getAttribute("name"));
								list.add(studentObj);
								return studentObj;

							}
						}
						return null;
					}

				}
				return null;

			}

			else {
				return null;
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
