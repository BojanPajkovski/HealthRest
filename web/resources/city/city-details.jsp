<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.04.2019
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>City details</title>

  <script src="resources/library/jquery-3.3.1.min.js" type="text/javascript"></script>
  <script src = "resources/city/city-details.js" type="text/javascript"></script>
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
    <input type="text" name="name" placeholder="name" id ="name" onkeyup="cityValidation()"/>
    <span id = "cityNameValidation" class="text-warning"> *Name is required </span>
  </div>

  <div class="form-group">
    <label for="population"> Population</label>
    <input type="number" name="population" id="population" placeholder="population" onkeyup="cityValidation()" />
    <span id = "cityPopulationValidation" class="text-warning"> *Population is required </span>
  </div>

  <button id = "restSave" onclick="saveCity()" class="btn btn-primary btn-sm"> SAVE</button>
</div>

<div id = "updateSuccess">

  <h2> YOU HAVE SUCCESSFULLY UPDATED THE CITY</h2>

  <button class="btn btn-success" onclick='window.location ="/city?action=list"' >GO TO CITIES</button>

</div>

<div id = "insertSuccess">

  <h2> YOU HAVE SUCCESSFULLY INSERTED A CITY</h2>

  <button class="btn btn-success" onclick='window.location ="/city?action=list"' >GO TO CITIES</button>

</div>
</body>
</html>
