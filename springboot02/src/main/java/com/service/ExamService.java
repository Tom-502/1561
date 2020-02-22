package com.service;

import com.dao.ExamDao;
import com.dao.HomeworkDao;
import com.pojo.Exam;
import com.pojo.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
public class ExamService {//实现一些功能

    @Autowired
    public static ArrayList<Exam> getExams()
    {
        ExamDao examDao=new ExamDao();
        return examDao.AllExamsOut();
    }


    public static boolean insertExams(Exam exam)
    {
        ExamDao examDao=new ExamDao();
        examDao.createExamTable();
        return examDao.AddOneExam(exam);
    }


    public static Exam getExamsById(int exam_id)
    {
        ExamDao examDao=new ExamDao();
        examDao.AllExamsOut();
        return examDao.GetOneExam(exam_id);
    }

    public static void deleteExams(int exam_id)
    {
        ExamDao examDao=new ExamDao();
        examDao.DeleteOneExam(exam_id);

    }
    public static boolean updateExams(Exam exam)
    {
        ExamDao examDao=new ExamDao();
        return examDao.UpdateOneExam(exam);
    }
}
