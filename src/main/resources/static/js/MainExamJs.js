$(document).ready(function () {
    let quest = document.getElementById("question");
    if (questions[0].type === 'MULTIPLE_CHOICE') {
        $(quest).append(`
            <div id="question-problem">${questions[0].problem}
                `)
        for (i = 0; i < questions[0].alternatives.length; i++){
            $(quest).append(`
                <div id="answer-div" class="form-check">
                    <input class="form-check-input" type="radio" name="answer" id="alternative + ${i}"
                        value="${i}" checked>
                            <label class="form-check-label" for="alternative + ${i}">
                                ${i + 1}. ${questions[0].alternatives[i]}
                            </label>
                            <br>
                    </div>
            `)
        }
    $(quest).append(`
        </div>
    `)
    } else {
        $(quest).append(`
                    <div id="question-problem">${questions[0].problem}
                    <div id="answer-div">
                        <input name="answer" class="form-control" placeholder="جواب سوال">
                    </div>
                    </div>`);
    }
    $(quest).append(`
                    <button type="button" name="button" id="next-question-btn"
                    class="btn btn-dark col-sm-2 text-warning" onclick="nextQuestion(0)">
                    سوال بعد
                    </button>`);
})
let z = 0;
function htmlMaker(x){
    z = x;
    let quest = document.getElementById("question");
    while(quest.hasChildNodes()){
        quest.removeChild(quest.firstChild);
    }
    if (questions[x].type == "MULTIPLE_CHOICES") {
        $(quest).append(`
            <div id="question-problem">${questions[x].problem}
                `)
        for (i = 0; i < questions[x].alternatives.length; i++){
            $(quest).append(`
                <div id="answer-div" class="form-check">
                    <input class="form-check-input" type="radio" name="answer" id="alternative + ${i}"
                        value="${i}" checked>
                            <label class="form-check-label" for="alternative + ${i}">
                                ${i + 1}. ${questions[x].alternatives[i]}
                            </label>
                            <br>
                    </div>
            `)
        }
    $(quest).append(`
        </div>
    `)
    } else {
        $(quest).append(`
                    <div id="question-problem">${questions[x].problem}
                    <div id="answer-div">
                        <input name="answer" class="form-control" placeholder="جواب سوال">
                    </div>
                    </div>`);
    }
    if (x >= 0 & x < questions.length - 1) {
        $(quest).append(`
                    <button type="button" name="button" id="next-question-btn"
                    class="btn btn-dark col-sm-2 text-warning" onclick="nextQuestion(z)">
                    سوال بعد
                    </button>`);
    }
    if (x > 0 & x <= questions.length - 1) {
        $(quest).append(`
                    <button type="button" id="previous-question-btn" name="button"
                    class="btn btn-dark col-sm-2 text-warning" onclick="previousQuestion(z)">
                    سوال قبل
                    </button>`);
    }
    if (x === questions.length - 1) {
        $(quest).append(`
                    <button type="button" id="exam-finisher-btn" name="button"
                    class="btn btn-dark col-sm-2 text-warning"
                    onclick="finisherButton(z)">
                    پایان
                    </button>`);
    }
}

function previousQuestion(y) {
    y -= 1;
    htmlMaker(y)
}

function nextQuestion(y) {
    sendJSON(y);
    y += 1;
    htmlMaker(y);
}

function finisherButton(y){
    sendJSON(y);
    // window.location.href = "/student/finish-exam/".concat(examSheetId);
}

function sendJSON(n){
    const toSend = {
        questionId: questions[n].id,
        answer: $('input[name ="answer"]').val(),
        examSheetId: examSheetId
    };
    var data = JSON.stringify(toSend);
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/student/answer-questions";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
}
