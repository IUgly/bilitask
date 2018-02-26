package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VideoCountDao {


    public int VideoCountUser(String userName){
        JDBC jdbc =new JDBC();
        jdbc.getConnection();
        String sql="select count(id) from tb_media where username =(?)";
        try {
            List<Object> params = new ArrayList<Object>();
            params.add(userName);

            Map<String,Object> result =jdbc.findSimpleResult(sql,params);

            Iterator<Map.Entry<String, Object>> it = result.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                int count = (int)(long)entry.getValue();
                return count;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        jdbc.releaseConn();
        return 0;
    }
}
