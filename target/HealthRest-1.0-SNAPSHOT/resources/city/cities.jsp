<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.05.2019
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cities.jsp</title>

  <script src="resources/library/jquery-3.3.1.min.js" type="text/javascript"></script>
  <script src="resources/city/cities.js" type="text/javascript"> </script>
  <link rel="stylesheet" href="resources/library/css/bootstrap.css"/>
</head>
<body>

<div id = "errorHappend">

  <h3> AN ERROR HAPPEND </h3>

</div>

<div id="container" class="container">
  <br/>
  <span>Search city</span>
  <div class="row">
    <div class="col-md-3">
      <input type="text" id="cityName" placeholder="City name">
      <span>insert city name</span>
    </div>
    <div class="col-md-3">
      <input type="number" id="cityPopulation" placeholder="City population">
      <span>insert city population</span>
    </div>
    <div class="col-md-2">
      <button class="btn btn-sm btn-primary" onclick="search()">Search</button>
    </div>
  </div>

  <h2>Cities Table</h2>
  <table class="table">
    <thead>
    <tr>
      <th>Name</th>
      <th>Population</th>
    </tr>
    </thead>
    <tbody id="table-body">

    </tbody>
  </table>

  <div class="form-group">
    <button class="btn btn-primary btn-sm" onclick="addCity()">Add City</button>
  </div>

  <div class="form-group">
    <input id="searchText" type="text" onkeyup="filterValidation()">
    <span id = "filterValidation" class="text-warning"> *You must enter text to do filter</span>
    <button class="btn btn-primary btn-sm" onclick="filterByCityName()">Filter Cities</button>
  </div>


  <button  id= "displayAllButton" class="btn btn-primary btn-sm" onclick="displayAllCities()">All Cities</button>

</div>

<div id = "deleteSuccess">

  <h3> YOU HAVE SUCCESSFULLY DELETED THE CITY</h3>

  <button class="btn btn-success" onclick='window.location ="/city?action=list"' >GO TO CITIES</button>

</div>

</body>
</html>
