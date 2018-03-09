package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.List;

public class UpPhotoDao {
    public int UpPhoto(List<Object> params){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        String sql = "update userinfo set photo=? where username=?";
        try{
            if(!jdbc.updateByPreparedStatement(sql,params)) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 2;
        }
        jdbc.releaseConn();
        return 0;
    }
}
