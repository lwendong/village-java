package com.village.controller;

import com.village.entity.Emotion;
import com.village.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private SensitiveWordService sensitiveService;

    @RequestMapping("/test")
    public String test(Emotion emotion, @RequestHeader("Authorization") String token) throws IllegalAccessException {
        sensitiveService.check(emotion,token);
        return "success";
    }
}
