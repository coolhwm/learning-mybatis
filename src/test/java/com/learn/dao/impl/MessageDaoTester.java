package com.learn.dao.impl;

import com.learn.bean.Message;
import com.learn.dao.MessageDao;
import com.learn.db.MybatisDb;
import com.learn.utils.Page;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
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
    public void testSave(){
        for (int i = 0; i < 20; i++) {
            Message message = new Message();
            message.setCommand("XXX" + i);
            message.setDescription("ZZZ" + i);
            message.setContent("YYY" + i);
            dao.save(message);
        }
    }

    @Test
    public void testQueryMessages(){
        Message message = new Message();
        message.setCommand("1");
        List<Message> messages = dao.queryMessages(message);

        System.out.println("messages = " + messages);
    }

    /**
     * 使用拦截器进行分页
     */
    @Test
    public void testQueryMessagesInterceptor(){
        Message message = new Message();
        message.setCommand("1");

        Page<Message> page = new Page<>();
        page.setCurrentPage(1);
        page.setPageNumber(5);
        page.setQuery(message);

        List<Message> messages = dao.queryMessagesByInterceptor(page);

        System.out.println("messages = " + messages);

        System.out.println("messages = " + Utils.messages);
    }

    @Test
    public void testDeleteOne(){
        dao.deleteOne(1);
    }
}
