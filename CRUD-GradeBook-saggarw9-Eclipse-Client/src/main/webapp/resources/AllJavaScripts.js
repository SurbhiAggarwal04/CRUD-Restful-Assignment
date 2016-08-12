/**
 * 
 */
var index=0;
function generateGradeBookElements() {
	
	index++;
	var noOfElements=document.getElementById("noOfElements");
	noOfElements.setAttribute("value", index);
	
	var labelElement1=document.createElement("label");
	labelElement1.innerHTML="Grading Element: ";
	
	var labelElement2=document.createElement("label");
	labelElement2.innerHTML="Percentage allocation: ";
	labelElement1.style.width = "200px";
	labelElement2.style.width = "200px";	
	labelElement1.style.display='inline-block';
	labelElement2.style.display='inline-block';
	
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "gradingElement"+index);
	
   
	var form = document.getElementById("elements");
	linebreak = document.createElement("br");
	form.appendChild(linebreak);	
	form.appendChild(linebreak);
	
	form.appendChild(labelElement1);
	form.appendChild(element);
	
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	form.appendChild(linebreak);


	form.appendChild(labelElement2);
	element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "percentageAllocation"+index);
	form.appendChild(element);
	
	document.getElementById("submitbtn").style.display="block";

}
index=0;
function updateGradeBookElements() {
	
	index++;
	var noOfElements=document.getElementById("noOfElementsUpdate");
	noOfElements.setAttribute("value", index);
	
	var labelElement1=document.createElement("label");
	labelElement1.innerHTML="Grading Element: ";
	labelElement1.style.display='inline-block';
	
	var labelElement2=document.createElement("label");
	labelElement2.innerHTML="Student id: ";
	labelElement2.style.display='inline-block';

	var labelElement3=document.createElement("label");
	labelElement3.innerHTML="Name: ";
	labelElement3.style.display='inline-block';

	var labelElement4=document.createElement("label");
	labelElement4.innerHTML="Grade: ";
	labelElement4.style.display='inline-block';

	var labelElement5=document.createElement("label");
	labelElement5.innerHTML="Feedback: ";
	labelElement5.style.display='inline-block';

	labelElement1.style.width = "150px";
	labelElement2.style.width = "150px";
	labelElement3.style.width = "150px";
	labelElement4.style.width = "150px";
	labelElement5.style.width = "150px";
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "updateGradingElement"+index);
   
	var form = document.getElementById("elementsUpdate");
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	
	form.appendChild(labelElement1);
	form.appendChild(element);
	
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	linebreak = document.createElement("br");
	form.appendChild(linebreak);


	form.appendChild(labelElement2);
	element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "studentId"+index);

	form.appendChild(element);
	
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	linebreak = document.createElement("br");
	form.appendChild(linebreak);


	form.appendChild(labelElement3);
	element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "studentName"+index);

	form.appendChild(element);
	
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	linebreak = document.createElement("br");
	form.appendChild(linebreak);


	form.appendChild(labelElement4);
	element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "studentGrade"+index);

	form.appendChild(element);
	
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	linebreak = document.createElement("br");
	form.appendChild(linebreak);


	form.appendChild(labelElement5);
	element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "studentFeedback"+index);

	form.appendChild(element);
	document.getElementById("updateSubmitbtn").style.display="block";

}
function readGradeBookElements()
{
	document.getElementById("readGradingElementbtn").style.display="block";
	document.getElementById("readStudentIdbtn").style.display="block";	
	document.getElementById("elementsRead").innerHTML="";
	document.getElementById("elementsRead").style.display="none";
	document.getElementById("readSubmitbtn").style.display="none";
	document.getElementById("readByBothbtn").style.display="block";
	
}

function readByGradingElement()
{
	document.getElementById("elementsRead").style.display="block";
	var form = document.getElementById("elementsRead");
	var labelElement1=document.createElement("label");
	labelElement1.innerHTML="Grading Element: ";
	
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "gradingElementToRead");
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	form.appendChild(labelElement1);
	form.appendChild(element);
	document.getElementById("readSubmitbtn").style.display="block";
	document.getElementById("readStudentIdbtn").style.display="none";	
	document.getElementById("readByBothbtn").style.display="none";
}

function readByStudentId()
{
	document.getElementById("elementsRead").style.display="block";
	var form = document.getElementById("elementsRead");
	var labelElement1=document.createElement("label");
	labelElement1.innerHTML="Student id: ";
	
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "studentIdToRead");
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	form.appendChild(labelElement1);
	form.appendChild(element);
	document.getElementById("readSubmitbtn").style.display="block";
	document.getElementById("readByBothbtn").style.display="none";
	document.getElementById("readGradingElementbtn").style.display="none";
}

function readByBoth()
{
	document.getElementById("elementsRead").style.display="block";
	var form = document.getElementById("elementsRead");
	var labelElement2=document.createElement("label");
	labelElement2.innerHTML="Student id: ";
	
	var labelElement1=document.createElement("label");
	labelElement1.innerHTML="Grading Element: "; 
	
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "gradingElementToRead");
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	form.appendChild(labelElement1);
	form.appendChild(element);
	
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "studentIdToRead");
	form.appendChild(labelElement2);
	form.appendChild(element);
	
	document.getElementById("readSubmitbtn").style.display="block";
	document.getElementById("readGradingElementbtn").style.display="none";
	document.getElementById("readStudentIdbtn").style.display="none";
}
function deleteGradeBookElements()
{
	document.getElementById("deletGradeElebtn").style.display="block";
	document.getElementById("deleteStudentbtn").style.display="block";	
	document.getElementById("elementsDelete").innerHTML="";
	document.getElementById("elementsDelete").style.display="none";
	document.getElementById("deleteSubmitbtn").style.display="none";
	document.getElementById("deletebothbtn").style.display="block";
	
}

function deletGradeElement()
{
	document.getElementById("elementsDelete").style.display="block";
	var form = document.getElementById("elementsDelete");
	var labelElement1=document.createElement("label");
	labelElement1.innerHTML="Grading Element: ";
	
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "gradingElementToDelete");
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	form.appendChild(labelElement1);
	form.appendChild(element);
	document.getElementById("deleteSubmitbtn").style.display="block";
	document.getElementById("deleteStudentbtn").style.display="none";	
	document.getElementById("deletebothbtn").style.display="none";
}
function deletStudent()
{
	document.getElementById("elementsDelete").style.display="block";
	var form = document.getElementById("elementsDelete");
	var labelElement1=document.createElement("label");
	labelElement1.innerHTML="Student id: ";
	
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "studentIdToDelete");
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	form.appendChild(labelElement1);
	form.appendChild(element);
	document.getElementById("deleteSubmitbtn").style.display="block";
	document.getElementById("deletGradeElebtn").style.display="none";
	document.getElementById("deletebothbtn").style.display="none";
}
function deletboth()
{
	document.getElementById("elementsDelete").style.display="block";
	var form = document.getElementById("elementsDelete");
	var labelElement2=document.createElement("label");
	labelElement2.innerHTML="Student id: ";
	
	var labelElement1=document.createElement("label");
	labelElement1.innerHTML="Grading Element: "; 
	
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "gradingElementToDelete");
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	form.appendChild(labelElement1);
	form.appendChild(element);
	
	linebreak = document.createElement("br");
	form.appendChild(linebreak);
	element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "studentIdToDelete");
	form.appendChild(labelElement2);
	form.appendChild(element);
	
	document.getElementById("deleteSubmitbtn").style.display="block";
	document.getElementById("deletGradeElebtn").style.display="none";
	document.getElementById("deleteStudentbtn").style.display="none";
}