package com.club.midnight.controller;

import com.club.midnight.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/signUp")
    public void signUp(@RequestParam String id, @RequestParam String password) {
        userInfoService.signUp(id, password);
    }

    @ResponseBody
    @PostMapping("/logIn")
    public String logIn(@RequestParam String id, @RequestParam String password) {
        return userInfoService.logIn(id, password);
    }

    @ResponseBody
    @PostMapping("/levelUp")
    public String levelUp(@RequestParam String id, @RequestParam String target, @RequestParam String userType) {
        return userInfoService.levelUp(id, target, userType);
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
