package com.learn.service.impl;

import com.learn.bean.Message;
import com.learn.dao.MessageDao;
import com.learn.dao.impl.MessageBatisDao;
import com.learn.service.QueryService;

import java.util.List;

/**
 * ListService
 *
 * @author hwm
 * @since 2016/8/31
 **/
public class MessageQueryService implements QueryService {

    private MessageDao messageDao = new MessageBatisDao();

    private static final String DEFAULT_MESSAGE = "未找到";

    public List<Message> queryMessages(String command, String description) {
        return messageDao.queryMessages(command, description);
    }

    public String queryByCommand(String command) {
        List<Message> messages = messageDao.queryMessages(command, null);
        if (!messages.isEmpty()) {
            return messages.get(0).getContent();
        } else {
            return DEFAULT_MESSAGE;
        }
    }

}
