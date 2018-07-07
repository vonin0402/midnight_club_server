package com.tag.midnight.club.server.controller;

import com.tag.midnight.club.server.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserInfoService userInfoService;

    @ResponseBody
    @PostMapping("/signUp")
    public String signUp(HttpServletRequest request) {
        return userInfoService.signUp(request);
    }

    @ResponseBody
    @PostMapping("/logIn")
    public String logIn(HttpServletRequest request) {
        return userInfoService.logIn(request);
    }

    @ResponseBody
    @PostMapping("/levelUp")
    public String levelUp(HttpServletRequest request) {
        return userInfoService.levelUp(request);
    }

    @DeleteMapping("/leave")
    public void leave(@RequestParam String id) {
        userInfoService.leave(id);
    }

    @ResponseBody
    @GetMapping("/health")
    public String test() {
        return "health check OK";
    }
}
