/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ACER
 */
public class DBUtils {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=PhoneManagement_3";
            conn=DriverManager.getConnection(url,"sa","01299399957aa");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
