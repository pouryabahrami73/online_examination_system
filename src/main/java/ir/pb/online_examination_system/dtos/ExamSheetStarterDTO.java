package ir.pb.online_examination_system.dtos;

public class ExamSheetStarterDTO {
    private long examSheetId;
    private String examStartingTime;
    private long examId;
    public ExamSheetStarterDTO() {
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public long getExamSheetId() {
        return examSheetId;
    }

    public void setExamSheetId(long examSheetId) {
        this.examSheetId = examSheetId;
    }

    public String getExamStartingTime() {
        return examStartingTime;
    }

    public void setExamStartingTime(String examStartingTime) {
        this.examStartingTime = examStartingTime;
    }
}
