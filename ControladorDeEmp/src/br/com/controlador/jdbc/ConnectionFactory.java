package br.com.controlador.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
        	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //return DriverManager.getConnection("jdbc:mysql://localhost/controledeempenhos?useTimezone=true&serverTimezone=UTC&useSSL=false", "admin", "9415524Ma@");
            return DriverManager.getConnection("jdbc:mysql://localhost/controledeempenhos?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "123321");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}	