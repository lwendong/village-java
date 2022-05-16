package com.village.api;

import com.village.entity.Note;
import com.village.utils.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@Api(tags = "便笺")
public interface NoteContollerApi {

    @Operation(summary = "添加便笺")
    @ApiImplicitParams({
            @ApiImplicitParam(name="addNote",value="添加便笺",dataTypeClass = Note.class,
                    paramType = "query",required = true)
    })
    public Response<Note> addNote(String token,Note note);

    @Operation(summary = "更新便笺")
    @ApiImplicitParams({
            @ApiImplicitParam(name="updateNote",value="更新便笺",dataTypeClass = Note.class,
                    paramType = "query",required = true)
    })
    public Response<Note> updateNote(String token,Note note);

    @Operation(summary = "获取当月所有便笺")
    @ApiImplicitParams({
            @ApiImplicitParam(name="getAllNoteAtMonth",value="获取当月所有便笺",dataTypeClass = Note.class,
                    paramType = "query",required = true)
    })
    public Response<List<Note>> getAllNoteAtMonth(String token,Note note);
}
