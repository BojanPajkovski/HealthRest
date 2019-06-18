$(document).ready(function () {

    $('#updateSuccess').hide();
    $('#insertSuccess').hide();
    $('#nameValidation').hide();
    $('#locationValidation').hide();
    $('#typeValidation').hide();
    $('#cityValidation').hide();
    $('#insert').hide();

    var id = $('#id').val();

    if (id) {
        $.ajax({

            url: "/rest/hospital/" +id,
            type: "GET",
            dataType:"json",
            contentType: "application/json",
            headers: {'token': 'token123'},
            success: function(hospital,status,xhr){
                console.log("success", hospital, "status : " , status, "xhr : ", xhr.status );


                $("#id").val(hospital.id);
                $("#name").val(hospital.name);
                $("#location").val(hospital.location);
                $("#type").val(hospital.type);


            },

            error: function(xhr,status,error){

                console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            }
        });

    }else{

        $('#insert').show();
        $('#edit').hide();


    }

    $.ajax({

        url: "/rest/city/all",
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(dataCities,status,xhr){
            console.log("success", dataCities, "status : " , status, "xhr : ", xhr.status );


           renderDropdown(dataCities);

        },

        error: function(xhr,status,error){

            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
        }
    });

})

function hospitalValidation(){

    $('#nameValidation').hide();
    $('#locationValidation').hide();
    $('#typeValidation').hide();

}

function renderDropdown(cities){

 $.each(cities, function (index,city){

 console.log(index,city);
 $("#cityDropdown").append('<option id="'+city.id+'" value="'+city.id+'">'+ city.name+'</option>')
 })
 }

function saveHospital(){

    console.log('Hello, You have reached to save hospital');

    var hospital = {};

    var id = $('#id').val();
    var name =  $('#name').val();
    var location = $('#location').val();
    var type = $('#type').val();
    var cityId = $('#cityDropdown').val();


    hospital['id'] = parseInt(id);
    hospital['name'] = name;
    hospital['location'] = location;
    hospital['type']= type;
    hospital['cityId']= cityId;



    if(id === null ||id===undefined ||id==="" ){

        if(name===null || name==="" || name===undefined) {

            $('#nameValidation').show();
            return;

        } else if(location===null || location==="" || location===undefined) {

            $('#locationValidation').show();
            return;

        }
        else if(type===null || type==="" || type===undefined) {

            $('#typeValidation').show();
            return;

        }

        $.ajax({

            url: "/rest/hospital/insert",
            type: "POST",
            data: JSON.stringify(hospital),
            contentType: "application/json",
            headers: {'token': 'token123'},
            success: function(result,status,xhr){

                console.log("success", result, "status : " , status, "xhr : ", xhr.status );
                $('#editForm').hide();
                $('#insertSuccess').show();
            },

            error: function(xhr,status,error){

                console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            }
        });

    } else{

        if(name===null || name==="" || name===undefined) {

            $('#nameValidation').show();
            return;

        } else if(location===null || location==="" || location===undefined) {

            $('#locationValidation').show();
            return;

        }
        else if(type===null || type==="" || type===undefined) {

            $('#typeValidation').show();
            return;

        }

        $.ajax({

            url: "/rest/hospital/update",
            type: "PUT",
            data: JSON.stringify(hospital),
            contentType: "application/json",
            dataType:"json",
            headers: {'token': 'token123'},
            success: function(result,status,xhr){

                console.log("success", result, "status : " , status, "xhr : ", xhr.status );
                $('#editForm').hide();
                $('#updateSuccess').show();

            },

            error: function(xhr,status,error){
                $('#errorHappend').show();

                console.log("error", "status : " ,status, "error" , error, "xhr : " , xhr)
            }
        })
    }

}

