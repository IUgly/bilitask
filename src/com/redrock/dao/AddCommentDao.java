package com.redrock.dao;

import com.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddCommentDao {

    public boolean addComment(String comment, String username, int videoid){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        String sql = "insert into comment(content,username,videoid) value (?, ?, ?) ";
        try {
            List<Object> params = new ArrayList<Object>();
            params.add(comment);
            params.add(username);
            params.add(videoid);

            return jdbc.updateByPreparedStatement(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.releaseConn();
        return false;
    }
}
