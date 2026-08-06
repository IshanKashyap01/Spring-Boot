package com.cn.cnEvent.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.cn.cnEvent.dal.SpeakerDAL;
import com.cn.cnEvent.entity.Speaker;
import com.cn.cnEvent.exception.ElementAlreadyExistException;
import com.cn.cnEvent.exception.NotFoundException;

@Service
public class SpeakerService
{
    private SpeakerDAL dal;

    public SpeakerService(SpeakerDAL dal)
    {
        this.dal = dal;
    }

    @Transactional
    public Speaker get(Long id)
    {
        Speaker speaker = dal.get(id);
        if(speaker == null)
        {
            throw new NotFoundException("speaker not found");
        }
        return speaker;
    }

    @Transactional
    public List<Speaker> getAll()
    {
        return dal.getAll();
    }

    @Transactional
    public void save(Speaker speaker)
    {
        if(dal.get(speaker.getId()) != null)
        {
            throw new ElementAlreadyExistException("speaker already exists");
        }
        dal.save(speaker);
    }
}
