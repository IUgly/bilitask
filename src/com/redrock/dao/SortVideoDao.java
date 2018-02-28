package com.redrock.dao;

import com.redrock.utils.JDBC;
import com.sun.javafx.collections.MappingChange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SortVideoDao {

    public List<Map<String,Object>> SortVideo(String sort, int start, int count){
        JDBC jdbc =new JDBC();
        jdbc.getConnection();
        String sql = "select id,title,src,descript,uptime,username " +
                "from tb_media where sort=? limit ?,?";

        try {
            List<Object> params = new ArrayList<Object>();
            params.add(sort);
            params.add(start);
            params.add(count);

            return jdbc.findModeResult(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
