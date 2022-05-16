package com.village.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "user")
@ApiModel(value = "User", description = "用户功能实体类")
public class User extends BaseModel{

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "昵称")
    @TableField(value = "nick")
    private String nick;

   @ApiModelProperty(value = "密码")
    @TableField(value = "password")
    private String password;

    @ApiModelProperty(value = "用户名")
    @TableField(value = "username")
    private String username;

    @ApiModelProperty(value = "用户真实姓名")
    @TableField(value = "realname")
    private String realname;

    @ApiModelProperty(value = "性别")
    @TableField(value = "sex")
    private String sex;

    @ApiModelProperty(value = "头像地址")
    @TableField(value = "avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "用户创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "用户信息更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    @TableLogic
    private Integer deleted;

}
