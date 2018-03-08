package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SrcPointIdDao {

    public Map<String,Object> SrcPointId (String src){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();

        String sql = "select id from tb_media where src = ?";

        List<Object> params = new ArrayList<Object>();
        params.add(src);
        try {
            return jdbc.findSimpleResult(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
