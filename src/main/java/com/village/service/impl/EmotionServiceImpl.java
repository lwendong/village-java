package com.village.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.village.constant.OperationCode;
import com.village.entity.Emotion;
import com.village.entity.User;
import com.village.entity.dto.EmotionDto;
import com.village.entity.vo.EmotionVo;
import com.village.mapper.EmotionMapper;
import com.village.service.EmotionService;
import com.village.service.SensitiveWordService;
import com.village.service.UserService;
import com.village.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmotionServiceImpl implements EmotionService {

    @Autowired
    private EmotionMapper emotionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private SensitiveWordService sensitiveWordService;


    @Override
    public EmotionVo addEmotion(EmotionDto emotionDto){
        Emotion emotion = new Emotion();
        EmotionVo emotionVo = new EmotionVo();
        BeanUtils.copyProperties(sensitiveWordService.check(emotionDto,emotionDto.getToken()), emotion);
        emotion.setUserId(TokenUtil.getClaimByName(emotionDto.getToken(), "userId").asString());
        emotionMapper.insert(emotion);
        BeanUtils.copyProperties(emotion, emotionVo);
        return emotionVo;
    }

    @Override
    public List<EmotionVo> queryAllEmotionByPage(EmotionDto emotionDto) {
        String loginUserId = TokenUtil.getClaimByName(emotionDto.getToken(), "userId").asString();
        IPage<Emotion> iPage = new Page<>(emotionDto.getPageNo(),emotionDto.getPageSize());
        QueryWrapper<Emotion> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        List<Emotion> emotionList = emotionMapper.selectPage(iPage, queryWrapper).getRecords();
        if(CollectionUtils.isEmpty(emotionList)){
            return null;
        }
        List<String> userIds = emotionList.stream().map(Emotion::getUserId).collect(Collectors.toList());
        List<User> userByIds = userService.getUserByIds(userIds);
        List<EmotionVo> emotionVoList = JSONArray.parseArray(JSON.toJSONString(emotionList), EmotionVo.class);
        emotionVoList.forEach((emotionVo -> {
            for(User user : userByIds){
                if(user.getId().equals(emotionVo.getUserId())){
                    emotionVo.setUserAvatarUrl(user.getAvatarUrl());
                    String nick = user.getNick();
                    if(StringUtils.isNotBlank(nick)){
                        emotionVo.setUserNick(nick);
                    }else{
                        emotionVo.setUserNick(user.getUsername());
                    }
                    break;
                }
            }

            if(loginUserId.equals(emotionVo.getUserId())){
                emotionVo.setCanDel(true);
            }

        }));
        return emotionVoList;
    }

    @Override
    public boolean delEmotionById(String emoId) {
        return emotionMapper.deleteById(emoId) > OperationCode.FAIL.getCode();
    }

    @Override
    public Long getEmotionCount() {
        QueryWrapper<Emotion> queryWrapper = new QueryWrapper<>();
        return emotionMapper.selectCount(queryWrapper);
    }
}
