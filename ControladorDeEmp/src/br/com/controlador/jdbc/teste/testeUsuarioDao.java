package br.com.controlador.jdbc.teste;

import org.apache.shiro.crypto.hash.SimpleHash;

import br.com.controlador.jdbc.dao.UsuarioDao;
import br.com.controlador.jdbc.modelo.Usuario;

public class testeUsuarioDao {
	public static void main(String[] args) {

		Usuario user = new Usuario();
		user.setIdUsuario(1);
		user.setNome("Castro");
		user.setSenha("123321");
		user.setTipoUser("tipo1");
		user.setEmail("licio.machado@hotmail.com");
		user.setSenhaGmail("19121998");
		user.setGraduacao("Cabo");

		SimpleHash hash = new SimpleHash("md5",user.getSenha());
		user.setSenha(hash.toHex());

		UsuarioDao dao = new UsuarioDao();
		dao.adiciona(user);
		dao.getLista();
		/*
		Usuario user2 = dao.autenticar(user);
		
		if (user2.getIdUsuario() != 0)  {
			System.out.println("Usuario Conectado");
		}else {
			System.out.println("Senha incorreta");
			
		}
		*/

	}
}
