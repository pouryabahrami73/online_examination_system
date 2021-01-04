$(document).ready(function () {
  if (uncompletedExamSheetId != null & examTime > 1){
      let ableExamsTable = document.getElementById("ableExams");
      ableExamsTable.remove();
      let uncompletedExamDiv = document.getElementById("uncompletedExam");
      $(uncompletedExamDiv).append(`<table id="uncompleted" class="table table-striped table-dark text-warning">
                <thead>
                <tr>
                    <th scope="col">شناسه</th>
                    <th scope="col">تاریخ آزمون</th>
                    <th scope="col">زمان آزمون</th>
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
                        ${examFinishTime}
                    </td>
                    <td>
                        <button type="submit"
                           class="btn btn-dark col-sm-4 text-warning"
                           onclick="continueExam(examSheetId)">ادامه آزمون</button>
                    </td>
                </tr>
                </tbody>
            </table>`);
  }
})
function continueExam(id){
        let xhr = new XMLHttpRequest();
        let url = "http://localhost:8080/student/continue-exam/".concat(id);
        xhr.open("GET", url, true);
        /*xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                result.innerHTML = this.responseText;

            }
        };*/
        xhr.send();
}