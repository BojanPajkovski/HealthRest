var cities = [];

$(document).ready(function () {

    $('#deleteSuccess').hide();
    $('#errorHappend').hide();
    $('#filterValidation').hide();

    $.ajax({

        url: "/rest/city/all",
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(citiesAjax,status,xhr){
            console.log("success", citiesAjax, "status : " , status, "xhr : ", xhr.status );
            cities = citiesAjax;
            renderTable(cities);

            $('#deleteSuccess').hide()
            $('#displayAllButton').hide();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });



})

function renderTable(citiesAjax) {

    $.each(citiesAjax,
        function(id,city){
            var tr = $("<tr></tr>");
            var tdname = $("<td></td>");
            tdname.append(city.name)
            var tdpopulation = $("<td></td>");
            tdpopulation.append(city.population)

            var tdaction = $("<td></td>");

            var editButton = $("<button class='btn-primary btn-sm'>Edit</button>");
            editButton.click(function(){
                editCity(city.id)
            });

            var deleteButton = $("<button class='btn-danger btn-sm'>Delete</button>");
            deleteButton.click(function (){
                deleteCity(city.id)

            })
            tdaction.append(editButton).append(deleteButton);
            tr.append(tdname);
            tr.append(tdpopulation);
            tr.append(tdaction);
            $("#table-body").append(tr);

        })
}


function editCity (id) {

    window.location = '/city?action=edit&id=' + id;

}

function deleteCity(id){

    console.log('Delete City' , id);

    $.ajax({
        url:"/rest/city/delete/" + id,
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

function addCity(){

    window.location = '/city?action=insert';
}

function filterValidation(){

    $("#filterValidation").hide();
}

function filterByCityName(){
    var name = $("#searchText").val();

    if(name === null || name===undefined || name === ""){

        $("#filterValidation").show();

        return;
    }

    var filtered = []
    for (var i = 0; i < cities.length ; i++){
        if(cities[i].name == name){
            filtered.push(cities[i]);
        }
    }
    console.log('filtered ', filtered);
    $( "#table-body" ).children("tr").remove();
    renderTable(filtered);
    $('#displayAllButton').show();

}


function displayAllCities() {
    $("#table-body").children("tr").remove();
    renderTable(cities);
    $('#displayAllButton').hide();

}

function search(){

    var name = $("#cityName").val();
    var population = $("#cityPopulation").val();

    console.log('values are' , name, population);
    var url = '/rest/city/search';
    var hasName = false;
    if(name){
        url+='?name=' + name;
        hasName =true;
    }

    if(population) {

        if(hasName){
            url+='&population='+population;
        } else {
            url+='?population=' + population;
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
