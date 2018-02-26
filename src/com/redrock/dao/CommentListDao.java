package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentListDao {
      public List<Map<String,Object>> CommentList(int videoid,int start,int count){
            JDBC jdbc = new JDBC();
            jdbc.getConnection();
            String sql = "select content,username from comment where videoid =? limit ?,?";

            try {
                  List<Object> params = new ArrayList<Object>();
                  params.add(videoid);
                  params.add(start);
                  params.add(count);

                  return jdbc.findModeResult(sql,params);
            } catch (SQLException e) {
                  e.printStackTrace();
            }
            return null;
      }
}
