package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ugly
 * 通过视频id找到视频地址
 */
public class IdPointSrcDao {
    public Map<String, Object> IdPointSrc(String id){
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
    public Map<String,Object> SrcPointId(String src){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();

        String sql ="select id from tb_media where src = ?";

        List<Object> params = new ArrayList<Object>();
        try {
            return jdbc.findSimpleResult(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
