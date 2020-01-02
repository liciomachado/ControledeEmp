package br.com.controlador.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;

import br.com.controlador.jdbc.ConnectionFactory;
import br.com.controlador.jdbc.modelo.Usuario;

public class UsuarioDao {
	private Connection connection;

	public UsuarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public UsuarioDao(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Usuario usuario) {
		String sql = "insert into usuario " +
				"(nome,senha,email,tipoUser)" +
				" values (?,?,?,?)";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1,usuario.getNome());
			stmt.setString(2,usuario.getSenha());
			stmt.setString(3,usuario.getEmail());
			stmt.setString(4,usuario.getTipoUser());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Usuario> getLista() {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from usuario");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				//usuario.setSenha(rs.getString("senha"));

				// adicionando o objeto � lista
				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
			return usuarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void altera(Usuario usuario) {
		String sql = "update usuario set nome=?, senha=?, email=?, graduacao=?,senhaGmail=?" +
				" where idusuario=?";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1,usuario.getNome());
			stmt.setString(2,usuario.getSenha());
			stmt.setString(3,usuario.getEmail());
			stmt.setString(4,usuario.getGraduacao());
			stmt.setString(5,usuario.getSenhaGmail());
			stmt.setInt(6,usuario.getIdUsuario());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void remove(Usuario usuario) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete " +
					"from usuario where idusuario=?");
			stmt.setLong(1, usuario.getIdUsuario());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Usuario autenticar(Usuario user) {
		
		SimpleHash hash = new SimpleHash("md5",user.getSenha());
		user.setSenha(hash.toHex());
		
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from usuario where nome=? and senha=? LIMIT 1");
			
			stmt.setString(1,user.getNome());
			stmt.setString(2,user.getSenha());
			
			ResultSet rs = stmt.executeQuery();
			
			Usuario usuario = new Usuario();
			while (rs.next()) {
				// criando o objeto Contato
				
				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setGraduacao(rs.getString("graduacao"));
				usuario.setSenhaGmail(rs.getString("senhaGmail"));
			}
			rs.close();
			stmt.close();
			return usuario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
