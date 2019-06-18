
$(document).ready(function () {

    $('#updateSuccess').hide();
    $('#insertSuccess').hide();
    $('#nameValidation').hide();
    $('#surNameValidation').hide();
    $('#jobPoistionValidation').hide();
    $('#ageValidation').hide();

    $('#insert').hide();

    var id = $('#id').val();

    if (id) {
        $.ajax({

            url: "/rest/doctor/" +id,
            type: "GET",
            dataType:"json",
            contentType: "application/json",
            headers: {'token': 'token123'},
            success: function(doctor,status,xhr){
                console.log("success", doctor, "status : " , status, "xhr : ", xhr.status );


                $("#id").val(doctor.id);
                $("#name").val(doctor.name);
                $("#surName").val(doctor.surName);
                $("#jobPosition").val(doctor.jobPosition);
                $("#age").val(doctor.age);


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

function doctorValidation(){

    $('#nameValidation').hide();
    $('#surNameValidation').hide();
    $('#jobPoistionValidation').hide();
    $('#ageValidation').hide();

}

function saveDoctor(){

    console.log('Hello, You have reached to save doctor');

    var doctor = {};

    var id = $('#id').val();
    var name =  $('#name').val();
    var surName = $('#surName').val();
    var jobPosition = $('#jobPosition').val();
    var age = $('#age').val();

    doctor['id'] = parseInt(id);
    doctor['name'] = name;
    doctor['surName'] = surName;
    doctor['jobPosition']=jobPosition;
    doctor['age'] = parseInt(age);


    if(id === null ||id===undefined ||id==="" ){

        if(name===null || name==="" || name===undefined) {

            $('#nameValidation').show();
            return;

        } else if(surName===null || surName==="" || surName===undefined) {

            $('#surNameValidation').show();
            return;

        }
        else if(jobPosition===null || jobPosition==="" || jobPosition===undefined) {

            $('#jobPoistionValidation').show();
            return;

        }
        else if(age===null || age==="" || age===undefined) {

            $('#ageValidation').show();
            return;

        }

        $.ajax({

            url: "/rest/doctor/insert",
            type: "POST",
            data: JSON.stringify(doctor),
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

            $('#nameValidation').show();
            return;

        } else if(surName===null || surName==="" || surName===undefined) {

            $('#surNameValidation').show();
            return;

        }
        else if(jobPosition===null || jobPosition==="" || jobPosition===undefined) {

            $('#jobPoistionValidation').show();
            return;

        }
        else if(age===null || age==="" || age===undefined) {

            $('#ageValidation').show();
            return;

        }
        $.ajax({

            url: "/rest/doctor/update",
            type: "PUT",
            data: JSON.stringify(doctor),
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

