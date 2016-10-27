package com.learn.service;

import com.learn.bean.Message;

import java.util.List;

/**
 * QueryService
 *
 * @author hwm
 * @since 2016/9/2
 **/
public interface MessagePagedQueryService {

    /**
     * 查询命令对应的描述
     */
    String queryByCommand(String command);

    /**
     * 分页查询
     */
    List<Message> queryMessages(String pageNumber, String pageSize, Message query);
}
