package com.learn.dao.impl;

import com.learn.bean.Content;
import com.learn.dao.ContentDao;
import com.learn.db.MybatisDb;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ContentDaoTester
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class ContentDaoTester {

    private ContentDao dao = new MybatisDb().getMapper(ContentDao.class);

    @Test
    public void testInsertBatch(){
        List<Content> contents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Content content = new Content();
            content.setContent("LLL" + i);
            content.setCommandId(1);
            contents.add(content);
        }

        dao.insertBatch(contents);
    }
}
