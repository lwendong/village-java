package com.village.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.village.constant.OperationCode;
import com.village.entity.User;
import com.village.entity.dto.UserDto;
import com.village.entity.vo.UserVo;
import com.village.mapper.UserMapper;
import com.village.service.SensitiveWordService;
import com.village.service.UserService;
import com.village.utils.TokenUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SensitiveWordService sensitiveWordService;

    @Override
    public UserVo login(UserDto userDto) {
        UserVo userVo = new UserVo();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDto.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(user)){
            userVo.setMessage("用户不存在");
        }else{
            if ((user.getPassword().equals(userDto.getPassword()))){
                userVo.setFlag(true);
                userVo.setToken(TokenUtil.createToken(user));
            }else{
                userVo.setMessage("密码不正确，登录失败");
            }
        }
        return userVo;
    }

    @Override
    public UserVo register(UserDto userDto) {
        userDto = sensitiveWordService.check(userDto, userDto.getToken());
        UserVo userVo = new UserVo();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDto.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if(ObjectUtils.isNotEmpty(user)){
            userVo.setMessage("此账号已存在,请登录");
        }else{
            user = new User();
            BeanUtils.copyProperties(userDto, user);
            if(userMapper.insert(user) > OperationCode.FAIL.getCode()){
                userVo.setFlag(true);
                userVo.setToken(TokenUtil.createToken(user));
            }

        }
        return userVo;
    }

    @Override
    public User getUserById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public UserVo updateUserInfo(User user) {
        UserVo userVo = new UserVo();
        String userId = TokenUtil.getClaimByName(user.getToken(), "userId").asString();
        user.setId(userId);
        if (userMapper.updateById(user) > OperationCode.FAIL.getCode()){
            userVo.setFlag(true);
            userVo.setMessage("修改信息成功");
        }
        return userVo;
    }

    @Override
    public User getUserInfo(String token) {
        String userId = TokenUtil.getClaimByName(token, "userId").asString();
        User user = userMapper.selectById(userId);
        return user;
    }

    @Override
    public List<User> getUserByIds(List<String> userIds) {
        if(!CollectionUtils.isEmpty(userIds)){
            return userMapper.selectBatchIds(userIds);
        }
        return null;
    }
}
