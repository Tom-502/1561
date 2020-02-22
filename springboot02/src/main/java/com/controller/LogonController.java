package com.controller;

import com.dao.AdminiDao;
import com.pojo.Admini;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LogonController {

    @RequestMapping(value = "/admini/logon")
    public String logon(@RequestParam("new_username") String new_username,
                        @RequestParam("new_password") String new_password,
                        Model model,
                        HttpSession session)
    {
        Admini admini = new Admini(new_username,new_password);
        AdminiDao adminiDao = new AdminiDao();
        adminiDao.createAdminiTable();
        adminiDao.AddOneAdmini(admini);
        session.setAttribute("loginUser",new_username );
        model.addAttribute("msg2","您已成功注册");
        return "index";
    }
}
