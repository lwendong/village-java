package com.village.controller;

import com.village.annotation.PassLoginVerify;
import com.village.annotation.RequireLogin;
import com.village.api.UserControllerApi;
import com.village.constant.ResponseCode;
import com.village.entity.User;
import com.village.entity.dto.UserDto;
import com.village.entity.vo.UserVo;
import com.village.service.UserService;
import com.village.utils.response.Response;
import com.village.utils.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerApi {

    @Autowired
    private UserService userService;

    @Override
    @PassLoginVerify
    @PostMapping("/login")
    public Response<UserVo> login( @RequestBody UserDto userDto) {
        return ResponseBuilder.success(ResponseCode.SUCCESS,userService.login(userDto));
    }

    @Override
    @PassLoginVerify
    @RequestMapping("/register")
    public Response<UserVo> register(@RequestBody UserDto userDto) {
        return ResponseBuilder.success(ResponseCode.SUCCESS,userService.register(userDto));
    }

    @Override
    @RequireLogin
    @RequestMapping("/updateUserInfo")
    public Response<UserVo> updateUserInfo(@RequestHeader("Authorization") String token, @RequestBody User user) {
        user.setToken(token);
        return ResponseBuilder.success(ResponseCode.SUCCESS,userService.updateUserInfo(user));
    }

    @Override
    @RequireLogin
    @RequestMapping("/getUserInfo")
    public Response<User> getUserInfo(@RequestHeader("Authorization") String token) {
        return ResponseBuilder.success(ResponseCode.SUCCESS,userService.getUserInfo(token));
    }
}
