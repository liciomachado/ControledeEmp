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
import br.com.controlador.jdbc.modelo.NotaFiscal;

public class NotaFiscalDao {
	private Connection connection;

	public NotaFiscalDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public NotaFiscalDao(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(NotaFiscal nf) {
		String sql = "insert into notafiscal " +
				"(numNota,chaveAcesso,valorTotal,idEmpenho,dataEmissao,dataRecebido)" +
				" values (?,?,?,?,?,?)";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1,nf.getNumNota());
			stmt.setString(2,nf.getChaveAcesso());
			stmt.setDouble(3,nf.getValorTotal());
			stmt.setInt(4,nf.getEmpenho().getIdEmpenho());
			stmt.setDate(5, new Date(nf.getDataEmissao().getTimeInMillis()));
			stmt.setDate(6, new Date(nf.getDataRecebido().getTimeInMillis()));

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<NotaFiscal> getLista() {
		try {
			List<NotaFiscal> nfs = new ArrayList<NotaFiscal>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from notafiscal");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				NotaFiscal nf = new NotaFiscal();
				nf.setIdNotaFiscal(rs.getInt("idnotaFiscal"));
				nf.setNumNota(rs.getInt("numNota"));
				nf.setChaveAcesso(rs.getString("chaveAcesso"));
				nf.setValorTotal(rs.getDouble("valorTotal"));
				nf.setIdEmpenho(rs.getString("idEmpenho"));
				nf.setIdEmpresa(rs.getString("idEmpresa"));
				
				Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataEmissao"));
                nf.setDataEmissao(data);
                data.setTime(rs.getDate("dataRecebido"));
				nf.setDataRecebido(data);

				// adicionando o objeto � lista
				nfs.add(nf);
			}
			rs.close();
			stmt.close();
			return nfs;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<NotaFiscal> getNotaRecebidos() {
		try {
			List<NotaFiscal> nfs = new ArrayList<NotaFiscal>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select a.idnotaFiscal,b.dataEmpenho,a.dataRecebido,empr.nome,a.numNota,a.valorTotal,\r\n" + 
							"b.numeroEmpenho,b.destino,a.chaveAcesso\r\n" + 
							"from notafiscal as a inner join empenho as b on a.idEmpenho = b.idempenho \r\n" + 
							"inner join  empresa as empr on empr.idempresa = b.idEmpresa");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				NotaFiscal nf = new NotaFiscal();
				nf.setIdNotaFiscal(rs.getInt("idnotaFiscal"));
				nf.setNumNota(rs.getInt("numNota"));
				nf.setChaveAcesso(rs.getString("chaveAcesso"));
				nf.setValorTotal(rs.getDouble("valorTotal"));
				
				Empenho empenho = new Empenho();
				empenho.setNumeroEmpenho(rs.getString("numeroEmpenho"));
				empenho.setDestino(rs.getString("destino"));
				nf.setEmpenho(empenho);
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				nf.setEmpresa(empresa);
				
				Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataEmpenho"));
                nf.setDataEmissao(data);
                data.setTime(rs.getDate("dataRecebido"));
				nf.setDataRecebido(data);

				// adicionando o objeto � lista
				nfs.add(nf);
			}
			rs.close();
			stmt.close();
			return nfs;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void buscaRecebidos(NotaFiscal nf) {
		String sql = "select a.idnotaFiscal,a.dataEmissao,a.dataRecebido,empr.nome,a.numNota,a.valorTotal," + 
				"b.numeroEmpenho,b.destino,a.chaveAcesso " + 
				"from notafiscal as a inner join empenho as b on a.idEmpenho = b.idempenho " + 
				"inner join  empresa as empr on empr.idempresa = a.idEmpresa";
		
	}
	
	public void altera(NotaFiscal nf) {
		String sql = "update notafiscal set numNota=?, chaveAcesso=?, valorTotal=?, idEmpenho=?,"
				+ "idEmpresa=?,dataEmissao=?,dataRecebido=? where idnotaFiscal=?";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1,nf.getNumNota());
			stmt.setString(2,nf.getChaveAcesso());
			stmt.setDouble(3,nf.getValorTotal());
			stmt.setInt(4,nf.getEmpenho().getIdEmpenho());
			stmt.setInt(5,nf.getEmpresa().getIdEmpresa());
			stmt.setDate(6, new Date(nf.getDataEmissao().getTimeInMillis()));
			stmt.setDate(7, new Date(nf.getDataRecebido().getTimeInMillis()));
			stmt.setInt(8,nf.getIdNotaFiscal());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void remove(NotaFiscal nf) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete " +
					"from usuario where idusuario=?");
			stmt.setLong(1, nf.getIdNotaFiscal());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
