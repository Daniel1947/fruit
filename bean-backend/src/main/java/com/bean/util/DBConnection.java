package com.bean.util;

import javax.naming.Context;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Daniel on 14-8-17.
 */
public class DBConnection {
    private static Context context = null;
    private static DataSource dataSource = null;

    /**
     * @return
     */
    public synchronized static Connection getConnection() {
        try {
//            if (null == context) {
//                context = new InitialContext();
//                dataSource = (DataSource) context.lookup("java:comp/env/jdbc/db");
//            }
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bean?useUnicode=true&characterEncoding=utf8";
            String username = "root";
            String pwd = "";

            Connection conn = DriverManager.getConnection(url , username , pwd ) ;
//            Connection conn = dataSource.getConnection();
            if (conn != null) {
                return conn;
            } else
                System.out.println("DB connection error!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException cnfe){
            System.out.println(cnfe.getMessage());
        }
        return null;
    }

    /**
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
