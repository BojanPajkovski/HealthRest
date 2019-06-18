var hospitals = [];
var cities = [];

$(document).ready(function () {

    $('#deleteSuccess').hide();
    $('#errorHappend').hide();
    $('#filterValidation').hide();

    $.ajax({

        url: "/rest/hospital/all",
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(hospitalsAjax,status,xhr){
            console.log("success", hospitalsAjax, "status : " , status, "xhr : ", xhr.status );
            hospitals = hospitalsAjax;
            renderTable(hospitals);

            $('#deleteSuccess').hide()
            $('#displayAllButton').hide();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });

    $.ajax({

        url: "/rest/city/all",
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(cityAjax,status,xhr){
            console.log("success", cityAjax, "status : " , status, "xhr : ", xhr.status );
            cities = cityAjax;
            renderDropdown(cities);

            $('#deleteSuccess').hide()
            $('#displayAllButton').hide();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });




})



function renderTable(hospitalsAjax) {

    $.each(hospitalsAjax,
        function(id,hospital){
            var tr = $("<tr></tr>");

            var tdname = $("<td></td>");
            tdname.append(hospital.name)

            var tdSurName = $("<td></td>");
            tdSurName.append(hospital.location);

            var tdType = $("<td></td>");
            tdType.append(hospital.type);


            var tdaction = $("<td></td>");

            var editButton = $("<button class='btn-primary btn-sm'>Edit</button>");
            editButton.click(function(){
                editHospital(hospital.id)
            });

            var deleteButton = $("<button class='btn-danger btn-sm'>Delete</button>");
            deleteButton.click(function (){
                deleteHospital(hospital.id)

            })
            tdaction.append(editButton).append(deleteButton);
            tr.append(tdname);
            tr.append(tdSurName);
            tr.append(tdType);
            tr.append(tdaction);
            $("#table-body").append(tr);

        })
}


function editHospital (id) {

    window.location = '/hospital?action=edit&id=' + id;

}


function deleteHospital(id){

    console.log(' You are in "Delete Hospital" ' , id);

    $.ajax({
        url:"/rest/hospital/delete/" + id,
        type: "DELETE",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success:function(result,status,xhr){

            $('#deleteSuccess').show();
            $('#container').hide();

        },

        error: function(result,status,xhr){

            $('#errorHappend').show();
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
        }
    })
}

function addHospital(){

    window.location = '/hospital?action=insert';
}

function filterValidation(){

    $("#filterValidation").hide();
}

function filterByHospitalsName(){
    var name = $("#searchText").val();

    if(name === null || name===undefined || name === ""){

        $("#filterValidation").show();

        return;
    }

    var filtered = []
    for (var i = 0; i < hospitals.length ; i++){
        if(hospitals[i].name == name){
            filtered.push(hospitals[i]);
        }
    }
    console.log('filtered ', filtered);
    $( "#table-body" ).children("tr").remove();
    renderTable(filtered);
    $('#displayAllButton').show();

}


function displayAllHospitals() {
    $("#table-body").children("tr").remove();
    renderTable(hospitals);
    $('#displayAllButton').hide();

}

function renderDropdown(cities){

    $.each(cities, function (index,city){

        console.log(index,city);
        $("#cityDropdown").append('<option id="'+city.id+'" value="'+city.id+'">'+ city.name+'</option>')
    })
}

function filterByCity() {

    var id = $("#cityDropdown").val();
    console.log(id);
    $.ajax({

        url: "/rest/hospital/filterByCity/" + id,
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(hospitalData,status,xhr){
            console.log("success", hospitalData, "status : " , status, "xhr : ", xhr.status );
            hospitals = hospitalData;
            $( "#table-body" ).children("tr").remove();
            renderTable(hospitals);

            $('#deleteSuccess').hide()
            $('#displayAllButton').hide();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });



}

function search(){

    var name = $("#hospitalName").val();
    var location = $("#hospitalLocation").val();

    console.log('values are' , name, location);
    var url = '/rest/hospital/search';
    var hasName = false;
    if(name){
        url+='?name=' + name;
        hasName =true;
    }

    if(location) {

        if(hasName
        ){
            url+='&location='+location;
        } else {
            url+='?location=' + location;
        }
    }
    console.log('url' , url);
    $.ajax({

        url: url,
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(citiesAjax,status,xhr){
            console.log("success", citiesAjax, "status : " , status, "xhr : ", xhr.status );
            cities = citiesAjax;
            $( "#table-body" ).children("tr").remove();
            renderTable(cities);

            $('#deleteSuccess').hide()
            $('#displayAllButton').hide();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });



}