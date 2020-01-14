package br.com.controlador.jdbc.teste;

import java.sql.DriverManager;
import java.sql.SQLException;

public class testeConexao {
	public static void main(String[] args) {
		try {
        	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            DriverManager.getConnection("jdbc:mysql://172.18.66.119:3306/controledeempenhos?useTimezone=true&serverTimezone=UTC&useSSL=false", "admin", "9415524Ma@");
            System.out.println("Executo com sucesso");
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
}
