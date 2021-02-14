function init(){
    document.querySelector('#firstName')
        .setAttribute('value', localStorage.getItem('fName'));
    document.querySelector('#lastName')
        .setAttribute('value', localStorage.getItem('lName'));
    document.querySelector('#userName')
        .setAttribute('value', localStorage.getItem('usrName'));
    document.querySelector('#nationalCode')
        .setAttribute('value', localStorage.getItem('natCode'));
    document.querySelector('#roles')
        .setAttribute('value', localStorage.getItem('role'));
}

document.addEventListener('DOMContentLoaded', function (){
    init();})