package com.learn.dao.impl;

import com.learn.bean.Message;
import com.learn.dao.MessageDao;
import org.junit.Test;

import java.util.List;

/**
 * MessageBatisDaoTester
 *
 * @author hwm
 * @since 2016/8/31
 **/
public class MessageBatisDaoTester {

    private MessageDao messageDao = new MessageBatisDao();

    @Test
    public void testQuery() {
        List<Message> messages = messageDao.queryMessages("", "");
        System.out.println(messages);
    }
}
