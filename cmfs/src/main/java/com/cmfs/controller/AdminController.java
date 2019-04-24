package com.cmfs.controller;

import com.cmfs.entity.ZAdmin;
import com.cmfs.service.AdminService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Resource
    private DefaultKaptcha defaultKaptcha;
    @Resource
    private AdminService adminService;
    @RequestMapping("getImg")
    public void getImg(HttpServletResponse response, HttpSession session) throws IOException {
        String text = defaultKaptcha.createText();
        session.setAttribute("code",text);
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"png",outputStream);
    }
    @RequestMapping("checkCode")
    public @ResponseBody Boolean checkCode(String code, HttpSession session){
        String code1 = (String) session.getAttribute("code");
        if(code.equalsIgnoreCase(code1)){
            return true;
        }else{
            return false;
        }
    }
    @RequestMapping("login")
    public @ResponseBody HashMap<String, Object> login(ZAdmin zAdmin,HttpSession session){
        HashMap<String, Object> map = new HashMap<>();
        try{
            ZAdmin admin = adminService.login(zAdmin);
            if(admin!=null){
                map.put("success",true);
                session.setAttribute("admin",admin);
            }
        }catch(Exception e){
            String message = e.getMessage();
            map.put("success",false);
            map.put("message",message);
        }
        return map;
    }
}
