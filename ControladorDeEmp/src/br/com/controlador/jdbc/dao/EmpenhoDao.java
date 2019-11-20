package br.com.controlador.jdbc.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Statement;
import com.sun.media.sound.EmergencySoundbank;

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

	public int adiciona(Empenho empenho) {
		int lastId = 0;
		String sql = "insert into empenho " +
				"(dataEmpenho,numeroEmpenho,idEmpresa,destino,valorTotal,empenhoDigitalizado)" +
				" values (?,?,?,?,?,?)";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			// seta os valores
			stmt.setDate(1, new Date(empenho.getDataEmpenho().getTimeInMillis()));
			stmt.setString(2,empenho.getNumeroEmpenho());
			stmt.setInt(3,empenho.getEmpresa().getIdEmpresa());
			stmt.setString(4, empenho.getDestino());
			stmt.setDouble(5, empenho.getValorTotal());
			stmt.setBytes(6, empenho.getEmpenhoDigitalizado());

			// executa
			stmt.execute();
			final ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
			    lastId = rs.getInt(1);
			}
			stmt.close();
			return lastId;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Empenho buscaPorID(int id) {
		Empenho empenho = new Empenho();
		try{
			PreparedStatement stmt = this.connection.prepareStatement("select * from empenho where idempenho=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				// criando o objeto Contato
				empenho.setIdEmpenho(rs.getInt("idempenho"));
				empenho.setNumeroEmpenho(rs.getString("numeroEmpenho"));
				//empenho.setEmpresa(rs.getString(""));
				empenho.setDestino(rs.getString("destino"));
				empenho.setValorTotal(rs.getDouble("valorTotal"));
				empenho.setEmpenhoDigitalizado(rs.getBytes("empenhoDigitalizado"));

				// montando a data atrav�s do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEmpenho"));
				empenho.setDataEmpenho(data);
				
				

				rs.close();
				stmt.close();
			}
			return empenho;
		}catch (Exception e) {
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
				empenho.setEmpenhoDigitalizado(rs.getBytes("empenhoDigitalizado"));

				// montando a data atrav�s do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEmpenho"));
				empenho.setDataEmpenho(data);

				// adicionando o objeto � lista
				empenhos.add(empenho);
			}
			rs.close();
			stmt.close();
			return empenhos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Empenho> getListaEmpenhosPendentes() {
		try {
			List<Empenho> empenhos = new ArrayList<Empenho>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from empenho where idempenho not in(select idempenho from notafiscal)");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Empenho empenho = new Empenho();
				empenho.setIdEmpenho(rs.getInt("idempenho"));
				empenho.setNumeroEmpenho(rs.getString("numeroEmpenho"));
				//empenho.setEmpresa(rs.getString(""));
				empenho.setDestino(rs.getString("destino"));
				empenho.setValorTotal(rs.getDouble("valorTotal"));
				empenho.setEmpenhoDigitalizado(rs.getBytes("empenhoDigitalizado"));

				// montando a data atrav�s do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEmpenho"));
				empenho.setDataEmpenho(data);

				// adicionando o objeto � lista
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
			stmt.setBytes(6, empenho.getEmpenhoDigitalizado());
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
	public boolean empenhoComFile( File f,Empenho empenho ){
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into empenho " +
					"(dataEmpenho,numeroEmpenho,idEmpresa,destino,valorTotal,empenhoDigitalizado)" +
					" values (?,?,?,?,?,?)");

			//converte o objeto file em array de bytes
			InputStream is = new FileInputStream( f );
			byte[] bytes = new byte[(int)f.length() ];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
				offset += numRead;
			}
			stmt.setDate(1, new Date(empenho.getDataEmpenho().getTimeInMillis()));
			stmt.setString(2,empenho.getNumeroEmpenho());
			stmt.setInt(3,empenho.getEmpresa().getIdEmpresa());
			stmt.setString(4, empenho.getDestino());
			stmt.setDouble(5, empenho.getValorTotal());
			stmt.setBytes(6, bytes);

			//stmt.setString( 1, f.getName() );
			stmt.execute();
			stmt.close();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;

	}
	public int empenhoComFile2( InputStream f,Empenho empenho ){
		int lastId = 0;
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into empenho " +
					"(dataEmpenho,numeroEmpenho,idEmpresa,destino,valorTotal,empenhoDigitalizado)" +
					" values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

			stmt.setDate(1, new Date(empenho.getDataEmpenho().getTimeInMillis()));
			stmt.setString(2,empenho.getNumeroEmpenho());
			stmt.setInt(3,empenho.getEmpresa().getIdEmpresa());
			stmt.setString(4, empenho.getDestino());
			stmt.setDouble(5, empenho.getValorTotal());
			stmt.setBlob(6, f);
			
			// executa
			stmt.executeUpdate();
			final ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
			    lastId = rs.getInt(1);
			}
			stmt.close();		
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lastId;
	}

}
