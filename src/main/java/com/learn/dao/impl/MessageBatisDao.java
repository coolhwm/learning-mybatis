package com.learn.dao.impl;

import com.learn.bean.Message;
import com.learn.dao.MessageDao;
import com.learn.db.MybatisDb;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
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
        SqlSession sqlSession = null;
        try {
            sqlSession = db.getSqlSession();
            Message condition = new Message();
            condition.setCommand(command);
            condition.setDescription(description);

            return sqlSession.selectList("Message.queryMessages", condition);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
