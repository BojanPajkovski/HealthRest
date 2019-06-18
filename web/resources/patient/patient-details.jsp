<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.05.2019
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient-details.jsp</title>

  <script src="resources/library/jquery-3.3.1.min.js" type="text/javascript"></script>
  <script src = "resources/patient/patient-details.js" type="text/javascript"></script>
  <link rel="stylesheet" href="resources/library/css/bootstrap.css"/>

</head>
<body>

<div id="editForm"  class="container">

  <input type="number" name="id" placeholder="id" id ="id" value="${id}"hidden="true" />

  <div id = "insert" class="form-group">
    <h3 >Insert form</h3>
  </div>

  <div id = "edit" class="form-group">
    <h3 >Edit form</h3>
  </div>

  <div class="form-group">
    <label for="name"> Name</label>
    <input type="text" name="name" placeholder="name" id ="name" onkeyup="patientValidation()"/>
    <span id = "patientNameValidation" class="text-warning"> *Name is required field </span>
  </div>

  <div class="form-group">
    <label for="surname"> Surname</label>
    <input type="text" name="surname" id="surname" placeholder="surname" onkeyup="patientValidation()" />
    <span id = "patientSurNameValidation" class="text-warning"> *Surname is required field </span>
  </div>

  <div class="form-group">
    <label for="age"> Age</label>
    <input type="number" name="age" id="age" placeholder="age" onkeyup="patientValidation()" />
    <span id = "patientAgeValidation" class="text-warning"> *Age is required field </span>
  </div>

  <button id = "restSave" onclick="savePatient()" class="btn btn-primary btn-sm"> SAVE</button>

</div>
<div id = "updateSuccess">

  <h2> YOU HAVE SUCCESSFULLY UPDATED THE PATIENT</h2>

  <button class="btn btn-success" onclick='window.location ="/patient?action=list"' >GO TO PATIENTS</button>

</div>

<div id = "insertSuccess">

  <h2> YOU HAVE SUCCESSFULLY INSERTED A PATIENT</h2>

  <button class="btn btn-success" onclick='window.location ="/patient?action=list"' >GO TO PATIENTS</button>

</div>

</body>
</html>
