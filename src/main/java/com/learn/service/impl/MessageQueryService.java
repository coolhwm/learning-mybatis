package com.learn.service.impl;

import com.learn.bean.Message;
import com.learn.dao.MessageDao;
import com.learn.db.MybatisDb;
import com.learn.service.MessagePagedQueryService;
import com.learn.utils.Page;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * ListService
 *
 * @author hwm
 * @since 2016/8/31
 **/
public class MessageQueryService implements MessagePagedQueryService {

    private MessageDao messageDao =  new MybatisDb().getMapper(MessageDao.class);

    private static final String DEFAULT_MESSAGE = "未找到";

    public List<Message> queryMessages(String currentPage, String pageNumber, Message query) {
        int totalNumber = messageDao.count(query);

        Page<Message> page = new Page<>();
        page.setQuery(query);

        page.setTotalNumber(totalNumber);
        page.setCurrentPage(NumberUtils.toInt(currentPage));
        page.setPageNumber(NumberUtils.toInt(pageNumber));

        return messageDao.queryMessagesByPage(page);
    }

    public String queryByCommand(String command) {
        Message message = new Message();
        message.setCommand(command);
        List<Message> messages = messageDao.queryMessages(message);
        if (!messages.isEmpty()) {
            return messages.get(0).getContent();
        } else {
            return DEFAULT_MESSAGE;
        }
    }

}
