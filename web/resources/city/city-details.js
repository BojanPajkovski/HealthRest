$(document).ready(function () {

    $('#updateSuccess').hide();
    $('#insertSuccess').hide();
    $('#cityPopulationValidation').hide();
    $('#cityNameValidation').hide();
    $('#insert').hide();

    var id = $('#id').val();
    if (id) {
        $.ajax({

            url: "/rest/city/" +id,
            type: "GET",
            dataType:"json",
            contentType: "application/json",
            headers: {'token': 'token123'},
            success: function(result,status,xhr){
                console.log("success", result, "status : " , status, "xhr : ", xhr.status );


                $("#id").val(result.id);
                $("#name").val(result.name);
                $("#population").val(result.population);




            },

            error: function(xhr,status,error){

                console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            }
        });

    }else{

        $('#insert').show();
        $('#edit').hide();


    }

})

function cityValidation(){

    $('#cityPopulationValidation').hide();
    $('#cityNameValidation').hide();
}

function saveCity(){

    console.log('from save city');

    var city = {};

    var id = $('#id').val();
    var name =  $('#name').val();
    var population = $('#population').val();

    city['id'] = parseInt(id);
    city['name'] = name;
    city['population'] = parseFloat(population);


    if(id === null ||id===undefined ||id==="" ){

        if(name===null || name==="" || name===undefined) {

            $('#cityNameValidation').show();
            return;

        } else if(population===null || population==="" || population===undefined) {

            $('#cityPopulationValidation').show();
            return;

        }

        $.ajax({

            url: "/rest/city/insert",
            type: "POST",
            data: JSON.stringify(city),
            contentType: "application/json",
            dataType:"json",
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

            $('#cityNameValidation').show();
            return;

        } else if(population===null || population==="" || population===undefined) {

            $('#cityPopulationValidation').show();
            return;

        }

        $.ajax({

            url: "/rest/city/update",
            type: "PUT",
            data: JSON.stringify(city),
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
