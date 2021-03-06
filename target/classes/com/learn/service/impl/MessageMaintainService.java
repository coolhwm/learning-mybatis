package com.learn.service.impl;

import com.learn.dao.impl.MessageMybatisDao;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * MaintainService
 *
 * @author hwm
 * @since 2016/9/1
 **/
public class MessageMaintainService {

    private MessageMybatisDao messageDao = new MessageMybatisDao();

    public void delete(String id) {
        Validate.notEmpty(id, "ID参数不能为空");
        int msgId = NumberUtils.toInt(id);
        messageDao.deleteOne(msgId);
    }

    public void delete(List<String> ids){
        List<Integer> msgIds = new ArrayList<>();
        for (String id : ids) {
            msgIds.add(NumberUtils.toInt(id));
        }
        messageDao.deleteBatch(msgIds);
    }
}
