package com.village.api;

import com.village.entity.Remind;
import com.village.utils.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@Api(tags = "提醒")
public interface RemindControllerApi {

    @Operation(summary = "添加提醒")
    @ApiImplicitParams({
            @ApiImplicitParam(name="addRemind",value="添加提醒",dataTypeClass = Remind.class,
                    paramType = "query",required = true)
    })
    public Response<Boolean> addRemind(String token,Remind remind);

    @Operation(summary = "查询所有提醒")
    @ApiImplicitParams({
            @ApiImplicitParam(name="all",value="查询所有提醒",dataTypeClass = Remind.class,
                    paramType = "query",required = true)
    })
    public Response<List<Remind>> all(String token);


    @Operation(summary = "删除提醒")
    @ApiImplicitParams({
            @ApiImplicitParam(name="delete",value="删除提醒",dataTypeClass = Remind.class,
                    paramType = "query",required = true)
    })
    public Response<Boolean> delete(String token, String id);

    @Operation(summary = "完成提醒")
    @ApiImplicitParams({
            @ApiImplicitParam(name="finish",value="删除提醒",dataTypeClass = Remind.class,
                    paramType = "query",required = true)
    })
    public Response<Boolean> finish(String token, String id);

//    @Operation(summary = "分页查询所有心情")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="queryAllEmotionByPage",value="分页查询所有心情",dataTypeClass = EmotionDto.class,
//                    paramType = "query",required = true)
//    })
//    public Response<List<EmotionVo>> queryAllEmotionByPage(String token,EmotionDto emotionDto);
//
//    @Operation(summary = "根据心情id删除心情")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="delEmotionById",value="根据心情id删除心情",dataTypeClass = String.class,
//                    paramType = "query",required = true)
//    })
//    public Response<Boolean> delEmotionById(String emoId);
//
//    @Operation(summary = "获取未删除的心情总数")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="getEmotionCount",value="获取未删除的心情总数",
//                    paramType = "query",required = true)
//    })
//    public Response<Long> getEmotionCount();
}