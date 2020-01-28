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
import br.com.controlador.jdbc.modelo.Usuario;

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
				"(numNota,chaveAcesso,valorTotal,idEmpenho,dataEmissao,dataRecebido,idusuario)" +
				" values (?,?,?,?,?,?,?);";
		
		String sql2 = "update empenho set etapa = 4 where idempenho = ?;";
		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,nf.getNumNota());
			stmt.setString(2,nf.getChaveAcesso());
			stmt.setDouble(3,nf.getValorTotal());
			stmt.setInt(4,nf.getEmpenho().getIdEmpenho());
			stmt.setDate(5, new Date(nf.getDataEmissao().getTimeInMillis()));
			stmt.setDate(6, new Date(nf.getDataRecebido().getTimeInMillis()));
			stmt.setInt(7, nf.getUsuario().getIdUsuario());
			stmt.execute();
			stmt.close();
			
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			stmt2.setInt(1,nf.getEmpenho().getIdEmpenho());
			stmt2.execute();
			stmt2.close();
			
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
	public List<NotaFiscal> getListaPorId(int id) {
		try {
			List<NotaFiscal> nfs = new ArrayList<NotaFiscal>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select a.idnotaFiscal,a.numNota,a.chaveAcesso,a.valorTotal,a.dataEmissao,"
							+ "a.dataRecebido,b.nome,a.dataProtocolado from notafiscal as a inner join"
							+ " usuario as b on a.idusuario = b.idusuario "
							+ "where idempenho = ?");
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				NotaFiscal nf = new NotaFiscal();
				nf.setIdNotaFiscal(rs.getInt("idnotaFiscal"));
				nf.setNumNota(rs.getInt("numNota"));
				nf.setChaveAcesso(rs.getString("chaveAcesso"));
				nf.setValorTotal(rs.getDouble("valorTotal"));
				
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("nome"));
				nf.setUsuario(usuario);
				
				Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataEmissao"));
                nf.setDataEmissao(data);
                
				Calendar data3 = Calendar.getInstance();
                data3.setTime(rs.getDate("dataRecebido"));
				nf.setDataRecebido(data3);
				try {
					Calendar data2 = Calendar.getInstance();
					data2.setTime(rs.getDate("dataProtocolado"));
					nf.setDataProtocolado(data2);
				} catch (Exception e) {
				}
				
				nfs.add(nf);
			}
			rs.close();
			stmt.close();
			return nfs;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public NotaFiscal getListaProtocolo(int id) {
		try {
			
			PreparedStatement stmt = this.connection.
					prepareStatement("SELECT a.numeroEmpenho,b.nome,nf.numNota,a.destino FROM notafiscal as nf inner join empenho as a on nf.idEmpenho = a.idempenho\r\n" + 
							"inner join empresa as b on b.idempresa = a.idEmpresa where nf.idnotaFiscal = ?");
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			NotaFiscal nf = new NotaFiscal();

			while (rs.next()) {
				// criando o objeto Contato
				nf.setNumNota(rs.getInt("numNota"));
				Empenho emp = new Empenho();
				emp.setDestino(rs.getString("destino"));
				emp.setNumeroEmpenho(rs.getString("numeroEmpenho"));
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));

				nf.setEmpenho(emp);
				nf.setEmpresa(empresa);
				
				
			}
			rs.close();
			stmt.close();
			return nf;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<NotaFiscal> getNotaRecebidos() {
		try {
			List<NotaFiscal> nfs = new ArrayList<NotaFiscal>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select a.idempenho,a.idnotaFiscal,b.dataEmpenho,a.dataRecebido,empr.nome,a.numNota,a.valorTotal,\r\n" + 
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
				empenho.setIdEmpenho(rs.getInt("idempenho"));
				nf.setEmpenho(empenho);
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				nf.setEmpresa(empresa);
				
				Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataEmpenho"));
                nf.setDataEmissao(data);
                Calendar data2 = Calendar.getInstance();
                data2.setTime(rs.getDate("dataRecebido"));
				nf.setDataRecebido(data2);

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
	public List<NotaFiscal> getNotaRecebidosFiltroEmpresa(String filtro) {
		try {
			List<NotaFiscal> nfs = new ArrayList<NotaFiscal>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select a.idempenho,a.idnotaFiscal,b.dataEmpenho,a.dataRecebido,empr.nome,a.numNota,a.valorTotal,\r\n" + 
							"							b.numeroEmpenho,b.destino,a.chaveAcesso\r\n" + 
							"							from notafiscal as a inner join empenho as b on a.idEmpenho = b.idempenho \r\n" + 
							"							inner join  empresa as empr on empr.idempresa = b.idEmpresa\r\n" + 
							"                            where empr.nome like ? ");
			stmt.setString(1, '%' + filtro + '%');
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
				empenho.setIdEmpenho(rs.getInt("idempenho"));
				nf.setEmpenho(empenho);
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				nf.setEmpresa(empresa);
				
				Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataEmpenho"));
                nf.setDataEmissao(data);
                Calendar data2 = Calendar.getInstance();
                data2.setTime(rs.getDate("dataRecebido"));
				nf.setDataRecebido(data2);

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
	public List<NotaFiscal> getNotaRecebidosFiltroDestino(String filtro) {
		try {
			List<NotaFiscal> nfs = new ArrayList<NotaFiscal>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select a.idempenho,a.idnotaFiscal,b.dataEmpenho,a.dataRecebido,empr.nome,a.numNota,a.valorTotal,\r\n" + 
							"							b.numeroEmpenho,b.destino,a.chaveAcesso\r\n" + 
							"							from notafiscal as a inner join empenho as b on a.idEmpenho = b.idempenho \r\n" + 
							"							inner join  empresa as empr on empr.idempresa = b.idEmpresa\r\n" + 
							"                            where b.destino like ? ");
			
			stmt.setString(1, '%' + filtro + '%');
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
				empenho.setIdEmpenho(rs.getInt("idempenho"));
				nf.setEmpenho(empenho);
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				nf.setEmpresa(empresa);
				
				Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataEmpenho"));
                nf.setDataEmissao(data);
                Calendar data2 = Calendar.getInstance();
                data2.setTime(rs.getDate("dataRecebido"));
				nf.setDataRecebido(data2);

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
	public List<NotaFiscal> getNotaMeusRecebidos(int id) {
		try {
			List<NotaFiscal> nfs = new ArrayList<NotaFiscal>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select a.idempenho,a.idnotaFiscal,b.dataEmpenho,a.dataRecebido,empr.nome,a.numNota,a.valorTotal,\r\n" + 
							"					b.numeroEmpenho,b.destino,a.chaveAcesso, u.nome\r\n" + 
							"					from notafiscal as a inner join empenho as b on a.idEmpenho = b.idempenho \r\n" + 
							"					inner join  empresa as empr on empr.idempresa = b.idEmpresa\r\n" + 
							"					inner join usuario as u on b.idusuario = u.idusuario\r\n" + 
							"                    where u.idusuario = ?");
			stmt.setInt(1, id);
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
				empenho.setIdEmpenho(rs.getInt("idempenho"));
				nf.setEmpenho(empenho);
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				nf.setEmpresa(empresa);
				
				Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataEmpenho"));
                nf.setDataEmissao(data);
                Calendar data2 = Calendar.getInstance();
                data2.setTime(rs.getDate("dataRecebido"));
				nf.setDataRecebido(data2);

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
	public List<NotaFiscal> getNotasProtocolar() {
		try {
			List<NotaFiscal> nfs = new ArrayList<NotaFiscal>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select a.idnotaFiscal,b.dataEmpenho,a.dataRecebido,empr.nome,a.numNota,a.valorTotal,\r\n" + 
							"b.numeroEmpenho,b.destino,a.chaveAcesso,b.idempenho\r\n" + 
							"from notafiscal as a inner join empenho as b on a.idEmpenho = b.idempenho \r\n" + 
							"inner join  empresa as empr on empr.idempresa = b.idEmpresa where b.etapa = 4;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				NotaFiscal nf = new NotaFiscal();
				nf.setIdNotaFiscal(rs.getInt("idnotaFiscal"));
				nf.setNumNota(rs.getInt("numNota"));
				nf.setChaveAcesso(rs.getString("chaveAcesso"));
				nf.setValorTotal(rs.getDouble("valorTotal"));
				
				Empenho empenho = new Empenho();
				empenho.setIdEmpenho(rs.getInt("idempenho"));
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
	public List<NotaFiscal> getMinhasNotasProtocolar(int id) {
		try {
			List<NotaFiscal> nfs = new ArrayList<NotaFiscal>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select a.idnotaFiscal,b.dataEmpenho,a.dataRecebido,empr.nome,a.numNota,a.valorTotal,\r\n" + 
							"		b.numeroEmpenho,b.destino,a.chaveAcesso,b.idempenho,u.nome\r\n" + 
							"		from notafiscal as a \r\n" + 
							"		inner join empenho as b on a.idEmpenho = b.idempenho\r\n" + 
							"		inner join  empresa as empr on empr.idempresa = b.idEmpresa \r\n" + 
							"        inner join usuario as u on b.idusuario = u.idusuario\r\n" + 
							"		where b.etapa = 4 and u.idusuario = ?;");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				NotaFiscal nf = new NotaFiscal();
				nf.setIdNotaFiscal(rs.getInt("idnotaFiscal"));
				nf.setNumNota(rs.getInt("numNota"));
				nf.setChaveAcesso(rs.getString("chaveAcesso"));
				nf.setValorTotal(rs.getDouble("valorTotal"));
				
				Empenho empenho = new Empenho();
				empenho.setIdEmpenho(rs.getInt("idempenho"));
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
	public void alteraNosDetalhes(NotaFiscal nf) {
		String sql = "update notafiscal set numNota=?, chaveAcesso=?, valorTotal=?,"
				+ "dataEmissao=? where idnotaFiscal=?";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1,nf.getNumNota());
			stmt.setString(2,nf.getChaveAcesso());
			stmt.setDouble(3,nf.getValorTotal());
			stmt.setDate(4, new Date(nf.getDataEmissao().getTimeInMillis()));
			stmt.setInt(5,nf.getIdNotaFiscal());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void adicionaDataProtocolado(int id, Calendar data) {
		
		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement("update notafiscal set dataProtocolado=? where idnotaFiscal=?");

			// seta os valores
			stmt.setDate(1, new Date(data.getTimeInMillis()));
			stmt.setInt(2, id);
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void remove(NotaFiscal nf) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from notafiscal where idnotaFiscal=?");
			stmt.setLong(1, nf.getIdNotaFiscal());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
