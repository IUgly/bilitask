package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ugly
 * 通过视频id找到视频地址
 */
public class PointSqlDao {

    public Map<String, Object> PointSqlUser(String id){
        JDBC jdbc =  new JDBC();
        jdbc.getConnection();

        String sql ="select src from tb_media where id = ?";

        List<Object> params = new ArrayList<Object>();
        params.add(id);
        try {
            return jdbc.findSimpleResult(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
