package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchVideoDao {

    public List<Map<String,Object>> SearchVideo(String videoname,int start,int count){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        String sql = "SELECT * FROM tb_media WHERE title LIKE ? limit ?,?";

        try {
            List<Object> params=new ArrayList<Object>();
            params.add(videoname);
            params.add(start);
            params.add(count);

            return jdbc.findModeResult(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
