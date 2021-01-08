package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamQuestion;
import ir.pb.online_examination_system.domains.Question;

import java.util.List;

public interface ExamQuestionService {

    List<ExamQuestion> findAllExamQuestionsOfCourse(String courseName);

    ExamQuestion makeExamQuestion(String courseName, Exam exam, Question question, Float mark);

    ExamQuestion save(ExamQuestion examQuestion);

    List<ExamQuestion> findAllQuestionsOfExam(Exam exam);

    void deleteByQuestionAndExam(Question question, Exam exam);

    ExamQuestion findExamQuestionByQuestionAndExam(Question question, Exam exam);

    void delete(ExamQuestion examQuestion);

    Float findMarkOfQuestion(Exam exam, Question question);
}
