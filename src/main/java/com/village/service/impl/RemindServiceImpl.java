package com.village.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.village.entity.Remind;
import com.village.mapper.RemindMapper;
import com.village.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RemindServiceImpl implements RemindService {

    @Autowired
    private RemindMapper remindMapper;

    @Override
    public Remind addRemind(Remind remind) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            remind.setEndTime(simpleDateFormat.parse(remind.getEndTimeString()));
            remind.setStartTime(new Date());
            remindMapper.insert(remind);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return remind;
    }

    @Override
    public List<Remind> queryAllRemind(String userId) {
        QueryWrapper<Remind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).orderByDesc("create_time");
        return  remindMapper.selectList(queryWrapper);
    }

    @Override
    public Boolean delete(String id) {
        return remindMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean finish(String id) {
        Remind build = Remind.builder().id(id).finish(true).build();
        return remindMapper.updateById(build) > 0;
    }
}
