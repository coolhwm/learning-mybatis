# MyBatis - 学习笔记
## 0. 学习日记
- Day1： 使用原生方式构建工程，搭建基础（2016/08/31）；
- Day2： 学习配置文件，实现查询（2016/09/01）；
- Day3： 一对多关系配置（2016/09/02）；

##  1. 配置文件

### 1.1 配置数据源

``` xml
 <dataSource type="UNPOOLED">
    <property name="driver" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://192.168.125.134:3306/micro_message"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
</dataSource>
```

### 1.2 加载配置文件
通过`Resources`进行配置文件的加载：
``` java
Resources.getResourceAsReader("com/learn/config/Configuration.xml");
```


## 2. SqlSession
###2.1 基本作用
- 向SQL语句传入参数；
- 执行SQL语句；
- 获取执行SQL语句；
- 事务的控制；

### 2.2 获取SqlSession
- 通过配置文件去数据库连接的相关信息；
- 通过配置信息构建`SqlSessionFactory`；
- 通过`SqlSessionFactory`打开数据库会话；
- 获取的`SqlSession`需要关闭；

``` java
//加载配置文件
Reader reader = Resources.getResourceAsReader("com/learn/config/Configuration.xml");
//构造SqlSessionFactoryBuilder
SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//构造SqlSessionFactory
SqlSessionFactory sessionFactory = builder.build(reader);
//打开session
SqlSession sqlSession = sessionFactory.openSession();
```

## 3. SQL配置文件
### 3.1  SQL配置
#### 3.1.1 基本配置

##### 3.1.1.1 select 标签
- `select`标签即为使用`sqlSession.select(id)`方法进行调用；
	- `id` - 必须是在命名空间内唯一的；
	- `resultMap` - 使用`resultMap`标签的映射关系进行映射； 
	- `resultType`- 使用类的字段名进行映射，字段名要和SQL中查询出来的列名一致；
	- `parameterType` - 参数类型；`lang`包下可以简写，其他需要完整类名；
	- `parameterMap` - 配置参数类和参数之间的映射关系；
``` xml
<select id="queryMessages" parameterType="long" resultMap="MessageResult">
  SELECT ID, COMMAND, CONTENT, DESCRIPTION FROM MESSAGE WHERE 1=1
</select>
```

##### 3.1.1.2 insert 标签
- `parameterType` - 参数的实体类；
- `useGeneratedKeys` - 是否使用自增标签；
- `keyProperty` - 主键的属性，新生成的主键值会回填到该属性中；
```
<insert id="insert" parameterType="com.learn.bean.Command" useGeneratedKeys="true" keyProperty="id">
    INSERT INTO COMMAND (NAME, DESCRIPTION) VALUES (#{name}, #{description})
</insert>
```

#### 3.1.2 动态SQL
![标签汇总](./1472969380886.png)

##### 3.1.2.1 表达式取值：
``` 
-- 预编译参数
#{propertyName}
-- 直接拼接参数，可用于指定排序列和顺序
${propertyName}
```

##### 3.1.2.2 if标签
- `test` - 判断条件；
``` xml
<if test="command != null and !&quot;&quot;.equals(command)" >
    AND COMMAND LIKE '%' #{command} '%'
</if>
```

##### 3.1.2.3 foreach标签
- `collection` - 集合；
- `index` - 下标或key值；
- `item` - 具体的一项；
- `separator` - 分隔符；
``` xml
<foreach collection="array" index="i" item="item"> 
<foreach/>
```

##### 3.1.2.4 where标签
- 若标签内部为空则不输出`where`关键字；
- 截掉第一个`AND`；
``` xml
<where>
    <if test="name != null">
        AND A.NAME LIKE '%' #{name} '%'
    </if>
    <if test="description != null">
        AND A.DESCRIPTION LIKE '%' #{description} '%'
    </if>
</where>
```

##### 3.1.2.5 set标签
- 用于处理拼接`SQL`语句时的逗号问题；

##### 3.1.2.6 SQL标签
- 定义SQL语句常量，可以使用`include`标签进行引用；
- `id`：唯一标识符；

##### 3.1.2.7 Trim标签
- `prefix`：如果标签内部有内容，则加上前缀；
- `suffix`：如果标签内部有内容，则加上后缀；
- `prefixOverride`：去掉最前面的字符；可用用`/`去掉多种字符；代替`where`；
- `suffixOverride`：去掉最后面的字符；`代替set`；

##### 3.1.2.8 choose-when-otherwise标签
- 类似`if-elseif-else`结构；


#### 3.1.3 OGNL
- 对象导航图语言，独立的表达式语言，在`struts2`中也存在；
- 取值范围：从`parameterType`中取值；也可以从其他的标签（循环）中取值；
- 取值写法：
	- String和基本数据类型 
		- `_parameter`
	- 自定义类型 
		- `属性名`
	- 数组
		- `array[索引]`
		- `array[索引].属性名`
	- 列表
		- `list[索引]`
		- `list[索引].属性名`
	- Map
		- `_parameter.key`
		- `key`
		- `key.属性属性名`
