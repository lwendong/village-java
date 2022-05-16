package com.village.api;

import com.village.entity.dto.EmotionDto;
import com.village.entity.vo.EmotionVo;
import com.village.utils.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@Api(tags = "心情")
public interface EmotionControllerApi {

    @Operation(summary = "添加心情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="addEmotion",value="添加心情",dataTypeClass = EmotionDto.class,
                    paramType = "query",required = true)
    })
    public Response<EmotionVo> addEmotion(String token,EmotionDto emotionDto);

    @Operation(summary = "分页查询所有心情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="queryAllEmotionByPage",value="分页查询所有心情",dataTypeClass = EmotionDto.class,
                    paramType = "query",required = true)
    })
    public Response<List<EmotionVo>> queryAllEmotionByPage(String token,EmotionDto emotionDto);

    @Operation(summary = "根据心情id删除心情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="delEmotionById",value="根据心情id删除心情",dataTypeClass = String.class,
                    paramType = "query",required = true)
    })
    public Response<Boolean> delEmotionById(String emoId);

    @Operation(summary = "获取未删除的心情总数")
    @ApiImplicitParams({
            @ApiImplicitParam(name="getEmotionCount",value="获取未删除的心情总数",
                    paramType = "query",required = true)
    })
    public Response<Long> getEmotionCount();
}