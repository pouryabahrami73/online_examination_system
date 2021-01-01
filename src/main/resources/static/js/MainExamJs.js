$(document).ready(function () {
    let quest = document.getElementById("question");
    if (questions[0].type === 'MULTIPLE_CHOICE') {
        console.log('salam')
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
        console.log('salam')
    } else {
        console.log(questions[x].type);
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
                    <button type="button" id="exam-finisher-btn" name="button" class="btn btn-dark col-sm-2 text-warning"
                    onclick="">
                    پایان
                    </button>`);
    }
}

function previousQuestion(y) {
    y -= 1;
    htmlMaker(y)
}

function nextQuestion(y) {
    y += 1;
    htmlMaker(y);
}
