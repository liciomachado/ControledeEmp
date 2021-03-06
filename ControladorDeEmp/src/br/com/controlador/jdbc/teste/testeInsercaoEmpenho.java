package br.com.controlador.jdbc.teste;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.Empresa;

public class testeInsercaoEmpenho {
	
	public static void main(String[] args) throws ParseException {
		
		Empresa empresa = new Empresa();
		empresa.setIdEmpresa(1);
		
		Empenho empenho = new Empenho();
		Calendar data = null;
		Date d = new Date();
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dStr);
        data = Calendar.getInstance();
		data.setTime(date);
		
		empenho.setDataEmpenho(data);
		empenho.setNumeroEmpenho("2019NE800501");
		empenho.setDestino("Almox");
		empenho.setValorTotal(800);
		empenho.setEmpresa(empresa);
		
		EmpenhoDao dao = new EmpenhoDao();
		
		File f = new File("C:/Users/Mauricio/Desktop/800501.pdf");
		
		//dao.empenhoComFile(f, empenho);
		int rs = dao.adiciona(empenho);
		System.out.println(rs);
		//dao.getLista();
		
		Empenho emp = dao.buscaPorID(11);
		System.out.println(emp.getDestino());
	}
	
	
}
