function sendJSON(){
    const toSend = {
        name: $('#name').val(),
        code: $('#code').val(),
        price: $('#price').val(),
        information: $('#information').val()
    };
    var data = JSON.stringify(toSend);
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/api/drug/add";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            result.innerHTML = this.responseText;

        }
    };
    xhr.send(data);
}

function init(){
    document.querySelector('#name')
        .setAttribute('value', localStorage.getItem('drugNameToBeEdited'));
    document.querySelector('#code')
        .setAttribute('value', localStorage.getItem('drugCodeToBeEdited'));
    document.querySelector('#price')
        .setAttribute('value', localStorage.getItem('drugPriceToBeEdited'));
    document.querySelector('#information')
        .setAttribute('value', localStorage.getItem('drugInformationToBeEdited'));
}

document.addEventListener('DOMContentLoaded', function (){
    init();})

