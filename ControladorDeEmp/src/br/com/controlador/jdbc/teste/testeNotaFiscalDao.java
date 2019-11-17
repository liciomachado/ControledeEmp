package br.com.controlador.jdbc.teste;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.controlador.jdbc.dao.NotaFiscalDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.Empresa;
import br.com.controlador.jdbc.modelo.NotaFiscal;

public class testeNotaFiscalDao {
	public static void main(String[] args) throws ParseException {
		
		Empenho emp = new Empenho();
		emp.setIdEmpenho(1);
		
		Empresa empresa = new Empresa();
		empresa.setIdEmpresa(2);
		
		NotaFiscal nota = new NotaFiscal();
		nota.setIdNotaFiscal(1);
		nota.setNumNota(201);
		nota.setChaveAcesso("12345678901234567890123456789012345678901234");
		nota.setValorTotal(300.5);
		nota.setEmpenho(emp);
		nota.setEmpresa(empresa);

		Calendar data = null;
		Date d = new Date();
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		Date date = new SimpleDateFormat("yyyy/MM/dd").parse(dStr);
        data = Calendar.getInstance();
		data.setTime(date);
		nota.setDataEmissao(data);
		
		nota.setDataRecebido(data);
		
		NotaFiscalDao dao = new NotaFiscalDao();
		
		//dao.adiciona(nota);
		dao.getLista();
	}
}
