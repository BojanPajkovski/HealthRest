<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.05.2019
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hospitals.jsp</title>

  <script src="resources/library/jquery-3.3.1.min.js" type="text/javascript"></script>
  <script src="resources/hospital/hospitals.js" type="text/javascript"> </script>
  <link rel="stylesheet" href="resources/library/css/bootstrap.css"/>

</head>
<body>

<div id = "errorHappend">

  <h3> AN ERROR HAPPEND </h3>

</div>



<div id="container" class="container">

                    <%--SEARCH--%>
  <br/>
  <span>Search hospital</span>
  <div class="row">
    <div class="col-md-3">
      <input type="text" id="hospitalName" placeholder="Hospital name">
      <span>insert hospital name</span>
    </div>
    <div class="col-md-3">
      <input type="text" id="hospitalLocation" placeholder="Hospital location">
      <span>insert hospital location</span>
    </div>
    <div class="col-md-2">
      <button class="btn btn-sm btn-primary" onclick="search()">Search</button>
    </div>
  </div>

  <h2>Hospitals Table</h2>
  <table class="table">
    <thead>
    <tr>
      <th>Name</th>
      <th>Location</th>
      <th>Type</th>

    </tr>
    </thead>
    <tbody id="table-body">

    </tbody>
  </table>



<div class="form-group">
  <button class="btn btn-primary btn-sm" onclick="addHospital()">Add Hospital</button>
</div>

<div class="form-group">
  <input id="searchText" type="text" onkeyup="filterValidation()">
  <span id = "filterValidation" class="text-warning"> *You must enter text to do filter</span>
  <button class="btn btn-primary btn-sm" onclick="filterByHospitalsName()">Filter Hospitals</button>
</div>

  <div class = "form-group">

    <select id ="cityDropdown" >

    </select>

    <button class="btn btn-primary btn-sm" id = "filterByCity"  onclick="filterByCity()"> Filter by City</button>

  </div>

  <button  id= "displayAllButton" class="btn btn-primary btn-sm" onclick="displayAllHospitals()">All Hospitals</button>

</div>


<div id = "deleteSuccess">

  <h3> YOU HAVE SUCCESSFULLY DELETED THE HOSPITAL</h3>

  <button class="btn btn-success" onclick='window.location ="/hospital?action=list"' >GO TO HOSPITALS</button>

</div>

</body>
</html>
