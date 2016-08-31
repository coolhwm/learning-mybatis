package com.learn.dao.impl;

import com.learn.bean.Message;
import com.learn.dao.MessageDao;
import com.learn.db.JdbcDb;
import com.learn.db.JdbcMySqlDb;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageDao
 *
 * @author hwm
 * @since 2016/8/30
 **/
public class MessageJdbcDao implements MessageDao {

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://192.168.125.134:3306/micro_message";

    private JdbcDb db = new JdbcMySqlDb(DB_URL, DB_USERNAME, DB_PASSWORD);

    /**
     * 查询消息列表
     */
    public List<Message> queryMessages(String command, String description){

        String sql = "SELECT ID, COMMAND, CONTENT, DESCRIPTION FROM MESSAGE WHERE 1=1 ";

        List<String> paramList = new ArrayList<>();
        if (StringUtils.isNotEmpty(command)) {
            sql += " AND COMMAND LIKE ?";
            paramList.add("%" + command + "%");
        }
        if (StringUtils.isNotEmpty(description)) {
            sql += " AND DESCRIPTION LIKE ?";
            paramList.add("%" + description + "%");
        }

        List<Message> messages = db.queryForList(sql, rs -> {
            Message message = new Message();
            message.setId(rs.getInt("ID"));
            message.setCommand(rs.getString("COMMAND"));
            message.setContent(rs.getString("CONTENT"));
            message.setDescription(rs.getString("DESCRIPTION"));
            return message;
        }, paramList.toArray());

        return messages;
    }
}
