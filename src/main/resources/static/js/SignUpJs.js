
/*$(document).ready(function () {
    $("#refresh").click(function () {
        $.ajax({
            url: "http://localhost:8080/api/drug/drug-store",
            type: "GET",
            data_type: "json",
            success: function (data) {
                let table = document.getElementById("table");
                while (table.hasChildNodes()) {
                    table.removeChild(table.firstChild);
                }
                for (j = 0; j < data.length; j++) {
                    $("#table").append(`
                <tr>
                    <td>${data[j].name}</td>
                    <td>${data[j].code}</td>
                    <td>${data[j].price}</td>
                    <td>${data[j].information}</td>
                    <td><button type="submit" class="btn btn-primary"
                    onclick="editFunc(${data[j].id})">ویرایش</button></td>
                    <td><button type="button" class="btn btn-danger" data-toggle="modal"
                    data-target="#exampleModalCenter" onclick="deleteFunc(${data[j].id})">حذف</button>
                </tr>`)
                };
            },
            error: function (data) {
                console.log("error");
            }
        })
    })
})*/

/*function sendJSON(){
    const toSend = {
        userName: $('input[name ="userName"]').val(),
        nationalCode: $('input[name ="nationalCode"]').val(),
    };
    var data = JSON.stringify(toSend);
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/user/check";
    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
}*/

/*$(document).ready(function () {
    let submitBtn = document.getElementById('submit');
    submitBtn.disabled = true;
    $("#check").click(function () {
        $.ajax({
            url: "http://localhost:8080/user/check",
            type: "GET",
            data: user,
            data_type: "json",
            success: function (data) {
                console.log(data);
                if (data.message != null) {
                    let message = document.getElementById("message");
                    message.innerText = $(data.message);
                } else {
                    submitBtn.disabled = false;
                };
            },
            error: function (data) {
                console.log("error");
            }
        })
    })
})*/

localStorage.setItem('fName', $('#firstName').val());
localStorage.setItem('lName', $('#lastName').val());
localStorage.setItem('natCode', $('#nationalCode').val());
localStorage.setItem('usrName', $('#userName').val());
localStorage.setItem('role', $('#roles').val());