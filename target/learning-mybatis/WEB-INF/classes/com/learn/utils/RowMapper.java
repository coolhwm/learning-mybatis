package com.learn.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper
 *
 * @author hwm
 * @since 2016/8/30
 **/
public interface RowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}
