function sendJSON(){
    const date = new Date();
    const now = date.getHours() + ":" + date.getMinutes();
    console.log(now);
    const toSend = {
        examStartingTime: now
    };
    /*var data = JSON.stringify(toSend);
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/student/exam-start-time-setter";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            result.innerHTML = this.responseText;

        }
    };
    xhr.send(data);*/
}
