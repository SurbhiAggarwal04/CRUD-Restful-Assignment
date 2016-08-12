<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page import="java.util.List"%>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/AllJavaScripts.js">
</script>

</head>
<body bgcolor="#c2c2d6" style="font-size: large;font-weight: bolder;">

<center><h3>Assignment-2</h3>
<h4>Surbhi Aggarwal</h4>
<h5>ASURITE:saggarw9</h5>
</center>
<c:if test="${not empty path }" >
The file is present at the following location:
${path }
</c:if>
<hr>
<form action="CreateGradeBook" name="CreateGradeBook" method="post">
<input type="number" name="noOfElements" style="display: none" id="noOfElements" value=1/>
<input type="button" name="createGradeBookbtn" value="Create Grade Book" onclick="generateGradeBookElements();" />
<br>
<span id="elements"></span>
<input type="submit" value="Submit" id="submitbtn" style="display: none"/>


</form>
<c:if test="${not empty locationCreate }" >
URI: ${locationCreate }
<%request.getSession().setAttribute("locationCreate", null); %>

</c:if>
<br>
<c:if test="${not empty createdElements }" >
Following elements are added in the gradebook.xml
<textarea rows="" cols="" style="width: 100%;height: 25%">${createdElements }</textarea>
<%request.getSession().setAttribute("createdElements", null); %>
</c:if>
<c:if test="${not empty notCreatedElements }" >
The Following elements already exist in gradebook.xml.
<textarea rows="" cols="" style="width: 100%;height: 25%">${notCreatedElements }</textarea>
<%request.getSession().setAttribute("notCreatedElements", null); %>
</c:if>


<hr>

<form action="ReadGradeBook" name="ReadGradeBook" method="post">
<input type="button" name="readGradeBookbtn" value="Read Grade Book" onclick="readGradeBookElements();" />
<input type="button" name="readGradingElementbtn" id="readGradingElementbtn" value="Read By Grading Element" onclick="readByGradingElement();" style="display: none"/>
<input type="button" name="readStudentIdbtn" id="readStudentIdbtn" value="Read By Student Id" onclick="readByStudentId();" style="display: none"/>
<input type="button" name="readByBothbtn" id="readByBothbtn" value="Read By Student Id and Grading Element" onclick="readByBoth();" style="display: none"/>
<br>
<span id="elementsRead"></span>
<input type="submit" value="Submit" id="readSubmitbtn" style="display: none"/>
</form>
<c:if test="${not empty location }" >
URI: ${location }
<%request.getSession().setAttribute("location", null); %>
</c:if>
<br>
<c:if test="${not empty readStudentElements }" >
Following elements are read from the gradebook.xml
<textarea rows="" cols="" style="width: 100%;height: 25%">${readStudentElements }</textarea>
<%request.getSession().setAttribute("readStudentElements", null); %>
</c:if>


<hr>

<form name="updateGradeBook" method="post" action="UpdateGradeBook">
<input type="number" name="noOfElementsUpdate" style="display: none" id="noOfElementsUpdate" value=1/>
<input type="button" name="updateGradeBookbtn" value="Add & Update Grade Book" onclick="updateGradeBookElements();" />
<br>
<span id="elementsUpdate"></span>
<input type="submit" value="Submit" id="updateSubmitbtn" style="display: none"/>
</form>
<c:if test="${not empty locationUpdate }" >
URI: ${locationUpdate }
<%request.getSession().setAttribute("locationUpdate", null); %>

</c:if>
<br>
<c:if test="${not empty createdStudentElements }" >
Following elements are added in the gradebook.xml
<textarea rows="" cols="" style="width: 100%;height: 25%">${createdStudentElements }</textarea>
<%request.getSession().setAttribute("createdStudentElements", null); %>
</c:if>


<hr>

<form action="DeleteGradeBook" method="post" name="deleteGradeBook">
<input type="button" name="deleteGradeBookbtn" value="Delete Grade Book" onclick="deleteGradeBookElements();" />
<input type="button" name="deletGradeElebtn" id="deletGradeElebtn" value="Delete By Grading Element" onclick="deletGradeElement();" style="display:none;"/><br>
<input type="button" name="deleteStudentbtn" id="deleteStudentbtn" value="Delete Student" onclick="deletStudent();" style="display:none;"/><br>
<input type="button" name="deletebothbtn" id="deletebothbtn" value="Delete By Student id and Grading Element" onclick="deletboth();" style="display:none;"/><br>

<span id="elementsDelete"></span>

<input type="submit" value="Submit" id="deleteSubmitbtn" style="display: none"/>

</form>
<%-- <c:if test="${not empty locationDelete }" >
URI: ${locationDelete }
<%request.getSession().setAttribute("locationDelete", null); %>

</c:if> --%>
<br>
<c:if test="${not empty deletedStudentElements }" >
Following elements are deleted from the gradebook.xml
<textarea rows="" cols="" style="width: 100%;height: 25%">${deletedStudentElements }</textarea>
<%request.getSession().setAttribute("deletedStudentElements", null); %>
</c:if>


<hr>


<form action="ReadXML" method="get">
<input type="submit" value="Read Complete XML file">
</form>
<c:if test="${not empty xml }" >
XML File
<textarea rows="" cols="" style="width: 100%;height: 75%">${xml }</textarea>
<%request.getSession().setAttribute("xml", null); %>
</c:if>
</body>
</html>
