package com.learn.dao.impl;

import com.learn.bean.Command;
import org.junit.Test;

/**
 * CommandDaoTester
 *
 * @author hwm
 * @since 2016/9/4
 **/
public class CommandDaoTester {

    private CommandDao commandDao = new CommandDao();

    @Test
    public void testAdd() {
        Command command = new Command();
        command.setDescription("X999");
        command.setName("X999");
        int add = commandDao.add(command);
        System.out.println("add = " + add);

    }
}
