package br.com.controlador.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.controlador.jdbc.ConnectionFactory;
import br.com.controlador.jdbc.modelo.Empresa;

public class EmpresaDao {
	
	private Connection connection;

    public EmpresaDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    public EmpresaDao(Connection connection) {
        this.connection = connection;
    }
    
    public void adiciona(Empresa empresa) {
        String sql = "insert into empresa " +
                "(nome,email,contato)" +
                " values (?,?,?)";

        try {
            // prepared statement para inser��o
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setString(1,empresa.getNome());
            stmt.setString(2,empresa.getEmail());
            stmt.setString(3,empresa.getContato());
            
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Empresa> getLista() {
        try {
            List<Empresa> empresas = new ArrayList<Empresa>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from empresa");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
            	Empresa empresa = new Empresa();
            	empresa.setIdEmpresa(rs.getInt("idempresa"));
            	empresa.setNome(rs.getString("nome"));
            	empresa.setEmail(rs.getString("email"));
            	empresa.setContato(rs.getString("contato"));
            	
                // adicionando o objeto � lista
            	empresas.add(empresa);
            }
            rs.close();
            stmt.close();
            return empresas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void altera(Empresa empresa) {
        String sql = "update empresa set nome=?, email=?, contato=?" +
                " where idempresa=?";

        try {
            // prepared statement para inser��o
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setString(1,empresa.getNome());
            stmt.setString(2,empresa.getEmail());
            stmt.setString(3,empresa.getContato());
            stmt.setInt(4,empresa.getIdEmpresa());
            
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Empresa buscaPorId(int id) {
    	Empresa empresa = new Empresa();
    	try{
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM empresa where idempresa = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
				if(rs.next()) {
					empresa.setIdEmpresa(rs.getInt("idempresa"));
	            	empresa.setNome(rs.getString("nome"));
	            	empresa.setEmail(rs.getString("email"));
	            	empresa.setContato(rs.getString("contato"));
				}
				
				return empresa;
	    	}catch (Exception e) {
				System.out.println("Algo de errado aconteceu na busca por id de empresa");
			}
		return empresa;
    }
    
    public void remove(Empresa empresa) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete " +
                    "from empresa where idempresa=?");
            stmt.setLong(1, empresa.getIdEmpresa());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
