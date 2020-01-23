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
import br.com.controlador.jdbc.modelo.NotaFiscal;
import br.com.controlador.jdbc.modelo.Usuario;

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
				"(dataEmpenho,numeroEmpenho,idEmpresa,destino,valorTotal,empenhoDigitalizado,etapa,idusuario)" +
				" values (?,?,?,?,?,?,?,?)";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			// seta os valores
			stmt.setDate(1, new Date(empenho.getDataEmpenho().getTimeInMillis()));
			stmt.setString(2,empenho.getNumeroEmpenho().toUpperCase());
			stmt.setInt(3,empenho.getEmpresa().getIdEmpresa());
			stmt.setString(4, empenho.getDestino());
			stmt.setDouble(5, empenho.getValorTotal());
			stmt.setBytes(6, empenho.getEmpenhoDigitalizado());
			stmt.setInt(7, 3);
			stmt.setInt(8, empenho.getUsuario().getIdUsuario());


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
	public Empenho buscaEmpenhoCompleto(String numeroEmpenho) {
		Empenho empenho = new Empenho();
		try{
			PreparedStatement stmt = this.connection.prepareStatement("SELECT a.idempenho,a.numeroEmpenho,a.destino,"
					+ "a.valorTotal,a.empenhoDigitalizado,a.etapa,a.dataEmpenho,c.idempresa,c.nome,c.contato,c.email,b.nome as nomeUsuario FROM empenho as a "
						+ "inner join empresa as c on a.idEmpresa = c.idempresa inner join usuario as b on b.idusuario = a.idusuario "
						+ "where numeroEmpenho = ? order by a.idempenho desc ;");

			stmt.setString(1, numeroEmpenho);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				// criando o objeto Contato
				empenho.setIdEmpenho(rs.getInt("idempenho"));
				empenho.setNumeroEmpenho(rs.getString("numeroEmpenho"));
				empenho.setDestino(rs.getString("destino"));
				empenho.setValorTotal(rs.getDouble("valorTotal"));
				empenho.setEmpenhoDigitalizado(rs.getBytes("empenhoDigitalizado"));
				empenho.setEtapa(rs.getInt("etapa"));
				// montando a data atraves
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataEmpenho"));
				empenho.setDataEmpenho(data);
				
				Empresa empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("idempresa"));
				empresa.setNome(rs.getString("nome"));
				empresa.setContato(rs.getString("contato"));
				empresa.setEmail(rs.getString("email"));
				
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("nomeUsuario"));
				empenho.setUsuario(usuario);
				
				/*NotaFiscal nf = new NotaFiscal();
				if(rs.getInt("idnotaFiscal") != 0 || rs.getDate("dataEmissao") != null) {
					
					nf.setIdNotaFiscal(rs.getInt("idnotaFiscal"));
					nf.setChaveAcesso(rs.getString("chaveAcesso"));
					nf.setNumNota(rs.getInt("numNota"));
					nf.setValorTotal(rs.getDouble("valorTotal"));
					empenho.setNotaFiscal(nf);
					// montando a data atraves
					Calendar dataNF = Calendar.getInstance();
					data.setTime(rs.getDate("dataEmissao"));
					nf.setDataEmissao(dataNF);
					
					Calendar dataNfRecebida = Calendar.getInstance();
					data.setTime(rs.getDate("dataRecebido"));
					nf.setDataRecebido(dataNfRecebida);
				}*/
				
				empenho.setEmpresa(empresa);
				
				rs.close();
				stmt.close();
			}
			return empenho;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Empenho buscaPorID(int id) {
		Empenho empenho = new Empenho();
		try{
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM controledeempenhos.empenho as a "
					+ "inner join notafiscal as b on a.idempenho = b.idEmpenho inner join"
					+ " empresa as c on c.idempresa = a.idEmpresa where a.idempenho=?");
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
				
				NotaFiscal nf = new NotaFiscal();
				nf.setNumNota(rs.getInt("numNota"));
				empenho.setNotaFiscal(nf);
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				empenho.setEmpresa(empresa);
				
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
	public Empenho buscaPorIDSemNF(int id) {
		Empenho empenho = new Empenho();
		try{
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM empenho "
					+ "where idempenho=?");
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
	public List<Empenho> buscaPorIDSemNFparaEmpresa(int id) {
		try {
			List<Empenho> empenhos = new ArrayList<Empenho>();
			PreparedStatement stmt = this.connection.
					prepareStatement("SELECT * FROM empenho as a inner join empresa as b on a.idEmpresa = b.idempresa where b.idempresa = ?");
			stmt.setInt(1, id);
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
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				empenho.setEmpresa(empresa);

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
	public Empenho buscaParaNF(int id) {
		Empenho empenho = new Empenho();
		try{
			PreparedStatement stmt = this.connection.prepareStatement("SELECT a.idempenho,a.valorTotal,b.nome from empenho as a inner join empresa as b on a.idEmpresa = b.idempresa where idempenho = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				// criando o objeto Contato
				empenho.setIdEmpenho(rs.getInt("idempenho"));
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				empenho.setEmpresa(empresa);
				empenho.setValorTotal(rs.getDouble("valorTotal"));

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
					prepareStatement("select * from empenho order by idempenho desc");
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
					prepareStatement("select * from empenho as c inner join empresa as b on c.idempresa = b.idempresa " + 
							"where not exists(select a.idempenho,sum(a.valorTotal),c.valorTotal from notafiscal as a inner join empenho as b on " + 
							"a.idEmpenho = b.idempenho group by a.idEmpenho HAVING sum(a.valorTotal) = c.valorTotal and a.idEmpenho = c.idempenho);");
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
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				empenho.setEmpresa(empresa);

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
	public List<Empenho> getListaEmpenhosPendentesFiltroEmpresa(String filtro) {
		try {
			List<Empenho> empenhos = new ArrayList<Empenho>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from empenho as c inner join empresa as b on c.idempresa = b.idempresa  \r\n" + 
							"where not exists(select a.idempenho,sum(a.valorTotal),c.valorTotal from notafiscal as a inner join empenho as b on \r\n" + 
							"a.idEmpenho = b.idempenho group by a.idEmpenho HAVING sum(a.valorTotal) = c.valorTotal and a.idEmpenho = c.idempenho)\r\n" + 
							"and b.nome like ?");
			stmt.setString(1, '%' + filtro + '%');
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
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				empenho.setEmpresa(empresa);

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
	public List<Empenho> getListaEmpenhosPendentesFiltroValor(String filtro) {
		try {
			List<Empenho> empenhos = new ArrayList<Empenho>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from empenho as c inner join empresa as b on c.idempresa = b.idempresa  \r\n" + 
							"where not exists(select a.idempenho,sum(a.valorTotal),c.valorTotal from notafiscal as a inner join empenho as b on \r\n" + 
							"a.idEmpenho = b.idempenho group by a.idEmpenho HAVING sum(a.valorTotal) = c.valorTotal and a.idEmpenho = c.idempenho)\r\n" + 
							"and c.valorTotal like ?");
			stmt.setString(1, filtro + '%');
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
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				empenho.setEmpresa(empresa);

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
	public List<Empenho> getListaEmpenhosPendentesFiltroDestino(String filtro) {
		try {
			List<Empenho> empenhos = new ArrayList<Empenho>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from empenho as c inner join empresa as b on c.idempresa = b.idempresa  \r\n" + 
							"where not exists(select a.idempenho,sum(a.valorTotal),c.valorTotal from notafiscal as a inner join empenho as b on \r\n" + 
							"a.idEmpenho = b.idempenho group by a.idEmpenho HAVING sum(a.valorTotal) = c.valorTotal and a.idEmpenho = c.idempenho)\r\n" + 
							"and c.destino like ?");
			stmt.setString(1, '%'+filtro+'%');
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
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				empenho.setEmpresa(empresa);

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
	public List<Empenho> getListaMeusEmpenhosPendentes(int id) {
		try {
			List<Empenho> empenhos = new ArrayList<Empenho>();
			PreparedStatement stmt = this.connection.
					prepareStatement("SELECT * FROM empenho as c inner join empresa as b on c.idEmpresa = b.idempresa inner join usuario as u on u.idusuario = c.idusuario\r\n" + 
							"where not exists (select a.idEmpenho,sum(a.valorTotal),c.valorTotal from notafiscal as A \r\n" + 
							"inner join empenho as b on a.idempenho = b.idempenho GROUP BY a.idEmpenho \r\n" + 
							"HAVING sum(a.valorTotal) = c.valorTotal and a.idEmpenho = c.idempenho)\r\n" + 
							"and c.idusuario = ?");
			stmt.setInt(1, id);
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
				
				Empresa empresa = new Empresa();
				empresa.setNome(rs.getString("nome"));
				empenho.setEmpresa(empresa);

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
		String sql = "update empenho set numeroEmpenho=?," +
				"destino=?, valorTotal=? where idempenho=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,empenho.getNumeroEmpenho());
			stmt.setString(2, empenho.getDestino());
			stmt.setDouble(3, empenho.getValorTotal());
			stmt.setLong(4, empenho.getIdEmpenho());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void alteraStatus(int id) {
		String sql = "update empenho as a inner join notafiscal as b on a.idempenho = b.idempenho set a.etapa=5 where b.idnotafiscal = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);

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
					"(dataEmpenho,numeroEmpenho,idEmpresa,destino,valorTotal,empenhoDigitalizado,etapa,idusuario)" +
					" values (?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

			stmt.setDate(1, new Date(empenho.getDataEmpenho().getTimeInMillis()));
			stmt.setString(2,empenho.getNumeroEmpenho());
			stmt.setInt(3,empenho.getEmpresa().getIdEmpresa());
			stmt.setString(4, empenho.getDestino());
			stmt.setDouble(5, empenho.getValorTotal());
			stmt.setBlob(6, f);
			stmt.setInt(7, 3);
			stmt.setInt(8, empenho.getUsuario().getIdUsuario());
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
