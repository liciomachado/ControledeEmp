package br.com.controlador.jdbc.teste;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.modelo.Empenho;

public class testEmpenhoTodos {
	public static void main(String[] args) {

		Empenho emp = new Empenho();
		EmpenhoDao dao = new EmpenhoDao();
		
		emp = dao.buscaEmpenhoCompleto("2019NE800534");
		System.out.println(emp.toString());
	}
}
