package com.village.controller;

import com.village.api.RemindControllerApi;
import com.village.constant.ResponseCode;
import com.village.entity.Remind;
import com.village.service.RemindService;
import com.village.utils.TokenUtil;
import com.village.utils.response.Response;
import com.village.utils.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remind")
public class RemindController implements RemindControllerApi {

    @Autowired
    private RemindService remindService;

    @Override
    @RequestMapping("/add")
    public Response<Boolean> addRemind(@RequestHeader("Authorization")String token, @RequestBody Remind remind) {
        remind.setUserId(TokenUtil.getClaimByName(token, "userId").asString());
        remindService.addRemind(remind);
        return ResponseBuilder.success(ResponseCode.SUCCESS,true);
    }

    @Override
    @RequestMapping("/all")
    public Response<List<Remind>> all(@RequestHeader("Authorization")String token) {
        String userId = TokenUtil.getClaimByName(token, "userId").asString();
        return ResponseBuilder.success(ResponseCode.SUCCESS,remindService.queryAllRemind(userId));
    }

    @Override
    @RequestMapping("/delete")
    public Response<Boolean> delete(@RequestHeader("Authorization")String token,String id) {
        return ResponseBuilder.success(ResponseCode.SUCCESS,remindService.delete(id));
    }

    @Override
    @RequestMapping("/finish")
    public Response<Boolean> finish(@RequestHeader("Authorization")String token,String id) {
        return ResponseBuilder.success(ResponseCode.SUCCESS,remindService.finish(id));
    }
}
