package com.learn.dao;

import com.learn.bean.Content;

import java.util.List;

/**
 * ContentDao
 *
 * @author hwm
 * @since 2016/9/5
 **/
public interface ContentDao {

    /**
     * 单条插入
     */
    void insertOne(Content content);

    /**
     * 批量新增
     */
    void insertBatch(List<Content> contents);
}
