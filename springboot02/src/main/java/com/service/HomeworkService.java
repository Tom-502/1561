package com.service;

import com.dao.HomeworkDao;
import com.pojo.Exam;
import com.pojo.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class HomeworkService { //在StudentController中实现对作业的操作
    @Autowired
    public static ArrayList<Homework> getHomeworks(int exam_id) //取出所有作业信息通过考核号
    {
        HomeworkDao homeworkDao=new HomeworkDao();
        homeworkDao.AllHomeworkOut(exam_id);
        return homeworkDao.getHomeworks1();
    }




    public static void deleteHomework(int exam_id,int homework_id)
    {
        HomeworkDao homeworkDao=new HomeworkDao();
        homeworkDao.DeleteOneHomework(exam_id,homework_id);
    }


    public static Homework getHomework(int exam_id,int homework_id)
    {
        HomeworkDao homeworkDao=new HomeworkDao();
        homeworkDao.AllHomeworkOut(exam_id);
        return homeworkDao.GetOneHomewor(homework_id);
    }

    public static void insertHomework(Homework homework, Exam exam)
    {
        HomeworkDao homeworkDao=new HomeworkDao();
        homeworkDao.InsertOneHomework(homework,exam);
    }
}
