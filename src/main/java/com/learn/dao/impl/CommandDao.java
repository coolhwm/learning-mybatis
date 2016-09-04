package com.learn.dao.impl;

import com.learn.bean.Command;
import com.learn.db.MybatisDb;

import java.util.List;

/**
 * CommandDao
 *
 * @author hwm
 * @since 2016/9/2
 **/
public class CommandDao {
    private MybatisDb db = new MybatisDb();

    public List<Command> queryCommandList(String name, String description){
        Command command = new Command();
        command.setName(name);
        command.setDescription(description);

        return db.queryForList("Command.queryCommands", command);
    }

    public int add(Command command){
        db.insert("Command.insert", command);
        return command.getId();
    }
}
