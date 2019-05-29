var doctors = [];

$(document).ready(function () {

    $('#deleteSuccess').hide();
    $('#errorHappend').hide();
    $('#filterValidation').hide();

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
            tdaction.append(editButton).append(deleteButton);
            tr.append(tdname);
            tr.append(tdSurName);
            tr.append(tdJobPosition);
            tr.append(tdAge);
            tr.append(tdaction);
            $("#table-body").append(tr);

        })
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
