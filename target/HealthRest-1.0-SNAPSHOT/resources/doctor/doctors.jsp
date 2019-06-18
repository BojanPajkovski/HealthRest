<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.05.2019
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Doctors.jsp</title>

  <script src="resources/library/jquery-3.3.1.min.js" type="text/javascript"></script>
  <script src="resources/doctor/doctors.js" type="text/javascript"> </script>
  <link rel="stylesheet" href="resources/library/css/bootstrap.css"/>
</head>
<body>

<div id = "errorHappend">

  <h3> AN ERROR HAPPEND </h3>

</div>

       <%--TABELA ZA DTO--%>

<div id="dtoContainer" class="container">

  <h2>Patients by Doctor Table</h2>
  <table class="table">
    <thead>
    <tr>
      <th>doctorName</th>
      <th>patientName</th>
      <th>dijagnoza</th>
      <th>age</th>
    </tr>
    </thead>
    <tbody id="dtoTable-body">

    </tbody>
  </table>

  <button  id= "displayAllButton1" class="btn btn-primary btn-sm" onclick="displayAllDoctors()">All Doctors</button>

  </div>

<div id="container" class="container">

  <br/>
  <span>Search Doctor</span>
  <div class="row" id = "searchContainer">
    <div class="col-md-3">
      <input type="text" id="doctorName" placeholder="Doctor name">
      <span>insert doctor name</span>
    </div>
    <div class="col-md-3">
      <input type="text" id="doctorSurname" placeholder="Doctor surname">
      <span>insert doctor surname</span>
    </div>
    <div class="col-md-2">
      <button class="btn btn-sm btn-primary" onclick="search()">Search</button>
    </div>
  </div>
  <h2>Doctors Table</h2>

  <table class="table">
    <thead>
    <tr>
      <th>Name</th>
      <th>Surname</th>
      <th>Position</th>
      <th>Age</th>
    </tr>
    </thead>
    <tbody id="table-body">

    </tbody>
  </table>



  <div class="form-group">
    <button class="btn btn-primary btn-sm" onclick="addDoctor()">Add Doctor</button>
  </div>


  <div class="form-group">
    <input id="searchText" type="text" onkeyup="filterValidation()">
    <span id = "filterValidation" class="text-warning"> *You must enter text to do filter</span>
    <button class="btn btn-primary btn-sm" onclick="filterByDoctorsName()">Filter Doctors</button>
  </div>

  <div class = "form-group">

    <select id ="hospitalDropdown" >

    </select>

    <button class="btn btn-primary btn-sm" id = "filterByHospital"  onclick="filterByHospital()"> Filter by Hospital</button>

  </div>


  <button  id= "displayAllButton" class="btn btn-primary btn-sm" onclick="displayAllDoctors()">All Doctors</button>

</div>


<div id = "deleteSuccess">

  <h3> YOU HAVE SUCCESSFULLY DELETED THE DOCTOR</h3>

  <button class="btn btn-success" onclick='window.location ="/doctor?action=list"' >GO TO CITIES</button>

</div>

</body>
</html>
