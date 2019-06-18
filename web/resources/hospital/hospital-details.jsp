<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.05.2019
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hospital-details.jsp</title>

  <script src="resources/library/jquery-3.3.1.min.js" type="text/javascript"></script>
  <script src = "resources/hospital/hospital-details.js" type="text/javascript"></script>
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
    <input type="text" name="name" placeholder="name" id ="name" onkeyup="hospitalValidation()"/>
    <span id = "nameValidation" class="text-warning"> *Name is required field </span>
  </div>

  <div class="form-group">
    <label for="location"> Location</label>
    <input type="text" name="location" id="location" placeholder="location" onkeyup="hospitalValidation()" />
    <span id = "locationValidation" class="text-warning"> *Location is required field </span>
  </div>

  <div class="form-group">
    <label for="location"> Type</label>
    <input type="text" name="type" id="type" placeholder="type" onkeyup="hospitalValidation()" />
    <span id = "typeValidation" class="text-warning"> *Type is required field </span>
  </div>

  <div class="form-group">

    <select id ="cityDropdown" >
      <option value=""> Select city </option>
    </select>

    <span id = "cityValidation" class="text-warning" > *You must select city </span>
  </div>




  <button id = "restSave" onclick="saveHospital()" class="btn btn-primary btn-sm"> SAVE</button>
</div>

<div id = "updateSuccess">

  <h2> YOU HAVE SUCCESSFULLY UPDATED THE HOSPITAL</h2>

  <button class="btn btn-success" onclick='window.location ="/hospital?action=list"' >GO TO HOSPITALS</button>

</div>

<div id = "insertSuccess">

  <h2> YOU HAVE SUCCESSFULLY INSERTED A HOSPITAL</h2>

  <button class="btn btn-success" onclick='window.location ="/hospital?action=list"' >GO TO HOSPITALS</button>

</div>

</body>
</html>
