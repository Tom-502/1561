package com.controller;


import com.dao.AdminiDao;
import com.dao.ExamDao;
import com.dao.HomeworkDao;
import com.pojo.Admini;
import com.pojo.Exam;
import com.pojo.Homework;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class LoginController {

    @RequestMapping(value = "/admini/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session)
    {
        /*Admini admini = new Admini(username,password);
        AdminiDao adminiDao = new AdminiDao();
        adminiDao.AllAdminisOut();
        int tag=0;
        for (int i =0 ;i<adminiDao.GetAllAdminis().size() ;i++)
        {
            if (admini.equals(adminiDao.GetAllAdminis().get(i)))
            {
                session.setAttribute("loginUser",username);     //登录成功
                tag=1;
                return "aaa.html";      //页面跳转
            }
        }*/
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登录成功！
            session.setAttribute("loginUser",username );
            return "redirect:/main.html";
        }else {
            //登录失败！存放错误信息
            model.addAttribute("msg","用户名密码错误");
            return "index";
        }
    }
}

