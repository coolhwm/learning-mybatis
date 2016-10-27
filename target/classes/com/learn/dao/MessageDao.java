package com.learn.dao;

import com.learn.bean.Message;
import com.learn.utils.Page;

import java.util.List;

/**
 * MessageDao
 *
 * @author hwm
 * @since 2016/8/31
 **/
public interface MessageDao {

    /**
     * 分页查询消息列表
     */
    List<Message> queryMessagesByPage(Page<Message> page);

    /**
     * 分页查询消息列表
     */
    List<Message> queryMessagesByInterceptor(Page<Message> page);

    /**
     * 通过command查询
     */
    List<Message> queryMessages(Message message);

    /**
     * 查询总条数
     */
    int count(Message message);

    /**
     * 删除一条消息
     */
    void deleteOne(int id);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 新增记录
     */
    void save(Message message);


}
