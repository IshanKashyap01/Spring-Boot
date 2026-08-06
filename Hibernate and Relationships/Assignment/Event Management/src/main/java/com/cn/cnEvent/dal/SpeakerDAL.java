package com.cn.cnEvent.dal;

import java.util.List;
import com.cn.cnEvent.entity.Speaker;

public interface SpeakerDAL 
{
    Speaker get(Long id);

    List<Speaker> getAll();

    void save(Speaker speaker);
}
