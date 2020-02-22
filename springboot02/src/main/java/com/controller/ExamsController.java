package com.controller;

import com.pojo.Exam;
import com.service.ExamService;
import com.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
@Controller
public class ExamsController {
    //考核列表显示
    @RequestMapping(value ="/ExamList")
    public String examsList(Model model)
    {
        ArrayList<Exam> exams=ExamService.getExams();
        model.addAttribute("exams",exams);        //将考核信息列表传给前台
        return "list";        //返回考核列表展示页面
    }

    //进入考核发布页面
    @GetMapping("/ExamAdd")
    public  String examsAdd()
    {
        return "add";      //返回考核发布页面
    }

    //发布考核信息
    @PostMapping("/ExamAdd")
    public  String add(@RequestParam("examName") String examName,
                       @RequestParam("content") String content,
                       @RequestParam("time") String time,
                       @RequestParam("date") String date, Model model)
    {

        String date0 = date+" "+time;
        System.out.println(date0);
        Date newDate=new Date();
        try
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     //设置时间格式为 "yyyy-MM-dd HH:mm:ss"
            newDate= format.parse(date0+":00");     //生成时间
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            Exam exam=new Exam(examName,content,newDate);
            ExamService.insertExams(exam);
            return "list";      //重定向至考核列表展示
        }
    }

    //进入考核详情页
    @GetMapping("/exam/{id}")
    public String assess(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("exam",ExamService.getExamsById(id));   //将该id的考核信息传给前台
        model.addAttribute("homeworks", HomeworkService.getHomeworks(id));      //将该考核下的所有作业信息传给前端
        return "assess";        //返回考核详情页
    }

    //进入信息修改页面
    @GetMapping("/examEdit/{id}")
    public String assessEdit(@PathVariable("id") int id,Model model)
    {
        model.addAttribute("exam",ExamService.getExamsById(id));   //将该条考核信息传给前台
        return "edit";          //返回考核信息修改页面
    }

    //修改考核信息
    @PostMapping("/examEdit")
    public  String edit(@RequestParam("id") int id,@RequestParam("examName") String examName,
                @RequestParam("content") String content,@RequestParam("deadline") Date deadline)
    {
        Exam exam=new Exam(id, examName, content, deadline);       //修改后的考核信息
        ExamService.updateExams(exam);       //更新信息
        return "edit";    //重定向至考核详情页
    }

    //删除考核
    @GetMapping("/examDelete/{id}")
    String delete(@PathVariable("id") int id)
    {
        ExamService.deleteExams(id);        //删除考核信息
        return "teacher";      //重定向至考核列表展示
    }
}
