package ir.pb.online_examination_system.services.impl;

import ir.pb.online_examination_system.domains.Exam;
import ir.pb.online_examination_system.domains.ExamSheet;
import ir.pb.online_examination_system.domains.Question;
import ir.pb.online_examination_system.domains.Student;
import ir.pb.online_examination_system.repositories.ExamSheetRepository;
import ir.pb.online_examination_system.services.ExamSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ExamSheetServiceImpl implements ExamSheetService {
    @Autowired
    private ExamSheetRepository repository;
    @Override
    public ExamSheet makeNewExamSheet(Student student, Exam exam, List<Question> questions) {
        ExamSheet examSheet = new ExamSheet();
        examSheet.setStudent(student);
        examSheet.setQuestions(questions);
        examSheet.setExam(exam);
        repository.save(examSheet);
        return examSheet;
    }

    @Override
    public ExamSheet findById(long examSheetId) {
        return repository.findById(examSheetId).get();
    }

    @Override
    public ExamSheet setStartAndFinishToExamSheet(ExamSheet examSheet, Date startingTime, int durationInMin) {
        examSheet.setExamStartingTime(startingTime);
        final int ONE_MINUTE_IN_MILLIS = 60000;
        Calendar date = Calendar.getInstance();
        date.setTime(startingTime);
        long t = date.getTimeInMillis();
        Date finishTime = new Date(t + (durationInMin * ONE_MINUTE_IN_MILLIS));
        examSheet.setExamFinishTime(finishTime);
        return repository.save(examSheet);
    }
}
