package com.learn.service;

import com.learn.bean.Message;
import com.learn.dao.MessageDao;

import java.util.List;

/**
 * ListService
 *
 * @author hwm
 * @since 2016/8/31
 **/
public class ListService {

    private MessageDao messageDao = new MessageDao();

    public List<Message> queryMessages(String command, String description){
        return messageDao.queryMessages(command, description);
    }
}
