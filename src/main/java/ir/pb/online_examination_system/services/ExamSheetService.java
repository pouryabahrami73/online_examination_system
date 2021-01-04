package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamSheet;
import ir.pb.online_examination_system.domains.Question;
import ir.pb.online_examination_system.domains.Student;

import java.util.Date;
import java.util.List;

public interface ExamSheetService {
    ExamSheet makeNewExamSheet(Student student, Exam exam, List<Question> questions);

    ExamSheet findById(long examSheetId);

    void setQuestionAnswer(ExamSheet examSheet, Question question, String answer);
}