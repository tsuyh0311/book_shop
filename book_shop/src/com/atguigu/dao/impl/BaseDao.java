package com.atguigu.dao.impl;

import com.atguigu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();


    public int update(String sql,Object ...args){
        Connection conn  = null;

        try {
             conn =JDBCUtils.getConnection();
            return queryRunner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }


    public <T> T query(Class<T> type,String sql,Object ...args){
         Connection conn = null;

        try {
            conn = JDBCUtils.getConnection();
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }


 public <T>List<T> queryForList(Class<T> type,String sql,Object ...args){
        Connection conn = null;

     try {
         conn = JDBCUtils.getConnection();
         return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
     } catch (SQLException throwables) {
         throwables.printStackTrace();
         throw new RuntimeException(throwables);
     }
 }


 public Object singleQuery(String sql,Object ...args){
     Connection conn = null;
     try {
         conn = JDBCUtils.getConnection();
         return queryRunner.query(conn,sql,new ScalarHandler(),args);
     } catch (SQLException throwables) {
         throwables.printStackTrace();
         throw new RuntimeException(throwables);
     }
 }

}
