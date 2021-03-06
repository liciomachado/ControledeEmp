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
import br.com.controlador.jdbc.modelo.Observacoes;
import br.com.controlador.jdbc.modelo.Usuario;

public class ObservacoesDao {
	private Connection connection;

	public ObservacoesDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public ObservacoesDao(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Observacoes obs) {
		String sql = "insert into observacoes " +
				"(idEmpenho,observacao,dataObs,idusuario)" +
				" values (?,?,?,?)";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1,obs.getIdEmpenho());
			stmt.setString(2,obs.getObservacao());
			stmt.setDate(3, new Date(obs.getDataObs().getTimeInMillis()));
			stmt.setInt(4, obs.getIdUsuario());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Observacoes> getLista() {
		try {
			List<Observacoes> obs = new ArrayList<Observacoes>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from observacoes");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Observacoes observacao = new Observacoes();
				observacao.setIdObs(rs.getInt("idobservacoes"));
				observacao.setIdEmpenho(rs.getInt("idempenho"));
				observacao.setObservacao(rs.getString("observacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataObs"));
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
	public List<Observacoes> getListaPeloId(int id) {
		try {
			List<Observacoes> obs = new ArrayList<Observacoes>();
			PreparedStatement stmt = this.connection.
					prepareStatement("SELECT a.idobservacoes,a.idempenho,a.dataObs,a.observacao,b.idusuario,"
							+ "b.nome FROM controledeempenhos.observacoes as a inner join usuario as b on"
							+ " a.idusuario = b.idusuario where idempenho=?;");
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Observacoes observacao = new Observacoes();
				observacao.setIdObs(rs.getInt("idobservacoes"));
				observacao.setIdEmpenho(rs.getInt("idempenho"));
				observacao.setObservacao(rs.getString("observacao"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataObs"));
				observacao.setDataObs(data);
				
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				observacao.setUsuario(usuario);

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
	public void altera(Observacoes obs){
		String sql = "update empenho set idempenho=?, observacao=?, dataObs=? where idobservacoes = ?";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1,obs.getIdEmpenho());
			stmt.setString(2,obs.getObservacao());
			stmt.setDate(3, new Date(obs.getDataObs().getTimeInMillis()));

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void remove(Observacoes obs) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete " +
					"from observacoes where idobservacoes=?");
			stmt.setLong(1, obs.getIdObs());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

}
