package com.village.controller;

import com.village.annotation.RequireLogin;
import com.village.api.EmotionControllerApi;
import com.village.constant.ResponseCode;
import com.village.entity.dto.EmotionDto;
import com.village.entity.vo.EmotionVo;
import com.village.service.EmotionService;
import com.village.utils.response.Response;
import com.village.utils.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emo")
public class EmotionController implements EmotionControllerApi {
    @Autowired
    private EmotionService emotionService;


    @Override
    @RequireLogin
    @PostMapping("/add")
    public Response<EmotionVo> addEmotion(@RequestHeader("Authorization") String token,@RequestBody EmotionDto emotionDto) {
        emotionDto.setToken(token);
        return ResponseBuilder.success(ResponseCode.SUCCESS,emotionService.addEmotion(emotionDto));
    }

    @Override
    @RequireLogin
    @GetMapping("/allByPage")
    public Response<List<EmotionVo>> queryAllEmotionByPage(@RequestHeader("Authorization") String token,EmotionDto emotionDto) {
        emotionDto.setToken(token);
        return ResponseBuilder.success(ResponseCode.SUCCESS,emotionService.queryAllEmotionByPage(emotionDto));
    }

    @Override
    @RequireLogin
    @GetMapping("/delEmotionById")
    public Response<Boolean> delEmotionById(String emoId) {
        return ResponseBuilder.success(ResponseCode.SUCCESS,emotionService.delEmotionById(emoId));
    }

    @Override
    @RequireLogin
    @GetMapping("/getEmotionCount")
    public Response<Long> getEmotionCount() {
        return ResponseBuilder.success(ResponseCode.SUCCESS,emotionService.getEmotionCount());
    }
}
