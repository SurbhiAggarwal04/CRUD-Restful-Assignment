package edu.asu.surbhi.assignment.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;

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

public class UpdateGradeBookDAO {
	
	public boolean updateGradeBook(Student student) throws URISyntaxException, SAXException, IOException
	{
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

				InputStream inputStream= new FileInputStream(file);
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
		            String gradingElementName = rootElement.getAttribute("name");
		            if(gradingElementName.equalsIgnoreCase(student.getGradingElement()))
		            {
		            	
		            	NodeList studentsList=rootElement.getElementsByTagName("Student");
		            	int j=0;
		            	for(;j<studentsList.getLength();j++)
		            	{
		            		Node studentNode=studentsList.item(j);
		            		Element studentEle=(Element)studentNode;
		            		String id=studentEle.getElementsByTagName("id").item(0).getTextContent();
		            		if(id.equals(student.getId()))
		            			{
		            			studentEle.getParentNode().removeChild(studentEle);
		            			break;
		            			}
		            	}
		            	
		            		Element studentElement=doc.createElement("Student");
			            	Element studentIdElement=doc.createElement("id");
			            	studentIdElement.setTextContent(student.getId());
			            	Element studentNameElement=doc.createElement("Name");
			            	studentNameElement.setTextContent(student.getName());
			            	Element studentGradeElement=doc.createElement("Grade");
			            	studentGradeElement.setTextContent(student.getGrade());
			            	Element studentFeedbackElement=doc.createElement("Feedback");
			            	studentFeedbackElement.setTextContent(student.getFeedback());
			            	studentElement.appendChild(studentIdElement);
			            	studentElement.appendChild(studentNameElement);
			            	studentElement.appendChild(studentGradeElement);
			            	studentElement.appendChild(studentFeedbackElement);
			            	
			            	rootElement.appendChild(studentElement);
			    			saveDoc(doc,fullPath);
		            	
		            	
		            	break;
		            }
	            }
	            if(i>=nList.getLength())return false;

			}
			
			else
			{
				return false;
			}
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
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
