package com.redrock.dao;

import com.redrock.control.DbcpConnection;
import com.redrock.utils.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddCommentDao {

    DbcpConnection dbcpcon = new DbcpConnection();
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public boolean addComment(String comment, String username, int videoid){
        JDBC jdbc = new JDBC();
//        jdbc.getConnection();
        String sql = "insert into comment(content,username,videoid) value (?, ?, ?) ";
        try {
            conn = DbcpConnection.getConnection();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            List<Object> params = new ArrayList<Object>();
            params.add(comment);
            params.add(username);
            params.add(videoid);

            return jdbc.updateByPreparedStatement(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        jdbc.releaseConn();
        return false;
    }
}
