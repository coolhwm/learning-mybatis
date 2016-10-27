package com.learn.db;

import com.learn.utils.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DbUtils
 *
 * @author hwm
 * @since 2016/8/30
 **/
public class JdbcMySqlDb implements JdbcDb {

    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";

    private String url;

    private String username;

    private String password;

    public JdbcMySqlDb(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public <T> List<T> queryForList(String sql, RowMapper<T> rowMapper, Object... params) {
        try {

            Connection conn = createConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
            }

            ResultSet rs = stmt.executeQuery();

            List<T> list = new ArrayList<>();
            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                list.add(object);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException("数据库查询发生错误：", e);
        }
    }

    private Connection createConnection() {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("数据链接初始化发生错误：", e);
        }
    }

}
