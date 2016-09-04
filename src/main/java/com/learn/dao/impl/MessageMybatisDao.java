package com.learn.dao.impl;

import com.learn.bean.Message;
import com.learn.dao.MessageDao;
import com.learn.db.MybatisDb;

import java.util.List;

/**
 * MessageBatisDao
 *
 * @author hwm
 * @since 2016/8/31
 **/
public class MessageMybatisDao implements MessageDao {

    private MybatisDb db = new MybatisDb();

    @Override
    public List<Message> queryMessages(Message message) {
        return db.queryForList("Message.queryMessages", message);
    }

    @Override
    public void deleteOne(int id) {
        db.delete("Message.deleteBatch", id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        db.delete("Message.deleteBatch", ids);
    }
}
