package com.learn.utils;

import java.util.List;

/**
 * Db
 *
 * @author hwm
 * @since 2016/8/31
 **/
public interface Db {

    /**
     *  查询列表
     */
    <T> List<T> queryForList(String sql, RowMapper<T> rowMapper, Object... params);
}
