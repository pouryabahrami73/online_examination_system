package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamSheet;
import ir.pb.online_examination_system.domains.Question;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.repositories.ExamSheetRepository;
import ir.pb.online_examination_system.services.ExamQuestionService;
import ir.pb.online_examination_system.services.ExamSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExamSheetServiceImpl implements ExamSheetService {
    @Autowired
    private ExamSheetRepository repository;
    @Autowired
    private ExamQuestionService examQuestionService;
    @Override
    public ExamSheet makeNewExamSheet(Student student, Exam exam, List<Question> questions) {
        ExamSheet examSheet = new ExamSheet();
        examSheet.setStudent(student);
        examSheet.setQuestions(questions);
        List<String> questionsInExamTime = new ArrayList<>();
        List<Float> eachQuestionMarkInExamTime = new ArrayList<>();
        questions.stream().forEach(question -> {
            questionsInExamTime.add(question.getProblem());
            eachQuestionMarkInExamTime.add(findQuestionMarkInExam(exam, question));});
        examSheet.setQuestionsInTimeOfExam(questionsInExamTime);
        examSheet.setExam(exam);
        Date date = new Date();
        examSheet.setExamStartingTime(date);
        final int ONE_MINUTE_IN_MILLIS = 60000;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long t = cal.getTimeInMillis();
        Date finishTime = new Date(t + (exam.getDurationInMin() * ONE_MINUTE_IN_MILLIS));
        examSheet.setExamFinishTime(finishTime);
        examSheet.setComplete(false);
        List<Float> eachQuestionMark = new ArrayList<>();
        List<String> defaultAnswers = new ArrayList<>();
        examSheet.getQuestions().stream().forEach(question -> {eachQuestionMark
                .add(examQuestionService.findExamQuestionByQuestionAndExam(question, exam).getMark());
        defaultAnswers.add("");
        });
        List<Float> eachQuestionMarkGotten = new ArrayList<>();
        questions.stream().forEach(mark -> eachQuestionMarkGotten.add(0F));
        examSheet.setEachQuestionMark(eachQuestionMark);
        examSheet.setStudentAnswer(defaultAnswers);
        examSheet.setStudentMarkFromEachQuestion(eachQuestionMarkGotten);
        repository.save(examSheet);
        return examSheet;
    }



    @Override
    public ExamSheet findById(long examSheetId) {
        return repository.findById(examSheetId).get();
    }

    @Override
    public void setQuestionAnswer(ExamSheet examSheet, Question question, String answer) {
        if (examSheet.getStudentAnswer().size() == examSheet.getQuestionsInTimeOfExam().size()) {
            int questionIndex = examSheet.getQuestions().indexOf(question);
            List<String> studentAnswers = examSheet.getStudentAnswer();
            studentAnswers.set(questionIndex, answer);
            examSheet.setStudentAnswer(studentAnswers);
        }
        repository.save(examSheet);
    }

    @Override
    public Integer getRemainedTimeToFinish(ExamSheet examSheet) {
        Date date = new Date();
        final int ONE_MINUTE_IN_MILLIS = 60000;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long nowMillis = cal.getTimeInMillis();
        cal.setTime(examSheet.getExamFinishTime());
        long examSheetFinishMillis = cal.getTimeInMillis();
        int remainedTime = (int) ((examSheetFinishMillis - nowMillis) / ONE_MINUTE_IN_MILLIS);
        if(remainedTime > 1) {
            return remainedTime;
        }else {
            examSheet.setComplete(true);
            repository.save(examSheet);
        }
        return 0;
    }

    @Override
    public ExamSheet findUncompletedExamSheetByStudent(Student student) {
        ExamSheet examSheet = repository.findByStudentAndComplete(student, false);
        return examSheet;
    }

    @Override
    public void completeExamSheet(Long id) {
        ExamSheet examSheet = repository.findById(id).get();
        examSheet.setComplete(true);
        repository.save(examSheet);
    }

    @Override
    public List<ExamSheet> findAllCompleteExamSheets(Exam exam) {
        return repository.findAllByExamAndComplete(exam, true)
                .stream().collect(Collectors.toList());
    }

    @Override
    public List<ExamSheet> findAllUncompletedExamSheets(Exam exam) {
        return repository.findAllByExamAndComplete(exam, false)
                .stream().collect(Collectors.toList());
    }

    @Override
    public Float findQuestionMarkInExam(Exam exam, Question question) {
        float mark = examQuestionService.findMarkOfQuestion(exam, question);
        return mark;
    }

    @Override
    public void save(ExamSheet examSheet) {
        repository.save(examSheet);
    }
}
