package com.learn.dao.impl;

import com.learn.bean.Message;
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
public class MessageJDBCDao{

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://192.168.125.134:3306/micro_message";

    private JdbcDb db = new JdbcMySqlDb(DB_URL, DB_USERNAME, DB_PASSWORD);


    /**
     * 查询消息列表
     * @param message
     */
    public List<Message> queryMessages(Message message){

        String sql = "SELECT ID, COMMAND, CONTENT, DESCRIPTION FROM MESSAGE WHERE 1=1 ";

        String command = message.getCommand();
        String description = message.getDescription();

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
            Message item = new Message();
            item.setId(rs.getInt("ID"));
            item.setCommand(rs.getString("COMMAND"));
            item.setContent(rs.getString("CONTENT"));
            item.setDescription(rs.getString("DESCRIPTION"));
            return item;
        }, paramList.toArray());

        return messages;
    }


}
