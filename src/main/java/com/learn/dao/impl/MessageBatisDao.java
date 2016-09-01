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
public class MessageBatisDao implements MessageDao {

    private MybatisDb db = new MybatisDb();

    @Override
    public List<Message> queryMessages(String command, String description) {
        Message condition = new Message();
        condition.setCommand(command);
        condition.setDescription(description);
        return db.queryForList("Message.queryMessages", condition);
    }

    @Override
    public void delete(int id) {
        db.delete("Message.deleteOne", id);
    }

    @Override
    public void delete(List<Integer> ids) {
        db.delete("Message.deleteBatch", ids);
    }
}
