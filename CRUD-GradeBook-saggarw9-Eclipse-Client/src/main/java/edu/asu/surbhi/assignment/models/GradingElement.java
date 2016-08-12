package edu.asu.surbhi.assignment.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "GradingElement")
public class GradingElement {
	private String element;
	private String allocation;
	
	
	public String getElement() {
		return element;
	}
	@XmlAttribute(name = "name")
	public void setElement(String element) {
		this.element = element;
	}
	
	
	public String getAllocation() {
		return allocation;
	}
	@XmlAttribute(name = "allocation")
	public void setAllocation(String allocation) {
		this.allocation = allocation;
	}

}
