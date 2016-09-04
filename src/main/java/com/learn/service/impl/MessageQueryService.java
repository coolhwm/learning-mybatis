package com.learn.service.impl;

import com.learn.bean.Message;
import com.learn.dao.MessageDao;
import com.learn.dao.impl.MessageMybatisDao;
import com.learn.service.QueryService;

import java.util.List;

/**
 * ListService
 *
 * @author hwm
 * @since 2016/8/31
 **/
public class MessageQueryService implements QueryService {

    private MessageDao messageDao = new MessageMybatisDao();

    private static final String DEFAULT_MESSAGE = "未找到";

    public List<Message> queryMessages(String command, String description) {
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        return messageDao.queryMessages(message);
    }

    public String queryByCommand(String command) {
        Message message = new Message();
        message.setCommand(command);
        List<Message> messages = messageDao.queryMessages(message);
        if (!messages.isEmpty()) {
            return messages.get(0).getContent();
        } else {
            return DEFAULT_MESSAGE;
        }
    }

}
