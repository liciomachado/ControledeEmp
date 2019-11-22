package br.com.controlador.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;

import br.com.controlador.jdbc.ConnectionFactory;
import br.com.controlador.jdbc.modelo.Observacoes;

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
				"(idEmpenho,observacao,dataObs)" +
				" values (?,?,?)";

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
