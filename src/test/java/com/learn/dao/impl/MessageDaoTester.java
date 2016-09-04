package com.learn.dao.impl;

import com.learn.bean.Message;
import com.learn.dao.MessageDao;
import com.learn.db.MybatisDb;
import org.junit.Test;

import java.util.List;

/**
 * MessageDaoTester
 *
 * @author hwm
 * @since 2016/9/4
 **/
public class MessageDaoTester {
    private MessageDao dao = new MybatisDb().getMapper(MessageDao.class);


    @Test
    public void testQueryMessages(){
        Message message = new Message();
        message.setCommand("1");
        List<Message> messages = dao.queryMessages(message);

        System.out.println("messages = " + messages);
    }

    @Test
    public void testDeleteOne(){
        dao.deleteOne(1);
    }
}
