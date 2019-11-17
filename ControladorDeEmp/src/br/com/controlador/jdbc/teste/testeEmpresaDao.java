package br.com.controlador.jdbc.teste;

import br.com.controlador.jdbc.dao.EmpresaDao;
import br.com.controlador.jdbc.modelo.Empresa;

public class testeEmpresaDao {

	public static void main(String[] args) {
		
		Empresa empresa = new Empresa();
		empresa.setContato("49 999195753");
		empresa.setEmail("x@empresa.com");
		empresa.setNome("DHZ comercio");
		empresa.setIdEmpresa(2);
		
		EmpresaDao dao = new EmpresaDao();
		
		dao.altera(empresa);
		dao.getLista();
	}
}
