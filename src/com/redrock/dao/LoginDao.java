package com.redrock.dao;


import com.redrock.service.LoginService;
import com.redrock.utils.JDBC;

import java.util.List;
import java.util.Map;

/**
 * @author ugly
 */
public class LoginDao  {

    public int LoginUser(List<Object> params) {
        JDBC jdbcutils =new JDBC();
        jdbcutils.getConnection();
        String sql = "select * from userinfo where username=? and pawd=?";
        try {
            Map<String,Object> result = jdbcutils.findSimpleResult(sql, params);
            if (result.isEmpty()) {
                // user or password error
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
        jdbcutils.releaseConn();

        return 0;
    }
}
