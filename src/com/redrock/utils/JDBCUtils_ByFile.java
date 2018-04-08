package com.redrock.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils_ByFile {
    private static final String driverClass;
    private static final String url;
    private static final String username;
    private static final String password;

    /**
     * 读取配置文件
     */
    static {
        Properties properties = null;
        try {
            InputStream in = new FileInputStream("src/jdbc.properties");
            properties = new Properties();
            properties.load(in);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driverClass = properties.getProperty("driverClass");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    /**
     * 加载驱动
     */
    public static void loadDriver() {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库 并返回连接conn
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            loadDriver();
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 释放资源
     *
     * @param rs
     *            结果集
     * @param stmt
     *            执行sql语句
     * @param conn
     *            连接
     */
    public static void closeConnection(ResultSet rs, PreparedStatement stmt,
                                       Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     *
     * @param stmt
     *            执行sql语句
     * @param conn
     *            数据库连接
     */
    public static void closeConnection(PreparedStatement stmt, Connection conn) {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
