package com.village.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseModel implements Serializable {

    @TableField(exist = false)
    protected String token;
}
