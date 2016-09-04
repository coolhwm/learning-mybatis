package com.learn.interceptor;

import com.learn.utils.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * PageInterceptor
 *
 * @author hwm
 * @since 2016/9/4
 **/
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class}))
public class PageInterceptor implements Interceptor {


    /**
     * 返回代理类
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 拦截方法
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //获取代理对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //获取反射的包装类
        MetaObject meta = MetaObject.forObject(statementHandler,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);

        //反射获取信息
        MappedStatement mappedStatement = (MappedStatement) meta.getValue("delegate.mappedStatement");
        String sqlId = mappedStatement.getId();

        //判断是否需要拦截
        if (sqlId.endsWith("ByInterceptor")) {
            BoundSql boundSql = statementHandler.getBoundSql();
            //获取原始的SQL语句
            String sql = boundSql.getSql();
            //获取输入参数
            Page page = (Page) boundSql.getParameterObject();

            //查询总条数的SQL语句
            String countSql = "SELECT COUNT(*) FROM (" + sql + ") a";
            //拦截参数
            Object[] args = invocation.getArgs();
            Connection conn = (Connection) args[0];
            PreparedStatement countStatement = conn.prepareStatement(countSql);
            //反射获取参数信息，并设置
            ParameterHandler parameterHandler = (ParameterHandler) meta.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countStatement);
            //获取总条数的查询结果
            ResultSet resultSet = countStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                page.setTotalNumber(count);
            }

            //拼接分页语句
            String pageSql = sql + " limit " + page.getDbIndex() + ", " + page.getDbNumber() + " ";
            //设置修改后的SQL语句
            meta.setValue("delegate.boundSql.sql", pageSql);
        }

        //交回主权
        return invocation.proceed();
    }

    /**
     * 可以获取到配置文件里面的属性值
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
