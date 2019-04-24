package com.cmfs.controller;

import com.cmfs.entity.UserMap;
import com.cmfs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("findBySex")
    @ResponseBody
    public List<UserMap> findBySex(String sex){
        System.out.println("111111"+sex);
        List<UserMap> users = userService.findBySex(sex);
        System.out.println(users);
        return users;
    }
}
