const startingMinutes = examTime;
console.log(startingMinutes);
let time = startingMinutes * 60;
const countdown = document.getElementById("countdown");
var intervals = setInterval(updateCountDown, 1000)
function updateCountDown() {
    const minutes = Math.floor(time / 60);
    let seconds = time % 60;
    countdown.innerHTML = `${seconds} : ${minutes}`;
    --time;
    if(time == 0){
        clearInterval(intervals);
        console.log('in time = 0 in timerjs')
        completeExam();
        window.location.href = "/student/finish-exam/".concat(examSheetId);
    }
}
/*if(time == 0){
    console.log('in time = 0 in timerjs')
    completeExam();
    window.location.href = "/student/finish-exam/".concat(examSheetId);
}*/

function completeExam(){
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/student/finish-exam/".concat(examSheetId);
    xhr.open("GET", url, true);
    xhr.send();
}