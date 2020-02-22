package com.controller;

import com.dao.ExamDao;
import com.dao.FileDao;
import com.dao.HomeworkDao;
import com.pojo.Exam;
import com.pojo.Homework;
import com.service.ExamService;
import com.service.HomeworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
@Controller
public class StudentController {
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("exam_id") int exam_id,
                         @RequestParam("study_number") long study_number,
                         @RequestParam("name") String name,
                         Model model,
                         HttpServletRequest request) throws Exception
    {
        String fileName = FileDao.fileNameCreate(file, study_number, exam_id);    //获取文件名
        String filePath = FileDao.filePathCreate(request, "/upload");   //获取文件目录
        Homework homework = new Homework(name,0 ,study_number, filePath,exam_id, fileName);//默认为审核中
        HomeworkDao homeworkDao = new HomeworkDao();
        System.out.println(filePath);
        System.out.println(fileName);
        boolean a =homeworkDao.InsertOneHomework(homework,ExamService.getExamsById(exam_id));
        if (a==true)
        {
            model.addAttribute("msg1",name+"同学你已上传成功");
        }
        else {
            model.addAttribute("msg1",name+"考核时间已到，上传失败");
        }
        System.out.println(a);
        FileDao.uploadFile(file.getBytes(), filePath, fileName);
        return "index";      //重定向至首页
    }
    //下载文件
    @RequestMapping("/download/{examid}/{homeworkid}")
    public String download(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable("examid") int examid,@PathVariable("homeworkid") int homeworkid) throws Exception
    {
        Homework homework=HomeworkService.getHomework(examid,homeworkid);   //获取要下载的作业信息
        String filePath = homework.getFilePath();   //获取文件路径
        String fileName = homework.getFileName();    //获取文件名
        System.out.println(filePath);
        System.out.println(fileName);
        FileDao.downloadFile(response,fileName,filePath);    //下载文件
        return "redirect:/exam/{examid}";      //返回下载成功页面
    }
    //删除作业信息
    @RequestMapping("/homeworkDelete/{examid}/{homeworkid}")
    public String delete(@PathVariable("examid") int examid,@PathVariable("homeworkid") int homeworkid)
    {
        HomeworkService.deleteHomework(examid,homeworkid);
        return "redirect:/exam/{examid}";    //重定向至考核详情页
    }


}
