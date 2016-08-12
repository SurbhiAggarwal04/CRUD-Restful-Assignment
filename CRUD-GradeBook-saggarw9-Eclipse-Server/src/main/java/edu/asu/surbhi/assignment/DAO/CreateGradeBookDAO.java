package edu.asu.surbhi.assignment.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

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

public class CreateGradeBookDAO {
    public GradingElement findGradeBookElement(String id)throws URISyntaxException, SAXException, IOException
    {
    	final String dir = System.getProperty("user.dir");
		String fullPath =dir+ "/gradebook.xml";
		File file = new File(fullPath);
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc=null;
			Element rootElement=null;
			InputStream inputStream= new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream,"UTF-8");
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");
            doc = docBuilder.parse(inputStream);
            NodeList nList= doc.getElementsByTagName("Gradebook");
            Node nNode = nList.item(0);
            rootElement = (Element) nNode;
            int i=0;
            
            Element node=(Element)nList.item(0);
            	NodeList eleList=node.getElementsByTagName("GradingElement");
            	 for(;i<eleList.getLength();i++)
 	            {
 	                Element gradElement=(Element)eleList.item(i);
 	                String name=gradElement.getAttribute("name");
 	                if(name.equalsIgnoreCase(id))
 	                {
 	                	GradingElement element=new GradingElement();
 	                	element.setAllocation(gradElement.getAttribute("allocation"));
 	                	element.setElement(name);
 	                	return element;
 	                }
 	            }
            	 return null;
		}
		catch(Exception e)
		{
			return null;
		}
		finally
		{
			
		}
		
    	
    }
	public boolean createGradeBook(GradingElement gradingEleemnt) throws URISyntaxException, SAXException, IOException
	{
		final String dir = System.getProperty("user.dir");
		String fullPath =dir+ "/gradebook.xml";
		File file = new File(fullPath);
		
		/*ClassLoader loader=Thread.currentThread().getContextClassLoader();
		InputStream inputStream=loader.getResourceAsStream("gradebook.xml");
		String path=null;
		try
		{
		path=loader.getResource("gradebook.xml").getPath();
		}
		catch(Exception f)
		{
			
		}*/
		boolean found=false;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc=null;
			Element rootElement=null;
			//if(path==null)
			if(!file.exists())
			{
				
				//path=CreateGradeBookDAO.class.getResource("/src/main/resources").getPath();

				
				System.out.println(file.getAbsolutePath());
				doc = docBuilder.newDocument();
				rootElement = doc.createElement("Gradebook");
				doc.appendChild(rootElement);

			}
			
			else
			{
				InputStream inputStream= new FileInputStream(file);
	            Reader reader = new InputStreamReader(inputStream,"UTF-8");
	            InputSource is = new InputSource(reader);
	            is.setEncoding("UTF-8");
	            doc = docBuilder.parse(inputStream);
	            NodeList nList= doc.getElementsByTagName("Gradebook");
	            Node nNode = nList.item(0);
	            rootElement = (Element) nNode;
	            int i=0;
	            
	            Element node=(Element)nList.item(0);
	            	NodeList eleList=node.getElementsByTagName("GradingElement");
	            	
	            for(;i<eleList.getLength();i++)
	            {
	                Element gradElement=(Element)eleList.item(i);
	                String name=gradElement.getAttribute("name");
	                if(name.equalsIgnoreCase(gradingEleemnt.getElement()))
	                {
	                	found=true;
	                	break;
	                }
	            }
	           
	            
			} 
	            
	            
			
			if(!found)
			{
				Element gradeElement=doc.createElement("GradingElement");
				gradeElement.setAttribute("name", gradingEleemnt.getElement());
				gradeElement.setAttribute("allocation", gradingEleemnt.getAllocation());
				rootElement.appendChild(gradeElement);
				//saveDoc(doc,fullPath);
				saveDoc(doc, fullPath);
				return true;
			}
			else {return false;}	
			
			
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
			return false;
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	    //return output.toString();
	}
}
