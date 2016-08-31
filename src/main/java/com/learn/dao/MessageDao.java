package com.learn.dao;

import com.learn.bean.Message;

import java.util.List;

/**
 * MessageDao
 *
 * @author hwm
 * @since 2016/8/31
 **/
public interface MessageDao {
    /**
     * 查询消息列表
     */
    public List<Message> queryMessages(String command, String description);
}
