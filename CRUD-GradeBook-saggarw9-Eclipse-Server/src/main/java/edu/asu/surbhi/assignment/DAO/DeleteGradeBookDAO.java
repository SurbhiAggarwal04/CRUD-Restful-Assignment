package edu.asu.surbhi.assignment.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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

import edu.asu.surbhi.assignment.models.GradingElement;
import edu.asu.surbhi.assignment.models.Student;

public class DeleteGradeBookDAO {
	final String dir = System.getProperty("user.dir");
	String fullPath =dir+ "/gradebook.xml";
	File file = new File(fullPath);
	public ArrayList<Student> deleteByStudentId(Student student) throws SAXException, IOException {
		final String dir = System.getProperty("user.dir");
		String fullPath =dir+ "/gradebook.xml";
		File file = new File(fullPath);
		
		ClassLoader loader = this.getClass().getClassLoader();
		//File file = new File( loader.getResource( "/gradebook.xml" ).getFile());
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc=null;
			Element rootElement=null;
			if(file.exists())
			{

				InputStream inputStream;
				
					inputStream = new FileInputStream(file);
				
	            Reader reader = new InputStreamReader(inputStream,"UTF-8");
	            InputSource is = new InputSource(reader);
	            is.setEncoding("UTF-8");
	            doc = docBuilder.parse(is);
	            NodeList nList= doc.getElementsByTagName("Student");
	            int i=0;
	            ArrayList<Student> deletedStudentLIst=new ArrayList<Student>();
	            for(;i<nList.getLength();i++)
	            {
	            	Node nNode = nList.item(i);
		            rootElement = (Element) nNode;	
		            String id = rootElement.getElementsByTagName("id").item(0).getTextContent();
		            if(id.equals(student.getId()))
		            {
		            	Student deletdStudent=new Student();
		            	deletdStudent.setFeedback(rootElement.getElementsByTagName("Feedback").item(0).getTextContent());
		            	deletdStudent.setName(rootElement.getElementsByTagName("Name").item(0).getTextContent());
		            	deletdStudent.setGrade(rootElement.getElementsByTagName("Grade").item(0).getTextContent());
		            	deletdStudent.setGradingElement(rootElement.getParentNode().getAttributes().item(1).getTextContent());
		            	deletdStudent.setId(rootElement.getElementsByTagName("id").item(0).getTextContent());
		            	deletedStudentLIst.add(deletdStudent);

		            	rootElement.getParentNode().removeChild(rootElement);
		            }
	            }
	            saveDoc(doc,fullPath);
                return deletedStudentLIst;
			}
			
			else
			{
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
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}


	public GradingElement deleteByGradingElement(Student student) throws SAXException, IOException {
		
		ClassLoader loader = this.getClass().getClassLoader();
		//File file = new File( loader.getResource( "/gradebook.xml" ).getFile());
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc=null;
			Element rootElement=null;
			if(file.exists())
			{

				InputStream inputStream;
				
					inputStream = new FileInputStream(file);
				
	            Reader reader = new InputStreamReader(inputStream,"UTF-8");
	            InputSource is = new InputSource(reader);
	            is.setEncoding("UTF-8");
	            doc = docBuilder.parse(is);
	            NodeList nList= doc.getElementsByTagName("GradingElement");
	            int i=0;
	            GradingElement deletedGradingEleemnt=new GradingElement();
	            for(;i<nList.getLength();i++)
	            {
	            	Node nNode = nList.item(i);
		            rootElement = (Element) nNode;	
		            String gradingElement = rootElement.getAttribute("name");
		            if(gradingElement.equalsIgnoreCase(student.getGradingElement()))
		            {
		            	deletedGradingEleemnt.setAllocation(rootElement.getAttribute("allocation"));
		            	deletedGradingEleemnt.setElement(rootElement.getAttribute("name"));

		            	rootElement.getParentNode().removeChild(rootElement);
			            saveDoc(doc,fullPath);
			            return deletedGradingEleemnt;
	
		            	
		            }
	            }

			}
			
			else
			{
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
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
		
	}

	public Student deleteByBoth(Student student) throws SAXException, IOException {
		
		ClassLoader loader = this.getClass().getClassLoader();
		//File file = new File( loader.getResource( "/gradebook.xml" ).getFile());
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc=null;
			Element rootElement=null;
			if(file.exists())
			{

				InputStream inputStream;
				
					inputStream = new FileInputStream(file);
				
	            Reader reader = new InputStreamReader(inputStream,"UTF-8");
	            InputSource is = new InputSource(reader);
	            is.setEncoding("UTF-8");
	            doc = docBuilder.parse(is);
	            NodeList nList= doc.getElementsByTagName("GradingElement");
	            int i=0;
	            for(;i<nList.getLength();i++)
	            {
	            	Node nNode = nList.item(i);
		            rootElement = (Element) nNode;	
		            String gradingElement = rootElement.getAttribute("name");

		            if(gradingElement.equalsIgnoreCase(student.getGradingElement()))
		            {
		            	System.out.println("yes");
		            	 NodeList studentList=rootElement.getElementsByTagName("Student");
		            	 int j=0;
				            for(;j<studentList.getLength();j++)
				            {
				            	Element studentEle=(Element)studentList.item(j);
					            String studentId=studentEle.getElementsByTagName("id").item(0).getTextContent();
                                if(studentId.equals(student.getId()))
                                {
               		            	Student deletdStudent=new Student();
            		            	deletdStudent.setFeedback(studentEle.getElementsByTagName("Feedback").item(0).getTextContent());
            		            	deletdStudent.setName(studentEle.getElementsByTagName("Name").item(0).getTextContent());
            		            	deletdStudent.setGrade(studentEle.getElementsByTagName("Grade").item(0).getTextContent());
            		            	deletdStudent.setGradingElement(studentEle.getParentNode().getAttributes().item(1).getTextContent());
            		            	deletdStudent.setId(studentEle.getElementsByTagName("id").item(0).getTextContent());
 
                                	studentEle.getParentNode().removeChild(studentEle);
            		            	 saveDoc(doc,fullPath);
            		            	 return deletdStudent;

                                }
				            }
		            }
		           
		            
		           
	            }
	           

			}
			
			else
			{
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
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return null;


	}
	
	public static void saveDoc(Node node, String fullPath) throws TransformerFactoryConfigurationError, TransformerException
	{
		Transformer xform = TransformerFactory.newInstance().newTransformer();
	    Result output=new StreamResult(new File(fullPath));
	    xform.setOutputProperty(OutputKeys.INDENT, "yes");
	    xform.setOutputProperty(OutputKeys.METHOD, "xml");
	    xform.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	    Source input=new DOMSource(node);
	    xform.transform(input, output);
	}
}
