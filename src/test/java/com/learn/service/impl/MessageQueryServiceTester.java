package com.learn.service.impl;

import com.learn.bean.Message;
import org.junit.Test;

import java.util.List;

/**
 * MessageQueryServiceTester
 *
 * @author hwm
 * @since 2016/9/4
 **/
public class MessageQueryServiceTester {

    private MessageQueryService messageQueryService = new MessageQueryService();

    @Test
    public void testQueryMessages() {
        Message message = new Message();
        message.setCommand("1");
        List<Message> messages = messageQueryService.queryMessages("1", "5", message);
        System.out.println("messages = " + messages);
    }
}
