package com.village.service;

import com.village.entity.Note;

import java.util.List;

public interface NoteService {

    public Note addNote(Note note);

    public Note updateNote(Note note);

    public Note noteExist(String userId,String currentGroup,String currentMonth);

    public List<Note> getAllNoteAtMonth(Note note);
}