- 操作符
	- java的常用操作符；
	- 自己特有的操作符：`and, or, mod, in, not in`；
- 可以调用java对象的方法；
- 表达式取值

### 3.2  命名空间
- 配置文件的根节点`mapper`可以指定`namespace`；
- 不同命名空间中的`id`是可以重复的；
``` xml
<mapper namespace="User"></mapper>
```

### 3.3  实体映射
- 通过`resultMap`标签可以配置结果集和`bean`之间的关系；
	- `type` - 属性指定对应类的全名称;
	- `id` - 唯一标识符，在`resutlMap`中唯一即可； 
- 通过`id`和`reuslt`标签配置字段间的关系
	- `column` - 数据库的列名；
	- `porperty` - 实体对象的属性名；
	- `typeHandler` - 类型转换；
	- `jdbcType` - 实体对象属性的类型，相当于`java.sql.Types`；
``` xml
 <resultMap type="com.learn.bean.Message" id="MessageResult">
    <id column="ID" jdbcType="INTEGER" property="id"/>
    <result column="COMMAND" jdbcType="VARCHAR" property="command"/>
    <result column="CONTENT" jdbcType="VARCHAR" property="content"/>
    <result column="DESCRIPTION" jdbcType="VARCHAR" property="description"/>
  </resultMap>
```
## 4. 一对多
- 在主表的实体类中需要包含子表的`List`；
- 配置`resultMap`时使用`collection`配置关联的集合映射；
	- `property` - 集合属性；
	- `resultMap` - 关联对象的映射器，需要使用命名空间前缀；
- 配置`select`时使用`join`查询语句；
- 配置多对一时，使用`association`标签配置其对应的1端；

### 4.1 类配置：
``` java
public class Command {
    private int id;
    private String description;
    private String name;
    private List<Content> contents;
    .....
}
```

### 4.2 XML配置：
``` xml
<mapper namespace="Command">
	<!-- 配置结果映射 -->
    <resultMap type="com.learn.bean.Command" id="CommandMap">
        <id column="COMMAND_ID" jdbcType="INTEGER" property="id"/>
        <result column="NAME" jdbcType="VARCHAR" property="name"/>
        <result column="DESCRIPTION" jdbcType="VARCHAR" property="description"/>
        <!-- 配置集合映射及使用的resultMap -->
        <collection property="contents" resultMap="Content.ContentMap"  />
    </resultMap>
    
	<!-- 使用JOIN语句提取数据 -->
    <select id="queryCommands" resultMap="CommandMap" parameterType="com.learn.bean.Command">
        SELECT
        A.ID AS COMMAND_ID,
        A.NAME,
        A.DESCRIPTION,
        B.ID,
        B.CONTENT,
        B.COMMAND_ID
        FROM COMMAND A LEFT JOIN COMMAND_CONTENT B ON A.ID = B.COMMAND_ID
    </select>
</mapper>
```

## 5. 面向接口编程
- 通过接口的方式调用方法，由`MyBatis`框架自动生成其实现类；
- 命名空间使用接口的全局限定名，避免发生冲突；
- 参数和返回值通过接口的方式限定，形成规范；

### 5.1 接口配置
定义一个接口类，类中的方法与配置文件中的语句一一对应：
``` java
public interface MessageDao {
    List<Message> queryMessages(Message message);
}
```
XML配置文件：
``` xml
 <select id="queryMessages" resultMap="MessageResult" parameterType="com.learn.bean.Message">
 </select>
```
配置文件的命名空间需要和类的全局限定名一致：
```
<mapper namespace="com.learn.dao.MessageDao"></mapper>
```

### 5.2 接口调用
使用`sqlSession.getMapper`方法可以获取其实现类，而不用自己写实现类：
``` java
MessageDao messageDao = sqlSession.getMapper(MessageDao.class);
```
可以直接调用接口的方法进行调用，避免调用`selectList`等底层方法：
``` java
List<Message> messages = messageDao .queryMessages(message);
```

## 6. 集成log4j
- 导入`log4j`包；
- 配置`log4j.properties`配置文件；
``` java
# 输出级别和位置
log4j.rootLogger=DEBUG,Console

# 指定输出位置为控制台
log4j.appender.Console=org.apache.log4j.ConsoleAppender

# 指定输出布局（自定义）
log4j.appender.Console.layout=org.apache.log4j.PatternLayout

# 布局格式 %d(时间) %t(线程名称) %p(日志级别) -5(右边补空格) %c(类全名) %m(附加信息) %n(换行)
log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n

# 精确控制某个包的日志级别
log4j.logger.org.apache=INFO
```