package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.util.List;
public class RegisterDao  {

    public int registerUser(List<Object> params) {
        JDBC jdbcUtils=new JDBC();
        jdbcUtils.getConnection();
        String sql = "insert into userinfo(username,pawd) values(?,?)";
        try{
            if (!jdbcUtils.updateByPreparedStatement(sql, params)) {
                return 1; // user exists
            }
        }catch(Exception e){
            e.printStackTrace();
            // database exception
            return 2;
        }

        jdbcUtils.releaseConn();
        return 0;
    }

}
