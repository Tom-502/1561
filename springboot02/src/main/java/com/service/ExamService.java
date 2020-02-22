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

    //从数据库中获取指定ID的考核信息
    public static Exam getExamsById(int exam_id)
    {
        ExamDao examDao=new ExamDao();
        examDao.AllExamsOut();
        return examDao.GetOneExam(exam_id);
    }

    //向数据库中插入一条考核信息
    public static boolean insertExams(Exam exam)
    {
        ExamDao examDao=new ExamDao();
        examDao.createExamTable();
        return examDao.AddOneExam(exam);
    }

    //修改数据库中的指定信息
    public static boolean updateExams(Exam exam)
    {
        ExamDao examDao=new ExamDao();
        return examDao.UpdateOneExam(exam);    //更新信息
    }

    //根据ID删除指定信息
    public static void deleteExams(int exam_id)
    {
        ExamDao examDao=new ExamDao();
        examDao.DeleteOneExam(exam_id);         //删除考核信息
    }
}
