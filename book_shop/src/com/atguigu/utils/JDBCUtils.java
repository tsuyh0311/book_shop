package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class JDBCUtils {
private static DruidDataSource dataSource;
private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
    static {
        try {
            Properties properties = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection(){
        Connection conn = conns.get();

        if(conn==null){
            try {
                conn = dataSource.getConnection();//从数据库连接池获取连接
                conns.set(conn);//保存到ThreadLocal对象中，供后面的jdbc使用
                conn.setAutoCommit(false);//设置为手动管理事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return conn;
    }


    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection!=null){ //如果不为空，说明之前用过连接，操作过数据库
            try {
                connection.commit();//提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();//一定要执行，否则会出错，因为tomcat服务器底层使用了线程池技术
    }


    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection!=null){ //如果不为空，说明之前用过连接，操作过数据库
            try {
                connection.rollback();//回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();//一定要执行，否则会出错，因为tomcat服务器底层使用了线程池技术
    }
}
