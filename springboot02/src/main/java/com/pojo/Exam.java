package com.pojo;


import com.dao.HomeworkDao;
import lombok.Data;

import java.util.Date;

@Data
public class Exam {
    private int Id;
    private String ExamName;
    private String ExamContent;
    private static int FirstExam = 0;
    private Date Deadline;
    private int number;//提交人数

    public Exam() {
        this.Id = FirstExam++; //无参
    }

    public void Getnumber(HomeworkDao homeworkDao)
    {
        number = homeworkDao.Getnumber(Id);
    }

    public int getNumber() {
        return number;
    }

    public Exam(int id, String examName, String examContent, Date deadline) {//生成当前时间考核
        Id = id;
        ExamName = examName;
        ExamContent = examContent;
        Deadline = deadline;
    }
    public Exam( String examName, String examContent, Date deadline) {//发布新考核
        Id = FirstExam++;
        ExamName = examName;
        ExamContent = examContent;
        Deadline = deadline;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setExamName(String examName) {
        ExamName = examName;
    }

    public void setExamContent(String examContent) {
        ExamContent = examContent;
    }

    public void setDeadline(Date deadline) {
        Deadline = deadline;
    }

    public int getId() {
        return Id;
    }

    public String getExamName() {
        return ExamName;
    }

    public String getExamContent() {
        return ExamContent;
    }

    public Date getDeadline() {
        return Deadline;
    }


    public boolean Overtime(Homework homework) //判断作业是否提交超时
    {
        if (homework.getTime().getTime() <= Deadline.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public void AddHomework_Exam(Homework homework) { //提交作业
        if (Overtime(homework)) {
            homework.setExamId(Id);
        }
    }
}

