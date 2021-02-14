$(document).ready(function () {
    let question_answer = document.getElementById("question-answer")
    for(i = 0; i < descriptiveQuestions.length; i++){
        $(question_answer).append(`
            <div>صورت سوال:</div>
            <div class="text-primary">${descriptiveQuestions[i]}</div>
            <div>جواب دانشجو: </div>
            <div class="text-info">${descriptiveAnswers[i]}</div>
            <div>
                نمره سوال در آزمون:
                ${eachQuestionMark[i]}
                <input id="${descriptiveIndex[i]}" class="form-control col-sm-3" placeholder="نمره">
            </div>
        `)
    }
    $(question_answer).append(`
        <div type="submit" onclick="finishCorrection()" class="btn btn-dark col-sm-4 text-warning">پایان تصحیح</div>
    `)
})
async function finishCorrection() {
    var map = {};
    for (i = 0; i < descriptiveQuestions.length; i++) {
        let mark = document.getElementById(descriptiveIndex[i]);
        if ($(mark).val() > eachQuestionMark[i] || $(mark).val() < 0) {
            let massage = document.getElementById("massage");
            massage.innerText = 'نمره خارج از بازه تعیین شده می باشد!';
            return;
        } else {
            massage.innerText = '';
            map[descriptiveIndex[i]] = $(mark).val();
        }
    }
    var data = JSON.stringify(map);
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/master/set-marks-of-descriptive-questions/".concat(examSheetId);
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
    await sleep(2000);
    window.location.href = "/master";
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}