<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.05.2019
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patients</title>

  <script src="resources/library/jquery-3.3.1.min.js" type="text/javascript"></script>
  <script src="resources/patient/patients.js" type="text/javascript"> </script>
  <link rel="stylesheet" href="resources/library/css/bootstrap.css"/>
</head>
<body>

<div id = "errorHappend">

  <h3> AN ERROR HAPPEND </h3>

</div>

<div id="container" class="container">

  <br/>
  <span>Search patient</span>
  <div class="row">
    <div class="col-md-3">
      <input type="text" id="patientName" placeholder="Patient name" onkeyup="searchValidation()" >
      <span id = "searchName" class="text-warning" >You must enter patient name to search</span>
    </div>
    <div class="col-md-3">
      <input type="text" id="patientSurname" placeholder="Patient surname" onkeyup="searchValidation()">
      <span id = "searchSurName" class="text-warning" >You must enter patient surname to search</span>
    </div>
    <div class="col-md-3">
      <input type="number" id="patientAge" placeholder="Patient age" onkeyup="searchValidation()">
      <span id = "searchAge" class="text-warning" >You must enter patient age to search</span>
    </div>
    <div class="col-md-2">
      <button class="btn btn-sm btn-primary" onclick="search()">Search</button>
    </div>
  </div>


  <h2>Patients Table</h2>
  <table class="table">
    <thead>
    <tr>
      <th>Name</th>
      <th>Surname</th>
      <th>Age</th>
    </tr>
    </thead>
    <tbody id="table-body">

    </tbody>
  </table>

  <div class="form-group">
    <button class="btn btn-primary btn-sm" onclick="addPatient()">Add Patient</button>
  </div>

  <div class="form-group">
    <input id="searchText" type="text" onkeyup="filterValidation()">
    <span id = "filterValidation" class="text-warning"> *You must enter text to do filter</span>
    <button class="btn btn-primary btn-sm" onclick="filterByPatientName()">Filter Patients</button>
  </div>


  <button  id= "displayAllButton" class="btn btn-primary btn-sm" onclick="displayAllPatients()">All Patients</button>

</div>

<div id = "deleteSuccess">

  <h3> YOU HAVE SUCCESSFULLY DELETED THE PATIENT</h3>

  <button class="btn btn-success" onclick='window.location ="/city?action=list"' >GO TO PATIENTS</button>

</div>

</body>
</html>
