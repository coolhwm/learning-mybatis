package com.learn.service.impl;

import com.learn.bean.Command;
import com.learn.bean.Content;
import com.learn.dao.impl.CommandDao;
import com.learn.service.QueryService;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * CommandQueryService
 *
 * @author hwm
 * @since 2016/9/2
 **/
public class CommandQueryService implements QueryService{

    private CommandDao commandDao = new CommandDao();


    private static final String DEFAULT_MESSAGE = "未找到";


    public String queryByCommand(String command) {
        List<Command> commands = commandDao.queryCommandList(command, null);
        if (!commands.isEmpty()) {
            List<Content> contents = commands.get(0).getContents();
            int index = RandomUtils.nextInt(0, contents.size());
            return contents.get(index).getContent();
        } else {
            return DEFAULT_MESSAGE;
        }
    }
}
