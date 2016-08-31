package com.learn.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * DBAccess
 *
 * @author hwm
 * @since 2016/8/31
 **/
public class MybatisDb {
    public SqlSession getSqlSession() throws IOException {
        //加载配置文件
        Reader reader = Resources.getResourceAsReader("com/learn/config/Configuration.xml");
        //构造SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //构造SqlSessionFactory
        SqlSessionFactory sessionFactory = builder.build(reader);
        //打开session
        SqlSession sqlSession = sessionFactory.openSession();
        return sqlSession;
    }
}
