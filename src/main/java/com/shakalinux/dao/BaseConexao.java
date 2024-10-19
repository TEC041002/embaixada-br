package com.shakalinux.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseConexao {
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/embaixada", "shakalinux", "221015");
    }

}
