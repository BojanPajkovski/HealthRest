var patients = [];

$(document).ready(function () {

    $('#deleteSuccess').hide();
    $('#errorHappend').hide();
    $('#filterValidation').hide();
    $('#searchName').hide();
    $('#searchSurName').hide();
    $('#searchAge').hide();

    $.ajax({

        url: "/rest/patient/all",
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(patientsAjax,status,xhr){
            console.log("success", patientsAjax, "status : " , status, "xhr : ", xhr.status );
            patients = patientsAjax;
            renderTable(patients);

            $('#deleteSuccess').hide()
            $('#displayAllButton').hide();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });



})

function renderTable(patientsAjax) {

    $.each(patientsAjax,
        function(id,patient){
            var tr = $("<tr></tr>");

            var tdname = $("<td></td>");
            tdname.append(patient.name);

            var tdSurName = $("<td></td>");
            tdSurName.append(patient.surName);

            var tdAge = $("<td></td>");
            tdAge.append(patient.age);


            var tdaction = $("<td></td>");

            var editButton = $("<button class='btn-primary btn-sm'>Edit</button>");
            editButton.click(function(){
                editPatient(patient.id)
            });

            var deleteButton = $("<button class='btn-danger btn-sm'>Delete</button>");
            deleteButton.click(function (){
                deletePatient(patient.id)

            })
            tdaction.append(editButton).append(deleteButton);
            tr.append(tdname);
            tr.append(tdSurName);
            tr.append(tdAge);
            tr.append(tdaction);
            $("#table-body").append(tr);

        })
}


function editPatient (id) {

    window.location = '/patient?action=edit&id=' + id;

}

function deletePatient(id){

    console.log('Delete Patient' , id);

    $.ajax({
        url:"/rest/patient/delete/" + id,
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

function addPatient(){

    window.location = '/patient?action=insert';
}

function filterValidation(){

    $("#filterValidation").hide();
}

function filterByPatientName(){
    var name = $("#searchText").val();

    if(name === null || name===undefined || name === ""){

        $("#filterValidation").show();

        return;
    }

    var filtered = [];
    for (var i = 0; i < patients.length ; i++){
        if(patients[i].name == name){
            filtered.push(patients[i]);
        }
    }
    console.log('filtered ', filtered);
    $( "#table-body" ).children("tr").remove();
    renderTable(filtered);
    $('#displayAllButton').show();

}


function displayAllPatients() {
    $("#table-body").children("tr").remove();
    renderTable(patients);
    $('#displayAllButton').hide();

}

function searchValidation(){

    $('#searchName').hide();
    $('#searchSurName').hide();
    $('#searchAge').hide();
}

function search(){

    var name = $("#patientName").val();
    var surname = $("#patientSurname").val();
    var age = $("#patientAge").val();

    console.log('values are' , name, surname, age);

    var url = '/rest/patient/search';


    if(name===null || name==="" || name===undefined) {

        $('#searchName').show();
        return;

    } else if(surname===null || surname==="" || surname===undefined) {

        $('#searchSurName').show();
        return;

    }

    else if(age===null || age==="" || age===undefined) {

        $('#searchAge').show();
        return;

    }

    url+='?name='+ name + '&surname='+surname +'&age='+age;

    console.log('url' , url);
    $.ajax({

        url: url,
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(patientAjax,status,xhr){
            console.log("success", patientAjax, "status : " , status, "xhr : ", xhr.status );
            //cities = citiesAjax;
            $( "#table-body" ).children("tr").remove();
            renderTable(patientAjax);


            $('#displayAllButton').show();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });


}

