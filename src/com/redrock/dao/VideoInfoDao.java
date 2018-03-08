package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoInfoDao {

    public int videoinfo(String title,String descript,String sort,String username,int id){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        String sql = "update tb_media set title=?,descript=?,sort=?,username=? where id=?";
        try {
            List<Object> params = new ArrayList<Object>();
            params.add(title);
            params.add(descript);
            params.add(sort);
            params.add(username);
            params.add(id);
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
