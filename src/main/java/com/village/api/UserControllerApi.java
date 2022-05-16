package com.village.api;

import com.village.entity.User;
import com.village.entity.dto.UserDto;
import com.village.entity.vo.UserVo;
import com.village.utils.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "用户")
public interface UserControllerApi {

    @Operation(summary = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="login",value="登录",dataTypeClass = UserDto.class,
                    paramType = "query",required = true)
    })
    public Response<UserVo> login(UserDto userDto);

    @Operation(summary = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name="register",value="注册",dataTypeClass = UserDto.class,
                    paramType = "query",required = true)
    })
    public Response<UserVo> register(UserDto userDto);

    @Operation(summary = "更改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="updateUserInfo",value="更改用户信息",dataTypeClass = User.class,
                    paramType = "query",required = true)
    })
    public Response<UserVo> updateUserInfo(String token,User user);

    @Operation(summary = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="getUserInfo",value="获取用户信息",dataTypeClass = String.class,
                    paramType = "query",required = true)
    })
    public Response<User> getUserInfo(String token);

}
