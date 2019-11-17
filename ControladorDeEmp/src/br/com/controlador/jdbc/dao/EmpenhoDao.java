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
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.Empresa;

public class EmpenhoDao {

	private Connection connection;

    public EmpenhoDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    public EmpenhoDao(Connection connection) {
        this.connection = connection;
    }	
	
    public void adiciona(Empenho empenho) {
        String sql = "insert into empenho " +
                "(dataEmpenho,numeroEmpenho,idEmpresa,destino,valorTotal,empenhoDigitalizado)" +
                " values (?,?,?,?,?,?)";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setDate(1, new Date(empenho.getDataEmpenho().getTimeInMillis()));
            stmt.setString(2,empenho.getNumeroEmpenho());
            stmt.setInt(3,empenho.getEmpresa().getIdEmpresa());
            stmt.setString(4, empenho.getDestino());
            stmt.setDouble(5, empenho.getValorTotal());
            stmt.setString(6, empenho.getEmpenhoDigitalizado());

            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Empenho> getLista() {
        try {
            List<Empenho> empenhos = new ArrayList<Empenho>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("select * from empenho");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto Contato
            	Empenho empenho = new Empenho();
            	empenho.setIdEmpenho(rs.getInt("idempenho"));
            	empenho.setNumeroEmpenho(rs.getString("numeroEmpenho"));
            	//empenho.setEmpresa(rs.getString(""));
            	empenho.setDestino(rs.getString("destino"));
            	empenho.setValorTotal(rs.getDouble("valorTotal"));
            	empenho.setEmpenhoDigitalizado(rs.getString("empenhoDigitalizado"));

                // montando a data através do Calendar
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataEmpenho"));
                empenho.setDataEmpenho(data);

                // adicionando o objeto à lista
                empenhos.add(empenho);
            }
            rs.close();
            stmt.close();
            return empenhos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void altera(Empenho empenho) {
        String sql = "update empenho set dataEmpenho=?, numeroEmpenho=?, idEmpresa=?," +
                "destino=?, valorTotal=?,empenhoDigitalizado=? where idempenho=?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, new Date(empenho.getDataEmpenho().getTimeInMillis()));
            stmt.setString(2,empenho.getNumeroEmpenho());
            stmt.setInt(3,empenho.getEmpresa().getIdEmpresa());
            stmt.setString(4, empenho.getDestino());
            stmt.setDouble(5, empenho.getValorTotal());
            stmt.setString(6, empenho.getEmpenhoDigitalizado());
            stmt.setLong(7, empenho.getIdEmpenho());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void remove(Empenho empenho) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete " +
                    "from empenho where idempenho=?");
            stmt.setLong(1, empenho.getIdEmpenho());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
