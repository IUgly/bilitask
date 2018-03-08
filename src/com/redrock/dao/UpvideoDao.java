package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.List;

public class UpvideoDao {
    public int Upvideo(List<Object> params){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        String sql = "insert into tb_media(src) value (?)";
        try {
            if (!jdbc.updateByPreparedStatement(sql,params)) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 2;
        }
        jdbc.getConnection();
        return 0;
    }
}
