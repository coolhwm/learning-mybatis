package com.learn.db;

import com.learn.utils.RowMapper;

import java.util.List;

/**
 * Db
 *
 * @author hwm
 * @since 2016/8/31
 **/
public interface JdbcDb {

    /**
     *  查询列表
     */
    <T> List<T> queryForList(String sql, RowMapper<T> rowMapper, Object... params);
}
