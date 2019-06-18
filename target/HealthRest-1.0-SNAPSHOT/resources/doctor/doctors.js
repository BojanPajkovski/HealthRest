var doctors = [];
var hospitals = [];
var patientsByDoctor = [];

$(document).ready(function () {

    $('#deleteSuccess').hide();
    $('#errorHappend').hide();
    $('#filterValidation').hide();
    $('#dtoContainer').hide();

    /* INSTANT AJAX CALL FOR ALL DOCTORS*/

    $.ajax({

        url: "/rest/doctor/all",
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(doctorsAjax,status,xhr){
            console.log("success", doctorsAjax, "status : " , status, "xhr : ", xhr.status );
            doctors = doctorsAjax;
            renderTable(doctors);

            $('#deleteSuccess').hide()
            $('#displayAllButton').hide();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });

                     /* INSTANT AJAX CALL FOR ALL HOSPITALS*/
    $.ajax({

        url: "/rest/hospital/all",
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(hospitalAjax,status,xhr){
            console.log("success", hospitalAjax, "status : " , status, "xhr : ", xhr.status );
            hospitals = hospitalAjax;
            renderDropdown(hospitals);

            $('#deleteSuccess').hide()
            $('#displayAllButton').hide();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });




})



function renderTable(doctorsAjax) {

    $.each(doctorsAjax,
        function(id,doctor){
            var tr = $("<tr></tr>");

            var tdname = $("<td></td>");
            tdname.append(doctor.name)

            var tdSurName = $("<td></td>");
            tdSurName.append(doctor.surName);

            var tdJobPosition = $("<td></td>");
            tdJobPosition.append(doctor.jobPosition);

            var tdAge = $("<td></td>");
            tdAge.append(doctor.age);


            var tdaction = $("<td></td>");

            var editButton = $("<button class='btn-primary btn-sm'>Edit</button>");
            editButton.click(function(){
                editDoctor(doctor.id)
            });

            var deleteButton = $("<button class='btn-danger btn-sm'>Delete</button>");
            deleteButton.click(function (){
                deleteDoctor(doctor.id)

            })

            var patientsByDoctor = $("<button class='btn-primary btn-sm'>Get Patients</button>");
            patientsByDoctor.click(function(){
                getPatietnsByDoctor(doctor.id)
            });


            tdaction.append(editButton).append(deleteButton).append(patientsByDoctor);
            tr.append(tdname);
            tr.append(tdSurName);
            tr.append(tdJobPosition);
            tr.append(tdAge);
            tr.append(tdaction);
            $("#table-body").append(tr);

        })
}

                    /*AJAX CALL FOR DTO*/

function  getPatietnsByDoctor(id){



    $.ajax({

        url: "/rest/doctor/patientsByDoctor/" + id,
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(DTO,status,xhr){
            console.log("success", DTO, "status : " , status, "xhr : ", xhr.status );
            patientsByDoctor = DTO;

            renderDtoTable(patientsByDoctor);

            $('#deleteSuccess').hide()
            $('#displayAllButton').hide();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });


}

function renderDtoTable(patientsByDoctor){

    $('#dtoContainer').show();
    $('#container').hide();
    $('#displayAllButton1').show();

    $.each(patientsByDoctor,
        function(id,dto){
            var tr = $("<tr></tr>");

            var doctorName = $("<td></td>");
            doctorName.append(dto.doctorName);

            var patientName = $("<td></td>");
            patientName.append(dto.patientName);

            var dijagnoza = $("<td></td>");
            dijagnoza.append(dto.dg);

            var age = $("<td></td>");
            age.append(dto.age);




            tr.append(doctorName);
            tr.append(patientName);
            tr.append(dijagnoza);
            tr.append(age);


            $("#dtoTable-body").append(tr);

        })


}

function renderDropdown(hospitals){

    $.each(hospitals, function (index,hospital){

        console.log(index,hospital);
        $("#hospitalDropdown").append('<option id="'+hospital.id+'" value="'+hospital.id+'">'+ hospital.name+'</option>')
    })
}

function filterByHospital() {

    var id = $("#hospitalDropdown").val();
    console.log(id);
    $.ajax({

        url: "/rest/doctor/filterByHospital/" + id,
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(doctorData,status,xhr){
            console.log("success", doctorData, "status : " , status, "xhr : ", xhr.status );

            $( "#table-body" ).children("tr").remove();
            renderTable(doctorData);

            $('#deleteSuccess').hide()
            $('#displayAllButton').hide();
            $('#searchContainer').hide();
            $('#displayAllButton').show();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });



}




function editDoctor (id) {

    window.location = '/doctor?action=edit&id=' + id;

}

function deleteDoctor(id){

    console.log('Delete Doctor' , id);

    $.ajax({
        url:"/rest/doctor/delete/" + id,
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

function addDoctor(){

    window.location = '/doctor?action=insert';
}

function filterValidation(){

    $("#filterValidation").hide();
}

function filterByDoctorsName(){
    var name = $("#searchText").val();

    if(name === null || name===undefined || name === ""){

        $("#filterValidation").show();

        return;
    }

    var filtered = []
    for (var i = 0; i < doctors.length ; i++){
        if(doctors[i].name == name){
            filtered.push(doctors[i]);
        }
    }
    console.log('filtered ', filtered);
    $( "#table-body" ).children("tr").remove();
    renderTable(filtered);
    $('#displayAllButton').show();

}


function displayAllDoctors() {
    $("#table-body").children("tr").remove();
    renderTable(doctors);
    $('#displayAllButton').hide();

}

function search(){

    var name = $("#doctorName").val();
    var surname = $("#doctorSurname").val();

    console.log('values are' , name, surname);
    var url = '/rest/doctor/search';
    var hasName = false;
    if(name){
        url+='?name=' + name;
        hasName =true;
    }

    if(surname) {

        if(hasName){
            url+='&surname='+surname;
        } else {
            url+='?surname=' + surname;
        }
    }
    console.log('url' , url);
    $.ajax({

        url: url,
        type: "GET",
        dataType:"json",
        contentType: "application/json",
        headers: {'token': 'token123'},
        success: function(doctorsAjax,status,xhr){
            console.log("success", doctorsAjax, "status : " , status, "xhr : ", xhr.status );
            //cities = citiesAjax;
            $( "#table-body" ).children("tr").remove();
            renderTable(doctorsAjax);


            $('#displayAllButton').show();


        },

        error: function(xhr,status,error){
            console.log("error", "status : " ,status, "errpor" , error, "xhr : " , xhr)
            $('#errorHappend').show();
        }
    });



}

