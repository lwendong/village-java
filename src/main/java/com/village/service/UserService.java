package com.village.service;

import com.village.entity.User;
import com.village.entity.dto.UserDto;
import com.village.entity.vo.UserVo;

import java.util.List;

public interface UserService {

    public UserVo login(UserDto userDto);

    public UserVo register(UserDto userDto);

    public User getUserById(String userId);

    public UserVo updateUserInfo(User user);

    public User getUserInfo(String token);

    public List<User> getUserByIds(List<String> userIds);
}
