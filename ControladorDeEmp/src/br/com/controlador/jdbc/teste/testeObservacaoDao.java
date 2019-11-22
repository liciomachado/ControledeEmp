package br.com.controlador.jdbc.teste;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.controlador.jdbc.dao.ObservacoesDao;
import br.com.controlador.jdbc.modelo.Observacoes;

public class testeObservacaoDao {

	public static void main(String[] args) throws ParseException {
		 
		Observacoes obs = new Observacoes();
		obs.setIdEmpenho(33);
		obs.setObservacao("encaminhado para pagamento");
		Calendar data = null;
		Date d = new Date();
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		Date date = new SimpleDateFormat("yyyy/MM/dd").parse(dStr);
        data = Calendar.getInstance();
		data.setTime(date);
		obs.setDataObs(data);

		ObservacoesDao dao = new ObservacoesDao();
		dao.adiciona(obs);
		dao.getLista();
	}

}
