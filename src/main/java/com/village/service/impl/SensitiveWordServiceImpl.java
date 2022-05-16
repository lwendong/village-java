package com.village.service.impl;

import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.village.entity.SensitiveWord;
import com.village.mapper.SensitiveMapper;
import com.village.service.SensitiveWordService;
import com.village.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;

@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {

    @Autowired
    private SensitiveMapper sensitiveMapper;

    @Override
    public <T> T check(T t, String token) {
        try {
            Class<?> aClass = t.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                if (String.class.equals(type)) {
                    String name = field.getName();
                    if("fileUrl".equals(name)){
                        continue;
                    }
                    String value = (String) field.get(t);
                    if (StringUtils.isNoneEmpty(value)) {
                        if (SensitiveWordHelper.contains(value)) {
                            String replace = SensitiveWordHelper.replace(value);
                            SensitiveWord sensitive = new SensitiveWord();
                            sensitive.setUserId(TokenUtil.getClaimByName(token, "userId").asString());
                            sensitive.setOriginalText(value);
                            sensitive.setReplaceText(replace);
                            sensitive.setCreate_time(new Date());
                            sensitiveMapper.insert(sensitive);
                            field.set(t, replace);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
