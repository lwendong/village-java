package com.village.service;

import com.village.entity.Remind;

import java.util.List;

public interface RemindService {

    public Remind addRemind(Remind remind);

    public List<Remind> queryAllRemind(String userId);

    public Boolean delete(String id);

    public Boolean finish(String id);
}
