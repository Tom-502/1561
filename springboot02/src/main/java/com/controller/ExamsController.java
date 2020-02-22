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
    @RequestMapping(value ="/ExamList")
    public String examsList(Model model)
    {
        ArrayList<Exam> exams=ExamService.getExams();
        model.addAttribute("exams",exams);
        return "list";
    }


    @GetMapping("/ExamAdd")
    public  String examsAdd()
    {
        return "add";
    }


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
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newDate= format.parse(date0+":00");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            Exam exam=new Exam(examName,content,newDate);
            ExamService.insertExams(exam);
            return "list";
        }
    }


    @GetMapping("/exam/{id}")
    public String assess(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("exam",ExamService.getExamsById(id));
        model.addAttribute("homeworks", HomeworkService.getHomeworks(id));
        return "assess";
    }


    @GetMapping("/examEdit/{id}")
    public String assessEdit(@PathVariable("id") int id,Model model)
    {
        model.addAttribute("exam",ExamService.getExamsById(id));
        return "edit";
    }
    @PostMapping("/examEdit")
    public  String edit(@RequestParam("id") int id,
                        @RequestParam("examName") String examName,
                        @RequestParam("content") String content,
                        @RequestParam("deadline") Date deadline)
    {
        Exam exam=new Exam(id, examName, content, deadline);
        ExamService.updateExams(exam);
        return "edit";
    }

    @GetMapping("/examDelete/{id}")
    String delete(@PathVariable("id") int id)
    {
        ExamService.deleteExams(id);
        return "teacher";
    }
}
