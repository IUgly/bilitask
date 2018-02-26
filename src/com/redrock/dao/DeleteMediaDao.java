package com.redrock.dao;

import com.redrock.service.UpMediaServce;
import com.redrock.utils.JDBC;

import java.util.List;

/**
 * @author ugly
 */
public class DeleteMediaDao {

    public int Media(List<Object> params){
        boolean flag=false;
        JDBC jdbc=new JDBC();
        jdbc.getConnection();
        String sql="DELETE FROM tb_media WHERE id = ?";
        try{
            if (!jdbc.updateByPreparedStatement(sql,params)){
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }

        jdbc.releaseConn();
        return 0;
    }
    public int upMedia(List<Object> params) {
        return 0;
    }
}
