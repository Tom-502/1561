package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class Homework {

    private int Id;//作业号
    private String Name;//提交人姓名
    private Date Time;//提交时间
    private int Target;//作业状态 0：审核中 1：通过 2：未通过
    private long StudyNumber;//学号
    private String FileName;//文件名
    private int ExamId =-1;//提交至哪个考核
    private String FilePath;//文件路径

    private static int FirstId = 0;


    //生成现在时间的最新作业
    public Homework(String name, int target, long study_number, String fileName, int examId, String filePath) {
        Id = FirstId ++;
        Name = name;
        Time = new Date() ;
        Target = target;
        StudyNumber = study_number;
        FileName = fileName;
        ExamId = examId;
        FilePath = filePath;
    }

    //有参构造
    public Homework(int id, String name, Date time, int target, long study_number, String fileName, int examId, String filePath) {
        Id = id;
        Name = name;
        Time = time;
        Target = target;
        StudyNumber = study_number;
        FileName = fileName;
        ExamId = examId;
        FilePath = filePath;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public Date getTime() {
        return Time;
    }

    public int getTarget() {
        return Target;
    }

    public long getStudy_number() {
        return StudyNumber;
    }

    public String getFileName() {
        return FileName;
    }

    public int getExamId() {
        return ExamId;
    }

    public Homework() {
        Id = FirstId++;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public void setTarget(int target) {
        Target = target;
    }

    public void setStudyNumber(long studyNumber) {
        StudyNumber = studyNumber;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public void setExamId(int examId) {
        ExamId = examId;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }
}
