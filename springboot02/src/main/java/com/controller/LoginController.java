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
        Admini admini = new Admini(username,password);
        AdminiDao adminiDao = new AdminiDao();
        if (!adminiDao.Cannotlogin(admini))
        {
            model.addAttribute("msg","用户名密码错误");            //登录失败！存放错误信息
            return "index";
        }
        else {
            session.setAttribute("loginUser",username );//登录成功！
            return "redirect:/main.html";
        }
    }
}

