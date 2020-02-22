package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogoutController {

    @ResponseBody
    @RequestMapping(value = "/logout")
    public String Logout ()
    {
        return  "您已退出登录";
    }
}
