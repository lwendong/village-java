package com.village.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.village.entity.Note;
import com.village.mapper.NoteMapper;
import com.village.service.NoteService;
import com.village.service.SensitiveWordService;
import com.village.utils.NoteUtil;
import com.village.utils.TokenUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private SensitiveWordService sensitiveWordService;

    @Override
    public Note addNote(Note note) {
        note = sensitiveWordService.check(note, note.getToken());
        if(StringUtils.isNoneEmpty(note.getId())){
            return  updateNote(note);

        }
        String userId = TokenUtil.getClaimByName(note.getToken(), "userId").asString();
        String currentGroup = NoteUtil.getCurrentGroup();
        String currentNumber = NoteUtil.getCurrentNumber();
        note.setNoteGroup(currentGroup);
        note.setNoteNumber(currentNumber);
        Note noteExist = noteExist(userId, currentGroup, currentNumber);
        if (ObjectUtils.isEmpty(noteExist)) {
            note.setUserId(userId);
            noteMapper.insert(note);
        } else {
            note.setId(noteExist.getId());
            updateNote(note);
        }
        return note;
    }

    @Override
    public Note updateNote(Note note) {
        noteMapper.updateById(note);
        return note;
    }

    @Override
    public Note noteExist(String userId,String currentGroup, String currentNumber) {
        QueryWrapper<Note> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("note_group", currentGroup).eq("note_number", currentNumber).eq("user_id", userId);
        return noteMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Note> getAllNoteAtMonth(Note note) {
        Claim userId = TokenUtil.getClaimByName(note.getToken(), "userId");
        QueryWrapper<Note> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("note_group", note.getNoteGroup())
                .eq("user_id", userId.asString());
        return noteMapper.selectList(queryWrapper);
    }
}
