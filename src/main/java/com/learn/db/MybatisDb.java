package com.learn.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

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
        return sessionFactory.openSession();
    }

    public <T> List<T> queryForList(String statement, Object parameter){
        return this.execute(new MybatisCallbackHandler<List<T>>() {
            @Override
            public List<T> callbackInSqlSession(SqlSession sqlSession) {
                return sqlSession.selectList(statement, parameter);
            }
        });
    }

    public void delete(String statement, Object parameter){
        this.execute(new MybatisCallbackHandler<Void>() {
            @Override
            public Void callbackInSqlSession(SqlSession sqlSession) {
                sqlSession.delete(statement, parameter);
                return null;
            }
        });
    }

    public <T> T execute(MybatisCallbackHandler<T> callbackHandler){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSqlSession();
            sqlSession.commit();
            return callbackHandler.callbackInSqlSession(sqlSession);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }



    interface MybatisCallbackHandler<T>{
        T callbackInSqlSession(SqlSession sqlSession);
    }

}
