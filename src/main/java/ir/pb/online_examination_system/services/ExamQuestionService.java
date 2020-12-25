package ir.pb.online_examination_system.services;

import ir.pb.online_examination_system.domains.Course;
import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamQuestion;
import ir.pb.online_examination_system.domains.Question;

import java.util.List;

public interface ExamQuestionService {

    List<ExamQuestion> findAllExamQuestionsOfCourse(Course course);

    ExamQuestion makeExamQuestion(Course course, Exam exam, Question question, Float mark);

    ExamQuestion save(ExamQuestion examQuestion);

    List<ExamQuestion> findAllQuestionsOfExam(Exam exam);
}
