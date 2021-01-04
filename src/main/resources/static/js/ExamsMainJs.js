$(document).ready(function () {
  if (uncompletedExamSheetId != null && examTime > 1){
      let ableExamsTable = document.getElementById("ableExams");
      ableExamsTable.remove();
      let uncompletedExamDiv = document.getElementById("uncompletedExam");
      $(uncompletedExamDiv).append(`<table id="uncompleted" class="table table-striped table-dark text-warning">
                <thead>
                <tr>
                    <th scope="col">شناسه</th>
                    <th scope="col">تاریخ آزمون</th>
                    <th scope="col">زمان آزمون باقی مانده</th>
                    <th scope="col">عملیات</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">
                        ${uncompletedExamSheetId}
                    </th>
                    <td>
                        ${examStartingTime}
                    </td>
                    <td>
                        ${examTime}
                    </td>
                    <td>
                        <a type="submit"
                           class="btn btn-dark col-sm-4 text-warning"
                           href="/student/continue-exam/${uncompletedExamSheetId}">ادامه آزمون</a>
                    </td>
                </tr>
                </tbody>
            </table>`);
  }else if (examTime <= 1){
      completeExam();
  }
})
function completeExam(){
        let xhr = new XMLHttpRequest();
        let url = "http://localhost:8080/student/continue-exam/".concat(uncompletedExamSheetId);
        xhr.open("GET", url, true);
        xhr.send();
}
