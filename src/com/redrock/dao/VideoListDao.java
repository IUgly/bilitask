package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VideoListDao {

    public List<Map<String, Object>> VideoListUser(String username, int start, int count){
        JDBC jdbc =new JDBC();
        jdbc.getConnection();
        String sql = "select id,title,src,descript,picture,uptime " +
                     "from tb_media where username=? limit ?,?";
        try {
            List<Object> params=new ArrayList<Object>();
            params.add(username);
            params.add(start);
            params.add(count);

            return jdbc.findModeResult(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
