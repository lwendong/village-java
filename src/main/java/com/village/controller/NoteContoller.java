package com.village.controller;

import com.village.annotation.RequireLogin;
import com.village.api.NoteContollerApi;
import com.village.constant.ResponseCode;
import com.village.entity.Note;
import com.village.service.NoteService;
import com.village.utils.response.Response;
import com.village.utils.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteContoller implements NoteContollerApi {

    @Autowired
    private NoteService noteService;

    @Override
    @RequireLogin
    @PostMapping("/add")
    public Response<Note> addNote(@RequestHeader("Authorization") String token,@RequestBody Note note) {
        note.setToken(token);
        return ResponseBuilder.success(ResponseCode.SUCCESS,noteService.addNote(note));
    }

    @Override
    @RequireLogin
    @RequestMapping("/update")
    public Response<Note> updateNote(@RequestHeader("Authorization") String token,@RequestBody Note note) {
        note.setToken(token);
        return ResponseBuilder.success(ResponseCode.SUCCESS,noteService.updateNote(note));
    }

    @Override
    @RequireLogin
    @RequestMapping("/all")
    public Response<List<Note>> getAllNoteAtMonth(@RequestHeader("Authorization") String token,Note note) {
        note.setToken(token);
        return ResponseBuilder.success(ResponseCode.SUCCESS,noteService.getAllNoteAtMonth(note));
    }
}
