STEPS TO RUN THE ASSIGNMENT

1. Import both the projects into Eclipse.
   Check the following things after importing the project.
   1. The JRE JDK version is 1.8 and the apache tomcat version is Tomcat 8.
   2. Go to Properties of both the projects:
          1. Go to Deployment Assembly, and check if Maven dependencies is there and if it's not there, Go to Add->Java Build path Entries->Maven Dependencies.
          2. Go to Java Build Path, check if JRE 1.8 is there, if not, Go to Libraries-> Remove any other jre present->Add Library->JRE System Library.
          3. Go to Java Compiler-> Select Compiler Compliance Level : 1.8
          4. Go to Project facets, it should have Java 1.8 and Runtimes should have Tomcat version 8 checked.

2. First Run "CRUD-GradeBook-saggarw9-Eclipse-Server" on Server. A page is displayed which confirms "Server Started".

3. Now Run "CRUD-GradeBook-saggarw9-Eclipse-Client" on Server.
   1. A UI is displayed with four buttons:"Create Gradebook", "Add & update Gradebook", "Read Gradebook", "Delete Gradebook", "Read Complete XML file".
   2. Path where the file is created is also shown on UI.

4. Functionality:
   1. "Create Gradebook" button adds grading elements along with their allocation into the gradebook.
       You can add multiple grading elements at a time by clicking on "Create Gradebook" button..
   
   2. "Read Gradebook" button reads students graded under a grading element.
       1. Read by Student id: This button reads the grading details of a student for all grading elements.
       2. Read by Grading Element: This button reads all the students graded for a grading element.
       3. Read by Student id and Grading element: This button reads a student graded for a grading element.

   3. "Add & update Gradebook" button adds or updates a student entry when graded for a grading element.
      You can add or update multiple student entries by clicking on "Add & update Gradebook" button.

   4. "Delete Gradebook" button deletes student entries or the complete grading element from gradebook.xml file.
      1. Delete by Student id: This button deletes all the entries for a student for the grading element he/she is graded.
      2  Delete by Grading Element: This button deletes the complete grading details for all students for a grading element.
      3. Delete by student id and Grading Element: This button deletes a graded student entry from the grading element given.

   5. "Read Complete XML file" button reads the whole XML file.

5. "Create Gradebook", "Add & update Gradebook", "Read Gradebook" also gives location URIs to access from the browser.

   
   