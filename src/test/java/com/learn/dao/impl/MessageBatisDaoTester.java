package com.learn.dao.impl;

import com.learn.bean.Message;
import org.junit.Test;

import java.util.List;

/**
 * MessageBatisDaoTester
 *
 * @author hwm
 * @since 2016/8/31
 **/
public class MessageBatisDaoTester {

    private MessageMybatisDao messageDao = new MessageMybatisDao();

    @Test
    public void testQuery() {
        List<Message> messages = messageDao.queryMessages(null);
        System.out.println(messages);
    }
}
