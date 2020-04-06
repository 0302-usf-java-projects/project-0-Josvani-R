package com.revature.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
  
    private static String URL = System.getenv("url");
    private static String USERNAME = System.getenv("username");
    private static String PASSWORD = System.getenv("password");
    
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
      
    }

}
