<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.05.2019
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Doctor-details.jsp</title>

  <script src="resources/library/jquery-3.3.1.min.js" type="text/javascript"></script>
  <script src = "resources/doctor/doctor-details.js" type="text/javascript"></script>
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
    <input type="text" name="name" placeholder="name" id ="name" onkeyup="doctorValidation()"/>
    <span id = "nameValidation" class="text-warning"> *Name is required field </span>
  </div>

  <div class="form-group">
    <label for="surName"> Surname</label>
    <input type="text" name="surName" id="surName" placeholder="surName" onkeyup="doctorValidation()" />
    <span id = "surNameValidation" class="text-warning"> *Surname is required field </span>
  </div>

  <div class="form-group">
    <label for="jobPosition"> Job Position</label>
    <input type="text" name="jobPosition" id="jobPosition" placeholder="jobPosition" onkeyup="doctorValidation()" />
    <span id = "jobPoistionValidation" class="text-warning"> *Job Position is required field </span>
  </div>

  <div class="form-group">
    <label for="age"> Age</label>
    <input type="number" name="age" id="age" placeholder="age" onkeyup="doctorValidation()" />
    <span id = "ageValidation" class="text-warning"> *Age is required field </span>
  </div>

  <button id = "restSave" onclick="saveDoctor()" class="btn btn-primary btn-sm"> SAVE</button>
</div>

<div id = "updateSuccess">

  <h2> YOU HAVE SUCCESSFULLY UPDATED THE DOCTOR</h2>

  <button class="btn btn-success" onclick='window.location ="/doctor?action=list"' >GO TO DOCTORS</button>

</div>

<div id = "insertSuccess">

  <h2> YOU HAVE SUCCESSFULLY INSERTED A DOCTOR</h2>

  <button class="btn btn-success" onclick='window.location ="/doctor?action=list"' >GO TO DOCTORS</button>

</div>


</body>
</html>
