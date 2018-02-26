package com.redrock.dao;

import com.redrock.service.UpMediaServce;
import com.redrock.utils.JDBC;

import java.util.List;

public class UpMediaDao  {

    public int upMedia(List<Object> params) {
        JDBC jdbc=new JDBC();
        jdbc.getConnection();
        String sql="insert into tb_media(title, descript, src,username) values (?, ?, ?, ?)";
        try {
            if (!jdbc.updateByPreparedStatement(sql,params)) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
        jdbc.releaseConn();
        return 0;
    }
}
