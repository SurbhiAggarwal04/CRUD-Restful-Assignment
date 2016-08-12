package edu.asu.surbhi.assignment.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="Student")
public class Student {
private String id;
private String name;
private String grade;
private String feedback;
private String gradingElement;



public String getGradingElement() {
	return gradingElement;
}

@XmlElement(name="GradingElement")
public void setGradingElement(String gradingElement) {
	this.gradingElement = gradingElement;
}
public String getId() {
	return id;
}
@XmlElement(name="id")
public void setId(String id) {
	this.id = id;
}

public String getName() {
	return name;
}
@XmlElement(name="name")
public void setName(String name) {
	this.name = name;
}

public String getGrade() {
	return grade;
}
@XmlElement(name="grade")
public void setGrade(String grade) {
	this.grade = grade;
}
public String getFeedback() {
	return feedback;
}
@XmlElement(name="feedback")
public void setFeedback(String feedback) {
	this.feedback = feedback;
}


}
