package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamSheet;
import ir.pb.online_examination_system.domains.Question;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.services.ExamSheetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSheetServiceImpl implements ExamSheetService {
    @Override
    public ExamSheet makeNewExamSheet(Student student, Exam exam, List<Question> questions) {
        ExamSheet examSheet = new ExamSheet();
        examSheet.setStudent(student);
        examSheet.setQuestions(questions);
        examSheet.setExam(exam);
        return examSheet;
    }
}
