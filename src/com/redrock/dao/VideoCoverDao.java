package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.List;

public class VideoCoverDao {
    public int VideoCover(List<Object> params){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        String sql = "update tb_media set picture=? where id=?";
        try {
            if (!jdbc.updateByPreparedStatement(sql,params)) {
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
