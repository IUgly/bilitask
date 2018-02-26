package com.redrock.dao;

import com.redrock.utils.JDBC;
//import org.springframework.jdbc.support.JdbcUtils;

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
            return 2; // database exception
        }
        //finally{
        jdbcUtils.releaseConn();
          //  return 3; // other errors
        //}

        return 0;
    }

}
