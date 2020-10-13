package com.bjfu.it.mipep.controller;



import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjfu.it.mipep.bean.User;
import com.bjfu.it.mipep.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller

public class LoginController {
    @Autowired
    private LoginService loginService;
    @RequestMapping("/getlogin2")
    @ResponseBody
    public String getlogin2(@RequestParam Map<String,String> reqMap, HttpSession session) throws JsonProcessingException {
        String username=reqMap.get("username");
        String password=reqMap.get("password");

        int ok=0;

        int usertype=loginService.loginService(username,password);
        if(usertype==2)
        {
            ok=2;
            User loginUser=loginService.getLoginUser(username);
            session.setAttribute("sessionUser", loginUser);

        }
        else if(usertype==1)
        {
            ok=1;
            User loginUser=loginService.getLoginUser(username);
            session.setAttribute("sessionUser", loginUser);

        }


        //忽略之下的
        List<String> a=new ArrayList<>();
        a.add("aa");
        String jsonString="{\"code\":0,\"message\":\"ok\", \"count\":"+ok+",\"data\":";
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(a);
        jsonString+=json;
        jsonString+="}";
        //System.out.println(jsonString);
        return jsonString;

    }
    @RequestMapping("/getlogin")
    public String getlogin(User user, HttpSession session, HttpServletResponse response) throws IOException {

        String rawpassword=user.getPassword();
        String processedPassword=PasswordProcess.Encrypt(rawpassword,"SHA-256");
        int usertype=loginService.loginService(user.getUserid(),processedPassword);
        if(usertype==2)
        {
            User loginUser=loginService.getLoginUser(user.getUserid());
            session.setAttribute("sessionUser", loginUser);
            return "adminIndex";
        }
        else if(usertype==1)
        {
            User loginUser=loginService.getLoginUser(user.getUserid());
            session.setAttribute("sessionUser", loginUser);
            return "index";
        }
        else {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("<script>alert(\"用户名或密码错误\")</script>");
            return "login";
        }
    }

    @GetMapping("login")
    public String login()
    {
        return "login";
    }
    @GetMapping("/newlogin")
    public String newlogin()
    {
        return "newlogin";
    }



    @RequestMapping("/getregister")
    public String getregister(String username,String pwd1,String pwd2,String phone, HttpServletResponse response) throws IOException {
        User user=new User();


        user.setUserid(username);
        if(!pwd1.equals(pwd2))
        {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("<script>alert(\"两次密码输入不一致！\")</script>");
            return "login";
        }
        String rawpassword=pwd1;
        String processedPassword=PasswordProcess.Encrypt(rawpassword,"SHA-256");
        user.setPassword(processedPassword);
        user.setPhone(phone);
        user.setUsertype("general");
        user.setName("待填写");
        user.setSex("待填写");
        int ok=loginService.creatAccount(user);
        if(ok==1) {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("<script>alert(\"注册成功！请登录！\")</script>");
            return "login";
        }else{
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("<script>alert(\"用户名已存在！请重新注册！\")</script>");
            return "login";
        }
    }
    @GetMapping("userinfo")
    public String userinfo(HttpSession session, Model model)
    {
        User user=(User)session.getAttribute("sessionUser");
        model.addAttribute("user",user);
        return "userinfo";
    }
    @GetMapping("changepassword")
    public String changepassword()
    {
        return "changepassword";
    }


}