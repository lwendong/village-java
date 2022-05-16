package com.village.service;

import com.village.entity.dto.EmotionDto;
import com.village.entity.vo.EmotionVo;

import java.util.List;

public interface EmotionService {

    public EmotionVo addEmotion(EmotionDto emotionDto);

    public List<EmotionVo> queryAllEmotionByPage(EmotionDto emotionDto);

    public boolean delEmotionById(String emoId);

    public Long getEmotionCount();
}
