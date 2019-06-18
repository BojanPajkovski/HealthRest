$(document).ready(function () {

    $('#updateSuccess').hide();
    $('#insertSuccess').hide();
    $('#patientNameValidation').hide();
    $('#patientSurNameValidation').hide();
    $('#patientAgeValidation').hide();
    $('#insert').hide();

    var id = $('#id').val();
    if (id) {
        $.ajax({

            url: "/rest/patient/" +id,
            type: "GET",
            dataType:"json",
            contentType: "application/json",
            headers: {'token': 'token123'},
            success: function(result,status,xhr){
                console.log("success", result, "status : " , status, "xhr : ", xhr.status );


                $("#id").val(result.id);
                $("#name").val(result.name);
                $("#surname").val(result.surName);
                $("#age").val(result.age);




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

function patientValidation(){

    $('#patientNameValidation').hide();
    $('#patientSurNameValidation').hide();
    $('#patientAgeValidation').hide();
}

function savePatient(){

    console.log('from save patient');

    var patient = {};

    var id = $('#id').val();
    var name =  $('#name').val();
    var surname = $('#surname').val();
    var age = $('#age').val();

    patient['id'] = parseInt(id);
    patient['name'] = name;
    patient['surName'] = surname;
    patient['age'] = parseInt(age);


    if(id === null ||id===undefined ||id==="" ){

        if(name===null || name==="" || name===undefined) {

            $('#patientNameValidation').show();
            return;

        } else if(surname===null || surname==="" || surname===undefined) {

            $('#patientSurNameValidation').show();
            return;

        }

        else if(age===null || age==="" || age===undefined) {

            $('#patientAgeValidation').show();
            return;

        }

        $.ajax({

            url: "/rest/patient/insert",
            type: "POST",
            data: JSON.stringify(patient),
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

            $('#patientNameValidation').show();
            return;

        } else if(surname===null || surname==="" || surname===undefined) {

            $('#patientSurNameValidation').show();
            return;

        }

        else if(age===null || age==="" || age===undefined) {

            $('#patientAgeValidation').show();
            return;

        }

        $.ajax({

            url: "/rest/patient/update",
            type: "PUT",
            data: JSON.stringify(patient),
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

