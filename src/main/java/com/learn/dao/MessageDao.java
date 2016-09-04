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
    List<Message> queryMessages(Message message);

    /**
     * 删除一条消息
     */
    void deleteOne(int id);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);
}
