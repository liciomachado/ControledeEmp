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
import br.com.controlador.jdbc.modelo.ObservacoesEmpresa;
import br.com.controlador.jdbc.modelo.Usuario;

public class ObservacoesEmpresaDao {
	private Connection connection;

	public ObservacoesEmpresaDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public ObservacoesEmpresaDao(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(ObservacoesEmpresa obs) {
		String sql = "insert into observacoesempresa " +
				"(idEmpresa,observacao,dataObservacao,idusuario)" +
				" values (?,?,?,?)";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1,obs.getIdEmpresa());
			stmt.setString(2,obs.getObservacao());
			stmt.setDate(3, new Date(obs.getDataObs().getTimeInMillis()));
			stmt.setInt(4, obs.getUsuario().getIdUsuario());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<ObservacoesEmpresa> getLista() {
		try {
			List<ObservacoesEmpresa> obs = new ArrayList<ObservacoesEmpresa>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from observacoesempresa");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				ObservacoesEmpresa observacao = new ObservacoesEmpresa();
				observacao.setIdObs(rs.getInt("idobservacoesEmpresa"));
				observacao.setIdEmpresa(rs.getInt("idEmpresa"));
				observacao.setObservacao(rs.getString("observacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataObservacao"));
				observacao.setDataObs(data);
				
				
				// adicionando o objeto � lista
				obs.add(observacao);
				
			}
			rs.close();
			stmt.close();
			return obs;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<ObservacoesEmpresa> getListaPeloId(int id) {
		try {
			List<ObservacoesEmpresa> obs = new ArrayList<ObservacoesEmpresa>();
			PreparedStatement stmt = this.connection.
					prepareStatement("SELECT a.idobservacoesEmpresa,a.idEmpresa,a.dataObservacao,a.observacao,b.idusuario,\r\n" + 
							"							b.nome FROM controledeempenhos.observacoesempresa as a inner join usuario as b on\r\n" + 
							"							a.idusuario = b.idusuario where idEmpresa=?");
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ObservacoesEmpresa observacao = new ObservacoesEmpresa();
				observacao.setIdObs(rs.getInt("idobservacoesEmpresa"));
				observacao.setIdEmpresa(rs.getInt("idEmpresa"));
				observacao.setObservacao(rs.getString("observacao"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataObservacao"));
				observacao.setDataObs(data);
				
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				observacao.setUsuario(usuario);

				obs.add(observacao);
			}
			rs.close();
			stmt.close();
			return obs;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(ObservacoesEmpresa obs) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete " +
					"from observacoesempresa where idobservacoesEmpresa=?");
			stmt.setLong(1, obs.getIdObs());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

}
